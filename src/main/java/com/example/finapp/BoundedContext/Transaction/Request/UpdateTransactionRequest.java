package com.example.finapp.BoundedContext.Transaction.Request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UpdateTransactionRequest {
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;
    private String category;

    public UpdateTransactionRequest(BigDecimal amount, String description, LocalDateTime createdAt, String category) {
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
