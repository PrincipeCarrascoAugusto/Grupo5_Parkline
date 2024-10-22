package testing.DAO;

//Librerias
import java.util.List;
import testing.modelo.*;

// Definición de la interfaz empleadoDAO
public interface empleadoDAO {
    
    //Método que obtiene una lista de empleados 
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
