package com.example.finapp.BoundedContext.UserManagment.Repository;

import com.example.finapp.BoundedContext.UserManagment.DTO.User;
import com.example.finapp.BoundedContext.UserManagment.Request.UpdateUserRequest;
import com.example.finapp.SharedContext.Service.SqlLoader;
import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {
        String sql = sqlLoader.load("queries/users/create.sql");
        jdbcTemplate.execute(sql);
    }

    public void create (User request) {
        String sql = sqlLoader.load("queries/users/insert.sql");
        jdbcTemplate.update(sql,
                request.getEmail(),
                request.getPassword(),
                request.getName()
        );
    }

    public void update (UpdateUserRequest request) {
        String sql = sqlLoader.load("queries/users/update.sql");
        jdbcTemplate.update(
                sql,
                request.getPassword(),
                request.getName(),
                request.isAuthenticated(),
                request.getEmail()
        );
    }

    public void delete(int userId) {
        String sql = sqlLoader.load("queries/users/delete.sql");
        jdbcTemplate.update(sql, userId);
    }
}
