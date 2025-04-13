package com.example.finapp.BoundedContext.Transaction.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdAt;
    private String category;

    public Transaction(Long id, BigDecimal amount, String description, LocalDateTime createdAt, String category) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
