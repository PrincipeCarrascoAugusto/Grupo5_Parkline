package testing.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TokenRecuperacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String token;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false) // Relación con la tabla Usuarios
    private Usuarios usuario;

    @Column(name = "fecha_creacion", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_expiracion")
    private LocalDateTime fechaExpiracion;

    // Constructor por defecto
    public TokenRecuperacion() {
    }

    // Constructor con parámetros: usuario ya debe ser un objeto Usuarios
    public TokenRecuperacion(String token, Usuarios usuario, LocalDateTime fechaExpiracion) {
        this.token = token;
        this.usuario = usuario;  // El usuario ahora se pasa como objeto completo
        this.fechaCreacion = LocalDateTime.now();
        this.fechaExpiracion = fechaExpiracion;
    }

    // Getter y Setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }
}
