package com.example.finapp.BoundedContext.Transaction.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a financial transaction associated with a user.
 * Includes information such as amount, description, timestamp, category, and moneybox reference.
 */
public class Transaction {
    private int id;
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;
    private int category;
    private int moneyboxId;

    /**
     * Constructs a Transaction object with specified attributes.
     *
     * @param id          the unique identifier of the transaction
     * @param amount      the amount of money involved in the transaction
     * @param description a textual description of the transaction
     * @param createdAt   the timestamp when the transaction was created
     * @param category    the ID of the category this transaction belongs to
     * @param moneyboxId  the ID of the associated moneybox, if any
     */
    public Transaction(int id, BigDecimal amount, String description, LocalDateTime createdAt, int category, int moneyboxId) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
        this.category = category;
        this.moneyboxId = moneyboxId;
    }

    /** @return the transaction ID */
    public int getId() {
        return id;
    }

    /** @param id the transaction ID to set */
    public void setId(int id) {
        this.id = id;
    }

    /** @return the amount of the transaction */
    public BigDecimal getAmount() {
        return amount;
    }

    /** @param amount the amount to set */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /** @return the description of the transaction */
    public String getDescription() {
        return description;
    }

    /** @param description the description to set */
    public void setDescription(String description) {
        this.description = description;
    }

    /** @return the timestamp when the transaction was created */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /** @param createdAt the creation timestamp to set */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /** @return the category ID of the transaction */
    public int getCategory() {
        return category;
    }

    /** @param category the category ID to set */
    public void setCategory(int category) {
        this.category = category;
    }

    /** @return the associated moneybox ID, or 0 if none */
    public int getMoneyboxId() {
        return moneyboxId;
    }

    /** @param moneyboxId the moneybox ID to associate with the transaction */
    public void setMoneyboxId(int moneyboxId) {
        this.moneyboxId = moneyboxId;
    }
}
