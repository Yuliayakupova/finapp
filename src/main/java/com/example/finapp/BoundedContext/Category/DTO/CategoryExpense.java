package com.example.finapp.BoundedContext.Category.DTO;

import java.math.BigDecimal;

public class CategoryExpense {
    private String categoryName;
    private BigDecimal amountSpent;

    public CategoryExpense(String categoryName, BigDecimal amountSpent) {
        this.categoryName = categoryName;
        this.amountSpent = amountSpent;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public BigDecimal getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(BigDecimal amountSpent) {
        this.amountSpent = amountSpent;
    }
}
