package es.unex.gc04.erasmusnest.verificacion;

import static org.assertj.core.api.Assertions.assertThat;

import es.unex.gc04.erasmusnest.usuario.EstadoCuenta;
import es.unex.gc04.erasmusnest.usuario.Usuario;
import es.unex.gc04.erasmusnest.usuario.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ExtendedModelMap;

/** Pantallas de verificación y reenvío (TK-009). Cuentas sintéticas (NFR-009). */
@SpringBootTest
class VerificacionControllerTest {

    @Autowired
    private VerificacionController controller;

    @Autowired
    private VerificacionService servicio;

    @Autowired
    private TokenVerificacionRepository tokens;

    @Autowired
    private UsuarioRepository usuarios;

    private Usuario ana;

    @BeforeEach
    void preparar() {
        tokens.deleteAll();
        usuarios.deleteAll();
        ana = usuarios.save(new Usuario("ana.garcia@alumnos.unex.es", "hash-sintetico"));
    }

    @Test
    void unEnlaceValidoMuestraLaConfirmacion() {
        String token = servicio.emitirEnlace(ana);

        assertThat(controller.abrirEnlace(token, new ExtendedModelMap())).isEqualTo("verificacion-correcta");
        assertThat(usuarios.findById(ana.getId()).orElseThrow().getEstado()).isEqualTo(EstadoCuenta.VERIFICADA);
    }

    @Test
    void unEnlaceUsadoMuestraElAvisoYElBotonDeReenvio() {
        String token = servicio.emitirEnlace(ana);
        controller.abrirEnlace(token, new ExtendedModelMap());
        ExtendedModelMap model = new ExtendedModelMap();

        assertThat(controller.abrirEnlace(token, model)).isEqualTo("verificacion-caducada");
        assertThat(model).containsEntry("aviso", "Este enlace ya se ha usado");
        assertThat(model.get("token")).isEqualTo(token);
    }

    @Test
    void unEnlaceSustituidoSeTrataComoCaducado() {
        String antiguo = servicio.emitirEnlace(ana);
        servicio.emitirEnlace(ana);
        ExtendedModelMap model = new ExtendedModelMap();

        assertThat(controller.abrirEnlace(antiguo, model)).isEqualTo("verificacion-caducada");
        assertThat(model.get("aviso")).isEqualTo("El enlace ha caducado");
    }

    @Test
    void unTokenManipuladoMuestraEnlaceNoValido() {
        assertThat(controller.abrirEnlace("manipulado", new ExtendedModelMap())).isEqualTo("verificacion-no-valida");
        assertThat(controller.abrirEnlace(null, new ExtendedModelMap())).isEqualTo("verificacion-no-valida");
    }

    @Test
    void reenviarDesdeUnEnlaceEmiteUnoNuevo() {
        String antiguo = servicio.emitirEnlace(ana);

        assertThat(controller.reenviar(antiguo, null)).isEqualTo("redirect:/verificacion/reenviada");
        assertThat(tokens.findByUsuarioId(ana.getId())).hasSize(2);
        assertThat(tokens.findByUsuarioIdAndUsadoFalseAndInvalidadoFalse(ana.getId())).hasSize(1);
    }

    @Test
    void reenviarPorCorreoNoRevelaSiLaCuentaExiste() {
        assertThat(controller.reenviar(null, "nadie@alumnos.unex.es")).isEqualTo("redirect:/verificacion/reenviada");
        assertThat(controller.reenviar(null, "ana.garcia@alumnos.unex.es")).isEqualTo("redirect:/verificacion/reenviada");
        assertThat(tokens.findByUsuarioId(ana.getId())).hasSize(1);
    }
}
