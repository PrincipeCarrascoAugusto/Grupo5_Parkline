
package testing.Servicio;

import org.springframework.data.jpa.repository.JpaRepository;
import testing.modelo.Usuarios;

public interface UsuarioRepositorio extends JpaRepository<Usuarios, Integer> {
    Usuarios findByEmail(String email); // Método para encontrar usuario por email
}
