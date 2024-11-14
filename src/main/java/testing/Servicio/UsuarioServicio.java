package testing.servicio;

import testing.repositorio.UsuariosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import testing.modelo.Usuarios;
import org.springframework.security.core.userdetails.*;

@Service
public class UsuarioServicio implements UserDetailsService{

    @Autowired
    private UsuariosRepositorio repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuarios usuarios = repo.findByEmail(email);
        if(usuarios !=null){
           var springUser = User.withUsername(usuarios.getEmail())
                   .password(usuarios.getContraseña())
                   .roles(usuarios.getRol())
                   .build();
            return springUser;           
       }
        return null;
    }
}
