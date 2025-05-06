package com.example.finapp.BoundedContext.Limit.Request;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * The CreateLimitRequest class is used to encapsulate the data needed to create a new limit.
 * It is used as the request body for creating a limit for a particular user and category.
 */
public class CreateLimitRequest {

    private BigDecimal maxAmount;  // The maximum allowed amount for the limit.
    private String period;         // The period of the limit (e.g., monthly, yearly).
    private LocalDate startDate;   // The start date of the limit.
    private int categoryId;        // The ID of the category associated with the limit.

    /**
     * Constructor to create a new CreateLimitRequest instance.
     *
     * @param maxAmount the maximum allowed amount for the limit.
     * @param period the period for the limit (e.g., "monthly", "yearly").
     * @param startDate the start date of the limit.
     * @param categoryId the ID of the category for which the limit is being set.
     */
    public CreateLimitRequest(BigDecimal maxAmount, String period, LocalDate startDate, int categoryId) {
        this.maxAmount = maxAmount;
        this.period = period;
        this.startDate = startDate;
        this.categoryId = categoryId;
    }

    // Getter and setter methods

    /**
     * Get the maximum allowed amount for the limit.
     *
     * @return the maxAmount.
     */
    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    /**
     * Set the maximum allowed amount for the limit.
     *
     * @param maxAmount the maxAmount to set.
     */
    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }

    /**
     * Get the period for the limit (e.g., "monthly", "yearly").
     *
     * @return the period.
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Set the period for the limit.
     *
     * @param period the period to set.
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * Get the start date of the limit.
     *
     * @return the startDate.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Set the start date of the limit.
     *
     * @param startDate the startDate to set.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Get the category ID associated with the limit.
     *
     * @return the categoryId.
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Set the category ID for the limit.
     *
     * @param categoryId the categoryId to set.
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
