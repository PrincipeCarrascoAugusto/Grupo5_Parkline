package testing.modelo;

//Libreria 
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

// La clase se vuelve una entidad gestionada por JPA y mapea la clase a la tabla "empleados" en la BD
@Entity
@Table(name="empleados")
public class empleado {
    //Se define "Id" como llave primaria
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    //Definimos columnas en la tabla asociadas a los campos de la clase
    //NotEmpty aseguro que el campo no este vacio y muestra un mensaje
    //Size define el tamaño minimo y maximo y muestra un mensaje
    //Pattern permite excluir ciertas letras si las definimos y muestra un mensaje
    //Email verifica que el formato del correo electronico sea valido y muestra un mensaje
    //Column mapea un atributo a una tabla de la BD
    @Column
    @NotEmpty(message="Debe de ingresar un nombre") 
    @Size(min=4, max=20, message="El nombre debe tener entre 4 a 20 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "El nombre no debe contener la letra 'ñ'")
    private String nombre_empl;
    
    
    @Column
    @NotEmpty(message = "Debe de ingresar un apellido")
    @Size(min = 4, max = 20, message = "El apellido debe tener entre 4 a 20 caracteres")
    @Pattern(regexp = "^[A-Za-z\\s]*$", message = "El apellido no debe contener la letra 'ñ' o caracteres especiales")
    private String apellido_empl;

    
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
    @NotEmpty(message="Debe de ingresar un rol")
    private String rol;
    
    @Column
    @NotEmpty(message="Debe de ingresar una contraseña")
    private String contrasena;
    
    //Constructor vacio
    public empleado(){}

    //Getters y setters
    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getNombre_empl() {return nombre_empl;}

    public void setNombre_empl(String nombre_empl) {this.nombre_empl = nombre_empl;}

    public String getApellido_empl() {return apellido_empl;}

    public void setApellido_empl(String apellido_empl) {this.apellido_empl = apellido_empl;}

    public String getDni() {return dni;}

    public void setDni(String dni) {this.dni = dni;}
    
    public String getTelefono() {return telefono;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getRol() {return rol;}

    public void setRol(String rol) {this.rol = rol;}

    public String getContrasena() {return contrasena;}

    public void setContrasena(String contrasena) {this.contrasena = contrasena;}

    

    

    

    
    
}
