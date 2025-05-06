package com.example.finapp.BoundedContext.Moneybox.Request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateMoneyboxRequest {
    private String name;
    private BigDecimal targetAmount; // Переименовал goal в targetAmount для согласованности
    private BigDecimal currentAmount;
    private LocalDate targetDate;

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
}
