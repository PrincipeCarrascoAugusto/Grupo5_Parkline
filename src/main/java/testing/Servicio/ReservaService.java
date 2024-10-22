package testing.Servicio;

//Librerias
import testing.modelo.Reserva;
import java.util.List;

public interface ReservaService {
    
    //Método que obtiene una lista de empleados
    public List<Reserva> get();
    
    //Método para obtener un empleado con el "ID"
    public Reserva get(int ID);
    
    //Método para guardar una nueva reserva en la BD
    public void save (Reserva res);
    
    //Método para actualizar una reserva en la BD
    public void update(Reserva res);
    
    //Método para eliminar una reserva en la BD con el "ID"
    public void delete(int ID);
}
