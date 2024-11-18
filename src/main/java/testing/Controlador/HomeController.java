package testing.Controlador;

//Librerias
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import testing.Servicio.ReservaService;
import testing.modelo.Reserva;

//Indica que la clase es un controlador
@Controller
public class HomeController {
    //Inyecta el servicio reserva
    @Autowired
    private ReservaService resService;
    
    // Mapea la ruta "/parkline" y carga la página de inicio
    @GetMapping("/parkline")
    public String HomeController(Model modelo) {
        // Agrega la lista de reservas al modelo
        modelo.addAttribute("listareserva", resService.get()); 
        return "index"; //retorna al index
    }
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
