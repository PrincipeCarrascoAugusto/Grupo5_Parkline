package testing.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import testing.repositorio.UsuariosRepositorio;
import testing.servicio.RecuperacionServicio;
import java.util.UUID;
import java.time.LocalDateTime;
import testing.Servicio.EmailService;

@Controller
public class RecuperacionController {

    @Autowired
    private RecuperacionServicio recuperacionServicio;

    @Autowired
    private UsuariosRepositorio usuariosRepositorio;
    
    @Autowired
    private EmailService emailService;
    
    
    // Mostrar formulario para ingresar el token
    @GetMapping("/verificar-token")
    public String mostrarFormularioToken() {
        return "token"; // Vista donde se ingresará el token
    }

    // Procesar la verificación del token
    @PostMapping("/verificar-token")
    public String verificarToken(@RequestParam("token") String token, Model model) {
        try {
            // Verificar si el token es válido
            if (recuperacionServicio.verificarToken(token)) {
                model.addAttribute("token", token); // Pasar el token válido a la siguiente vista
                return "recuperar_contraseña"; // Redirigir a la página para restablecer la contraseña
            } else {
                model.addAttribute("error", "El token es inválido o ha expirado.");
                return "token"; // Regresar a la vista para ingresar el token con el mensaje de error
            }
        } catch (Exception e) {
            model.addAttribute("error", "Hubo un problema al verificar el token: " + e.getMessage());
            return "token"; // Página de error en caso de excepción
        }
    }

    
    
    
    
    

    // Mostrar formulario para solicitar recuperación de contraseña
    @GetMapping("/recuperar")
    public String mostrarFormularioRecuperacion() {
        return "recuperar_contraseña"; // Vista para solicitar el correo electrónico
    }

    @PostMapping("/recuperar")
    public String enviarToken(@RequestParam("email") String email, Model model) {
        try {
            if (usuariosRepositorio.findByEmail(email) == null) {
                model.addAttribute("error", "El correo electrónico no está registrado.");
                return "recuperar_contraseña";
            }

            // Generar el token
            String token = UUID.randomUUID().toString();
            LocalDateTime fechaExpiracion = LocalDateTime.now().plusHours(1);
            recuperacionServicio.crearTokenRecuperacion(email, token, fechaExpiracion);

            // Enviar el token por correo electrónico
            emailService.sendEmail(email, "Recuperación de Contraseña", "Tu código de recuperación es: " + token);

            model.addAttribute("mensaje", "Te hemos enviado un correo con tu código de recuperación.");
            return "token"; // Vista donde se ingresa el token
        } catch (Exception e) {
            model.addAttribute("error", "Hubo un error al procesar tu solicitud: " + e.getMessage());
            return "recuperar_contraseña";
        }
    }


    // Mostrar formulario para restablecer la contraseña con el token
    @GetMapping("/restablecer")
    public String mostrarFormularioRestablecer(@RequestParam("token") String token, Model model) {
        try {
            // Verificar si el token es válido
            if (recuperacionServicio.verificarToken(token)) {
                model.addAttribute("token", token); // Pasar el token a la vista
                return "restablecer_contraseña"; // Vista para ingresar la nueva contraseña
            } else {
                model.addAttribute("error", "El token ha expirado o no es válido.");
                return "error"; // Página de error si el token no es válido
            }
        } catch (Exception e) {
            model.addAttribute("error", "Hubo un problema con el token: " + e.getMessage());
            return "error"; // Página de error en caso de excepción
        }
    }

    // Procesar el restablecimiento de la contraseña
    @PostMapping("/restablecer")
    public String restablecerContraseña(@RequestParam("token") String token,
                                        @RequestParam("nuevaContraseña") String nuevaContraseña,
                                        @RequestParam("confirmarContraseña") String confirmarContraseña,
                                        Model model) {
        try {
            // Verificar que las contraseñas coinciden
            if (!nuevaContraseña.equals(confirmarContraseña)) {
                model.addAttribute("error", "La nueva contraseña y la confirmación no coinciden.");
                model.addAttribute("token", token); // Asegurarse de que el token siga en el modelo
                return "restablecer_contraseña"; 
            }

            // Restablecer la contraseña
            recuperacionServicio.restablecerContraseña(token, nuevaContraseña);

            model.addAttribute("mensaje", "Contraseña restablecida correctamente. Ahora puedes acceder a tu cuenta.");
            return "redirect:/hola"; // Redirigir a la página de bienvenida

        } catch (IllegalArgumentException ex) {
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("token", token); // Asegurarse de que el token siga en el modelo
            return "restablecer_contraseña";
        } catch (Exception ex) {
            model.addAttribute("error", "Hubo un error inesperado al restablecer la contraseña.");
            model.addAttribute("token", token); // Asegurarse de que el token siga en el modelo
            return "restablecer_contraseña"; // Regresar al formulario con el error
        }
    }
}
