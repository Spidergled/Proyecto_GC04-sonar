package es.unex.gc04.erasmusnest.registro;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Formulario de registro (TK-004, US-001-AC-01, AC-05 y AC-06).
 * La validación la hace RegistroService en el servidor (TK-003, NFR-007).
 */
@Controller
public class RegistroController {

    static final String VISTA_FORMULARIO = "registro";
    static final String VISTA_ENVIADO = "registro-enviado";

    private final RegistroService registroService;

    public RegistroController(RegistroService registroService) {
        this.registroService = registroService;
    }

    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("form", new RegistroForm());
        return VISTA_FORMULARIO;
    }

    @PostMapping("/registro")
    public String registrar(@ModelAttribute("form") RegistroForm form, Model model,
                            RedirectAttributes redirect) {
        ResultadoRegistro resultado = registroService.registrar(form);

        if (!resultado.esAceptado()) {
            // Se conserva el correo, pero nunca se devuelven las contraseñas al navegador
            form.setPassword(null);
            form.setConfirmacion(null);
            model.addAttribute("errores", resultado.getErrores());
            return VISTA_FORMULARIO;
        }

        // Post/Redirect/Get: recargar la página no vuelve a enviar el formulario
        redirect.addFlashAttribute("mensaje", resultado.getMensaje());
        return "redirect:/registro/enviado";
    }

    @GetMapping("/registro/enviado")
    public String registroEnviado() {
        return VISTA_ENVIADO;
    }
}
