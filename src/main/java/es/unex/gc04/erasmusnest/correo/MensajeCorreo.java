package es.unex.gc04.erasmusnest.correo;

import java.time.Instant;

/** Un correo enviado al buzón de pruebas (TD-02). */
public record MensajeCorreo(String para, String asunto, String cuerpo, Instant enviado) {
}
