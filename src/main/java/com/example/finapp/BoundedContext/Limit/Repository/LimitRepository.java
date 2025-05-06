package com.example.finapp.BoundedContext.Limit.Repository;

import com.example.finapp.BoundedContext.Limit.DTO.Limit;
import com.example.finapp.BoundedContext.Limit.Request.CreateLimitRequest;
import com.example.finapp.SharedContext.Service.SqlLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The LimitRepository class is responsible for handling the data persistence of the limits.
 * It interacts with the database using JdbcTemplate and SQL queries for creating, updating, retrieving,
 * and deleting limits, as well as increasing the used amount for a specific limit.
 */
@Repository
public class LimitRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SqlLoader sqlLoader;

    /**
     * Constructor for LimitRepository that initializes JdbcTemplate and SqlLoader.
     *
     * @param jdbcTemplate the JdbcTemplate for database operations.
     * @param sqlLoader the SqlLoader used to load SQL queries from external files.
     */
    public LimitRepository(JdbcTemplate jdbcTemplate, SqlLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

    /**
     * Creates a new limit in the database.
     *
     * @param request the details of the limit to be created.
     * @param userId the ID of the user to whom the limit belongs.
     */
    public void create(CreateLimitRequest request, int userId) {
        String sql = sqlLoader.load("queries/limit/insert.sql");
        jdbcTemplate.update(sql,
                request.getMaxAmount(),
                request.getPeriod(),
                request.getStartDate(),
                request.getCategoryId(),
                userId
        );
    }

    /**
     * Updates an existing limit in the database.
     *
     * @param limitId the ID of the limit to be updated.
     * @param request the new details for the limit.
     * @param userId the ID of the user to whom the limit belongs.
     */
    public void update(int limitId, CreateLimitRequest request, int userId) {
        String sql = sqlLoader.load("queries/limit/update.sql");
        jdbcTemplate.update(sql,
                request.getMaxAmount(),
                request.getPeriod(),
                request.getStartDate(),
                request.getCategoryId(),
                limitId,
                userId
        );
    }

    /**
     * Retrieves all limits for a specific user.
     *
     * @param userId the ID of the user for whom limits are being retrieved.
     * @return a list of Limit objects associated with the specified user.
     */
    public List<Limit> getByUser(int userId) {
        String sql = sqlLoader.load("queries/limit/get_by_user.sql");
        return jdbcTemplate.query(sql, new Object[]{userId}, limitRowMapper);
    }

    /**
     * Deletes a specific limit from the database.
     *
     * @param limitId the ID of the limit to be deleted.
     * @param userId the ID of the user to whom the limit belongs.
     */
    public void delete(int limitId, int userId) {
        String sql = sqlLoader.load("queries/limit/delete.sql");
        jdbcTemplate.update(sql, limitId, userId);
    }

    /**
     * RowMapper implementation that maps the SQL result set to a Limit object.
     */
    private final RowMapper<Limit> limitRowMapper = new RowMapper<Limit>() {
        @Override
        public Limit mapRow(ResultSet rs, int rowNum) throws SQLException {
            Limit limit = new Limit();
            limit.setLimitId(rs.getInt("limit_id"));
            limit.setMaxAmount(rs.getBigDecimal("max_amount"));
            limit.setRemainingAmount(rs.getBigDecimal("max_amount").subtract(rs.getBigDecimal("used_amount")));
            limit.setPeriod(rs.getString("period"));
            limit.setStartDate(rs.getDate("start_date").toLocalDate());
            limit.setCategoryId(rs.getInt("category_id"));
            limit.setUserId(rs.getInt("user_id"));
            return limit;
        }
    };

    /**
     * Increases the used amount for a specific limit when a transaction is made.
     *
     * @param transactionAmount the amount of the transaction to be added to the used amount.
     * @param userId the ID of the user for whom the transaction is being processed.
     * @param categoryId the ID of the category to which the limit belongs.
     */
    public void increaseUsedAmount(BigDecimal transactionAmount, int userId, int categoryId) {
        String sql = sqlLoader.load("queries/limit/increase_used_amount.sql");
        jdbcTemplate.update(sql, transactionAmount, userId, categoryId);
    }
}
