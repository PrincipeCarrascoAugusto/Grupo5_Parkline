package testing.modelo;

import jakarta.validation.constraints.*;

public class RegisterDto {
    
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String apellido;
    
    @NotEmpty
    private String dni;
    
    @NotEmpty
    private String telefono;
    
    @NotEmpty
    @Email
    private String email;
    
    
    private Integer puntos_acumulados;
    
    @NotEmpty
    private String rol;
    
    @Size(min=6, message="La contraseña debe tener al menos 6 caracteres")
    private String contraseña;
    private String confirmContraseña;

    public RegisterDto() {
        this.puntos_acumulados = 0; 
        this.rol = "cliente"; 
    }

    
    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return apellido;}

    public void setApellido(String apellido) {this.apellido = apellido;}

    public String getDni() {return dni;}

    public void setDni(String dni) {this.dni = dni;}

    public String getTelefono() {return telefono;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public Integer getPuntos_acumulados() {return puntos_acumulados;}

    public void setPuntos_acumulados(Integer puntos_acumulados) {this.puntos_acumulados = puntos_acumulados;}

    public String getRol() {return rol;}

    public void setRol(String rol) {this.rol = rol;}
    
    public String getContraseña() {return contraseña;}

    public void setContraseña(String contraseña) {this.contraseña = contraseña;}

    public String getConfirmContraseña() {return confirmContraseña;}

    public void setConfirmContraseña(String confirmContraseña) {this.confirmContraseña = confirmContraseña;}

    
    
    
    
    
}
