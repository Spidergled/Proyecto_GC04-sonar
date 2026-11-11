package es.unex.gc04.erasmusnest.correo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Component;

/**
 * Buzón de pruebas en memoria (EXT-02): guarda los correos en lugar de enviarlos,
 * para que en la demo se puedan consultar en /buzon-pruebas.
 */
@Component
public class BuzonPruebas implements EnvioCorreo {

    private final List<MensajeCorreo> mensajes = new CopyOnWriteArrayList<>();

    @Override
    public void enviar(String para, String asunto, String cuerpo) {
        mensajes.add(new MensajeCorreo(para, asunto, cuerpo, Instant.now()));
    }

    /** Del más reciente al más antiguo. */
    public List<MensajeCorreo> getMensajes() {
        List<MensajeCorreo> copia = new ArrayList<>(mensajes);
        Collections.reverse(copia);
        return copia;
    }

    public Optional<MensajeCorreo> ultimoPara(String para) {
        return getMensajes().stream().filter(m -> m.para().equalsIgnoreCase(para)).findFirst();
    }

    public void vaciar() {
        mensajes.clear();
    }
}
