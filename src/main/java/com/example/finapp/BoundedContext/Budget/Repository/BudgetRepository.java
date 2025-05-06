package com.example.finapp.BoundedContext.Budget.Repository;

import com.example.finapp.BoundedContext.Budget.DTO.Budget;
import com.example.finapp.SharedContext.Service.SqlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Repository for retrieving user budget data from the database.
 * Uses raw SQL loaded via {@link SqlLoader} and executed with {@link JdbcTemplate}.
 */
@Repository
public class BudgetRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SqlLoader sqlLoader;

    /**
     * Constructs a new BudgetRepository with the specified dependencies.
     *
     * @param jdbcTemplate the JDBC template used for database access
     * @param sqlLoader    utility for loading SQL queries from resource files
     */
    @Autowired
    public BudgetRepository(JdbcTemplate jdbcTemplate, SqlLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

    /**
     * Retrieves the total budget balance for a specific user.
     *
     * @param userId the ID of the user whose budget is being queried
     * @return a {@link Budget} object containing the user's total balance
     */
    public Budget getUserBudget(int userId) {
        // Load SQL query from file: resources/queries/budget/select_user_budget.sql
        String sql = sqlLoader.load("queries/budget/select_user_budget.sql");

        // Execute the query and retrieve the result as BigDecimal
        BigDecimal balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);

        // Return the budget DTO
        return new Budget(balance);
    }
}
