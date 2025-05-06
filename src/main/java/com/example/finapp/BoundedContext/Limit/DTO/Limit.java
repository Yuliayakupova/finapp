package com.example.finapp.BoundedContext.Limit.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The Limit class represents a spending limit for a specific category within a given period.
 * It holds details about the limit's amount, period, and the associated user and category.
 */
public class Limit {

    private int limitId;           // Unique identifier for the limit
    private BigDecimal maxAmount;  // Maximum allowed spending amount
    private BigDecimal remainingAmount; // Remaining amount that can be spent
    private String period;         // Period for the limit (e.g., "monthly", "yearly")
    private LocalDate startDate;   // Start date of the limit
    private int categoryId;        // ID of the category to which this limit applies
    private int userId;            // ID of the user to whom this limit belongs

    /**
     * Constructs a new Limit object with the specified parameters.
     *
     * @param limitId the unique identifier for the limit.
     * @param maxAmount the maximum allowed amount for the limit.
     * @param remainingAmount the remaining amount that can be spent.
     * @param period the period for which the limit applies.
     * @param startDate the start date of the limit.
     * @param categoryId the ID of the category this limit applies to.
     * @param userId the ID of the user who owns the limit.
     */
    public Limit(int limitId, BigDecimal maxAmount, BigDecimal remainingAmount, String period, LocalDate startDate, int categoryId, int userId) {
        this.limitId = limitId;
        this.maxAmount = maxAmount;
        this.remainingAmount = remainingAmount;
        this.period = period;
        this.startDate = startDate;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    /**
     * Default constructor for Limit.
     */
    public Limit() {
    }

    /**
     * Gets the unique identifier for the limit.
     *
     * @return the unique identifier for the limit.
     */
    public int getLimitId() {
        return limitId;
    }

    /**
     * Sets the unique identifier for the limit.
     *
     * @param limitId the unique identifier for the limit.
     */
    public void setLimitId(int limitId) {
        this.limitId = limitId;
    }

    /**
     * Gets the maximum allowed spending amount for the limit.
     *
     * @return the maximum allowed spending amount.
     */
    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    /**
     * Sets the maximum allowed spending amount for the limit.
     *
     * @param maxAmount the maximum allowed spending amount.
     */
    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    /**
     * Gets the remaining amount that can be spent under this limit.
     *
     * @return the remaining amount that can be spent.
     */
    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    /**
     * Sets the remaining amount that can be spent under this limit.
     *
     * @param remainingAmount the remaining amount that can be spent.
     */
    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    /**
     * Gets the period for which the limit is valid.
     *
     * @return the period of the limit.
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Sets the period for which the limit is valid.
     *
     * @param period the period of the limit.
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * Gets the start date of the limit.
     *
     * @return the start date of the limit.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Sets the start date of the limit.
     *
     * @param startDate the start date of the limit.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets the ID of the category to which this limit applies.
     *
     * @return the category ID.
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the ID of the category to which this limit applies.
     *
     * @param categoryId the category ID.
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Gets the ID of the user who owns this limit.
     *
     * @return the user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user who owns this limit.
     *
     * @param userId the user ID.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
