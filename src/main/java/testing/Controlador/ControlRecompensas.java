
package testing.Controlador;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControlRecompensas {
    @GetMapping("/Recompensas")
    public String mostrarRecompensas() {
        return "Recompensas"; // Nombre del archivo HTML
    }    
}
