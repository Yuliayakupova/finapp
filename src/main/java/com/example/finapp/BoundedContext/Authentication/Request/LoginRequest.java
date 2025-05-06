package com.example.finapp.BoundedContext.Authentication.Request;

/**
 * A request class that holds the data required for user login.
 * Contains the user's email and password.
 */
public class LoginRequest {

    private String email;
    private String password;

    /**
     * Constructor to create a new LoginRequest with email and password.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     */
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
