package com.example.finapp.BoundedContext.AI.DTO;

/**
 * Data Transfer Object for financial advice responses.
 */
public class FinancialAdviceResponse {
    private String advice;

    public FinancialAdviceResponse(String advice) {
        this.advice = advice;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}