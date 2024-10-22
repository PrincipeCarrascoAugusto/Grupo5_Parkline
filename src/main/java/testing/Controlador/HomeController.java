package testing.Controlador;

//Librerias
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import testing.Servicio.ReservaService;
import testing.modelo.Reserva;

@Controller
public class HomeController {
    
    @Autowired
    private ReservaService resService;
    
    @GetMapping("/parkline")
    public String HomeController(Model modelo) {
        modelo.addAttribute("listareserva", resService.get()); 
        return "index"; 
    }
}
