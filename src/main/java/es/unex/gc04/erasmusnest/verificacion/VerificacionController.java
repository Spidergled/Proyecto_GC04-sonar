package es.unex.gc04.erasmusnest.verificacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Pantallas al abrir el enlace y reenvío de un enlace nuevo
 * (TK-009, US-002-AC-02, AC-03, NFR-004).
 */
@Controller
public class VerificacionController {

    static final String VISTA_CORRECTA = "verificacion-correcta";
    static final String VISTA_CADUCADA = "verificacion-caducada";
    static final String VISTA_NO_VALIDA = "verificacion-no-valida";
    static final String VISTA_REENVIADA = "verificacion-reenviada";

    static final String AVISO_CADUCADO = "El enlace ha caducado";
    static final String AVISO_USADO = "Este enlace ya se ha usado";

    private final VerificacionService verificacion;

    public VerificacionController(VerificacionService verificacion) {
        this.verificacion = verificacion;
    }

    @GetMapping("/verificar")
    public String abrirEnlace(@RequestParam(name = "token", required = false) String token, Model model) {
        ResultadoVerificacion resultado = verificacion.verificar(token);
        switch (resultado) {
            case VERIFICADA -> {
                return VISTA_CORRECTA;
            }
            case CADUCADO, YA_USADO -> {
                model.addAttribute("aviso", resultado == ResultadoVerificacion.CADUCADO ? AVISO_CADUCADO : AVISO_USADO);
                model.addAttribute("token", token);
                return VISTA_CADUCADA;
            }
            default -> {
                return VISTA_NO_VALIDA; // «Enlace no válido», sin más detalles
            }
        }
    }

    /** Botón «Enviar un nuevo enlace»: desde un enlace caducado o usado, o desde el correo. */
    @PostMapping("/verificacion/reenviar")
    public String reenviar(@RequestParam(name = "token", required = false) String token,
                           @RequestParam(name = "email", required = false) String email) {
        if (token != null && !token.isBlank()) {
            verificacion.reenviarDesdeToken(token);
        } else {
            verificacion.reenviarPorCorreo(email);
        }
        // Mismo mensaje en todos los casos: no revela si la cuenta existe
        return "redirect:/verificacion/reenviada";
    }

    @GetMapping("/verificacion/reenviada")
    public String reenviada() {
        return VISTA_REENVIADA;
    }
}
