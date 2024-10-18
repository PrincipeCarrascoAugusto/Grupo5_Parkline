package testing.Controlador;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import testing.modelo.reserva;

@RequestMapping("/index")
public class ControlReserva {
    @PostMapping("/reserva")
    public String crearReserva(@ModelAttribute reserva nuevaReserva, Model model) {
        // Aquí puedes guardar la reserva en la base de datos usando Hibernate
        // reservaService.save(nuevaReserva);

        // Agregar la reserva al modelo para usarla en la vista reserva.html
        model.addAttribute("reserva", nuevaReserva);

        // Redirigir a la vista reserva.html
        return "reserva"; // Asegúrate de que esta vista exista
    }

    
}
