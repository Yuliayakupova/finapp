package com.example.finapp.BoundedContext.Moneybox.Request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdateMoneyboxRequest {
    private String name;
    private BigDecimal goal;
    private BigDecimal currentAmount;
    private LocalDate targetDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getGoal() {
        return goal;
    }

    public void setGoal(BigDecimal goal) {
        this.goal = goal;
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
