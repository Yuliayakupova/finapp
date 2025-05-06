package com.example.finapp.BoundedContext.Transaction.Request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for creating a new transaction.
 * Contains the required fields to be passed from the client to the backend.
 */
public class CreateTransactionRequest {
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;
    private Integer moneyboxId;
    private int categoryId;

    /**
     * Constructs a CreateTransactionRequest with all required fields.
     *
     * @param amount      the transaction amount
     * @param description the transaction description
     * @param createdAt   the time the transaction was created
     * @param moneyboxId  the ID of the associated moneybox (nullable)
     * @param categoryId  the category ID for this transaction
     */
    public CreateTransactionRequest(BigDecimal amount, String description, LocalDateTime createdAt, Integer moneyboxId, int categoryId) {
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
        this.moneyboxId = moneyboxId;
        this.categoryId = categoryId;
    }

    /**
     * Gets the transaction amount.
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the transaction amount.
     *
     * @param amount the new amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the description of the transaction.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the transaction.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the creation date and time of the transaction.
     *
     * @return the creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation date and time of the transaction.
     *
     * @param createdAt the new creation timestamp
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the associated moneybox ID.
     *
     * @return the moneybox ID or null if not applicable
     */
    public Integer getMoneyboxId() {
        return moneyboxId;
    }

    /**
     * Sets the associated moneybox ID.
     *
     * @param moneyboxId the new moneybox ID
     */
    public void setMoneyboxId(Integer moneyboxId) {
        this.moneyboxId = moneyboxId;
    }

    /**
     * Gets the category ID associated with this transaction.
     *
     * @return the category ID
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the category ID for this transaction.
     *
     * @param categoryId the new category ID
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
