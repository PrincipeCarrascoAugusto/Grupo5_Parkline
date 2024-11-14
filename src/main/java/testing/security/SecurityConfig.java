package testing.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    // Inyectar el CustomAuthenticationSuccessHandler
    public SecurityConfig(CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/parkline").permitAll()
                .requestMatchers("/Nosotros").permitAll()
                .requestMatchers("/Recompensas").permitAll()
                .requestMatchers("/css/**", "/img/**", "/js/**", "/html/**").permitAll()
                .requestMatchers("/registro").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/api/**").hasAnyRole("admin", "empleado")
                .requestMatchers("/logout").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .successHandler(customAuthenticationSuccessHandler) // Usar el handler personalizado
            )
            .logout(config -> config.logoutSuccessUrl("/login"))
            .build();           
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
