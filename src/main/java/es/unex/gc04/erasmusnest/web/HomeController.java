package es.unex.gc04.erasmusnest.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Página de inicio. Punto de partida del esqueleto (TK-001); las historias
 * de usuario añaden sus propios controladores en este paquete.
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
