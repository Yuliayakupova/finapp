package com.example.finapp.SharedContext.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

/**
 * JwtService is a service that handles the generation and validation of JSON Web Tokens (JWT).
 * This class provides methods for creating JWTs for authenticated users and extracting user IDs
 * from the token.
 */
@Service
public class JwtService {

    // Secret key used to sign the JWT. In production, this should be stored securely.
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Expiration time for the token in milliseconds (1 day)
    private final long expirationMillis = 1000 * 60 * 60 * 24;

    /**
     * Generates a JWT token for a given user ID.
     *
     * @param userId The ID of the user for whom the token is generated.
     * @return The generated JWT token.
     */
    public String generateToken(int userId) {
        // Current time
        Date now = new Date();
        // Expiration time calculated by adding the expirationMillis to the current time
        Date expiryDate = new Date(now.getTime() + expirationMillis);

        // Build the JWT with subject (userId), issuedAt, and expiration date
        return Jwts.builder()
                .setSubject(String.valueOf(userId))  // Set the subject of the token as the user ID
                .setIssuedAt(now)                   // Set the token's issued time
                .setExpiration(expiryDate)          // Set the expiration time for the token
                .signWith(key)                      // Sign the token with the secret key
                .compact();                         // Return the compacted JWT string
    }

    /**
     * Extracts the user ID from the given JWT token.
     *
     * @param token The JWT token from which the user ID is extracted.
     * @return The user ID extracted from the token.
     */
    public int getUserIdFromToken(String token) {
        // Parse the JWT token, extract the subject (which is the user ID), and return it as an integer
        return Integer.parseInt(Jwts.parserBuilder()
                .setSigningKey(key)  // Validate the token using the same secret key used to sign it
                .build()
                .parseClaimsJws(token)  // Parse the token's claims
                .getBody()
                .getSubject());  // Extract the subject, which is the user ID
    }
}
