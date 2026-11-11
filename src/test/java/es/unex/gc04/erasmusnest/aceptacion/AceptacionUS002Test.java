package es.unex.gc04.erasmusnest.aceptacion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import es.unex.gc04.erasmusnest.correo.BuzonPruebas;
import es.unex.gc04.erasmusnest.usuario.EstadoCuenta;
import es.unex.gc04.erasmusnest.usuario.Usuario;
import es.unex.gc04.erasmusnest.usuario.UsuarioRepository;
import es.unex.gc04.erasmusnest.verificacion.ControlVerificacion;
import es.unex.gc04.erasmusnest.verificacion.CuentaNoVerificadaException;
import es.unex.gc04.erasmusnest.verificacion.TokenVerificacionRepository;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Pruebas de aceptación de US-002 (TK-010): el usuario se registra, recibe el enlace en el
 * buzón de pruebas y lo abre por HTTP. Cubre AC-01…AC-04 y los casos límite del spec.md
 * (enlace válido, caducado, reutilizado y manipulado). Solo cuentas sintéticas (NFR-009).
 */
@SpringBootTest
class AceptacionUS002Test {

    private static final String CORREO = "ana.garcia@alumnos.unex.es";
    private static final Pattern TOKEN = Pattern.compile("token=([A-Za-z0-9_-]+)");

    @Autowired private WebApplicationContext contexto;
    @Autowired private UsuarioRepository usuarios;
    @Autowired private TokenVerificacionRepository tokens;
    @Autowired private BuzonPruebas buzon;
    @Autowired private ControlVerificacion control;
    @Autowired private JdbcTemplate jdbc;

    private MockMvc mvc;

    @BeforeEach
    void preparar() {
        mvc = MockMvcBuilders.webAppContextSetup(contexto).build();
        buzon.vaciar();
        tokens.deleteAll();
        usuarios.deleteAll();
    }

    private void registrarse(String email) throws Exception {
        mvc.perform(post("/registro").param("email", email)
                        .param("password", "Erasmus2026").param("confirmacion", "Erasmus2026"))
                .andExpect(redirectedUrl("/registro/enviado"));
    }

    /** Lee del buzón de pruebas el token del último correo recibido por esa dirección. */
    private String tokenDelUltimoCorreo(String email) {
        String cuerpo = buzon.ultimoPara(email).orElseThrow().cuerpo();
        Matcher m = TOKEN.matcher(cuerpo);
        assertThat(m.find()).as("el correo contiene un enlace de verificación").isTrue();
        return m.group(1);
    }

    private EstadoCuenta estado(String email) {
        return usuarios.findByEmail(email).orElseThrow().getEstado();
    }

    @Test
    @DisplayName("AC-01: tras registrarse llega al buzón de pruebas un enlace de verificación único")
    void ac01LlegaUnEnlaceUnico() throws Exception {
        registrarse(CORREO);
        registrarse("luis.perez@alumnos.unex.es");

        String tokenAna = tokenDelUltimoCorreo(CORREO);
        String tokenLuis = tokenDelUltimoCorreo("luis.perez@alumnos.unex.es");
        assertThat(buzon.ultimoPara(CORREO).orElseThrow().cuerpo()).contains("/verificar?token=");
        assertThat(tokenAna).isNotEqualTo(tokenLuis);
    }

    @Test
    @DisplayName("AC-02: un enlace válido verifica la cuenta y muestra la confirmación")
    void ac02EnlaceValidoVerifica() throws Exception {
        registrarse(CORREO);

        mvc.perform(get("/verificar").param("token", tokenDelUltimoCorreo(CORREO)))
                .andExpect(status().isOk())
                .andExpect(view().name("verificacion-correcta"))
                .andExpect(content().string(containsString("Cuenta verificada")));

        assertThat(estado(CORREO)).isEqualTo(EstadoCuenta.VERIFICADA);
    }

