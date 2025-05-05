package com.example.finapp.BoundedContext.UserManagment.DTO;

import java.time.LocalDate;

public class User {
    private int userId;
    private String email;
    private String password;
    private String name;
    private LocalDate registrationDate;
    private boolean isAuthenticated;

    public User(int userId, String email, String password, String name, LocalDate registrationDate, boolean isAuthenticated) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.registrationDate = registrationDate;
        this.isAuthenticated = isAuthenticated;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }
}
