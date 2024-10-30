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
    private String nombre;
    @Column
    @NotEmpty(message="Debe de ingresar un nombre")
    @Size(min=4, max=20, message="el nombre debe tener entre 4 a 20 caracteres")
    private String apellido;
    @Column
    private String dni;
    @Column
    private String telefono;
    @Column
    private String email;
    @Column
    private String contraseña;

    public Usuarios() {
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    
    
    
    
    
    
    
    
}
