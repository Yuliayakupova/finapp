package com.example.finapp.BoundedContext.Budget.DTO;

import java.math.BigDecimal;

public class Budget {
    private BigDecimal totalBalance;

    public Budget(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }
}