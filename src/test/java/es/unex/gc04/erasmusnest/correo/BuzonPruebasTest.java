package es.unex.gc04.erasmusnest.correo;

import static org.assertj.core.api.Assertions.assertThat;

import es.unex.gc04.erasmusnest.registro.RegistroForm;
import es.unex.gc04.erasmusnest.registro.RegistroService;
import es.unex.gc04.erasmusnest.usuario.UsuarioRepository;
import es.unex.gc04.erasmusnest.verificacion.TokenVerificacionRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ExtendedModelMap;

/** Envío al buzón de pruebas (TK-008, TD-02, US-002-AC-01). Solo cuentas sintéticas (NFR-009). */
@SpringBootTest
class BuzonPruebasTest {

    private static final String CORREO = "ana.garcia@alumnos.unex.es";

    @Autowired
    private BuzonPruebas buzon;

    @Autowired
    private BuzonPruebasController controller;

    @Autowired
    private RegistroService registro;

    @Autowired
    private UsuarioRepository usuarios;

    @Autowired
    private TokenVerificacionRepository tokens;

    @BeforeEach
    void preparar() {
        buzon.vaciar();
        tokens.deleteAll();
        usuarios.deleteAll();
    }

    @Test
    void alRegistrarseLlegaUnCorreoConElEnlaceDeVerificacion() {
        registro.registrar(new RegistroForm(CORREO, "Erasmus2026", "Erasmus2026"));

        MensajeCorreo correo = buzon.ultimoPara(CORREO).orElseThrow();
        String token = tokens.findAll().get(0).getValor();
        assertThat(correo.asunto()).isEqualTo("Verifica tu cuenta de ErasmusNest");
        assertThat(correo.cuerpo()).contains("/verificar?token=" + token);
    }

    @Test
    void unCorreoYaRegistradoNoRecibeOtroCorreo() {
        registro.registrar(new RegistroForm(CORREO, "Erasmus2026", "Erasmus2026"));
        registro.registrar(new RegistroForm(CORREO, "OtraClave99", "OtraClave99"));

        assertThat(buzon.getMensajes()).hasSize(1);
    }

    @Test
    @SuppressWarnings("unchecked")
    void laPaginaDelBuzonMuestraLosCorreos() {
        registro.registrar(new RegistroForm(CORREO, "Erasmus2026", "Erasmus2026"));
        ExtendedModelMap model = new ExtendedModelMap();

        assertThat(controller.verBuzon(model)).isEqualTo("buzon-pruebas");
        assertThat((List<MensajeCorreo>) model.get("mensajes")).hasSize(1);
    }
}
