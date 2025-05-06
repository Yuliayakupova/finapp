package com.example.finapp.BoundedContext.Moneybox.Request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AddTransactionRequest {
    private BigDecimal amount;
    private String description;
    private LocalDate date;

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}