package testing.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import testing.modelo.Reserva;

public class ReservaController {
    @PostMapping("/reserva")
    public String handleReserva(@ModelAttribute Reserva nuevaReserva, Model model) {
        
        model.addAttribute("reserva", nuevaReserva);
        
        return "newreserva"; 
    }
    
    
    
   
    
}
