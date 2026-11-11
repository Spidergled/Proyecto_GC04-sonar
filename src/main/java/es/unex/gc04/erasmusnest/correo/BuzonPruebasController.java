package es.unex.gc04.erasmusnest.correo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/** Página del buzón de pruebas para la demo (EXT-02). */
@Controller
public class BuzonPruebasController {

    private final BuzonPruebas buzon;

    public BuzonPruebasController(BuzonPruebas buzon) {
        this.buzon = buzon;
    }

    @GetMapping("/buzon-pruebas")
    public String verBuzon(Model model) {
        model.addAttribute("mensajes", buzon.getMensajes());
        return "buzon-pruebas";
    }
}
