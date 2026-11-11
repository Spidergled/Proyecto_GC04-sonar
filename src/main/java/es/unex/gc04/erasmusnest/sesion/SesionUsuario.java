package es.unex.gc04.erasmusnest.sesion;

import es.unex.gc04.erasmusnest.usuario.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.time.Duration;
import java.util.Optional;

/** Manejo de la sesión en el servidor (TK-011, US-003-AC-03 y AC-04, NFR-001). */
public final class SesionUsuario {

    /** La sesión caduca tras 2 horas de inactividad (NFR-001, US-003-AC-04). */
    public static final Duration INACTIVIDAD_MAXIMA = Duration.ofHours(2);

    static final String ATRIBUTO_USUARIO = "usuarioId";

    private SesionUsuario() {
    }

    /** Inicia la sesión con un identificador nuevo, para evitar la fijación de sesión. */
    public static void iniciar(HttpServletRequest request, Usuario usuario) {
        HttpSession anterior = request.getSession(false);
        if (anterior != null) {
            anterior.invalidate();
        }
        HttpSession nueva = request.getSession(true);
        nueva.setAttribute(ATRIBUTO_USUARIO, usuario.getId());
        nueva.setMaxInactiveInterval((int) INACTIVIDAD_MAXIMA.toSeconds());
    }

    /** Cierra la sesión: queda invalidada en el servidor (US-003-AC-03). */
    public static void cerrar(HttpServletRequest request) {
        HttpSession sesion = request.getSession(false);
        if (sesion != null) {
            sesion.invalidate();
        }
    }

    public static Optional<Long> usuarioActual(HttpServletRequest request) {
        HttpSession sesion = request.getSession(false);
        if (sesion == null) {
            return Optional.empty();
        }
        Object id = sesion.getAttribute(ATRIBUTO_USUARIO);
        return id instanceof Long valor ? Optional.of(valor) : Optional.empty();
    }
}
