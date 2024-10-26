package testing.DAO;

import jakarta.persistence.EntityManager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testing.modelo.Recompensas;
import testing.modelo.Reserva;


//La clase es un repositorio Spring para acceder a la BD
@Repository
public class RecompensasDAOimpl implements RecompensasDAO{
    
    //Inyecta automáticamente el EntityManager gestionado por Spring para interactuar con la BD
    @Autowired
    EntityManager entityManager;
    
    @Override
    public List<Recompensas> get() {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        // Crea un query para obtener una lista de recompensas, ordenada por el nombre de la BD
        Query<Recompensas> query = currentSession.createQuery(
                        "from Recompensas order by nom_recom", Recompensas.class);
        //Ejecuta la consulta y obtiene el resultado como una lista
        List<Recompensas> list = query.getResultList();
        return list;
    }

    @Override
    public Recompensas get(int ID_recom) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        //Busca y devuelve una recompensa específico con su ID
        Recompensas RecompensasObj = currentSession.get(Recompensas.class, ID_recom);
        return RecompensasObj;
    }

    @Override
    public void save(Recompensas recom) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        // Guarda o actualiza la recompensa en la BD, dependiendo de si ya existe
        currentSession.saveOrUpdate(recom);
    }

    @Override
    public void update(Recompensas recom) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        //Actualiza una recompensa existente en la BD
        currentSession.update(recom);
    }

    @Override
    public void delete(int ID_recom) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        //Busca una recompensa por su ID y luego lo elimina de la BD
        Recompensas RecompensasObj = currentSession.get(Recompensas.class, ID_recom);
        currentSession.delete(RecompensasObj);
    }
    
}
