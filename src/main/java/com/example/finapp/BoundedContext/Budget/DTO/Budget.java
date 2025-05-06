package com.example.finapp.BoundedContext.Budget.DTO;

import java.math.BigDecimal;

/**
 * Represents the budget data for a user.
 * Currently, it contains only the total balance.
 * Used as a Data Transfer Object (DTO) to return budget information.
 */
public class Budget {

    /**
     * The total financial balance for the user.
     */
    private BigDecimal totalBalance;

    /**
     * Constructs a new Budget instance with the specified total balance.
     *
     * @param totalBalance the user's total balance
     */
    public Budget(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    /**
     * Returns the total balance of the user.
     *
     * @return the total balance
     */
    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    /**
     * Sets the total balance for the user.
     *
     * @param totalBalance the new total balance
     */
    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }
}
