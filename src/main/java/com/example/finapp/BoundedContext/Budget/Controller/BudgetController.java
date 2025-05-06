package com.example.finapp.BoundedContext.Budget.Controller;

import com.example.finapp.BoundedContext.Budget.DTO.Budget;
import com.example.finapp.BoundedContext.Budget.Repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BudgetController handles the API requests related to the user's budget.
 * It fetches the user's budget based on the authenticated user's ID.
 */
@RestController
@RequestMapping("/api/v1/budget")
public class BudgetController {

    private final BudgetRepository budgetRepository;

    /**
     * Constructor to inject the BudgetRepository dependency.
     *
     * @param budgetRepository The repository used to access the user's budget.
     */
    @Autowired
    public BudgetController(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    /**
     * Endpoint to get the budget for the authenticated user.
     * The user ID is fetched from the authentication token.
     *
     * @return The budget details of the authenticated user.
     */
    @GetMapping
    public Budget getUserBudget() {
        // Fetch the authentication details of the current user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract the user ID from the authentication principal
        int userId = (int) authentication.getPrincipal();

        // Fetch and return the budget from the repository using the user ID
        return budgetRepository.getUserBudget(userId);
    }
}
