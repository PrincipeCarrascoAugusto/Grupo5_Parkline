package testing.Controlador;

//Librerias
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import testing.Servicio.ReservaService;
import testing.Servicio.empleadoService;
import testing.modelo.Reserva;
import testing.modelo.empleado;


@Controller
public class ControlEmpleado {

    @Autowired
    private ReservaService reservaservice;
    
    @Autowired
    private empleadoService empleadoservice;
    
    List<String> listarol = Arrays.asList("Administrador","Empleado");
    List<String> listaespacio = Arrays.asList("A1","A2","A3","A4","B1","B2","B3","B4");
    
    //CRUD para los empleados
    
    @GetMapping("/nuevoemp")
    public String NuevoEmp(Model modelo){
        empleado emp = new empleado();
        modelo.addAttribute("empleado",emp);
        modelo.addAttribute("roles",listarol);
        return "nuevoempleado";
    }
    
    @GetMapping("/guardaremp")
    public String GuardarEmp(@ModelAttribute("empleado") empleado empl){
        empleadoservice.save(empl);
        return "redirect:/dashboard";
    }
    
    @GetMapping("/empleado/editar/{id}")
    public String EditarEmp(@PathVariable Integer id,Model modelo){
        modelo.addAttribute("empleado",empleadoservice.get(id));
        modelo.addAttribute("roles",listarol);
        return "editarempleado";
    }
    
    @GetMapping("/empleado/actualizar/{id}")
    public String ActualizarEmp(@PathVariable Integer id,@ModelAttribute("empleado") empleado empleado){
        empleado actual = empleadoservice.get(id);
        actual.setId(id);
        actual.setNombre_empl(empleado.getNombre_empl());
        actual.setApellido_empl(empleado.getApellido_empl());
        actual.setDni(empleado.getDni());
        actual.setTelefono(empleado.getTelefono());
        actual.setEmail(empleado.getEmail());
        actual.setRol(empleado.getRol());
        actual.setContrasena(empleado.getContrasena());
        empleadoservice.update(actual);
        return "redirect:/dashboard";
    }
    
    @GetMapping("/empleado/eliminar/{id}")
    public String EliminarEmp(@PathVariable Integer id){
        empleadoservice.delete(id);
        return "redirect:/dashboard";
    }
    
    //CRUD para la reserva
    
    @GetMapping("/nuevares")
    public String NuevoRes(Model modelo){
        Reserva res = new Reserva();
        modelo.addAttribute("reserva",res);
        modelo.addAttribute("espacio", listaespacio);
        return "nuevareserva";
    }
    
    @GetMapping("/guardarres")
    public String GuadarRes(@ModelAttribute("reserva") Reserva res){
        reservaservice.save(res);
        return "redirect:/dashboard";
    }
    
    @GetMapping("/reserva/editar/{ID}")
    public String EditarRes(@PathVariable Integer ID,Model modelo){
        modelo.addAttribute("reserva",reservaservice.get(ID));
        modelo.addAttribute("espacio", listaespacio);
        return "editarreserva";
    }
    
    @GetMapping("/reserva/actualizar/{ID}")
    public String ActualizarRes(@PathVariable Integer ID,@ModelAttribute("reserva") Reserva reserva){
        Reserva actual = reservaservice.get(ID);
        actual.setID(ID);
        actual.setUsuario(reserva.getUsuario());
        actual.setPlaca(reserva.getPlaca());
        actual.setHora_entrada(reserva.getHora_entrada());
        actual.setHora_salida(reserva.getHora_salida());
        actual.setEspacio(reserva.getEspacio());
        actual.setPago(reserva.getPago());
        actual.setFecha(reserva.getFecha());
        reservaservice.update(actual);
        return "redirect:/dashboard";
    }
    
    @GetMapping("/reserva/eliminar/{ID}")
    public String EliminarRes(@PathVariable Integer ID){
        reservaservice.delete(ID);
        return "redirect:/dashboard";
    }
    
    
    
    @GetMapping("/dashboard")
    public String get(Model modelo){
        modelo.addAttribute("lista",empleadoservice.get());
        modelo.addAttribute("listareserva", reservaservice.get());
        return "dashboard";
    }
}
