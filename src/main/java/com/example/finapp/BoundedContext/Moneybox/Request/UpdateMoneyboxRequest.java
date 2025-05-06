package com.example.finapp.BoundedContext.Moneybox.Request;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * UpdateMoneyboxRequest is a data transfer object (DTO) used to encapsulate
 * the input data required to update an existing moneybox.
 * <p>
 * It includes updated values for the name, target amount, current amount,
 * and target date of the moneybox.
 */
public class UpdateMoneyboxRequest {

    /** The updated name of the moneybox. */
    private String name;

    /** The updated target amount the user wants to save. */
    private BigDecimal targetAmount;

    /** The updated current amount already saved in the moneybox. */
    private BigDecimal currentAmount;

    /** The updated target date for reaching the savings goal. */
    private LocalDate targetDate;

    /**
     * Gets the updated name of the moneybox.
     *
     * @return the updated name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the updated name of the moneybox.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the updated target amount for the moneybox.
     *
     * @return the updated target amount
     */
    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    /**
     * Sets the updated target amount for the moneybox.
     *
     * @param targetAmount the amount to set
     */
    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

    /**
     * Gets the updated current amount saved in the moneybox.
     *
     * @return the updated current amount
     */
    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    /**
     * Sets the updated current amount saved in the moneybox.
     *
     * @param currentAmount the amount to set
     */
    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    /**
     * Gets the updated target date for reaching the savings goal.
     *
     * @return the updated target date
     */
    public LocalDate getTargetDate() {
        return targetDate;
    }

    /**
     * Sets the updated target date for reaching the savings goal.
     *
     * @param targetDate the date to set
     */
    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
}
