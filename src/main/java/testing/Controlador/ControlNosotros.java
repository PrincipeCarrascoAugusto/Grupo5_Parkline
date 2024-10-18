
package testing.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControlNosotros {
    @GetMapping("/Nosotros")
    public String mostrarLogin() {
        return "Nosotros"; // Nombre del archivo HTML
    }
    
}
