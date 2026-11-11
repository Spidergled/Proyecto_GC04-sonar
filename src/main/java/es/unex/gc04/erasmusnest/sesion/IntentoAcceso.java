package es.unex.gc04.erasmusnest.sesion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

/**
 * Intentos fallidos seguidos por correo (TK-011, US-003-AC-02). Se guarda por correo y no
 * por usuario para tratar igual los correos que no existen y no revelar si hay cuenta.
 */
@Entity
@Table(name = "intento_acceso")
public class IntentoAcceso {

    @Id
    @Column(length = 254)
    private String email;

    @Column(name = "fallos_seguidos", nullable = false)
    private int fallosSeguidos;

    @Column(name = "bloqueado_hasta")
    private Instant bloqueadoHasta;

    protected IntentoAcceso() {
        // Requerido por JPA
    }

    public IntentoAcceso(String email) {
        this.email = email;
    }

    public String getEmail() { return email; }
    public int getFallosSeguidos() { return fallosSeguidos; }
    public Instant getBloqueadoHasta() { return bloqueadoHasta; }

    boolean estaBloqueado(Instant ahora) {
        return bloqueadoHasta != null && ahora.isBefore(bloqueadoHasta);
    }

    void registrarFallo() { fallosSeguidos++; }

    void bloquearHasta(Instant hasta) {
        this.bloqueadoHasta = hasta;
        this.fallosSeguidos = 0;
    }

    void reiniciar() {
        this.fallosSeguidos = 0;
        this.bloqueadoHasta = null;
    }
}
