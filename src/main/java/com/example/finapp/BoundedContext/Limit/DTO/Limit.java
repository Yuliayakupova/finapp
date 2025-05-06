package com.example.finapp.BoundedContext.Limit.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Limit {
    private int limitId;
    private BigDecimal maxAmount;
    private BigDecimal remainingAmount;
    private String period;
    private LocalDate startDate;
    private int categoryId;
    private int userId;

    public Limit(int limitId, BigDecimal maxAmount, BigDecimal remainingAmount, String period, LocalDate startDate, int categoryId, int userId) {
        this.limitId = limitId;
        this.maxAmount = maxAmount;
        this.remainingAmount = remainingAmount;
        this.period = period;
        this.startDate = startDate;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public Limit() {

    }

    public int getLimitId() {
        return limitId;
    }

    public void setLimitId(int limitId) {
        this.limitId = limitId;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
