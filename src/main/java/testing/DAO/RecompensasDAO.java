package testing.DAO;

//Librerias
import java.util.List;
import testing.modelo.*;


//Definición de la interfaz RecompensasDAO
public interface RecompensasDAO {
    
    //Método que obtiene una lista de recompensas
    public List<Recompensas> get();
    
    //Método para obtener una recompensa con el "ID"
    public Recompensas get(int ID_recom);
    
    //Método para guardar una nueva recompensa en la BD
    public void save(Recompensas recom);
    
    //Método para actualizar una reocmpensa en la BD
    public void update(Recompensas recom);
    
    //Método para eliminar una recompensa en la BD con el "ID"
    public void delete(int ID_recom);
}
