package testing.Controlador;

//Librerias
import testing.modelo.empleado;
import testing.Servicio.empleadoService;
import testing.Servicio.ReservaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ControlDashboard {

    @Autowired
    private empleadoService emplService;
    @Autowired
    private ReservaService resService;
    
    
    @GetMapping("/dashboard")
    public String listaempleados(Model modelo){
        modelo.addAttribute("lista", emplService.get());
        modelo.addAttribute("listareserva", resService.get());
        return "dashboard"; 
    }
}
