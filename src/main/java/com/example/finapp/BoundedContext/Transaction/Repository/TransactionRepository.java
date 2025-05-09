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

/**
 * Repository class for handling CRUD operations related to financial transactions.
 * Uses JDBC and external SQL files for data persistence and querying.
 */
@Repository
public class TransactionRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SqlLoader sqlLoader;

    /**
     * Constructs a TransactionRepository with dependencies.
     *
     * @param jdbcTemplate Spring's JdbcTemplate for executing SQL queries
     * @param sqlLoader    custom service to load SQL queries from resources
     */
    public TransactionRepository(JdbcTemplate jdbcTemplate, SqlLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

    /**
     * Retrieves all transactions from the database.
     *
     * @return list of all transactions
     */
    public List<Transaction> getAllByUserId(int userId) {
        String sql = sqlLoader.load("queries/transaction/get_all_by_user.sql");
        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> new Transaction(
                rs.getInt("id"),
                rs.getBigDecimal("amount"),
                rs.getString("description"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getInt("category_id"),
                rs.getInt("moneybox_id")
        ));
    }

    /**
     * Inserts a new transaction into the database.
     *
     * @param request the transaction creation request
     * @param userId  the ID of the user creating the transaction
     */
    public void create(CreateTransactionRequest request, int userId) {
        String sql = sqlLoader.load("queries/transaction/insert.sql");
        jdbcTemplate.update(sql,
                request.getAmount(),
                request.getDescription(),
                request.getMoneyboxId(),
                userId,
                request.getCategoryId()
        );
    }

    /**
     * Deletes a transaction by its ID.
     *
     * @param id the ID of the transaction to delete
     */
    public void deleteById(int id) {
        String sql = sqlLoader.load("queries/transaction/delete.sql");
        jdbcTemplate.update(sql, id);
    }

    /**
     * Filters transactions based on the provided parameters.
     *
     * @param startDate filter by creation time from this date
     * @param endDate   filter by creation time to this date
     * @param minAmount minimum transaction amount
     * @param maxAmount maximum transaction amount
     * @param category  category ID to filter by
     * @return list of filtered transactions
     */
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
                rs.getInt("id"),
                rs.getBigDecimal("amount"),
                rs.getString("description"),
                rs.getTimestamp("created_at").toLocalDateTime(),
                rs.getInt("category_id"),
                rs.getInt("moneybox_id")
        ));
    }

    /**
     * Updates an existing transaction with new values.
     *
     * @param id      the ID of the transaction to update
     * @param request the updated transaction data
     */
    public void update(int id, UpdateTransactionRequest request) {
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
