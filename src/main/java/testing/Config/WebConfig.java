package testing.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import testing.repositorio.LugarEstacionamientoConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LugarEstacionamientoConverter lugarEstacionamientoConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(lugarEstacionamientoConverter);
    }
}
