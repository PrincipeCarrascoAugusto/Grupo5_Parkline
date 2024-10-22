package testing.DAO;

//Librerias
import testing.modelo.empleado;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//La clase es un repositorio Spring para acceder a la BD
@Repository
public class empleadoDAOimpl implements empleadoDAO {
    //Inyecta automáticamente el EntityManager gestionado por Spring para interactuar con la BD
    @Autowired
    EntityManager entityManager;
    
    @Override
    public List<empleado> get() {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        // Crea un query para obtener una lista de empleados, ordenada por el nombre de la BD
        Query<empleado> query = currentSession.createQuery(
                        "from empleado order by nombre_empl", empleado.class);
        //Ejecuta la consulta y obtiene el resultado como una lista
        List<empleado> list = query.getResultList();
        return list;
    }

    @Override
    public empleado get(int id) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        //Busca y devuelve un empleado específico con su ID
        empleado empleadoObj = currentSession.get(empleado.class, id);
        return empleadoObj;
    }

    @Override
    public void save(empleado empl) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        // Guarda o actualiza el empleado en la BD, dependiendo de si ya existe
        currentSession.saveOrUpdate(empl);
    }
    
    @Override
    public void update(empleado empl) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        //Actualiza un empleado existente en la BD
        currentSession.update(empl);
    }

    @Override
    public void delete(int id) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        //Busca al empleado por su ID y luego lo elimina de la BD
        empleado empleadoObj = currentSession.get(empleado.class, id);
        currentSession.delete(empleadoObj);
    }

    
    
}
