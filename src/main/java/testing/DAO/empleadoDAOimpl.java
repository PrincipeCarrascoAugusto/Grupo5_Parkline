package testing.DAO;

import testing.modelo.empleado;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class empleadoDAOimpl implements empleadoDAO {
    @Autowired
    EntityManager entityManager;
    @Override
    public List<empleado> get() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<empleado> query = currentSession.createQuery(
                        "from empleado order by nombre_empl", empleado.class);
        List<empleado> list = query.getResultList();
        return list;
    }

    @Override
    public empleado get(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        empleado empleadoObj = currentSession.get(empleado.class, id);
        return empleadoObj;
    }

    @Override
    public void save(empleado empl) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(empl);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        empleado empleadoObj = currentSession.get(empleado.class, id);
        currentSession.delete(empleadoObj);
    }
    
}
