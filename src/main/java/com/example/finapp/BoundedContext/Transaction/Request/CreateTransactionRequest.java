package com.example.finapp.BoundedContext.Transaction.Request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateTransactionRequest {
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;
    private String category;
    private Integer moneyboxId;
    private Integer categoryId;

    public CreateTransactionRequest(BigDecimal amount, String description, LocalDateTime createdAt, String category, Integer moneyboxId, Integer categoryId) {
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getMoneyboxId() {
        return moneyboxId;
    }

    public void setMoneyboxId(Integer moneyboxId) {
        this.moneyboxId = moneyboxId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
