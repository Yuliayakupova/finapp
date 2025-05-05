package com.example.finapp.BoundedContext.Limit.Request;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CreateLimitRequest {
    private BigDecimal maxAmount;
    private String period;
    private LocalDate startDate;
    private int categoryId;

    public CreateLimitRequest(BigDecimal maxAmount, String period, LocalDate startDate, int categoryId) {
        this.maxAmount = maxAmount;
        this.period = period;
        this.startDate = startDate;
        this.categoryId = categoryId;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
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
}
