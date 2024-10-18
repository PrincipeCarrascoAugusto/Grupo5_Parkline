package testing.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import testing.modelo.reserva;

public class ReservaController {
    @PostMapping("/reserva")
    public String handleReserva(@ModelAttribute reserva nuevaReserva, Model model) {
        // Agrega la reserva al modelo
        model.addAttribute("reserva", nuevaReserva);
        
        // Redirige a la página de confirmación
        return "reserva"; // Esto se traduce en /src/main/resources/templates/reserva.html
    }
}