    @Test
    @DisplayName("AC-03: un enlace de más de 24 h muestra el aviso y permite pedir uno nuevo, que sí funciona")
    void ac03EnlaceCaducadoYReenvio() throws Exception {
        registrarse(CORREO);
        String caducado = tokenDelUltimoCorreo(CORREO);
        jdbc.update("update token_verificacion set emitido = ? where valor = ?",
                Timestamp.from(Instant.now().minus(Duration.ofHours(30))), caducado);

        mvc.perform(get("/verificar").param("token", caducado))
                .andExpect(view().name("verificacion-caducada"))
                .andExpect(content().string(containsString("El enlace ha caducado")))
                .andExpect(content().string(containsString("Enviar un nuevo enlace")));
        assertThat(estado(CORREO)).isEqualTo(EstadoCuenta.NO_VERIFICADA);

        mvc.perform(post("/verificacion/reenviar").param("token", caducado))
                .andExpect(redirectedUrl("/verificacion/reenviada"));
        String nuevo = tokenDelUltimoCorreo(CORREO);
        assertThat(nuevo).isNotEqualTo(caducado);

        mvc.perform(get("/verificar").param("token", nuevo))
                .andExpect(view().name("verificacion-correcta"));
        assertThat(estado(CORREO)).isEqualTo(EstadoCuenta.VERIFICADA);
    }

    @Test
    @DisplayName("AC-03: un enlace ya usado muestra el aviso")
    void ac03EnlaceReutilizado() throws Exception {
        registrarse(CORREO);
        String token = tokenDelUltimoCorreo(CORREO);
        mvc.perform(get("/verificar").param("token", token));

        mvc.perform(get("/verificar").param("token", token))
                .andExpect(view().name("verificacion-caducada"))
                .andExpect(content().string(containsString("Este enlace ya se ha usado")));
    }

    @Test
    @DisplayName("AC-04: sin verificar, publicar y contactar están bloqueados y se indica por qué")
    void ac04BloqueoSinVerificar() throws Exception {
        registrarse(CORREO);
        Usuario pendiente = usuarios.findByEmail(CORREO).orElseThrow();

        assertThatThrownBy(() -> control.exigirCuentaVerificada(pendiente))
                .isInstanceOf(CuentaNoVerificadaException.class);
        mvc.perform(get("/cuenta/pendiente"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("pendiente de verificar")))
                .andExpect(content().string(containsString("publicar")));

        mvc.perform(get("/verificar").param("token", tokenDelUltimoCorreo(CORREO)));
        Usuario verificada = usuarios.findByEmail(CORREO).orElseThrow();
        assertThat(control.puedePublicarYContactar(verificada)).isTrue();
    }

    @Test
    @DisplayName("Caso límite: si se piden varios enlaces, solo vale el último")
    void limiteSoloValeElUltimo() throws Exception {
        registrarse(CORREO);
        String primero = tokenDelUltimoCorreo(CORREO);
        mvc.perform(post("/verificacion/reenviar").param("email", CORREO));
        String ultimo = tokenDelUltimoCorreo(CORREO);

        mvc.perform(get("/verificar").param("token", primero))
                .andExpect(view().name("verificacion-caducada"));
        mvc.perform(get("/verificar").param("token", ultimo))
                .andExpect(view().name("verificacion-correcta"));
    }

    @Test
    @DisplayName("Caso límite: un token manipulado muestra «Enlace no válido» sin más detalles")
    void limiteTokenManipulado() throws Exception {
        registrarse(CORREO);
        String token = tokenDelUltimoCorreo(CORREO);

        mvc.perform(get("/verificar").param("token", token.substring(1) + "A"))
                .andExpect(view().name("verificacion-no-valida"))
                .andExpect(content().string(containsString("Enlace no válido")));
        assertThat(estado(CORREO)).isEqualTo(EstadoCuenta.NO_VERIFICADA);
    }

    @Test
    @DisplayName("Reenvío por correo: no revela si la cuenta existe")
    void reenvioNoRevelaLaCuenta() throws Exception {
        mvc.perform(post("/verificacion/reenviar").param("email", "nadie@alumnos.unex.es"))
                .andExpect(redirectedUrl("/verificacion/reenviada"));
        assertThat(buzon.getMensajes()).isEmpty();
    }
}
