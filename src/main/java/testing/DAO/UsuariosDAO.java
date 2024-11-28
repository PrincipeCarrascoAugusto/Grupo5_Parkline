package testing.DAO;

//Librerias
import java.util.List;
import testing.modelo.*;

public interface UsuariosDAO {
    
    //Método que obtiene una lista de usuarios
    public List<Usuarios> get();
    
    //Método para obtener un usuario con el "id"
    public Usuarios get(int id);
    
    //Método para guardar una nuevo usuario en la BD
    public void save (Usuarios usua);
    
    //Método para actualizar un usuario en la BD
    public void update(Usuarios usua);
    
    //Método para eliminar un usuario en la BD con el "id"
    public void delete(int id);
    
}
