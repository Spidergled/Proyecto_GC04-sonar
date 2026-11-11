package es.unex.gc04.erasmusnest.verificacion;

import es.unex.gc04.erasmusnest.usuario.Usuario;
import org.springframework.stereotype.Component;

/**
 * Bloqueo de publicar y contactar para cuentas no verificadas (TK-007, RN-02, US-002-AC-04).
 * US-006 (publicar) y US-017 (contactar) llamarán a exigirCuentaVerificada antes de actuar.
 */
@Component
public class ControlVerificacion {

    public boolean puedePublicarYContactar(Usuario usuario) {
        return usuario != null && usuario.estaVerificada();
    }

    public void exigirCuentaVerificada(Usuario usuario) {
        if (!puedePublicarYContactar(usuario)) {
            throw new CuentaNoVerificadaException();
        }
    }
}
