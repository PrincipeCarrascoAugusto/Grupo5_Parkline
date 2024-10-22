package testing.Servicio;

//Librerias
import testing.modelo.Reserva;
import testing.DAO.ReservaDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//La clase se marca como un servicio gestionada por Spring
@Service
public class ReservaServicioimpl implements ReservaService{
    //Inyección de dependencias para usar el DAO
    @Autowired
    private ReservaDAO resDAO;
    
    //Asegura que esta operación esté dentro de una transacción
    @Transactional
    //Obtiene la lista de reservas de la BD
    @Override
    public List<Reserva> get() {return resDAO.get();}
    
    @Transactional
    //Método para obtener una reserva por su ID
    @Override
    public Reserva get(int ID) {return resDAO.get(ID);}
    
    @Transactional
    //Método para guardar una nueva reserva
    @Override
    public void save(Reserva res) {resDAO.save(res);}
    
    @Transactional
    //Método para actualizar una reserva existente
    @Override
    public void update(Reserva res) {resDAO.update(res);}
    
    @Transactional
    //Método para eliminar una reserva por su ID
    @Override
    public void delete(int ID) {resDAO.delete(ID);}
    
}
