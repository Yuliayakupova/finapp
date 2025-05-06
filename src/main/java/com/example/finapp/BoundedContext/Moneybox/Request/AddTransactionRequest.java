package com.example.finapp.BoundedContext.Moneybox.Request;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * AddTransactionRequest is a data transfer object (DTO) used to encapsulate
 * the information required to add a transaction to a moneybox.
 * <p>
 * This includes the amount of money, a textual description, and the transaction date.
 */
public class AddTransactionRequest {

    /** The monetary amount of the transaction. */
    private BigDecimal amount;

    /** A brief description of the transaction. */
    private String description;

    /** The date the transaction took place. */
    private LocalDate date;

    /**
     * Gets the amount of the transaction.
     *
     * @return the transaction amount
     */
    public BigDecimal getAmount() { return amount; }

    /**
     * Sets the amount of the transaction.
     *
     * @param amount the amount to set
     */
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    /**
     * Gets the description of the transaction.
     *
     * @return the transaction description
     */
    public String getDescription() { return description; }

    /**
     * Sets the description of the transaction.
     *
     * @param description the description to set
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Gets the date of the transaction.
     *
     * @return the transaction date
     */
    public LocalDate getDate() { return date; }

    /**
     * Sets the date of the transaction.
     *
     * @param date the date to set
     */
    public void setDate(LocalDate date) { this.date = date; }
}
