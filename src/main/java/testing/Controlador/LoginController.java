package testing.controlador;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import testing.modelo.Usuarios;
import testing.servicio.UsuarioServicio;

@Controller
public class LoginController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Mostrar el formulario de inicio de sesión
    @GetMapping("/login")
    public String mostrarLogin(Model model) {
        return "login"; // nombre de tu archivo login.html
    }

    // Procesar el inicio de sesión
    @PostMapping("/login")
    public String procesarLogin(@RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model, HttpSession session) {
        Usuarios usuario = usuarioServicio.buscarPorEmail(email);

        if (usuario != null && usuario.getContraseña().equals(password)) {
            // Almacenar el nombre del usuario en la sesión
            session.setAttribute("nombreUsuario", usuario.getNombre());
            return "/index"; // Cambia a la ruta de tu página principal
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login"; // Volver a la página de login
        }
    }

    // Mostrar el formulario de registro
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        return "login"; // Podrías cambiar esto a una vista de registro separada si prefieres
    }

    // Procesar el registro
    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("telefono") String telefono,
            @RequestParam("dni") String dni,
            @RequestParam("email") String email,
            @RequestParam("contraseña") String contraseña,
            Model model) {
        // Crear un nuevo usuario
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setTelefono(telefono);
        nuevoUsuario.setDni(dni);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setContraseña(contraseña);

        // Validar si el usuario ya existe
        if (usuarioServicio.buscarPorEmail(email) != null) {
            model.addAttribute("error", "El correo ya está registrado");
            return "login"; // Volver a la página de login con un mensaje de error
        }

        // Guardar el nuevo usuario
        usuarioServicio.guardarUsuario(nuevoUsuario);
        model.addAttribute("mensaje", "Registro exitoso. Puedes iniciar sesión.");
        return "login"; // Redirigir a la página de login
    }

    // Método para cerrar sesión
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {
        session.invalidate(); // Invalida la sesión
        return "redirect:/login"; // Redirige al login
    }
}
