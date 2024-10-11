package testing.modelo;
import jakarta.persistence.*;

@Entity
@Table(name="empleados")
public class empleado {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String nombre_empl;
    @Column
    private String apellido_empl;
    @Column
    private Integer dni;
    @Column
    private String rol;
    @Column
    private Integer contrasena;
    
    public empleado(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre_empl() {
        return nombre_empl;
    }

    public void setNombre_empl(String nombre_empl) {
        this.nombre_empl = nombre_empl;
    }

    public String getApellido_empl() {
        return apellido_empl;
    }

    public void setApellido_empl(String apellido_empl) {
        this.apellido_empl = apellido_empl;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Integer getContrasena() {
        return contrasena;
    }

    public void setContrasena(Integer contrasena) {
        this.contrasena = contrasena;
    }
    
}
