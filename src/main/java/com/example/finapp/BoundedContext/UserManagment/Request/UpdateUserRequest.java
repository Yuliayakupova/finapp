package com.example.finapp.BoundedContext.UserManagment.Request;

/**
 * Request object for updating an existing user.
 * Contains the fields that can be modified when updating a user.
 */
public class UpdateUserRequest {
    private int id;
    private String email;
    private String password;
    private String name;
    private boolean isAuthenticated;

    /**
     * Constructs an UpdateUserRequest with the specified user details.
     *
     * @param email the user's email
     * @param password the user's password
     * @param name the user's name
     * @param isAuthenticated indicates whether the user is authenticated
     */
    public UpdateUserRequest(int id, String email, String password, String name, boolean isAuthenticated) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.isAuthenticated = isAuthenticated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the user's email.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email the new email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the new password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's name.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    /**
     * Sets the authentication status of the user.
     *
     * @param authenticated the new authentication status of the user
     */
    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }
}
