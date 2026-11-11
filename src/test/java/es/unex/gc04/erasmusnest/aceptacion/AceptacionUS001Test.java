package es.unex.gc04.erasmusnest.aceptacion;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasEntry;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import es.unex.gc04.erasmusnest.registro.RegistroService;
import es.unex.gc04.erasmusnest.usuario.EstadoCuenta;
import es.unex.gc04.erasmusnest.usuario.Usuario;
import es.unex.gc04.erasmusnest.usuario.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Pruebas de aceptación de US-001 (TK-005): recorren el registro como lo haría el usuario,
 * enviando el formulario por HTTP, y comprueban AC-01…AC-06, los ejemplos y los casos límite
 * del spec.md. Solo cuentas sintéticas (NFR-009).
 */
@SpringBootTest
class AceptacionUS001Test {

    private static final String CORREO = "ana.garcia@alumnos.unex.es";

    @Autowired
    private WebApplicationContext contexto;

    @Autowired
    private UsuarioRepository usuarios;

    private MockMvc mvc;

    @BeforeEach
    void preparar() {
        mvc = MockMvcBuilders.webAppContextSetup(contexto).build();
        usuarios.deleteAll();
    }

    private ResultActions enviar(String email, String password, String confirmacion) throws Exception {
        return mvc.perform(post("/registro")
                .param("email", email)
                .param("password", password)
                .param("confirmacion", confirmacion));
    }

    @Test
    @DisplayName("AC-01: el formulario pide correo, contraseña y confirmación, cada uno con su etiqueta")
    void ac01FormularioConTresCamposEtiquetados() throws Exception {
        mvc.perform(get("/registro"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<label for=\"email\"")))
                .andExpect(content().string(containsString("<label for=\"password\"")))
                .andExpect(content().string(containsString("<label for=\"confirmacion\"")))
                .andExpect(content().string(containsString("id=\"email\"")))
                .andExpect(content().string(containsString("id=\"password\"")))
                .andExpect(content().string(containsString("id=\"confirmacion\"")));
    }

    @Test
    @DisplayName("AC-02: un correo no institucional se rechaza con su mensaje y no crea cuenta")
    void ac02CorreoNoInstitucional() throws Exception {
        enviar("ana.garcia@gmail.com", "Erasmus2026", "Erasmus2026")
                .andExpect(status().isOk())
                .andExpect(view().name("registro"))
                .andExpect(model().attribute("errores",
                        hasEntry("email", "Solo se admiten correos institucionales de la UEx")))
                .andExpect(content().string(containsString("id=\"email-error\"")));

        assertThat(usuarios.count()).isZero();
    }

    @Test
    @DisplayName("AC-03: un correo ya registrado muestra el mismo mensaje genérico y no crea otra cuenta")
    void ac03CorreoYaRegistradoNoSeRevela() throws Exception {
        enviar(CORREO, "Erasmus2026", "Erasmus2026")
                .andExpect(redirectedUrl("/registro/enviado"))
                .andExpect(flash().attribute("mensaje", RegistroService.MENSAJE_GENERICO));

        enviar(CORREO, "OtraClave99", "OtraClave99")
                .andExpect(redirectedUrl("/registro/enviado"))
                .andExpect(flash().attribute("mensaje", RegistroService.MENSAJE_GENERICO));

        assertThat(usuarios.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("AC-04: un registro válido crea la cuenta en estado No verificada")
    void ac04RegistroValidoNoVerificado() throws Exception {
        enviar(CORREO, "Erasmus2026", "Erasmus2026")
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/registro/enviado"));

        Usuario usuario = usuarios.findByEmail(CORREO).orElseThrow();
        assertThat(usuario.getEstado()).isEqualTo(EstadoCuenta.NO_VERIFICADA);
        // NFR-001: la contraseña no se guarda en texto plano
        assertThat(usuario.getPasswordHash()).isNotEqualTo("Erasmus2026");
        // El envío de la verificación (segunda parte de AC-04) se prueba en US-002 (TK-010).
    }

    @Test
    @DisplayName("AC-05: la contraseña indica qué le falta")
    void ac05ContrasenaIndicaQueFalta() throws Exception {
        enviar(CORREO, "erasmus", "erasmus")
                .andExpect(model().attribute("errores",
                        hasEntry("password", "La contraseña necesita al menos 8 caracteres y un número")));
        enviar(CORREO, "12345678", "12345678")
                .andExpect(model().attribute("errores",
                        hasEntry("password", "La contraseña necesita una letra")));
        enviar(CORREO, "Erasmusss", "Erasmusss")
                .andExpect(model().attribute("errores",
                        hasEntry("password", "La contraseña necesita un número")));

        assertThat(usuarios.count()).isZero();
    }

    @Test
    @DisplayName("AC-06: si la confirmación no coincide se rechaza, en el campo de confirmación, sin crear cuenta")
    void ac06ConfirmacionNoCoincide() throws Exception {
        enviar(CORREO, "Erasmus2026", "Erasmus2025")
                .andExpect(view().name("registro"))
                .andExpect(model().attribute("errores",
                        hasEntry("confirmacion", "Las contraseñas no coinciden")))
                .andExpect(content().string(containsString("id=\"confirmacion-error\"")));

        assertThat(usuarios.count()).isZero();
    }

    @Test
    @DisplayName("Caso límite: mayúsculas y espacios en el correo se normalizan")
    void limiteMayusculasYEspacios() throws Exception {
        enviar("  Ana.Garcia@Alumnos.UNEX.es ", "Erasmus2026", "Erasmus2026")
                .andExpect(redirectedUrl("/registro/enviado"));

        assertThat(usuarios.existsByEmail(CORREO)).isTrue();
    }

    @Test
    @DisplayName("Caso límite: correos mal formados se rechazan antes de comprobar el dominio")
    void limiteCorreoMalFormado() throws Exception {
        for (String malFormado : new String[] {"ana.garciaalumnos.unex.es", "ana garcia@alumnos.unex.es",
                "ana@@alumnos.unex.es"}) {
            enviar(malFormado, "Erasmus2026", "Erasmus2026")
                    .andExpect(model().attribute("errores", hasEntry("email", "Introduce un correo válido")));
        }
        assertThat(usuarios.count()).isZero();
    }

    @Test
    @DisplayName("Caso límite: un subdominio parecido al institucional se rechaza")
    void limiteSubdominioParecido() throws Exception {
        enviar("ana@alumnos.unex.es.fake.com", "Erasmus2026", "Erasmus2026")
                .andExpect(model().attribute("errores",
                        hasEntry("email", "Solo se admiten correos institucionales de la UEx")));
    }

    @Test
    @DisplayName("Caso límite: doble envío del formulario solo crea una cuenta")
    void limiteDobleEnvio() throws Exception {
        enviar(CORREO, "Erasmus2026", "Erasmus2026");
        enviar(CORREO, "Erasmus2026", "Erasmus2026");

        assertThat(usuarios.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("Tras un registro con errores, el formulario conserva el correo pero no las contraseñas")
    void conservaCorreoPeroNoContrasenas() throws Exception {
        enviar(CORREO, "Erasmus2026", "Erasmus2025")
                .andExpect(content().string(containsString("value=\"" + CORREO + "\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Erasmus2026"))));
    }
}
