package com.example.finapp.BoundedContext.Budget.Controller;

import com.example.finapp.BoundedContext.Budget.DTO.Budget;
import com.example.finapp.BoundedContext.Budget.Repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/budget")
public class BudgetController {

    private final BudgetRepository budgetRepository;

    @Autowired
    public BudgetController(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @GetMapping
    public Budget getUserBudget() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = (int) authentication.getPrincipal();
        return budgetRepository.getUserBudget(userId);
    }
}