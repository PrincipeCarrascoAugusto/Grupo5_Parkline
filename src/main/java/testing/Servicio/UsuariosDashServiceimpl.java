package testing.Servicio;

//Librerias
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testing.DAO.UsuariosDAO;
import testing.modelo.Recompensas;
import testing.modelo.Usuarios;


//La clase se marca como un servicio gestionada por Spring
@Service
public class UsuariosDashServiceimpl implements UsuariosDashService{

    //Inyección de dependencias para usar el DAO
    @Autowired
    private UsuariosDAO usuDAO;

    
    //Asegura que esta operación esté dentro de una transacción
    @Transactional
    //Obtiene la lista de recompensas de la BD
    @Override
    public List<Usuarios> get() {return usuDAO.get();}

    
    @Transactional
    //Método para obtener una recompensa por su ID
    @Override
    public Usuarios get(int id) {return usuDAO.get(id);}

    @Transactional
    //Método para guardar una nueva recompensa
    @Override
    public void save(Usuarios usua) {usuDAO.save(usua);}

    @Transactional
    //Método para actualizar una recompensa existente
    @Override
    public void update(Usuarios usua) {usuDAO.update(usua);}

    @Transactional
    //Método para eliminar una recompensa por su ID
    @Override
    public void delete(int id) {usuDAO.delete(id);}
    
    
}
