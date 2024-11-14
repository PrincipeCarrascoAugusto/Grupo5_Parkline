package testing.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws java.io.IOException {

        // Obtener el rol del usuario autenticado
        String role = authentication.getAuthorities().toString();

        // Redirigir según el rol
        if (role.contains("ROLE_admin") || role.contains("ROLE_empleado")) {
            // Redirigir a /api/dashboard para admin o empleado
            response.sendRedirect("/api/dashboard");
        } else if (role.contains("ROLE_cliente")) {
            // Redirigir a /parkline para cliente
            response.sendRedirect("/parkline");
        }
    }
}
