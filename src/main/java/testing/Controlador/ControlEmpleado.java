package testing.Controlador;

//Librerias
import jakarta.validation.*;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import testing.Servicio.*;
import testing.modelo.Reserva;
import testing.modelo.empleado;

//Indica que la clase es un controlador 
@Controller
public class ControlEmpleado {

    //Inyecta los servicio de reserva y empleado
    @Autowired
    private ReservaService reservaservice;
    
    @Autowired
    private empleadoService empleadoservice;
    
    @Autowired
    private RecompensasService recompensasservice;
    
    //Listas de roles y espacios disponibles
    List<String> listarol = Arrays.asList("Administrador","Empleado");
    List<String> listaespacio = Arrays.asList("A1","A2","A3","A4","B1","B2","B3","B4");
    
    //CRUD para los empleados
    
    
    
    @GetMapping("/nuevoemp")
    public String NuevoEmp(Model modelo){
        //Se crea un nuevo objeto "emp", lo agrega al modelo junto con la lista de roles y retorna la vista "nuevoempleado"
        empleado emp = new empleado();
        modelo.addAttribute("empleado",emp);
        modelo.addAttribute("roles",listarol);
        return "nuevoempleado";
    }
    
    @GetMapping("/guardaremp")
    public String GuardarEmp(@Valid @ModelAttribute("empleado") empleado empl, BindingResult resultado, Model modelo){
        //Valida el objeto empleado si hay errores retorna a nuevoempleado, si no, te retorna al dashboard
        modelo.addAttribute("roles", listarol);
        if(resultado.hasErrors()){
            return "nuevoempleado";
        }
        
        empleadoservice.save(empl);
        return "redirect:/api/dashboard";
    }
    
    
    @GetMapping("/empleado/editar/{id}")
    public String EditarEmp(@PathVariable Integer id,Model modelo){
        //Método para llamar a un empleado a traves de su id
        modelo.addAttribute("empleado",empleadoservice.get(id));
        modelo.addAttribute("roles",listarol);
        return "editarempleado";
    }
    
    @GetMapping("/empleado/actualizar/{id}")
    public String ActualizarEmp(@PathVariable Integer id,@ModelAttribute("empleado") empleado empleado){
        //Método para actualizar a un empleado que sera llamado a traves de su id
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
        return "redirect:/api/dashboard";
    }
    
    @GetMapping("/empleado/eliminar/{id}")
    public String EliminarEmp(@PathVariable Integer id){
        //Método para eliminar un empleado que sera llamado a traves de su id
        empleadoservice.delete(id);
        return "redirect:/api/dashboard";
    }
    
    //CRUD para la reserva
    
    @GetMapping("/nuevares")
    public String NuevoRes(Model modelo){
        //Se crea un nuevo objeto "res", lo agrega al modelo junto con la lista de roles y retorna la vista "nuevareserva"
        Reserva res = new Reserva();
        modelo.addAttribute("reserva",res);
        modelo.addAttribute("espacio", listaespacio);
        return "nuevareserva";
    }
    
    @GetMapping("/guardarres")
    public String GuardarRes(@ModelAttribute("reserva") Reserva res){
        //Valida el objeto reserva, si es asi te lleva la dashboard
        reservaservice.save(res);
        return "redirect:/api/dashboard";
    }
    
    @GetMapping("/reserva/editar/{ID}")
    public String EditarRes(@PathVariable Integer ID,Model modelo){
        //Método para llamar a una reserva a traves de su id
        modelo.addAttribute("reserva",reservaservice.get(ID));
        modelo.addAttribute("espacio", listaespacio);
        return "editarreserva";
    }
    
    @GetMapping("/reserva/actualizar/{ID}")
    public String ActualizarRes(@PathVariable Integer ID,@ModelAttribute("reserva") Reserva reserva){
        //Método para actualizar a una reserva que sera llamado a traves de su id
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
        return "redirect:/api/dashboard";
    }
    
    @GetMapping("/reserva/eliminar/{ID}")
    public String EliminarRes(@PathVariable Integer ID){
        //Método para eliminar una reserva a traves de su id
        reservaservice.delete(ID);
        return "redirect:/api/dashboard";
    }
    
    
    
    @GetMapping("/dashboard")
    public String get(Model modelo){
        modelo.addAttribute("lista",empleadoservice.get());
        modelo.addAttribute("listareserva", reservaservice.get());
        modelo.addAttribute("listarecompensas",recompensasservice.get());
        return "/api/dashboard";
    }
    
    
    //CRUD para las recompensas
    
    
    
    
    
    
    
    
}
