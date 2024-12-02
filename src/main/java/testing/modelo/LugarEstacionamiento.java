package testing.modelo;

//Librerias
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

//Anotaciones
@Entity
@Table(name = "lugares_estacionamientos") //Conexion con la tabla de la BD
public class LugarEstacionamiento {
    
    //Columnas de la tabla
    //Excepciones a la hora de ingresar datos
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_lugar;
    
    
    @Column
    @NotEmpty(message = "Debe de ingresar un nombre para el lugar del estacionamiento")
    @Size(min = 2, max = 4, message = "El nombre debe tener entre 2 a 4 caracteres")
    private String nom;
    
    @Column
    @NotEmpty(message = "Debe de ingresar un nombre para el estado del estacionamiento")
    private String estado;
    
    
    //Constructor vacio
    public LugarEstacionamiento(){}

    
    
    
    //Getters and Setter
    
    public Integer getId_lugar() {return id_lugar;}

    public void setId_lugar(Integer id_lugar) {this.id_lugar = id_lugar;}



    public String getEstado() {return estado;}

    public void setEstado(String estado) {this.estado = estado;}

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}
    
    
    
    
    
}
