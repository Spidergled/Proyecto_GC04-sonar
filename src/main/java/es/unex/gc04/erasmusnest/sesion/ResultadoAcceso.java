package es.unex.gc04.erasmusnest.sesion;

import es.unex.gc04.erasmusnest.usuario.Usuario;

/** Resultado de un intento de inicio de sesión (US-003-AC-01 y AC-02). */
public record ResultadoAcceso(Tipo tipo, Usuario usuario) {

    public enum Tipo { CORRECTO, CREDENCIALES_INCORRECTAS, BLOQUEADA }

    static ResultadoAcceso correcto(Usuario usuario) {
        return new ResultadoAcceso(Tipo.CORRECTO, usuario);
    }

    static ResultadoAcceso incorrecto() {
        return new ResultadoAcceso(Tipo.CREDENCIALES_INCORRECTAS, null);
    }

    static ResultadoAcceso bloqueada() {
        return new ResultadoAcceso(Tipo.BLOQUEADA, null);
    }

    public boolean esCorrecto() {
        return tipo == Tipo.CORRECTO;
    }
}
