package com.example.finapp.BoundedContext.Moneybox.Repository;

import com.example.finapp.BoundedContext.Moneybox.DTO.Moneybox;
import com.example.finapp.BoundedContext.Moneybox.Request.CreateMoneyboxRequest;
import com.example.finapp.SharedContext.Service.SqlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
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
     * Retrieves all moneyboxes from the database.
     *
     * @return a list of Moneybox objects representing all moneyboxes.
     */
    public List<Moneybox> getAll() {
        String sql = sqlLoader.load("queries/moneybox/find_all.sql");
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

    public BigDecimal getUserIncome(int userId) {
        String sql = sqlLoader.load("queries/moneybox/get_available_budget.sql");
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, userId, userId);
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

        String currentAmountSql = sqlLoader.load("queries/moneybox/get_current_amount.sql");
        BigDecimal currentAmount = jdbcTemplate.queryForObject(currentAmountSql, BigDecimal.class, moneyboxId, userId);

        String targetAmountSql = sqlLoader.load("queries/moneybox/get_target_amount.sql");
        BigDecimal targetAmount = jdbcTemplate.queryForObject(targetAmountSql, BigDecimal.class, moneyboxId, userId);

        if (currentAmount.add(amount).compareTo(targetAmount) > 0) {
            throw new IllegalArgumentException("The target amount has already been reached or exceeded.");
        }

        if (date == null) {
            date = LocalDate.now();
        }

        String insertSql = sqlLoader.load("queries/moneybox/insert_moneybox_transaction.sql");
        jdbcTemplate.update(insertSql, amount, description, date, userId, moneyboxId);

        String updateSql = sqlLoader.load("queries/moneybox/update_moneybox_amount.sql");
        jdbcTemplate.update(updateSql, amount, moneyboxId);

        int salaryCategoryId = 17;
        String subtractSql = sqlLoader.load("queries/moneybox/subtract_from_budget.sql");
        jdbcTemplate.update(subtractSql, amount.negate(), "Transfer to moneybox", date, userId, salaryCategoryId);
    }

    public void deleteMoneybox(int moneyboxId, int userId) {
        String checkSql = sqlLoader.load("queries/moneybox/check_exists.sql");
        Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, moneyboxId, userId);
        if (count == null || count == 0) {
            throw new IllegalArgumentException("Moneybox not found or already deleted");
        }

        String sumSql = sqlLoader.load("queries/moneybox/get_sum.sql");
        BigDecimal totalAmount = jdbcTemplate.queryForObject(sumSql, BigDecimal.class, moneyboxId, userId);

        if (totalAmount == null || totalAmount.compareTo(BigDecimal.ZERO) == 0) {
            totalAmount = BigDecimal.ZERO;
        }

        String insertTransactionSql = sqlLoader.load("queries/moneybox/insert_return_transaction.sql");
        jdbcTemplate.update(insertTransactionSql, totalAmount, "Return from moneybox", LocalDate.now(), userId, 17);

        String softDeleteSql = sqlLoader.load("queries/moneybox/soft_delete.sql");
        jdbcTemplate.update(softDeleteSql, moneyboxId, userId);
    }

    /**
     * Returns the total amount stored in a specific moneybox.
     *
     * @param moneyboxId the ID of the moneybox
     * @param userId     the user who owns the moneybox
     * @return total amount in the moneybox
     */
    public BigDecimal getMoneyboxAmount(int moneyboxId, int userId) {
        String sql = sqlLoader.load("queries/moneybox/sum_moneybox_amount.sql");
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, moneyboxId, userId);
    }

    public void returnAmountToIncomeCategory(int userId, BigDecimal amount) {
        String sql = sqlLoader.load("queries/moneybox/return_to_income.sql");
        jdbcTemplate.update(sql, amount, userId);
    }
}
