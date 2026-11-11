package es.unex.gc04.erasmusnest.verificacion;

import es.unex.gc04.erasmusnest.usuario.Usuario;
import es.unex.gc04.erasmusnest.usuario.UsuarioRepository;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Enlace de verificación único y caducidad de 24 h (TK-006, US-002, RN-03).
 * El envío por correo lo añade TK-008.
 */
@Service
public class VerificacionService {

    /** El enlace vale estrictamente menos de 24 h (caso límite de US-002). */
    static final Duration VALIDEZ = Duration.ofHours(24);

    private static final SecureRandom ALEATORIO = new SecureRandom();

    private final TokenVerificacionRepository tokens;
    private final UsuarioRepository usuarios;

    public VerificacionService(TokenVerificacionRepository tokens, UsuarioRepository usuarios) {
        this.tokens = tokens;
        this.usuarios = usuarios;
    }

    /** Emite un enlace nuevo e invalida los anteriores: solo vale el último. Devuelve el token. */
    @Transactional
    public String emitirEnlace(Usuario usuario) {
        return emitirEnlace(usuario, Instant.now());
    }

    String emitirEnlace(Usuario usuario, Instant ahora) {
        List<TokenVerificacion> activos = tokens.findByUsuarioIdAndUsadoFalseAndInvalidadoFalse(usuario.getId());
        activos.forEach(TokenVerificacion::invalidar);
        tokens.saveAll(activos);

        String valor = generarValor();
        tokens.save(new TokenVerificacion(valor, usuario.getId(), ahora));
        return valor;
    }

    /** Abre un enlace: verifica la cuenta del enlace (no la de la sesión) si es válido. */
    @Transactional
    public ResultadoVerificacion verificar(String token) {
        return verificar(token, Instant.now());
    }

    ResultadoVerificacion verificar(String token, Instant ahora) {
        if (token == null || token.isBlank()) {
            return ResultadoVerificacion.NO_VALIDO;
        }
        Optional<TokenVerificacion> encontrado = tokens.findByValor(token);
        if (encontrado.isEmpty()) {
            return ResultadoVerificacion.NO_VALIDO;
        }
        TokenVerificacion enlace = encontrado.get();
        if (enlace.isUsado()) {
            return ResultadoVerificacion.YA_USADO;
        }
        if (enlace.isInvalidado() || !ahora.isBefore(enlace.getEmitido().plus(VALIDEZ))) {
            return ResultadoVerificacion.CADUCADO;
        }
        Optional<Usuario> usuario = usuarios.findById(enlace.getUsuarioId());
        if (usuario.isEmpty()) {
            return ResultadoVerificacion.NO_VALIDO;
        }
        usuario.get().verificar();
        usuarios.save(usuario.get());
        enlace.marcarUsado();
        tokens.save(enlace);
        return ResultadoVerificacion.VERIFICADA;
    }

    /** Pide un enlace nuevo a partir de uno caducado o usado (US-002-AC-03). */
    @Transactional
    public boolean reenviarDesdeToken(String tokenAnterior) {
        if (tokenAnterior == null || tokenAnterior.isBlank()) {
            return false;
        }
        return tokens.findByValor(tokenAnterior)
                .flatMap(enlace -> usuarios.findById(enlace.getUsuarioId()))
                .filter(usuario -> !usuario.estaVerificada())
                .map(usuario -> {
                    emitirEnlace(usuario, Instant.now());
                    return true;
                })
                .orElse(false);
    }

    /** Pide un enlace nuevo con el correo. No revela si la cuenta existe. */
    @Transactional
    public void reenviarPorCorreo(String email) {
        if (email == null) {
            return;
        }
        usuarios.findByEmail(email.trim().toLowerCase(Locale.ROOT))
                .filter(usuario -> !usuario.estaVerificada())
                .ifPresent(usuario -> emitirEnlace(usuario, Instant.now()));
    }

    /** 32 bytes aleatorios en Base64 URL: imposible de adivinar. */
    private static String generarValor() {
        byte[] bytes = new byte[32];
        ALEATORIO.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
