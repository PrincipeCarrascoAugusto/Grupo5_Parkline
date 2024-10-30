
package testing.Controlador;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import testing.Servicio.RecompensasService;
import testing.modelo.Recompensas;

//Indica que la clase es un controlador
@Controller
public class ControlRecompensas {
    //Inyecta los servicio de recompensa
    @Autowired
    private RecompensasService recompensasservice;

    //Mapea la ruta "/Recompensas" y muestra la Recompensas
    @GetMapping("/Recompensas")
    public String mostrarRecompensas(Model modelo) {
        List<Recompensas> listarecompensas = recompensasservice.get();
        modelo.addAttribute("listarecompensas", listarecompensas);
        return "Recompensas"; // Nombre del archivo HTML
    }    
}
