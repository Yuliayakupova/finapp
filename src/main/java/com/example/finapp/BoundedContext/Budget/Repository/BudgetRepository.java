package com.example.finapp.BoundedContext.Budget.Repository;

import com.example.finapp.BoundedContext.Budget.DTO.Budget;
import com.example.finapp.SharedContext.Service.SqlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class BudgetRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SqlLoader sqlLoader;

    @Autowired
    public BudgetRepository(JdbcTemplate jdbcTemplate, SqlLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

    public Budget getUserBudget(int userId) {
        String sql = sqlLoader.load("queries/budget/select_user_budget.sql");
        BigDecimal balance = jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);
        return new Budget(balance);
    }
}