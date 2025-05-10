package com.example.finapp.BoundedContext.Moneybox.Repository;

import com.example.finapp.BoundedContext.Moneybox.DTO.Moneybox;
import com.example.finapp.BoundedContext.Moneybox.Request.CreateMoneyboxRequest;
import com.example.finapp.BoundedContext.Moneybox.Request.UpdateMoneyboxRequest;
import com.example.finapp.SharedContext.Service.SqlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The MoneyboxRepository class is responsible for interacting with the database
 * to manage moneyboxes and related transactions. It provides methods for creating,
 * retrieving, updating, filtering, and deleting moneyboxes, as well as managing
 * transactions associated with moneyboxes.
 */
@Repository
public class MoneyboxRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SqlLoader sqlLoader;

    /**
     * Constructs a MoneyboxRepository instance.
     *
     * @param jdbcTemplate the JdbcTemplate used for executing SQL queries.
     * @param sqlLoader the SqlLoader used to load SQL queries from files.
     */
    @Autowired
    public MoneyboxRepository(JdbcTemplate jdbcTemplate, SqlLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

    /**
     * Retrieves all moneyboxes from the database.
     *
     * @return a list of Moneybox objects representing all moneyboxes.
     */
    public List<Moneybox> getAll() {
        String sql = sqlLoader.load("queries/moneybox/get_all.sql");
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Moneybox(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getBigDecimal("target_amount"),
                rs.getBigDecimal("current_amount"),
                rs.getDate("target_date").toLocalDate(),
                rs.getInt("user_id")
        ));
    }

    /**
     * Creates a new moneybox in the database.
     *
     * @param request the CreateMoneyboxRequest object containing the data for the new moneybox.
     * @param userId the ID of the user creating the moneybox.
     */
    public void create(CreateMoneyboxRequest request, int userId) {
        String sql = sqlLoader.load("queries/moneybox/insert.sql");
        jdbcTemplate.update(sql,
                request.getName(),
                request.getTargetAmount(),
                request.getCurrentAmount(),
                request.getTargetDate(),
                userId
        );
    }

    /**
     * Deletes a moneybox by its ID.
     *
     * @param id the ID of the moneybox to delete.
     */
    public void deleteById(int id) {
        String sql = sqlLoader.load("queries/moneybox/delete.sql");
        jdbcTemplate.update(sql, id);
    }

    /**
     * Filters moneyboxes based on specified criteria.
     *
     * @param minAmount the minimum current amount of the moneyboxes (can be null).
     * @param maxAmount the maximum current amount of the moneyboxes (can be null).
     * @param name the name of the moneybox (can be null or empty).
     * @return a list of moneyboxes matching the filter criteria.
     */
    public List<Moneybox> filter(BigDecimal minAmount, BigDecimal maxAmount, String name) {
        String baseSql = sqlLoader.load("queries/moneybox/filter_base.sql");
        StringBuilder sql = new StringBuilder(baseSql);
        List<Object> params = new ArrayList<>();

        if (minAmount != null) {
            sql.append(" AND current_amount >= ? ");
            params.add(minAmount);
        }
        if (maxAmount != null) {
            sql.append(" AND current_amount <= ? ");
            params.add(maxAmount);
        }
        if (name != null && !name.isEmpty()) {
            sql.append(" AND name LIKE ? ");
            params.add("%" + name + "%");
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) -> new Moneybox(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getBigDecimal("target_amount"),
                rs.getBigDecimal("current_amount"),
                rs.getDate("target_date").toLocalDate(),
                rs.getInt("user_id")
        ));
    }

    /**
     * Updates the details of an existing moneybox.
     *
     * @param id the ID of the moneybox to update.
     * @param request the UpdateMoneyboxRequest object containing the updated data.
     */
    public void update(int id, UpdateMoneyboxRequest request) {
        String sql = sqlLoader.load("queries/moneybox/update.sql");
        jdbcTemplate.update(
                sql,
                request.getName(),
                request.getTargetAmount(),
                request.getCurrentAmount(),
                id
        );
    }

    /**
     * Checks if the specified moneybox belongs to the given user.
     *
     * @param moneyboxId the ID of the moneybox.
     * @param userId the ID of the user.
     * @return true if the moneybox belongs to the user, otherwise false.
     */
    public boolean isMoneyboxBelongsToUser(int moneyboxId, int userId) {
        String sql = sqlLoader.load("queries/moneybox/check_moneybox_belongs_to_user.sql");
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, moneyboxId, userId);
        return count != null && count > 0;
    }

    /**
     * Adds a transaction to a moneybox and updates the current amount of the moneybox.
     *
     * @param moneyboxId the ID of the moneybox.
     * @param amount the amount of the transaction.
     * @param description the description of the transaction.
     * @param date the date of the transaction (can be null, in which case the current date is used).
     * @param userId the ID of the user associated with the transaction.
     */
    public void addTransactionToMoneybox(int moneyboxId, BigDecimal amount, String description, LocalDate date, int userId) {
        String insertSql = sqlLoader.load("queries/moneybox/insert_moneybox_transaction.sql");
        if (date == null) {
            date = LocalDate.now();
        }
        jdbcTemplate.update(insertSql, amount, description, date, userId, moneyboxId);

        String updateSql = sqlLoader.load("queries/moneybox/update_moneybox_amount.sql");
        jdbcTemplate.update(updateSql, amount, moneyboxId);
    }
}
