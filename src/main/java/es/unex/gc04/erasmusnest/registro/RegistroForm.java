package es.unex.gc04.erasmusnest.registro;

/** Datos que envía el formulario de registro (US-001-AC-01). El formulario lo hace TK-004. */
public class RegistroForm {

    private String email;
    private String password;
    private String confirmacion;

    public RegistroForm() {
    }

    public RegistroForm(String email, String password, String confirmacion) {
        this.email = email;
        this.password = password;
        this.confirmacion = confirmacion;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getConfirmacion() { return confirmacion; }
    public void setConfirmacion(String confirmacion) { this.confirmacion = confirmacion; }
}
