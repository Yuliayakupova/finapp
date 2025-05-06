package com.example.finapp.SharedContext.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfig is responsible for configuring Spring Security settings for the application.
 * It sets up HTTP security, password encoding, and custom JWT-based authentication filter.
 */
@Configuration
public class SecurityConfig {

    // The JWT authentication filter, which intercepts requests to check for JWT tokens.
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * Constructor to inject the JWT authentication filter.
     *
     * @param jwtAuthenticationFilter The custom JWT filter to handle authentication.
     */
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * Defines the password encoder bean.
     * BCrypt is used to hash passwords securely.
     *
     * @return A new instance of BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Return a password encoder that uses BCrypt hashing.
    }

    /**
     * Configures the security filter chain for the application.
     * This method configures HTTP security settings, including authentication and authorization.
     *
     * @param http The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain.
     * @throws Exception If any errors occur while configuring security.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection (because we are using JWT-based authentication).
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/v1/authentication/**").permitAll() // Permit all requests to authentication endpoints.
                        .anyRequest().authenticated() // Require authentication for any other request.
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add our custom JWT filter before the UsernamePasswordAuthenticationFilter.

        return http.build(); // Build and return the security configuration.
    }
}
