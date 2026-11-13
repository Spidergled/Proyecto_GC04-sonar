package es.unex.gc04.erasmusnest.verificacion;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Pantalla de cuenta pendiente de verificar (TK-007, US-002-AC-04). */
@Controller
public class CuentaPendienteController {

    static final String VISTA = "cuenta-pendiente";

    @GetMapping("/cuenta/pendiente")
    public String cuentaPendiente(Model model) {
        model.addAttribute("motivo", CuentaNoVerificadaException.MOTIVO);
        return VISTA;
    }

    /** Si una acción bloqueada lanza la excepción, se muestra esta pantalla con el motivo. */
    @ControllerAdvice
    public static class ManejadorCuentaNoVerificada {

        @ExceptionHandler(CuentaNoVerificadaException.class)
        @ResponseStatus(HttpStatus.FORBIDDEN)
        public String cuentaNoVerificada(CuentaNoVerificadaException e, Model model) {
            model.addAttribute("motivo", e.getMessage());
            return VISTA;
        }
    }
}
