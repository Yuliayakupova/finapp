package com.example.finapp.BoundedContext.Transaction.Request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for updating an existing transaction.
 * Contains fields that can be modified by the user.
 */
public class UpdateTransactionRequest {
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;
    private String category;

    /**
     * Constructs an UpdateTransactionRequest with all modifiable fields.
     *
     * @param amount      the new amount for the transaction
     * @param description the new description
     * @param createdAt   the new creation timestamp
     * @param category    the new category (as a string)
     */
    public UpdateTransactionRequest(BigDecimal amount, String description, LocalDateTime createdAt, String category) {
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
        this.category = category;
    }

    /**
     * Gets the new amount for the transaction.
     *
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the new amount for the transaction.
     *
     * @param amount the new amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Gets the new description for the transaction.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the new description for the transaction.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the new creation timestamp.
     *
     * @return the creation timestamp
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the new creation timestamp.
     *
     * @param createdAt the new timestamp
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gets the new category as a string.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the new category as a string.
     *
     * @param category the new category
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
