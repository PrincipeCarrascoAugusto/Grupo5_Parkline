
package testing.Controlador;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import testing.Servicio.ReservaService;
import testing.modelo.Reserva;

@Controller
public class ControlReservas {
    
    @Autowired
    private ReservaService reservaservice;
    
    List<String> listaespacio = Arrays.asList("A1","A2","A3","A4","B1","B2","B3","B4");
    
    
    @GetMapping("/reservas")
    public String mostrarReservas(Model modelo) {
        Reserva res = new Reserva();
        modelo.addAttribute("reserva",res);
        modelo.addAttribute("espacio", listaespacio);
        return "Reservas"; 
    }  
    
    @GetMapping("/guarres")
    public String GuadarRes(@ModelAttribute("reserva") Reserva res){
        reservaservice.save(res);
        return "index";
    }
    
    
    
    
    
    
    
}
