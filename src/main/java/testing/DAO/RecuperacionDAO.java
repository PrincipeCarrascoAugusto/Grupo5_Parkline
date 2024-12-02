package testing.dao;

import testing.modelo.TokenRecuperacion;

public interface RecuperacionDAO {

    public void guardarTokenRecuperacion(TokenRecuperacion tokenRecuperacion);
    
    TokenRecuperacion encontrarTokenPorValor(String token);
    
    void eliminarToken(String token);
}
