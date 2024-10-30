package testing.modelo;

//Librerias
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

// La clase se vuelve una entidad gestionada por JPA y mapea la clase a la tabla "empleados" en la BD
@Entity
@Table(name="reservas")
public class Reserva {
    //Se define "ID" como llave primaria
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer ID;
    
    //Definimos columnas en la tabla asociadas a los campos de la clase
    @Column
    private String usuario;
    @Column
    private String placa;
    @Column
    private LocalTime hora_entrada;
    @Column
    private LocalTime hora_salida;
    @Column
    private String espacio;
    @Column
    private Double pago;
    @Column
    private LocalDate fecha;
    
    //Constructor
    public Reserva(){
        this.fecha = LocalDate.now();
    }
    
    //Getter y setter
    public Integer getID() {return ID;}

    public void setID(Integer ID) {this.ID = ID;}

    public String getUsuario() {return usuario;}

    public void setUsuario(String usuario) {this.usuario = usuario;}

    public String getPlaca() {return placa;}

    public void setPlaca(String placa) {this.placa = placa;}

    public LocalTime getHora_entrada() {return hora_entrada;}

    public void setHora_entrada(LocalTime hora_entrada) {this.hora_entrada = hora_entrada;}

    public LocalTime getHora_salida() {return hora_salida;}

    public void setHora_salida(LocalTime hora_salida) {this.hora_salida = hora_salida;}

    public String getEspacio() {return espacio;}

    public void setEspacio(String espacio) {this.espacio = espacio;}

    public Double getPago() {return pago;}

    public void setPago(Double pago) {this.pago = pago;}

    public LocalDate getFecha() {return fecha;}

    public void setFecha(LocalDate fecha) {this.fecha = fecha;}

}
