package es.unex.gc04.erasmusnest.verificacion;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenVerificacionRepository extends JpaRepository<TokenVerificacion, Long> {

    Optional<TokenVerificacion> findByValor(String valor);

    List<TokenVerificacion> findByUsuarioId(Long usuarioId);

    List<TokenVerificacion> findByUsuarioIdAndUsadoFalseAndInvalidadoFalse(Long usuarioId);
}
