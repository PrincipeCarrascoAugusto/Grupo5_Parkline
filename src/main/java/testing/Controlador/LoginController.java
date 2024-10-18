
package testing.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Nombre del archivo HTML
    }
    
}