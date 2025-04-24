package com.example.finapp.BoundedContext.UserManagment.Request;

public class UpdateUserRequest {
    private String email;
    private String password;
    private String name;
    private boolean isAuthenticated;

    public UpdateUserRequest(String email, String password, String name, boolean isAuthenticated) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.isAuthenticated = isAuthenticated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }
}
