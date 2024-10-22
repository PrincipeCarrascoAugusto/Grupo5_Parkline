package testing.DAO;

//Librerias
import java.util.List;
import testing.modelo.*;

// Definición de la interfaz ReservaDAO
public interface ReservaDAO {
    
    //Método que obtiene una lista de reservas
    public List<Reserva> get();
    
    //Método para obtener una reserva con el "ID"
    public Reserva get(int ID);
    
    //Método para guardar una nueva reserva en la BD
    public void save (Reserva res);
    
    //Método para actualizar una reserva en la BD
    public void update(Reserva res);
    
    //Método para eliminar una reserva en la BD con el "ID"
    public void delete(int ID);
}
