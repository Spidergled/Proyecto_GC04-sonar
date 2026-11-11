package es.unex.gc04.erasmusnest.verificacion;

/** Se lanza cuando una cuenta no verificada intenta publicar o contactar (RN-02, US-002-AC-04). */
public class CuentaNoVerificadaException extends RuntimeException {

    public static final String MOTIVO = "Tu cuenta aún no está verificada. Para publicar anuncios o contactar "
            + "con otros estudiantes, abre el enlace que te hemos enviado a tu correo institucional.";

    public CuentaNoVerificadaException() {
        super(MOTIVO);
    }
}
