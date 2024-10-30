package testing.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testing.modelo.Usuarios;
import testing.Servicio.UsuarioRepositorio;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void guardarUsuario(Usuarios usuario) {
        usuarioRepositorio.save(usuario); // Guardar el usuario en la base de datos
    }

    public Usuarios buscarPorEmail(String email) {
        return usuarioRepositorio.findByEmail(email); // Buscar el usuario por email
    }
}
