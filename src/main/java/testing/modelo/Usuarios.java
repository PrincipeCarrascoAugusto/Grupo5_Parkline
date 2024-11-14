package testing.modelo;

//Librerias
import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name="usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    
    @Column
    @NotEmpty(message="Debe de ingresar un nombre") 
    @Size(min=4, max=20, message="El nombre debe tener entre 4 a 20 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "El nombre no debe contener la letra 'ñ'")
    private String nombre;
    
    
    @Column
    @NotEmpty(message = "Debe de ingresar un apellido")
    @Size(min = 4, max = 20, message = "El apellido debe tener entre 4 a 20 caracteres")
    @Pattern(regexp = "^[A-Za-z\\s]*$", message = "El apellido no debe contener la letra 'ñ' o caracteres especiales")
    private String apellido;
    
    
    @Column
    @NotEmpty(message="Debe de ingresar el Dni")
    @Size(min=8, max=8, message="El Dni debe tener 8 caracteres")
    @Pattern(regexp = "\\d{8}", message = "El Dni debe contener solo números")
    private String dni;
    
    
    @Column
    @NotEmpty(message="Debe de ingresar un número de teléfono")
    @Size(min=9, max=9, message="El teléfono debe tener 9 dígitos")
    @Pattern(regexp="\\d{9}", message="El teléfono solo debe contener números")
    private String telefono;
    
    
    @Column
    @NotEmpty(message="Debe de ingresar un email")
    @Size(min=5, max=30, message="El email debe tener entre 5 a 30 caracteres")
    @Email(message = "Debe de ingresar un correo electrónico válido")
    private String email;
    
    @Column
    private Integer puntos_acumulados;
    
    @Column
    private String rol;
    
    
    @Column
    private String contraseña;
    
    

    public Usuarios() {}

    public Integer getId() {return id;}

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

    
}
