package testing.Servicio;

//Librerias
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testing.DAO.RecompensasDAO;
import testing.modelo.Recompensas;

//La clase se marca como un servicio gestionada por Spring
@Service
public class RecompensasServicioimpl implements RecompensasService{
    //Inyección de dependencias para usar el DAO
    @Autowired
    private RecompensasDAO recomDAO;
    
    //Asegura que esta operación esté dentro de una transacción
    @Transactional
    //Obtiene la lista de recompensas de la BD
    @Override
    public List<Recompensas> get() {return recomDAO.get();}

    @Transactional
    //Método para obtener una recompensa por su ID
    @Override
    public Recompensas get(int ID_recom) {return recomDAO.get(ID_recom);}
    
    @Transactional
    //Método para guardar una nueva recompensa
    @Override
    public void save(Recompensas recom) {recomDAO.save(recom);}

    @Transactional
    //Método para actualizar una recompensa existente
    @Override
    public void update(Recompensas recom) {recomDAO.update(recom);}

    @Transactional
    //Método para eliminar una recompensa por su ID
    @Override
    public void delete(int ID_recom) {recomDAO.delete(ID_recom);}
    
}
