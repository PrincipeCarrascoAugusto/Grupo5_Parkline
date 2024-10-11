package testing.DAO;
import java.util.List;
import testing.modelo.*;

public interface empleadoDAO {
    public List<empleado> get();
    public empleado get(int id);
    public void save(empleado empl);
    public void delete(int id);
}
