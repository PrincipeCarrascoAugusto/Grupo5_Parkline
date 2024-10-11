package testing.Servicio;
import testing.modelo.empleado;
import java.util.List;

public interface empleadoService {
    public List<empleado> get();
    public empleado get(int id);
    public void save(empleado empl);
    public void delete(int id);
}
