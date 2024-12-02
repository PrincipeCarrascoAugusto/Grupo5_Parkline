package testing.servicio;

import java.time.LocalDateTime;

public interface RecuperacionServicio {

    // Método para generar un token de recuperación y asociarlo al usuario
    public void crearTokenRecuperacion(String email, String token, LocalDateTime fechaExpiracion) throws Exception;

    // Método para verificar si el token es válido
    public boolean verificarToken(String token);

    // Método para restablecer la contraseña con el token
    public void restablecerContraseña(String token, String nuevaContraseña);
}
