package testing.Controlador;

//Librerias
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//Indica que la clase es un controlador
@Controller
public class ControlNosotros { 
    //Mapea la ruta "/Nosotros" y muestra la página Nosotros
    @GetMapping("/Nosotros")
    public String mostrarLogin() {
        return "Nosotros"; 
    }
    
}
