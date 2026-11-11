package es.unex.gc04.erasmusnest.registro;

import static org.assertj.core.api.Assertions.assertThat;

import es.unex.gc04.erasmusnest.usuario.EstadoCuenta;
import es.unex.gc04.erasmusnest.usuario.UsuarioRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Validación en servidor de US-001 (TK-003): ejemplos y casos límite del spec.
 * Las pruebas de aceptación completas son TK-005 (José). Solo cuentas sintéticas (NFR-009).
 */
@SpringBootTest
@ActiveProfiles("test")
class RegistroServiceTest {

    @Autowired
    private RegistroService servicio;

    @Autowired
    private UsuarioRepository usuarios;

    // Estas pruebas confirman en la base de datos: se limpia antes y después
    // para no dejar cuentas que afecten a otras clases de prueba
    @BeforeEach
    @AfterEach
    void limpiar() {
        usuarios.deleteAll();
    }

    private ResultadoRegistro registrar(String email, String password, String confirmacion) {
        return servicio.registrar(new RegistroForm(email, password, confirmacion));
    }

    @Test
    void registroValidoCreaCuentaNoVerificada() {
        ResultadoRegistro r = registrar("ana.garcia@alumnos.unex.es", "Erasmus2026", "Erasmus2026");

        assertThat(r.esAceptado()).isTrue();
        assertThat(r.getMensaje()).isEqualTo(RegistroService.MENSAJE_GENERICO);
        assertThat(usuarios.findByEmail("ana.garcia@alumnos.unex.es"))
                .hasValueSatisfying(u -> assertThat(u.getEstado()).isEqualTo(EstadoCuenta.NO_VERIFICADA));
    }

    @Test
    void correoNoInstitucionalSeRechaza() {
        ResultadoRegistro r = registrar("ana.garcia@gmail.com", "Erasmus2026", "Erasmus2026");

        assertThat(r.getErrores()).containsEntry("email", RegistroService.ERROR_DOMINIO);
        assertThat(usuarios.count()).isZero();
    }

    @Test
    void subdominioParecidoSeRechaza() {
        ResultadoRegistro r = registrar("ana@alumnos.unex.es.fake.com", "Erasmus2026", "Erasmus2026");

        assertThat(r.getErrores()).containsEntry("email", RegistroService.ERROR_DOMINIO);
    }

    @Test
    void correoMalFormadoSeRechazaAntesDelDominio() {
        assertThat(registrar("ana.garciaalumnos.unex.es", "Erasmus2026", "Erasmus2026").getErrores())
                .containsEntry("email", RegistroService.ERROR_CORREO_MAL_FORMADO);
        assertThat(registrar("ana garcia@alumnos.unex.es", "Erasmus2026", "Erasmus2026").getErrores())
                .containsEntry("email", RegistroService.ERROR_CORREO_MAL_FORMADO);
        assertThat(registrar("ana@@alumnos.unex.es", "Erasmus2026", "Erasmus2026").getErrores())
                .containsEntry("email", RegistroService.ERROR_CORREO_MAL_FORMADO);
    }

    @Test
    void mayusculasYEspaciosSeNormalizan() {
        registrar("  Ana.Garcia@Alumnos.UNEX.es ", "Erasmus2026", "Erasmus2026");

        assertThat(usuarios.existsByEmail("ana.garcia@alumnos.unex.es")).isTrue();
    }

    @Test
    void contrasenaCortaYSinNumeroIndicaQueFalta() {
        ResultadoRegistro r = registrar("ana.garcia@alumnos.unex.es", "erasmus", "erasmus");

        assertThat(r.getErrores())
                .containsEntry("password", "La contraseña necesita al menos 8 caracteres y un número");
        assertThat(usuarios.count()).isZero();
    }

    @Test
    void confirmacionDistintaSeRechazaEnSuCampo() {
        ResultadoRegistro r = registrar("ana.garcia@alumnos.unex.es", "Erasmus2026", "Erasmus2025");

        assertThat(r.getErrores()).containsEntry("confirmacion", RegistroService.ERROR_NO_COINCIDEN);
        assertThat(usuarios.count()).isZero();
    }

    @Test
    void correoYaRegistradoNoRevelaQueExiste() {
        registrar("ana.garcia@alumnos.unex.es", "Erasmus2026", "Erasmus2026");

        ResultadoRegistro segundo = registrar("ana.garcia@alumnos.unex.es", "OtraClave99", "OtraClave99");

        assertThat(segundo.esAceptado()).isTrue();
        assertThat(segundo.getMensaje()).isEqualTo(RegistroService.MENSAJE_GENERICO);
        assertThat(usuarios.count()).isEqualTo(1);
    }

    @Test
    void dobleEnvioSoloCreaUnaCuenta() {
        registrar("ana.garcia@alumnos.unex.es", "Erasmus2026", "Erasmus2026");
        registrar("ana.garcia@alumnos.unex.es", "Erasmus2026", "Erasmus2026");

        assertThat(usuarios.count()).isEqualTo(1);
    }
}
