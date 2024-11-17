package testing.Servicio;

//Librerias
import testing.modelo.Usuarios;
import java.util.List;

public interface UsuariosDashService {
    
    public List<Usuarios> get();
    
    public Usuarios get(int id);
    
    public void save(Usuarios usua);
    
    public void update(Usuarios usua);
    
    public void delete(int id);
    
}
