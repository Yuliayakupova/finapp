package com.example.finapp.BoundedContext.Moneybox.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Moneybox {
    private int id;                // The unique identifier for the moneybox.
    private String name;           // The name of the moneybox (e.g., "Vacation Fund").
    private BigDecimal targetAmount;  // The target amount the user wants to save.
    private BigDecimal currentAmount; // The current amount the user has saved.
    private LocalDate targetDate;    // The date by which the user wants to reach the target amount.
    private int userId;             // The ID of the user who owns this moneybox.

    /**
     * Constructor to create a new Moneybox instance.
     *
     * @param id the unique identifier for the moneybox.
     * @param name the name of the moneybox.
     * @param targetAmount the target amount to save.
     * @param currentAmount the current amount saved.
     * @param targetDate the target date for saving the amount.
     * @param userId the ID of the user who owns this moneybox.
     */
    public Moneybox(int id, String name, BigDecimal targetAmount, BigDecimal currentAmount, LocalDate targetDate, int userId) {
        this.id = id;
        this.name = name;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.targetDate = targetDate;
        this.userId = userId;
    }

    // Getter and setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
