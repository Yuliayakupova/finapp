package com.example.finapp.BoundedContext.Transaction.Repository;

import com.example.finapp.BoundedContext.Transaction.DTO.Transaction;
import com.example.finapp.BoundedContext.Transaction.Request.CreateTransactionRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepository {
    private final JdbcTemplate jdbcTemplate;

    public TransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        jdbcTemplate.execute("""
            CREATE TABLE IF NOT EXISTS transactions (
                id SERIAL PRIMARY KEY,
                amount NUMERIC(10, 2) NOT NULL,
                description TEXT,
                created_at TIMESTAMP DEFAULT now()
            )
        """);
    }

    public List<Transaction> getAll() {
        String sql = "SELECT id, amount, description, created_at FROM transactions";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Transaction t = new Transaction();
            t.setId(rs.getLong("id"));
            t.setAmount(rs.getBigDecimal("amount"));
            t.setDescription(rs.getString("description"));
            t.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            return t;
        });
    }

    public void create(CreateTransactionRequest request) {
        String sql = "INSERT INTO transactions (amount, description, created_at) VALUES (?, ?, now())";
        jdbcTemplate.update(sql, request.getAmount(), request.getDescription());
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM transactions WHERE id = ?", id);
    }
}
