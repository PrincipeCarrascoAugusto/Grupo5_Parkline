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
import testing.Servicio.empleadoService;
import testing.modelo.empleado;


@Controller
public class ControlEmpleado {
    
    @Autowired
    private empleadoService empleadoservice;
    List<String> listarol = Arrays.asList("Administrador","Empleado");
    
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
    
    @GetMapping("/dashboard")
    public String get(Model modelo){
        modelo.addAttribute("lista",empleadoservice.get());
        return "dashboard";
    }
}
