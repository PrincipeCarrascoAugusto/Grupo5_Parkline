package testing.repositorio;


import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import testing.modelo.LugarEstacionamiento;
import testing.modelo.Reserva;


public interface ReservaRepositorio extends JpaRepository<Reserva, Integer> {
    // Actualiza el campo 'espacio' en base al 'nom' de LugarEstacionamiento
    @Modifying
    @Query("UPDATE Reserva r SET r.espacio = (SELECT l.nom FROM LugarEstacionamiento l WHERE l.id_lugar = r.lugarEstacionamiento.id_lugar) WHERE r.id = :id")
    void actualizarEspacio(@Param("id") Integer id);
    
     // Método para obtener todas las reservas para un lugar en una fecha determinada
    @Query("SELECT r FROM Reserva r WHERE r.lugarEstacionamiento = :lugar AND r.fecha = :fecha")
    List<Reserva> findByLugarEstacionamientoAndFecha(@Param("lugar") LugarEstacionamiento lugar, @Param("fecha") LocalDate fecha);
}
