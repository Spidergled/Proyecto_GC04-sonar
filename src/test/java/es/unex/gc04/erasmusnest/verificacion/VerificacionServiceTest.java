package es.unex.gc04.erasmusnest.verificacion;

import static org.assertj.core.api.Assertions.assertThat;

import es.unex.gc04.erasmusnest.registro.RegistroForm;
import es.unex.gc04.erasmusnest.registro.RegistroService;
import es.unex.gc04.erasmusnest.usuario.EstadoCuenta;
import es.unex.gc04.erasmusnest.usuario.Usuario;
import es.unex.gc04.erasmusnest.usuario.UsuarioRepository;
import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/** Enlace único y caducidad de 24 h (TK-006, RN-03). Solo cuentas sintéticas (NFR-009). */
@SpringBootTest
class VerificacionServiceTest {

    private static final Instant EMISION = Instant.parse("2026-11-12T10:00:00Z");

    @Autowired
    private VerificacionService servicio;

    @Autowired
    private TokenVerificacionRepository tokens;

    @Autowired
    private UsuarioRepository usuarios;

    @Autowired
    private RegistroService registro;

    private Usuario ana;

    @BeforeEach
    void preparar() {
        tokens.deleteAll();
        usuarios.deleteAll();
        ana = usuarios.save(new Usuario("ana.garcia@alumnos.unex.es", "hash-sintetico"));
    }

    private EstadoCuenta estadoDeAna() {
        return usuarios.findById(ana.getId()).orElseThrow().getEstado();
    }

    @Test
    void unRegistroValidoEmiteUnEnlace() {
        registro.registrar(new RegistroForm("luis.perez@alumnos.unex.es", "Erasmus2026", "Erasmus2026"));

        Usuario luis = usuarios.findByEmail("luis.perez@alumnos.unex.es").orElseThrow();
        assertThat(tokens.findByUsuarioId(luis.getId())).hasSize(1);
    }

    @Test
    void cadaEnlaceEsUnico() {
        String primero = servicio.emitirEnlace(ana, EMISION);
        String segundo = servicio.emitirEnlace(ana, EMISION);

        assertThat(primero).isNotEqualTo(segundo).hasSizeGreaterThanOrEqualTo(40);
    }

    @Test
    void enlaceValidoVerificaLaCuenta() {
        String token = servicio.emitirEnlace(ana, EMISION);

        assertThat(servicio.verificar(token, EMISION.plusSeconds(300))).isEqualTo(ResultadoVerificacion.VERIFICADA);
        assertThat(estadoDeAna()).isEqualTo(EstadoCuenta.VERIFICADA);
    }

    @Test
    void unSegundoAntesDeLas24hSigueValiendo() {
        String token = servicio.emitirEnlace(ana, EMISION);

        assertThat(servicio.verificar(token, EMISION.plus(Duration.ofHours(24)).minusSeconds(1)))
                .isEqualTo(ResultadoVerificacion.VERIFICADA);
    }

    @Test
    void justoALas24hEstaCaducado() {
        String token = servicio.emitirEnlace(ana, EMISION);

        assertThat(servicio.verificar(token, EMISION.plus(Duration.ofHours(24))))
                .isEqualTo(ResultadoVerificacion.CADUCADO);
        assertThat(estadoDeAna()).isEqualTo(EstadoCuenta.NO_VERIFICADA);
    }

    @Test
    void unEnlaceSoloSePuedeUsarUnaVez() {
        String token = servicio.emitirEnlace(ana, EMISION);
        servicio.verificar(token, EMISION.plusSeconds(60));

        assertThat(servicio.verificar(token, EMISION.plusSeconds(120))).isEqualTo(ResultadoVerificacion.YA_USADO);
    }

    @Test
    void siSePidenVariosSoloValeElUltimo() {
        String antiguo = servicio.emitirEnlace(ana, EMISION);
        String ultimo = servicio.emitirEnlace(ana, EMISION.plusSeconds(60));

        assertThat(servicio.verificar(antiguo, EMISION.plusSeconds(120))).isEqualTo(ResultadoVerificacion.CADUCADO);
        assertThat(servicio.verificar(ultimo, EMISION.plusSeconds(120))).isEqualTo(ResultadoVerificacion.VERIFICADA);
    }

    @Test
    void unTokenManipuladoNoEsValido() {
        String token = servicio.emitirEnlace(ana, EMISION);

        assertThat(servicio.verificar(token + "x", EMISION)).isEqualTo(ResultadoVerificacion.NO_VALIDO);
        assertThat(servicio.verificar("", EMISION)).isEqualTo(ResultadoVerificacion.NO_VALIDO);
        assertThat(servicio.verificar(null, EMISION)).isEqualTo(ResultadoVerificacion.NO_VALIDO);
    }

    @Test
    void desdeUnEnlaceCaducadoSePuedePedirOtro() {
        String caducado = servicio.emitirEnlace(ana, EMISION.minus(Duration.ofHours(30)));

        assertThat(servicio.reenviarDesdeToken(caducado)).isTrue();
        assertThat(tokens.findByUsuarioIdAndUsadoFalseAndInvalidadoFalse(ana.getId())).hasSize(1);
    }

    @Test
    void unaCuentaYaVerificadaNoRecibeMasEnlaces() {
        String token = servicio.emitirEnlace(ana, EMISION);
        servicio.verificar(token, EMISION.plusSeconds(60));

        assertThat(servicio.reenviarDesdeToken(token)).isFalse();
    }
}
