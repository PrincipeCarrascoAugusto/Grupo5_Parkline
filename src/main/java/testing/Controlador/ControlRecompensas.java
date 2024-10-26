
package testing.Controlador;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Indica que la clase es un controlador
@Controller
public class ControlRecompensas {
    //Mapea la ruta "/Recompensas" y muestra la Recompensas
    @GetMapping("/Recompensas")
    public String mostrarRecompensas() {
        return "Recompensas"; // Nombre del archivo HTML
    }    
}
