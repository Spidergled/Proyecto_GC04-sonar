package es.unex.gc04.erasmusnest.sesion;

import es.unex.gc04.erasmusnest.usuario.Usuario;
import es.unex.gc04.erasmusnest.usuario.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/** Pantallas de inicio y cierre de sesión y panel del usuario (TK-012, US-003, NFR-004). */
@Controller
public class SesionController {

    static final String VISTA_LOGIN = "login";
    static final String VISTA_PANEL = "panel";

    private final AccesoService acceso;
    private final UsuarioRepository usuarios;

    public SesionController(AccesoService acceso, UsuarioRepository usuarios) {
        this.acceso = acceso;
        this.usuarios = usuarios;
    }

    @GetMapping("/login")
    public String mostrarLogin(@RequestParam(name = "salida", required = false) String salida, Model model) {
        if (salida != null) {
            model.addAttribute("aviso", "Has cerrado la sesión.");
        }
        return VISTA_LOGIN;
    }

    @PostMapping("/login")
    public String iniciarSesion(@RequestParam(name = "email", required = false) String email,
                                @RequestParam(name = "password", required = false) String password,
                                HttpServletRequest request, Model model) {
        ResultadoAcceso resultado = acceso.autenticar(email, password);
        if (resultado.esCorrecto()) {
            SesionUsuario.iniciar(request, resultado.usuario());
            return "redirect:/panel";
        }
        model.addAttribute("email", email);
        model.addAttribute("error", resultado.tipo() == ResultadoAcceso.Tipo.BLOQUEADA
                ? AccesoService.ERROR_BLOQUEADA
                : AccesoService.ERROR_GENERICO); // no indica qué dato falla (AC-01)
        return VISTA_LOGIN;
    }

    @PostMapping("/logout")
    public String cerrarSesion(HttpServletRequest request) {
        SesionUsuario.cerrar(request);
        return "redirect:/login?salida";
    }

    @GetMapping("/panel")
    public String panel(HttpServletRequest request, HttpServletResponse response, Model model) {
        noGuardarEnCache(response); // volver atrás tras cerrar sesión no muestra datos (AC-03)
        Optional<Usuario> usuario = SesionUsuario.usuarioActual(request).flatMap(usuarios::findById);
        if (usuario.isEmpty()) {
            return "redirect:/login";
        }
        model.addAttribute("email", usuario.get().getEmail());
        model.addAttribute("verificada", usuario.get().estaVerificada());
        return VISTA_PANEL;
    }

    static void noGuardarEnCache(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, private");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    }
}
