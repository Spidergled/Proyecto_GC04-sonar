package es.unex.gc04.erasmusnest.aceptacion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import es.unex.gc04.erasmusnest.sesion.AccesoService;
import es.unex.gc04.erasmusnest.sesion.IntentoAccesoRepository;
import es.unex.gc04.erasmusnest.usuario.Usuario;
import es.unex.gc04.erasmusnest.usuario.UsuarioRepository;
import java.sql.Timestamp;
import java.time.Instant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Pruebas de aceptación de US-003 (TK-013): inicio y cierre de sesión por HTTP.
 * Cubre AC-01…AC-04 y RN-02. Solo cuentas sintéticas (NFR-009).
 */
@SpringBootTest
class AceptacionUS003Test {

    private static final String VERIFICADA = "ana.garcia@alumnos.unex.es";
    private static final String PENDIENTE = "luis.perez@alumnos.unex.es";
    private static final String CLAVE = "Erasmus2026";

    @Autowired private WebApplicationContext contexto;
    @Autowired private UsuarioRepository usuarios;
    @Autowired private IntentoAccesoRepository intentos;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JdbcTemplate jdbc;

    private MockMvc mvc;

    @BeforeEach
    void preparar() {
        mvc = MockMvcBuilders.webAppContextSetup(contexto).build();
        intentos.deleteAll();
        usuarios.deleteAll();
        Usuario ana = new Usuario(VERIFICADA, encoder.encode(CLAVE));
        ana.verificar();
        usuarios.save(ana);
        usuarios.save(new Usuario(PENDIENTE, encoder.encode(CLAVE)));
    }

    private MvcResult entrar(String email, String password) throws Exception {
        return mvc.perform(post("/login").param("email", email).param("password", password)).andReturn();
    }

    private MockHttpSession sesionDe(MvcResult resultado) {
        return (MockHttpSession) resultado.getRequest().getSession(false);
    }

    @Test
    @DisplayName("AC-01: con credenciales correctas se accede al panel")
    void ac01CredencialesCorrectas() throws Exception {
        MvcResult r = entrar(VERIFICADA, CLAVE);
        assertThat(r.getResponse().getRedirectedUrl()).isEqualTo("/panel");

        mvc.perform(get("/panel").session(sesionDe(r)))
                .andExpect(status().isOk())
                .andExpect(view().name("panel"))
                .andExpect(content().string(containsString(VERIFICADA)));
    }

    @Test
    @DisplayName("AC-01: el mensaje de error es el mismo si falla la contraseña o si el correo no existe")
    void ac01MensajeGenerico() throws Exception {
        mvc.perform(post("/login").param("email", VERIFICADA).param("password", "ClaveMala1"))
                .andExpect(view().name("login"))
                .andExpect(model().attribute("error", AccesoService.ERROR_GENERICO));
        mvc.perform(post("/login").param("email", "nadie@alumnos.unex.es").param("password", CLAVE))
                .andExpect(view().name("login"))
                .andExpect(model().attribute("error", AccesoService.ERROR_GENERICO));
    }

    @Test
    @DisplayName("AC-02: tras 5 fallos seguidos la cuenta se bloquea 15 minutos, incluso con la contraseña buena")
    void ac02BloqueoTrasCincoFallos() throws Exception {
        for (int i = 0; i < 5; i++) {
            entrar(VERIFICADA, "ClaveMala1");
        }
        mvc.perform(post("/login").param("email", VERIFICADA).param("password", CLAVE))
                .andExpect(view().name("login"))
                .andExpect(model().attribute("error", AccesoService.ERROR_BLOQUEADA));

        // Pasados los 15 minutos se puede entrar
        jdbc.update("update intento_acceso set bloqueado_hasta = ? where email = ?",
                Timestamp.from(Instant.now().minusSeconds(1)), VERIFICADA);
        assertThat(entrar(VERIFICADA, CLAVE).getResponse().getRedirectedUrl()).isEqualTo("/panel");
    }

    @Test
    @DisplayName("AC-03: cerrar sesión la invalida y volver al panel no muestra datos privados")
    void ac03CerrarSesion() throws Exception {
        MockHttpSession sesion = sesionDe(entrar(VERIFICADA, CLAVE));

        mvc.perform(post("/logout").session(sesion)).andExpect(redirectedUrl("/login?salida"));
        assertThat(sesion.isInvalid()).isTrue();

        mvc.perform(get("/panel"))
                .andExpect(redirectedUrl("/login"))
                .andExpect(header().string("Cache-Control", containsString("no-store")));
    }

    @Test
    @DisplayName("AC-04: la sesión caduca tras 2 horas de inactividad")
    void ac04CaducidadPorInactividad() throws Exception {
        MockHttpSession sesion = sesionDe(entrar(VERIFICADA, CLAVE));

        assertThat(sesion.getMaxInactiveInterval()).isEqualTo(2 * 60 * 60);
    }

    @Test
    @DisplayName("RN-02: una cuenta no verificada puede entrar, pero se le avisa de que tiene acciones limitadas")
    void rn02CuentaNoVerificadaEntraLimitada() throws Exception {
        MockHttpSession sesion = sesionDe(entrar(PENDIENTE, CLAVE));

        mvc.perform(get("/panel").session(sesion))
                .andExpect(view().name("panel"))
                .andExpect(content().string(containsString("pendiente de verificar")));
    }
}
