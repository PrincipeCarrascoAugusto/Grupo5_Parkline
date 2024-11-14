package testing.controlador;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import testing.modelo.*;
import testing.repositorio.UsuariosRepositorio;

@Controller
public class LoginController {

    @Autowired
    private UsuariosRepositorio repo;

    // Mostrar el formulario de inicio de sesión
    @GetMapping("/registro")
    public String registro(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute(registerDto);
        model.addAttribute("success",false);
        return "registro"; 
    }

    // Procesar el registro de usuario
    @PostMapping("/registro")
    public String registro(Model model, @Valid @ModelAttribute RegisterDto registerDto,
                           BindingResult result, HttpSession session) {
        // Verificar si las contraseñas coinciden
        if (!registerDto.getContraseña().equals(registerDto.getConfirmContraseña())) {
            result.addError(new FieldError("registerDto", "confirmContraseña",
                                           "Contraseña y Confirma contraseña no son iguales"));
        }

        // Verificar si el email ya está registrado
        Usuarios usuarios = repo.findByEmail(registerDto.getEmail());
        if (usuarios != null) {
            result.addError(new FieldError("registerDto", "email",
                                           "El email ya está siendo usado"));
        }

        // Si hay errores de validación, imprimir los detalles de los errores
        if (result.hasErrors()) {
            return "registro";
        }

        try {
            var bCryptEncoder = new BCryptPasswordEncoder();

            // Crear el nuevo usuario
            Usuarios newusuario = new Usuarios();
            newusuario.setNombre(registerDto.getNombre());
            newusuario.setApellido(registerDto.getApellido());
            newusuario.setDni(registerDto.getDni());
            newusuario.setTelefono(registerDto.getTelefono());
            newusuario.setEmail(registerDto.getEmail());
            newusuario.setPuntos_acumulados(0); // Valor predeterminado
            newusuario.setRol("cliente"); // Valor predeterminado
            newusuario.setContraseña(bCryptEncoder.encode(registerDto.getContraseña()));

            // Guardar el usuario en la base de datos
            repo.save(newusuario);

            // Limpiar el formulario y mostrar mensaje de éxito
            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);

            System.out.println("Usuario registrado correctamente con rol: " + newusuario.getRol());
            
            // Redirigir según el rol
            if (newusuario.getRol().equals("admin") || newusuario.getRol().equals("empleado")) {
                System.out.println("Redirigiendo a /api/** con rol: " + newusuario.getRol());
                session.setAttribute("userRole", newusuario.getRol());  // Almacenar el rol en la sesión
                return "redirect:/api/**";  // Redirigir a la ruta de API
            } else {
                System.out.println("El usuario no tiene rol de admin o empleado.");
            }

        } catch (Exception ex) {
            result.addError(new FieldError("registerDto", "nombre", ex.getMessage()));
            System.out.println("Error al registrar el usuario: " + ex.getMessage());
        }

        return "registro";
    }


    
    
}
