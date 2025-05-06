package com.example.finapp.BoundedContext.Authentication.Request;

/**
 * A request class that holds the data required for user registration.
 * Contains the user's email, password, and name.
 */
public class CreateUserRequest {
    private String email;
    private String password;
    private String name;

    /**
     * Constructor to create a new CreateUserRequest with email, password, and name.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @param name The name of the user.
     */
    public CreateUserRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
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

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }
}