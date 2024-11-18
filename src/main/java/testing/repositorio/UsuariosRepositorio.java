package testing.repositorio;

import testing.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepositorio extends JpaRepository<Usuarios,Integer>{
    public Usuarios findByEmail(String email);
}
