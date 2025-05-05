package com.example.finapp.BoundedContext.Transaction.Repository;

import com.example.finapp.BoundedContext.Transaction.DTO.Transaction;
import com.example.finapp.BoundedContext.Transaction.Request.CreateTransactionRequest;
import com.example.finapp.BoundedContext.Transaction.Request.UpdateTransactionRequest;
import com.example.finapp.SharedContext.Service.SqlLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SqlLoader sqlLoader;

    public TransactionRepository(JdbcTemplate jdbcTemplate, SqlLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

    public List<Transaction> getAll() {
        String sql = sqlLoader.load("queries/transaction/get_all.sql");
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Transaction(
                rs.getLong("id"),
                rs.getBigDecimal("amount"),
                rs.getString("description"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getInt("category")
        ));
    }

    public Transaction findById(Long id) {
        String sql = sqlLoader.load("queries/transaction/get_by_id.sql");
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (rs, rowNum) -> new Transaction(
                        rs.getLong("id"),
                        rs.getBigDecimal("amount"),
                        rs.getString("description"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getInt("category")
                )
        );
    }

    public void create (CreateTransactionRequest request, int userId) {
        String sql = sqlLoader.load("queries/transaction/insert.sql");
        jdbcTemplate.update(sql,
                request.getAmount(),
                request.getDescription(),
                request.getMoneyboxId(),
                userId,
                request.getCategoryId()
        );
    }

    public void deleteById(Long id) {
        String sql = sqlLoader.load("queries/transaction/delete.sql");
        jdbcTemplate.update(sql, id);
    }

    public List<Transaction> filter(LocalDateTime startDate, LocalDateTime endDate, BigDecimal minAmount, BigDecimal maxAmount, int category) {
        String baseSql = sqlLoader.load("queries/transaction/filter_base.sql");
        StringBuilder sql = new StringBuilder(baseSql);
        List<Object> params = new ArrayList<>();

        if (startDate != null) {
            sql.append(" AND created_at >= ? ");
            params.add(Timestamp.valueOf(startDate));
        }
        if (endDate != null) {
            sql.append(" AND created_at <= ? ");
            params.add(Timestamp.valueOf(endDate));
        }
        if (minAmount != null) {
            sql.append(" AND amount >= ? ");
            params.add(minAmount);
        }
        if (maxAmount != null) {
            sql.append(" AND amount <= ? ");
            params.add(maxAmount);
        }
        if (category > 0) {
            sql.append(" AND category = ? ");
            params.add(category);
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) -> new Transaction(
                rs.getLong("id"),
                rs.getBigDecimal("amount"),
                rs.getString("description"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getInt("category")
        ));
    }

    public void update(Long id, UpdateTransactionRequest request) {
        String sql = sqlLoader.load("queries/transaction/update.sql");
        jdbcTemplate.update(
                sql,
                request.getAmount(),
                request.getDescription(),
                Timestamp.valueOf(request.getCreatedAt()),
                request.getCategory(),
                id
        );
    }
}