package testing.Servicio;

//Librerias
import testing.modelo.empleado;
import java.util.List;

public interface empleadoService {
    
    //Método que obtiene una lista de empleado 
    public List<empleado> get();
    
    //Método para obtener un empleado con el "id"
    public empleado get(int id);
    
    //Método para guardar un nuevo empleado en la BD
    public void save(empleado empl);
    
    //Método para actualizar un empleado en la BD
    public void update(empleado empl);
    
    //Método para eliminar un empleado en la BD con el "id"
    public void delete(int id);
}
