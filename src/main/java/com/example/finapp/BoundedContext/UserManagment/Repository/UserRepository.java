package com.example.finapp.BoundedContext.UserManagment.Repository;

import com.example.finapp.BoundedContext.UserManagment.DTO.User;
import com.example.finapp.BoundedContext.UserManagment.Request.UpdateUserRequest;
import com.example.finapp.SharedContext.Service.SqlLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SqlLoader sqlLoader;

    public UserRepository(JdbcTemplate jdbcTemplate, SqlLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

    public void create (User request) {
        String sql = sqlLoader.load("queries/user/insert.sql");
        jdbcTemplate.update(sql,
                request.getEmail(),
                request.getPassword(),
                request.getName()
        );
    }

    public void update (UpdateUserRequest request) {
        String sql = sqlLoader.load("queries/user/update.sql");
        jdbcTemplate.update(
                sql,
                request.getPassword(),
                request.getName(),
                request.isAuthenticated(),
                request.getEmail()
        );
    }

    public void delete(int userId) {
        String sql = sqlLoader.load("queries/user/delete.sql");
        jdbcTemplate.update(sql, userId);
    }

    public User findByEmail(String email) {
        String sql = sqlLoader.load("queries/user/find_by_email.sql");
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) -> {
            return new User(
                    rs.getInt("user_id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getDate("registration_date").toLocalDate(),
                    rs.getBoolean("is_authenticated")
            );
        });
    }
}
