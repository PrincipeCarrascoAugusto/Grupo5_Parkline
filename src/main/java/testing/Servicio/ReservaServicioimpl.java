package testing.Servicio;

//Librerias
import java.time.LocalDate;
import java.time.LocalTime;
import testing.modelo.Reserva;
import testing.DAO.ReservaDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testing.modelo.LugarEstacionamiento;
import testing.repositorio.LugarEstacionamientoRepositorio;
import testing.repositorio.ReservaRepositorio;


//La clase se marca como un servicio gestionada por Spring
@Service
public class ReservaServicioimpl implements ReservaService{
    //Inyección de dependencias para usar el DAO
    @Autowired
    private ReservaDAO resDAO;
    
    @Autowired
    private ReservaRepositorio reservaRepositorio;

    @Autowired
    private LugarEstacionamientoRepositorio lugarEstacionamientoRepositorio;
    
    // Método para verificar si hay solapamiento en las reservas para un lugar en una fecha específica
    public boolean verificarSolapamientoReserva(LugarEstacionamiento lugar, LocalTime horaEntrada, LocalTime horaSalida, LocalDate fecha) {
        // Obtener todas las reservas para el lugar en la fecha específica
        List<Reserva> reservas = reservaRepositorio.findByLugarEstacionamientoAndFecha(lugar, fecha);

        for (Reserva reserva : reservas) {
            // Verificar si las horas se solapan
            if ((horaEntrada.isBefore(reserva.getHora_salida()) && horaSalida.isAfter(reserva.getHora_entrada()))) {
                return true; // Hay un solapamiento
            }
        }
        return false; // No hay solapamiento
    }
    
    
    
    
    
    
    
    
    //Asegura que esta operación esté dentro de una transacción
    @Transactional
    //Obtiene la lista de reservas de la BD
    @Override
    public List<Reserva> get() {return resDAO.get();}
    
    @Transactional
    //Método para obtener una reserva por su ID
    @Override
    public Reserva get(int ID) {return resDAO.get(ID);}
    
    @Transactional
    //Método para guardar una nueva reserva
    @Override
    public void save(Reserva res) {
    // Verificar si el lugar está disponible en las horas solicitadas
        boolean haySolapamiento = verificarSolapamientoReserva(res.getLugarEstacionamiento(), res.getHora_entrada(), res.getHora_salida(), res.getFecha());

        if (haySolapamiento) {
            throw new IllegalArgumentException("El espacio de estacionamiento ya está reservado en el horario solicitado.");
        }

        // Guardar la reserva
        resDAO.save(res);

        // Marcar el lugar como ocupado o reservado
        LugarEstacionamiento lugar = res.getLugarEstacionamiento();
        lugar.setEstado("Reservado"); // Cambiar estado a "Reservado"
        lugarEstacionamientoRepositorio.save(lugar); // Guardar los cambios del lugar}
    }
    
    
    
    
    
    
    
//    public void save(Reserva res) {resDAO.save(res);}
    
    
    
    
    
    
    
    @Transactional
    //Método para actualizar una reserva existente
    @Override
    public void update(Reserva res) {resDAO.update(res);}
    
    @Transactional
    //Método para eliminar una reserva por su ID
    @Override
    public void delete(int ID) {resDAO.delete(ID);}
    
}
