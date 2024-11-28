package testing.repositorio;

import org.springframework.core.convert.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import testing.modelo.LugarEstacionamiento;

@Component
public class LugarEstacionamientoConverter implements Converter<String, LugarEstacionamiento> {
    @Autowired
    private LugarEstacionamientoRepositorio lugarEstacionamientoRepositorio;

    @Override
    public LugarEstacionamiento convert(String source) {
        try {
            return lugarEstacionamientoRepositorio.findById(Integer.parseInt(source))
                    .orElse(null); // Si no encuentra el lugar, retorna null
        } catch (NumberFormatException e) {
            return null; // Manejo de excepción si el valor no es un número válido
        }
}
}
