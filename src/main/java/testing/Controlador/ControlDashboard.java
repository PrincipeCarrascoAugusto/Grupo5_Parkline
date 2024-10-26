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
import testing.Servicio.RecompensasService;
import testing.modelo.Reserva;

//Indica que la clase es un controlador
@Controller
@RequestMapping("/api")
public class ControlDashboard {
    
    //Inyecta los servicio de reserva y empleado
    @Autowired
    private empleadoService emplService;
    
    @Autowired
    private ReservaService resService;
    
    @Autowired
    private RecompensasService recomService;
    
    // Lista de espacios disponibles
    private List<String> listaEspacios = Arrays.asList("A1", "A2", "A3", "A4", "B1", "B2", "B3", "B4");

    @GetMapping("/dashboard")
    public String listaempleados(Model modelo) {
        //Agregamos ala lista de empleados,reservas,espacios al dashboard
        modelo.addAttribute("lista", emplService.get());
        modelo.addAttribute("listareserva", resService.get());
        modelo.addAttribute("listarecompensas",recomService.get());
        modelo.addAttribute("reserva", new Reserva()); // Modelo para el formulario
        modelo.addAttribute("espacio", listaEspacios); // Agrega la lista de espacios al modelo
        return "dashboard"; 
    }
}
