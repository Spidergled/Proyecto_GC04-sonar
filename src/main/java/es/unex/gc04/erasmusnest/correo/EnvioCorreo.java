package es.unex.gc04.erasmusnest.correo;

/**
 * Componente de envío de correo (TD-02, TK-008). Lo reutilizará US-005.
 * La implementación actual es el buzón de pruebas: no se envían correos reales (EXT-02).
 */
public interface EnvioCorreo {

    void enviar(String para, String asunto, String cuerpo);
}
