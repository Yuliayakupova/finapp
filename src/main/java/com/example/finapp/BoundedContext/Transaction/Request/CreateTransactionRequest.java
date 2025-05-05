package com.example.finapp.BoundedContext.Transaction.Request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateTransactionRequest {
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;
    private Integer moneyboxId;
    private int categoryId;

    public CreateTransactionRequest(BigDecimal amount, String description, LocalDateTime createdAt, Integer moneyboxId, int categoryId) {
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
        this.moneyboxId = moneyboxId;
        this.categoryId = categoryId;
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

    public Integer getMoneyboxId() {
        return moneyboxId;
    }

    public void setMoneyboxId(Integer moneyboxId) {
        this.moneyboxId = moneyboxId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
