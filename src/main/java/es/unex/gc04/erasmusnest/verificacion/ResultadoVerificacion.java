package es.unex.gc04.erasmusnest.verificacion;

/** Resultado de abrir un enlace de verificación (US-002-AC-02 y AC-03). */
public enum ResultadoVerificacion {
    /** El enlace era válido y la cuenta ha pasado a Verificada. */
    VERIFICADA,
    /** Han pasado 24 h o más, o se pidió un enlace más nuevo (RN-03). */
    CADUCADO,
    /** El enlace ya se había usado (RN-03). */
    YA_USADO,
    /** El token no existe o se ha manipulado: no se dan más detalles. */
    NO_VALIDO
}
