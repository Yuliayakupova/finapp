package com.example.finapp.BoundedContext.Limit.Repository;

import com.example.finapp.BoundedContext.Limit.DTO.Limit;
import com.example.finapp.BoundedContext.Limit.Request.CreateLimitRequest;
import com.example.finapp.SharedContext.Service.SqlLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LimitRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SqlLoader sqlLoader;

    public LimitRepository(JdbcTemplate jdbcTemplate, SqlLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

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

    public List<Limit> getByUser(int userId) {
        String sql = sqlLoader.load("queries/limit/get_by_user.sql");
        return jdbcTemplate.query(sql, new Object[]{userId}, limitRowMapper);
    }

    public void delete(int limitId, int userId) {
        String sql = sqlLoader.load("queries/limit/delete.sql");
        jdbcTemplate.update(sql, limitId, userId);
    }

    private final RowMapper<Limit> limitRowMapper = new RowMapper<>() {
        @Override
        public Limit mapRow(ResultSet rs, int rowNum) throws SQLException {
            Limit limit = new Limit();
            limit.setLimitId(rs.getInt("limit_id"));
            limit.setMaxAmount(rs.getBigDecimal("max_amount"));
            limit.setPeriod(rs.getString("period"));
            limit.setStartDate(rs.getDate("start_date").toLocalDate());
            limit.setCategoryId(rs.getInt("category_id"));
            limit.setUserId(rs.getInt("user_id"));
            return limit;
        }
    };
}
