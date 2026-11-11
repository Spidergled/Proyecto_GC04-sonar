package es.unex.gc04.erasmusnest.verificacion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

/**
 * Enlace de verificación de una cuenta (TK-006, RN-03): único, caduca a las 24 h
 * y solo se puede usar una vez. Guarda el id del usuario y no una relación JPA,
 * para no bloquear el borrado de usuarios en las pruebas.
 */
@Entity
@Table(name = "token_verificacion")
public class TokenVerificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 64)
    private String valor;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private Instant emitido;

    @Column(nullable = false)
    private boolean usado;

    /** true si después se pidió otro enlace: solo vale el último. */
    @Column(nullable = false)
    private boolean invalidado;

    protected TokenVerificacion() {
        // Requerido por JPA
    }

    public TokenVerificacion(String valor, Long usuarioId, Instant emitido) {
        this.valor = valor;
        this.usuarioId = usuarioId;
        this.emitido = emitido;
    }

    public Long getId() { return id; }
    public String getValor() { return valor; }
    public Long getUsuarioId() { return usuarioId; }
    public Instant getEmitido() { return emitido; }
    public boolean isUsado() { return usado; }
    public boolean isInvalidado() { return invalidado; }

    void marcarUsado() { this.usado = true; }
    void invalidar() { this.invalidado = true; }
}
