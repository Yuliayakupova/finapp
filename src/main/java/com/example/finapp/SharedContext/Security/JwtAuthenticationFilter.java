package com.example.finapp.SharedContext.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.Collections;

/**
 * JwtAuthenticationFilter is a filter that checks for a valid JWT in the request's Authorization header.
 * If a valid JWT is found, it authenticates the user and sets the authentication context for the request.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    /**
     * Constructor to initialize JwtAuthenticationFilter with the JwtService dependency.
     *
     * @param jwtService A service responsible for generating and validating JWT tokens.
     */
    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * This method filters the incoming HTTP request and performs the JWT authentication.
     * It checks if the Authorization header contains a Bearer token, validates it,
     * and sets the security context with the authenticated user.
     *
     * @param request The incoming HTTP request.
     * @param response The outgoing HTTP response.
     * @param filterChain The filter chain that allows the request to continue processing.
     * @throws ServletException If an error occurs during filter processing.
     * @throws IOException If an I/O error occurs during the request filtering.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Extract the Authorization header
        String authHeader = request.getHeader("Authorization");

        // Check if the header is present and starts with "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Extract the token from the header
            try {
                // Get user ID from the token using JwtService
                int userId = jwtService.getUserIdFromToken(token);
                System.out.println("User ID from token: " + userId);

                // Create an Authentication object and set it in the SecurityContext
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        userId,
                        null,
                        Collections.singletonList(new SimpleGrantedAuthority("USER")) // Set user role
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (Exception e) {
                // Handle exceptions (e.g., invalid token)
                System.out.println("Exception while processing token: " + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Unauthorized access
                return;
            }
        }

        // Continue the request filter chain
        filterChain.doFilter(request, response);
    }
}
