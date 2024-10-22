package testing.Controlador;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import testing.modelo.Reserva;

@Controller
public class HomeController {
    @GetMapping("/parkline")
    public String HomeController(Model model) {
        model.addAttribute("reserva", new Reserva()); // Asegúrate de que 'reserva' sea una clase que tienes
        return "index"; 
    }
}
