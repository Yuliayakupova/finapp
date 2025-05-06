package com.example.finapp.SharedContext.Security;

/**
 * JwtResponse is a DTO (Data Transfer Object) that represents the structure of the response
 * containing a JWT token.
 * This class is used when a user successfully logs in and the backend returns a JWT token to
 * be used for authenticating further requests.
 */
public class JwtResponse {

    private String token;

    /**
     * Constructor to initialize the JwtResponse with the given token.
     *
     * @param token The JWT token to be returned to the client.
     */
    public JwtResponse(String token) {
        this.token = token;
    }

    /**
     * Gets the JWT token.
     *
     * @return The JWT token.
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the JWT token.
     *
     * @param token The JWT token to set.
     */
    public void setToken(String token) {
        this.token = token;
    }
}
