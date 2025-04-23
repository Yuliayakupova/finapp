package com.example.finapp.BoundedContext.Moneybox.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Moneybox {
    private Long id;
    private String name;
    private BigDecimal goal;
    private BigDecimal currentAmount;
    private LocalDate targetDate;

    public Moneybox(Long id, String name, BigDecimal goal, BigDecimal currentAmount, LocalDate targetDate) {
        this.id = id;
        this.name = name;
        this.goal = goal;
        this.currentAmount = currentAmount;
        this.targetDate = targetDate;
    }

    public Moneybox() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

