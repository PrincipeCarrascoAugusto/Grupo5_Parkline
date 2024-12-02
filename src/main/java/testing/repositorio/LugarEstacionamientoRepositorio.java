package testing.repositorio;
//Librerias
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import testing.modelo.LugarEstacionamiento;

public interface LugarEstacionamientoRepositorio extends JpaRepository<LugarEstacionamiento, Integer>{
    
    //Busca el lugar del estacionamiento por su nombre
    Optional<LugarEstacionamiento> findByNom(String nom);
    
    //Filtra los lugares del estacionamiento segun su estado
    List<LugarEstacionamiento> findAll();
    
    Optional<LugarEstacionamiento> findById(Integer id);
}
