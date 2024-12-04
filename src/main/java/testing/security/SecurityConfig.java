package testing.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator").permitAll()  // Permitir acceso sin autenticación a /actuator
                .requestMatchers("/actuator/prometheus").permitAll()  // Permitir acceso sin autenticación a /actuator/prometheus
                .requestMatchers("/parkline").permitAll()
                .requestMatchers("/Nosotros").permitAll()
                .requestMatchers("/Recompensas").permitAll()
                .requestMatchers("/css/**", "/img/**", "/js/**", "/html/**").permitAll()
                .requestMatchers("/registro").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/recuperar").permitAll()  
                .requestMatchers("/restablecer").permitAll()
                .requestMatchers("/verificar-token").permitAll()    
                .requestMatchers("/api/**").hasAnyRole("admin", "empleado")
                .requestMatchers("/logout").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(customAuthenticationSuccessHandler()) // Configuramos el handler personalizado
            )
            .logout(config -> config.logoutSuccessUrl("/login"))
            .build();           
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    // Definimos el AuthenticationSuccessHandler personalizado
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                Authentication authentication) throws IOException, ServletException {
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                for (GrantedAuthority authority : authorities) {
                    if (authority.getAuthority().equals("ROLE_admin")) {
                        response.sendRedirect("/api/dashboard");
                        return;
                    } else if (authority.getAuthority().equals("ROLE_cliente")) {
                        response.sendRedirect("/parkline");
                        return;
                    }
                }
                // Redirige a una página predeterminada si no tiene roles específicos
                response.sendRedirect("/default");
            }
        };
    }
    
}
