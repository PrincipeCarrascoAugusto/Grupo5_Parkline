package testing.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import testing.modelo.TokenRecuperacion;
import testing.modelo.Usuarios;


public interface TokenRecuperacionRepositorio extends JpaRepository<TokenRecuperacion, Integer> {
    
    // Buscar un token específico
    TokenRecuperacion findByToken(String token);

    // Eliminar tokens asociados a un usuario
    void deleteByUsuario(Usuarios usuario);
}
