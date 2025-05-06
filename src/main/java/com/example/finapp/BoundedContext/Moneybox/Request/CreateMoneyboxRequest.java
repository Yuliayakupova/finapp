package com.example.finapp.BoundedContext.Moneybox.Request;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * CreateMoneyboxRequest is a data transfer object (DTO) used to encapsulate
 * the input data required to create a new moneybox.
 * <p>
 * It contains the name of the moneybox, the target savings amount,
 * the current amount already saved, and the target completion date.
 */
public class CreateMoneyboxRequest {

    /** The name of the moneybox. */
    private String name;

    /** The target amount the user wants to save. */
    private BigDecimal targetAmount;

    /** The current amount already saved in the moneybox. */
    private BigDecimal currentAmount;

    /** The target date by which the user aims to reach the savings goal. */
    private LocalDate targetDate;

    /**
     * Gets the name of the moneybox.
     *
     * @return the name of the moneybox
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the moneybox.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the target amount for the moneybox.
     *
     * @return the target savings amount
     */
    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    /**
     * Sets the target amount for the moneybox.
     *
     * @param targetAmount the amount to set
     */
    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

    /**
     * Gets the current amount saved in the moneybox.
     *
     * @return the current amount
     */
    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    /**
     * Sets the current amount saved in the moneybox.
     *
     * @param currentAmount the amount to set
     */
    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    /**
     * Gets the target date for reaching the savings goal.
     *
     * @return the target date
     */
    public LocalDate getTargetDate() {
        return targetDate;
    }

    /**
     * Sets the target date for reaching the savings goal.
     *
     * @param targetDate the date to set
     */
    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }
}
