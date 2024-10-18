
package testing.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControlReservas {
    @GetMapping("/reservas")
    public String mostrarReservas() {
        return "Reservas"; // Nombre del archivo HTML
    }  
}
