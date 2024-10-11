package testing.Controlador;

import testing.modelo.empleado;
import testing.Servicio.empleadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ControlPrueba {

    @Autowired
    private empleadoService emplService;
    
    @GetMapping("/empleado")
    public String listaempleados(Model modelo){
        modelo.addAttribute("lista", emplService.get()); 
        return "dashboard"; 
    }
}
