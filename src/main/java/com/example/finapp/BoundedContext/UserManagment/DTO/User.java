package com.example.finapp.BoundedContext.UserManagment.DTO;

import java.time.LocalDate;

/**
 * Data Transfer Object representing a user in the system.
 * Contains basic user information and authentication status.
 */
public class User {
    private int userId;
    private String email;
    private String password;
    private String name;
    private LocalDate registrationDate;
    private boolean isAuthenticated;

    /**
     * Constructs a User object with the specified details.
     *
     * @param userId            unique identifier of the user
     * @param email             user's email address
     * @param password          user's hashed password
     * @param name              user's full name
     * @param registrationDate  the date the user registered
     * @param isAuthenticated   whether the user is currently authenticated
     */
    public User(int userId, String email, String password, String name, LocalDate registrationDate, boolean isAuthenticated) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.registrationDate = registrationDate;
        this.isAuthenticated = isAuthenticated;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     *
     * @param userId the new user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the user's email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email the new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password (hashed).
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password (hashed).
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the registration date.
     *
     * @return the registration date
     */
    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Sets the registration date.
     *
     * @param registrationDate the new registration date
     */
    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * Checks whether the user is authenticated.
     *
     * @return true if authenticated, false otherwise
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    /**
     * Sets the authentication status.
     *
     * @param authenticated the new authentication status
     */
    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }
}
