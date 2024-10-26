package testing.modelo;
//Librerias
import jakarta.persistence.*;

// La clase se vuelve una entidad gestionada por JPA y mapea la clase a la tabla "empleados" en la BD
@Entity
@Table(name="recompensas")
public class Recompensas {
    //Se define "ID_recom" como llave primaria
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer ID_recom;
    
    //Definimos columnas en la tabla asociadas a los campos de la clase
    @Column
    private String nom_recom;
    
    @Column
    private String descri_recom;
    
    @Column
    private String imagen;
    
    @Column
    private Integer puntos_necesarios;
    
    @Column
    private boolean activo;
    
    //Constructor vacio
    public Recompensas(){
        
    }
    
    //Getter y Setter

    public Integer getID_recom() {return ID_recom;}

    public void setID_recom(Integer ID_recom) {this.ID_recom = ID_recom;}

    public String getNom_recom() {return nom_recom;}

    public void setNom_recom(String nom_recom) {this.nom_recom = nom_recom;}

    public String getDescri_recom() {return descri_recom;}

    public void setDescri_recom(String descri_recom) {this.descri_recom = descri_recom;}

    public String getImagen() {return imagen;}

    public void setImagen(String imagen) {this.imagen = imagen;}

    public Integer getPuntos_necesarios() {return puntos_necesarios;}

    public void setPuntos_necesarios(Integer puntos_necesarios) {this.puntos_necesarios = puntos_necesarios;}

    public boolean isActivo() {return activo;}

    public void setActivo(boolean activo) {this.activo = activo;}

    
    
    
}
