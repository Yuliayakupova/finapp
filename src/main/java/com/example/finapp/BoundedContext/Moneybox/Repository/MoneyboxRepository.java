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

@Repository
public class MoneyboxRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SqlLoader sqlLoader;

    @Autowired
    public MoneyboxRepository(JdbcTemplate jdbcTemplate, SqlLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

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

    public Moneybox findById(int id) {
        String sql = sqlLoader.load("queries/moneybox/get_by_id.sql");
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (rs, rowNum) -> new Moneybox(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBigDecimal("target_amount"),
                        rs.getBigDecimal("current_amount"),
                        rs.getDate("target_date").toLocalDate(),
                        rs.getInt("user_id")
                )
        );
    }

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

    public void deleteById(int id) {
        String sql = sqlLoader.load("queries/moneybox/delete.sql");
        jdbcTemplate.update(sql, id);
    }

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

    public boolean isMoneyboxBelongsToUser(int moneyboxId, int userId) {
        String sql = sqlLoader.load("queries/moneybox/check_moneybox_belongs_to_user.sql");
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, moneyboxId, userId);
        return count != null && count > 0;
    }

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