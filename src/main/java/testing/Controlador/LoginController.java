
package testing.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Indica que la clase es un controlador
@Controller
public class LoginController {
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Nombre del archivo HTML
    }
    
}
