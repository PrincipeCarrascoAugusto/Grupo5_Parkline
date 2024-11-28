package testing.Servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testing.dao.RecuperacionDAO;
import testing.modelo.TokenRecuperacion;
import testing.modelo.Usuarios;
import testing.repositorio.UsuariosRepositorio;
import testing.servicio.RecuperacionServicio;

import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class RecuperacionServicioimpl implements RecuperacionServicio {
    @Autowired
    private RecuperacionDAO recuperacionDAO;

    @Autowired
    private UsuariosRepositorio usuariosRepositorio;
    
    @Autowired
    private EmailService emailService;

    // Método para crear un token de recuperación
   @Override
   public void crearTokenRecuperacion(String email, String token, LocalDateTime fechaExpiracion) throws Exception {
        Usuarios usuario = usuariosRepositorio.findByEmail(email);
        if (usuario == null) {
            throw new Exception("El usuario con el correo proporcionado no existe.");
        }

        // Generar un código corto si no se pasa uno
        if (token == null) {
            token = String.format("%06d", (int) (Math.random() * 1000000)); // Generar un código de 6 dígitos
        }

        if (fechaExpiracion == null) {
            fechaExpiracion = LocalDateTime.now().plusHours(1);
        }

        TokenRecuperacion tokenRecuperacion = new TokenRecuperacion(token, usuario, fechaExpiracion);
        recuperacionDAO.guardarTokenRecuperacion(tokenRecuperacion);

        // Enviar el correo con el código corto
        String subject = "Código de Recuperación de Contraseña";
        String body = "Tu código de recuperación es: " + token + ". Este código es válido por 1 hora.";
        emailService.sendEmail(email, subject, body);
   }


    // Método para verificar si el token es válido (no ha expirado)
    @Override
    public boolean verificarToken(String token) {
        TokenRecuperacion tokenRecuperacion = recuperacionDAO.encontrarTokenPorValor(token);
        if (tokenRecuperacion == null) {
            return false; // El token no existe
        }

        // Verificar si el token ha expirado
        if (tokenRecuperacion.getFechaExpiracion().isBefore(LocalDateTime.now())) {
            return false; // El token ha expirado
        }

        return true;
    }

    
    
    
    // Método para restablecer la contraseña
    @Override
public void restablecerContraseña(String token, String nuevaContraseña) {
    System.out.println("Iniciando restablecerContraseña con token: " + token);

    // Buscar el token en la base de datos
    TokenRecuperacion tokenRecuperacion = recuperacionDAO.encontrarTokenPorValor(token);
    
    if (tokenRecuperacion == null) {
        System.out.println("Error: Token no encontrado o es nulo.");
        
    }

    // Verificar si el token ha expirado
    if (tokenRecuperacion.getFechaExpiracion().isBefore(LocalDateTime.now())) {
        System.out.println("Error: Token expirado.");
        
    }

    System.out.println("Token válido. Obteniendo usuario asociado...");

    // Obtener el usuario asociado al token
    Usuarios usuario = tokenRecuperacion.getUsuario();
    if (usuario == null) {
        System.out.println("Error: Usuario no encontrado para el token.");
        
    }

    // Impresión para verificar el usuario y el email
    System.out.println("Usuario encontrado: " + usuario.getEmail());

    // Validar la nueva contraseña (si necesitas agregar reglas como mínimo de longitud)
    if (nuevaContraseña == null || nuevaContraseña.isEmpty()) {
        System.out.println("Error: La nueva contraseña no puede estar vacía.");
        
    }

    // Codificar la nueva contraseña con BCrypt
    System.out.println("Codificando la nueva contraseña...");
    var bCryptEncoder = new BCryptPasswordEncoder();
    String contraseñaCodificada = bCryptEncoder.encode(nuevaContraseña);

    // Impresión para verificar la contraseña codificada
    System.out.println("Contraseña codificada: " + contraseñaCodificada);

    // Actualizar la contraseña del usuario
    usuario.setContraseña(contraseñaCodificada);

    // Guardar la nueva contraseña en la base de datos
    System.out.println("Guardando nueva contraseña en la base de datos...");
    usuariosRepositorio.save(usuario);  // Asegúrate de que el repositorio está bien configurado

    // Eliminar el token después de usarlo
    System.out.println("Eliminando token usado...");
    recuperacionDAO.eliminarToken(token);

    System.out.println("Contraseña actualizada exitosamente para el usuario: " + usuario.getEmail());
}

    





    
}
