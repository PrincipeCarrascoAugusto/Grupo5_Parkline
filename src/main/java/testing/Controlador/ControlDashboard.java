package testing.Controlador;

//Librerias
import java.util.Arrays;
import testing.modelo.empleado;
import testing.Servicio.empleadoService;
import testing.Servicio.ReservaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import testing.modelo.Reserva;

@Controller
@RequestMapping("/api")
public class ControlDashboard {

    @Autowired
    private empleadoService emplService;
    @Autowired
    private ReservaService resService;
    
    // Lista de espacios disponibles
    private List<String> listaEspacios = Arrays.asList("A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4");

    @GetMapping("/dashboard")
    public String listaempleados(Model modelo) {
        modelo.addAttribute("lista", emplService.get());
        modelo.addAttribute("listareserva", resService.get());
        modelo.addAttribute("reserva", new Reserva()); // Modelo para el formulario
        modelo.addAttribute("espacio", listaEspacios); // Agrega la lista de espacios al modelo
        return "dashboard"; 
    }
}
