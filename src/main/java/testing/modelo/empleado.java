package testing.modelo;

//Libreria 
import jakarta.persistence.*;

// La clase se vuelve una entidad gestionada por JPA y mapea la clase a la tabla "empleados" en la BD
@Entity
@Table(name="empleados")
public class empleado {
    //Se define "Id" como llave primaria
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    //Definimos columnas en la tabla asociadas a los campos de la clase
    @Column
    private String nombre_empl;
    @Column
    private String apellido_empl;
    @Column
    private Integer dni;
    @Column
    private Integer telefono;
    @Column
    private String email;
    @Column
    private String rol;
    @Column
    private Integer contrasena;
    
    //Constructor vacio
    public empleado(){}

    //Getter y setter
    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getNombre_empl() {return nombre_empl;}

    public void setNombre_empl(String nombre_empl) {this.nombre_empl = nombre_empl;}

    public String getApellido_empl() {return apellido_empl;}

    public void setApellido_empl(String apellido_empl) {this.apellido_empl = apellido_empl;}

    public Integer getDni() {return dni;}

    public void setDni(Integer dni) {this.dni = dni;}
    
    public Integer getTelefono() {return telefono;}

    public void setTelefono(Integer telefono) {this.telefono = telefono;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getRol() {return rol;}

    public void setRol(String rol) {this.rol = rol;}

    public Integer getContrasena() {return contrasena;}

    public void setContrasena(Integer contrasena) {this.contrasena = contrasena;}

    
}
