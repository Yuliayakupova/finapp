package com.example.finapp.BoundedContext.Category.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The CategoryWithLimitDTO class represents a category with its associated limit data.
 * It holds details about the category such as its unique identifier (id), name, type, and limit details.
 */
public class CategoryWithLimitDTO {

    // Category details
    private int categoryId;
    private String name;
    private String type;

    // Limit details
    private int limitId;
    private BigDecimal maxAmount;
    private BigDecimal usedAmount;
    private String period;
    private LocalDate startDate;

    // Getters and Setters for Category fields
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getters and Setters for Limit fields
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

    public BigDecimal getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(BigDecimal usedAmount) {
        this.usedAmount = usedAmount;
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
}