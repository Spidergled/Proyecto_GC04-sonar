package es.unex.gc04.erasmusnest.sesion;

import es.unex.gc04.erasmusnest.usuario.Usuario;
import es.unex.gc04.erasmusnest.usuario.UsuarioRepository;
import java.time.Duration;
import java.time.Instant;
import java.util.Locale;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Comprobación de credenciales en el servidor (TK-011, US-003, NFR-001 y NFR-007):
 * mensaje genérico ante cualquier fallo y bloqueo de 15 minutos tras 5 fallos seguidos.
 * Una cuenta no verificada puede entrar, con acciones limitadas (RN-02).
 */
@Service
public class AccesoService {

    public static final String ERROR_GENERICO = "Correo o contraseña incorrectos";
    public static final String ERROR_BLOQUEADA =
            "Demasiados intentos fallidos. Vuelve a intentarlo dentro de 15 minutos.";

    static final int MAX_FALLOS_SEGUIDOS = 5;
    static final Duration DURACION_BLOQUEO = Duration.ofMinutes(15);

    private final UsuarioRepository usuarios;
    private final IntentoAccesoRepository intentos;
    private final PasswordEncoder encoder;

    public AccesoService(UsuarioRepository usuarios, IntentoAccesoRepository intentos, PasswordEncoder encoder) {
        this.usuarios = usuarios;
        this.intentos = intentos;
        this.encoder = encoder;
    }

    @Transactional
    public ResultadoAcceso autenticar(String email, String password) {
        return autenticar(email, password, Instant.now());
    }

    ResultadoAcceso autenticar(String email, String password, Instant ahora) {
        String correo = email == null ? "" : email.trim().toLowerCase(Locale.ROOT);
        IntentoAcceso intento = intentos.findById(correo).orElseGet(() -> new IntentoAcceso(correo));

        if (intento.estaBloqueado(ahora)) {
            return ResultadoAcceso.bloqueada(); // ni siquiera se comprueba la contraseña
        }

        Optional<Usuario> usuario = usuarios.findByEmail(correo);
        boolean correcta = usuario.isPresent() && password != null
                && encoder.matches(password, usuario.get().getPasswordHash());

        if (correcta) {
            intento.reiniciar();
            intentos.save(intento);
            return ResultadoAcceso.correcto(usuario.get());
        }

        intento.registrarFallo();
        if (intento.getFallosSeguidos() >= MAX_FALLOS_SEGUIDOS) {
            intento.bloquearHasta(ahora.plus(DURACION_BLOQUEO));
            intentos.save(intento);
            return ResultadoAcceso.bloqueada();
        }
        intentos.save(intento);
        return ResultadoAcceso.incorrecto();
    }
}
