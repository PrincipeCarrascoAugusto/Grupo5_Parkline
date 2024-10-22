package testing.Servicio;

//Librerias
import testing.modelo.empleado;
import testing.DAO.empleadoDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class empleadoServicioimpl implements  empleadoService{
    @Autowired
    private empleadoDAO emplDAO;
    @Transactional
    @Override
    public List<empleado> get() { return emplDAO.get(); }
    @Transactional
    @Override
    public empleado get(int id) { return emplDAO.get(id);}
    @Transactional
    @Override
    public void save(empleado empl) { emplDAO.save(empl);}
    @Transactional
    @Override
    public void update(empleado empl) {emplDAO.update(empl);}
    @Transactional
    @Override
    public void delete(int id) { emplDAO.delete(id);}

    
    
}
