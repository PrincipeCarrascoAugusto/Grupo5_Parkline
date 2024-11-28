package testing.DAO;

//Librerias
import testing.modelo.empleado;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testing.modelo.Usuarios;

//La clase es un repositorio Spring para acceder a la BD
@Repository
public class UsuariosDAOimpl implements UsuariosDAO{
    //Inyecta automáticamente el EntityManager gestionado por Spring para interactuar con la BD
    @Autowired
    EntityManager entityManager;

    @Override
    public List<Usuarios> get() {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        // Crea un query para obtener una lista de empleados, ordenada por el nombre de la BD
        Query<Usuarios> query = currentSession.createQuery(
                        "from Usuarios order by nombre", Usuarios.class);
        //Ejecuta la consulta y obtiene el resultado como una lista
        List<Usuarios> list = query.getResultList();
        return list;
        
    }

    @Override
    public Usuarios get(int id) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        //Busca y devuelve un usuario específico con su id
        Usuarios usuariosobj = currentSession.get(Usuarios.class, id);
        return usuariosobj;
    }

    @Override
    public void save(Usuarios usua) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        // Guarda o actualiza el empleado en la BD, dependiendo de si ya existe
        currentSession.saveOrUpdate(usua);
    }

    @Override
    public void update(Usuarios usua) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        //Actualiza un empleado existente en la BD
        currentSession.update(usua);
    }

    @Override
    public void delete(int id) {
        //Desenvuelve la sesión de Hibernate
        Session currentSession = entityManager.unwrap(Session.class);
        
        //Busca al empleado por su ID y luego lo elimina de la BD
        Usuarios usuariosObj = currentSession.get(Usuarios.class, id);
        currentSession.delete(usuariosObj);
    }
}
