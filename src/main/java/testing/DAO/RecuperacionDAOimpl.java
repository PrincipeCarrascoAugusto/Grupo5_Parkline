package testing.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testing.dao.RecuperacionDAO;
import testing.modelo.TokenRecuperacion;
import testing.repositorio.TokenRecuperacionRepositorio;


@Repository
public class RecuperacionDAOimpl implements RecuperacionDAO {

    @Autowired
    private TokenRecuperacionRepositorio tokenRecuperacionRepositorio;

    @Override
    public void guardarTokenRecuperacion(TokenRecuperacion tokenRecuperacion) {
        tokenRecuperacionRepositorio.save(tokenRecuperacion);
    }

    @Override
    public TokenRecuperacion encontrarTokenPorValor(String token) {
        // Buscar el token en la base de datos y devolverlo directamente, 
        // o lanzar una excepción si no se encuentra
        TokenRecuperacion tokenRecuperacion = tokenRecuperacionRepositorio.findByToken(token);
        if (tokenRecuperacion == null) {
            throw new RuntimeException("Token no encontrado");
        }
        return tokenRecuperacion;
    }

    @Override
    public void eliminarToken(String token) {
        // Buscar el token en la base de datos
        TokenRecuperacion tokenRecuperacion = tokenRecuperacionRepositorio.findByToken(token);

        // Si el token no existe, lanzar una excepción o manejarlo como desees
        if (tokenRecuperacion == null) {
            throw new RuntimeException("Token no encontrado para eliminar");
        }

        // Eliminar el token
        tokenRecuperacionRepositorio.delete(tokenRecuperacion);
    }
        
}
