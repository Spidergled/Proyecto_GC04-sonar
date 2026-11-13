package es.unex.gc04.erasmusnest.registro;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Resultado de un intento de registro.
 * Los errores van por campo ("email", "password", "confirmacion") para que el
 * formulario los asocie a su campo (NFR-004).
 */
public class ResultadoRegistro {

    private final Map<String, String> errores;

    private ResultadoRegistro(Map<String, String> errores) {
        this.errores = errores;
    }

    static ResultadoRegistro aceptado() {
        return new ResultadoRegistro(Collections.emptyMap());
    }

    static ResultadoRegistro rechazado(Map<String, String> errores) {
        return new ResultadoRegistro(Collections.unmodifiableMap(new LinkedHashMap<>(errores)));
    }

    /**
     * true si no hay errores de validación. Se devuelve igual si la cuenta se ha creado
     * o si el correo ya existía, para no revelar si la cuenta existe (US-001-AC-03).
     */
    public boolean esAceptado() {
        return errores.isEmpty();
    }

    public Map<String, String> getErrores() {
        return errores;
    }

    /** Mensaje que se muestra tras un registro aceptado, exista o no la cuenta (US-001-AC-03). */
    public String getMensaje() {
        return esAceptado() ? RegistroService.MENSAJE_GENERICO : null;
    }
}
