package testing.DAO;

//Librerias
import testing.modelo.Reserva;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//La clase es un repositorio Spring para acceder a la BD
@Repository
public class ReservaDAOimpl implements ReservaDAO{
    //Inyecta automáticamente el EntityManager gestionado por Spring para interactuar con la BD
    @Autowired
    EntityManager entityManager;
    
    @Override
    public List<Reserva> get() {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        // Crea un query para obtener una lista de reservas, ordenada por el usuario de la BD
        Query<Reserva> query = currentSession.createQuery(
                        "from Reserva order by usuario", Reserva.class);
        //Ejecuta la consulta y obtiene el resultado como una lista
        List<Reserva> list = query.getResultList();
        return list;
    }

    @Override
    public Reserva get(int ID) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        //Busca y devuelve una reserva específico con su ID
        Reserva ReservaObj = currentSession.get(Reserva.class, ID);
        return ReservaObj;
    }

    @Override
    public void save(Reserva res) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        // Guarda o actualiza el reserva en la BD, dependiendo de si ya existe
        currentSession.saveOrUpdate(res);
    }

    @Override
    public void update(Reserva res) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        //Actualiza una reserva existente en la BD
        currentSession.update(res);
    }

    @Override
    public void delete(int ID) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        //Busca una reserva por su ID y luego lo elimina de la BD
        Reserva ReservaObj = currentSession.get(Reserva.class, ID);
        currentSession.delete(ReservaObj);
    }
    
}
