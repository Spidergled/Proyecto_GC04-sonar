package es.unex.gc04.erasmusnest.sesion;

import static org.assertj.core.api.Assertions.assertThat;

import es.unex.gc04.erasmusnest.usuario.Usuario;
import es.unex.gc04.erasmusnest.usuario.UsuarioRepository;
import java.time.Duration;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Inicio de sesión en servidor, bloqueo y caducidad (TK-011). Cuentas sintéticas (NFR-009). */
@SpringBootTest
class AccesoServiceTest {

    private static final String CORREO = "ana.garcia@alumnos.unex.es";
    private static final Instant AHORA = Instant.parse("2026-11-12T10:00:00Z");

    @Autowired private AccesoService acceso;
    @Autowired private UsuarioRepository usuarios;
    @Autowired private IntentoAccesoRepository intentos;
    @Autowired private PasswordEncoder encoder;

    private Usuario ana;

    @BeforeEach
    void preparar() {
        intentos.deleteAll();
        usuarios.deleteAll();
        ana = usuarios.save(new Usuario(CORREO, encoder.encode("Erasmus2026")));
    }

    private ResultadoAcceso.Tipo fallar(Instant cuando) {
        return acceso.autenticar(CORREO, "ClaveMala1", cuando).tipo();
    }

    @Test
    void credencialesCorrectasDanAcceso() {
        ResultadoAcceso r = acceso.autenticar("  Ana.Garcia@Alumnos.UNEX.es ", "Erasmus2026", AHORA);

        assertThat(r.esCorrecto()).isTrue();
        assertThat(r.usuario().getId()).isEqualTo(ana.getId());
    }

    @Test
    void contrasenaMalaYCorreoInexistenteDanElMismoResultado() {
        assertThat(acceso.autenticar(CORREO, "ClaveMala1", AHORA).tipo())
                .isEqualTo(ResultadoAcceso.Tipo.CREDENCIALES_INCORRECTAS);
        assertThat(acceso.autenticar("nadie@alumnos.unex.es", "ClaveMala1", AHORA).tipo())
                .isEqualTo(ResultadoAcceso.Tipo.CREDENCIALES_INCORRECTAS);
    }

    @Test
    void unaCuentaNoVerificadaPuedeEntrar() {
        assertThat(acceso.autenticar(CORREO, "Erasmus2026", AHORA).esCorrecto()).isTrue();
    }

    @Test
    void trasCincoFallosSeguidosSeBloqueaQuinceMinutos() {
        for (int i = 1; i <= 4; i++) {
            assertThat(fallar(AHORA)).isEqualTo(ResultadoAcceso.Tipo.CREDENCIALES_INCORRECTAS);
        }
        assertThat(fallar(AHORA)).isEqualTo(ResultadoAcceso.Tipo.BLOQUEADA);

        // Bloqueada aunque la contraseña sea correcta
        assertThat(acceso.autenticar(CORREO, "Erasmus2026", AHORA.plus(Duration.ofMinutes(14))).tipo())
                .isEqualTo(ResultadoAcceso.Tipo.BLOQUEADA);
        // A los 15 minutos se puede volver a entrar
        assertThat(acceso.autenticar(CORREO, "Erasmus2026", AHORA.plus(Duration.ofMinutes(15))).esCorrecto())
                .isTrue();
    }

    @Test
    void unAccesoCorrectoReiniciaLaCuentaDeFallos() {
        for (int i = 0; i < 4; i++) {
            fallar(AHORA);
        }
        acceso.autenticar(CORREO, "Erasmus2026", AHORA);

        assertThat(fallar(AHORA)).isEqualTo(ResultadoAcceso.Tipo.CREDENCIALES_INCORRECTAS);
    }

    @Test
    void laSesionCaducaTrasDosHorasDeInactividadYSeCierra() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpSession anterior = new MockHttpSession();
        request.setSession(anterior);

        SesionUsuario.iniciar(request, ana);

        assertThat(anterior.isInvalid()).isTrue(); // identificador de sesión nuevo
        assertThat(request.getSession(false).getMaxInactiveInterval()).isEqualTo(2 * 60 * 60);
        assertThat(SesionUsuario.usuarioActual(request)).contains(ana.getId());

        SesionUsuario.cerrar(request);
        assertThat(SesionUsuario.usuarioActual(request)).isEmpty();
    }
}
