package testing.Servicio;

//Librerias
import testing.modelo.empleado;
import testing.DAO.empleadoDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//La clase se marca como un servicio gestionada por Spring
@Service
public class empleadoServicioimpl implements  empleadoService{
    //Inyección de dependencias para usar el DAO
    @Autowired
    private empleadoDAO emplDAO;
    
    //Asegura que esta operación esté dentro de una transacción
    @Transactional
    //Método para obtener todos los empleados
    @Override
    public List<empleado> get() { return emplDAO.get(); }
    
    
    @Transactional
    //Método para obtener un empleado por su ID
    @Override
    public empleado get(int id) { return emplDAO.get(id);}
    
    
    @Transactional
    //Método para guardar un nuevo empleado
    @Override
    public void save(empleado empl) { emplDAO.save(empl);}
    
    
    @Transactional
    //Método para actualizar un empleado existente
    @Override
    public void update(empleado empl) {emplDAO.update(empl);}
    
    
    @Transactional
    //Método para eliminar un empleado por su ID
    @Override
    public void delete(int id) { emplDAO.delete(id);}

    
    
}
