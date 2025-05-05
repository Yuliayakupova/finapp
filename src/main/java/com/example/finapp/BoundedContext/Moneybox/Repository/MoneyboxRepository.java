package com.example.finapp.BoundedContext.Moneybox.Repository;


import com.example.finapp.BoundedContext.Moneybox.DTO.Moneybox;
import com.example.finapp.BoundedContext.Moneybox.Request.CreateMoneyboxRequest;
import com.example.finapp.SharedContext.Service.SqlLoader;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MoneyboxRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SqlLoader sqlLoader;

    public MoneyboxRepository(JdbcTemplate jdbcTemplate, SqlLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

    @PostConstruct
    public void init() {
        String sql = sqlLoader.load("queries/moneybox/create.sql");
        jdbcTemplate.execute(sql);
    }

    public Moneybox save(Moneybox moneybox) {
        String sql = sqlLoader.load("queries/moneybox/insert.sql");
        jdbcTemplate.update(sql,
                moneybox.getName(),
                moneybox.getGoal(),
                moneybox.getTargetDate(),
                moneybox.getCurrentAmount()
        );
        return findById(moneybox.getName());
    }

    public Moneybox findById(String name) {
        String sql = sqlLoader.load("queries/moneybox/find_by_name.sql");
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, (rs, rowNum) -> new Moneybox(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getBigDecimal("goal"),
                rs.getBigDecimal("current_amount"),
                rs.getDate("target_date").toLocalDate()
        ));
    }

    public List<Moneybox> findAll() {
        String sql = sqlLoader.load("queries/moneybox/find_all.sql");
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Moneybox(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getBigDecimal("goal"),
                rs.getBigDecimal("current_amount"),
                rs.getDate("target_date").toLocalDate()
        ));
    }

    public void deleteById(Long id) {
        String sql = sqlLoader.load("queries/moneybox/delete.sql");
        jdbcTemplate.update(sql, id);
    }


}