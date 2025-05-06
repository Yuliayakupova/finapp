package com.example.finapp.BoundedContext.UserManagment.Repository;

import com.example.finapp.BoundedContext.UserManagment.DTO.User;
import com.example.finapp.BoundedContext.UserManagment.Request.UpdateUserRequest;
import com.example.finapp.SharedContext.Service.SqlLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class for performing CRUD operations related to users in the database.
 * Provides methods to create, update, delete, and retrieve users.
 */
@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SqlLoader sqlLoader;

    /**
     * Constructs a UserRepository with the specified JdbcTemplate and SqlLoader.
     *
     * @param jdbcTemplate the JdbcTemplate for executing SQL queries
     * @param sqlLoader the SqlLoader for loading SQL queries from external files
     */
    public UserRepository(JdbcTemplate jdbcTemplate, SqlLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

    /**
     * Creates a new user in the database.
     *
     * @param request the user details to be created
     */
    public void create(User request) {
        String sql = sqlLoader.load("queries/user/insert.sql");
        jdbcTemplate.update(sql,
                request.getEmail(),
                request.getPassword(),
                request.getName()
        );
    }

    /**
     * Updates an existing user's details in the database.
     *
     * @param request the updated user details
     */
    public void update(UpdateUserRequest request) {
        String sql = sqlLoader.load("queries/user/update.sql");
        jdbcTemplate.update(
                sql,
                request.getPassword(),
                request.getName(),
                request.isAuthenticated(),
                request.getEmail()
        );
    }

    /**
     * Deletes a user by their user ID.
     *
     * @param userId the ID of the user to delete
     */
    public void delete(int userId) {
        String sql = sqlLoader.load("queries/user/delete.sql");
        jdbcTemplate.update(sql, userId);
    }

    /**
     * Finds a user by their email address.
     *
     * @param email the email of the user to find
     * @return the user with the specified email
     */
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
