package com.example.finapp.BoundedContext.Category.Repository;

import com.example.finapp.BoundedContext.Category.DTO.Category;
import com.example.finapp.BoundedContext.Category.Request.CreateCategoryRequest;
import com.example.finapp.BoundedContext.Category.Request.UpdateCategoryRequest;
import com.example.finapp.SharedContext.Service.SqlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The CategoryRepository class provides data access methods for performing CRUD operations
 * related to categories in the personal finance application. It interacts with the database
 * using SQL queries loaded from external files.
 */
@Repository
public class CategoryRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SqlLoader sqlLoader;
    private static final Logger log = LoggerFactory.getLogger(CategoryRepository.class);

    /**
     * Constructs a CategoryRepository with JdbcTemplate and SqlLoader for database operations.
     *
     * @param jdbcTemplate the JdbcTemplate for interacting with the database.
     * @param sqlLoader the SqlLoader for loading SQL queries from external files.
     */
    public CategoryRepository(JdbcTemplate jdbcTemplate, SqlLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

    /**
     * Checks if a category with the given name and type already exists in the database.
     *
     * @param name the name of the category.
     * @param type the type of the category.
     * @return true if a category with the given name and type exists, false otherwise.
     */
    public boolean existsByNameAndType(String name, String type) {
        String sql = sqlLoader.load("queries/category/exists_by_name_and_type.sql");
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name, type);
        return count != null && count > 0;
    }

    /**
     * Retrieves all categories from the database.
     *
     * @return a list of all categories.
     */
    public List<Category> findAll() {
        String sql = sqlLoader.load("queries/category/find_all.sql");
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type")
                )
        );
    }

    /**
     * Retrieves a category by its unique identifier.
     *
     * @param id the unique identifier of the category.
     * @return the category with the given id.
     */
    public Category findById(int id) {
        String sql = sqlLoader.load("queries/category/find_by_id.sql");
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (rs, rowNum) -> new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("type")
                )
        );
    }

    /**
     * Creates a new category in the database based on the provided request.
     *
     * @param request the data to create a new category.
     * @throws IllegalArgumentException if a category with the same name and type already exists.
     */
    public void create(CreateCategoryRequest request) {
        if (existsByNameAndType(request.getName(), request.getType())) {
            throw new IllegalArgumentException("Category already exists.");
        }

        String sql = sqlLoader.load("queries/category/insert.sql");
        jdbcTemplate.update(sql, request.getName(), request.getType());
    }

    /**
     * Updates an existing category in the database based on the provided id and request.
     *
     * @param id the id of the category to be updated.
     * @param request the data to update the category.
     */
    public void update(int id, UpdateCategoryRequest request) {
        String sql = sqlLoader.load("queries/category/update.sql");
        jdbcTemplate.update(
                sql,
                request.getName(),
                request.getType(),
                id
        );
    }

    /**
     * Deletes a category from the database based on its unique identifier.
     *
     * @param id the id of the category to be deleted.
     */
    public void delete(int id) {
        String sql = sqlLoader.load("queries/category/delete.sql");
        jdbcTemplate.update(sql, id);
    }

    /**
     * Creates a custom category for a specific user.
     *
     * @param name the name of the custom category.
     * @param type the type of the custom category.
     * @param userId the id of the user who owns the custom category.
     */
    public void createCustomCategory(String name, String type, int userId) {
        String sql = sqlLoader.load("queries/category/insert_custom.sql");
        jdbcTemplate.update(sql, name, type, userId);
    }

    /**
     * Deletes a custom category based on its unique identifier.
     *
     * @param id the id of the custom category to be deleted.
     */
    public void deleteCustomCategory(int id) {
        String sql = sqlLoader.load("queries/category/delete_custom.sql");
        jdbcTemplate.update(sql, id);
    }

    /**
     * Updates a custom category for a specific user.
     *
     * @param id the id of the custom category to be updated.
     * @param name the new name for the custom category.
     * @param type the new type for the custom category.
     * @param userId the id of the user who owns the custom category.
     */
    public void updateCustomCategory(int id, String name, String type, int userId) {
        String sql = sqlLoader.load("queries/category/update_custom.sql");
        jdbcTemplate.update(sql, name, type, userId, id);
    }

    /**
     * Checks if a category belongs to a specific user.
     *
     * @param category_id the id of the category.
     * @param userId the id of the user.
     * @return true if the category belongs to the user, false otherwise.
     */
    public boolean isCategoryBelongsToUser(int category_id, int userId) {
        String sql = sqlLoader.load("queries/category/check_category_belongs_to_user.sql");
        log.debug("Executing SQL: {} with parameters category_id = {}, userId = {}", sql, category_id, userId);
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, category_id, userId);
        log.debug("Result count: {}", count);
        return count != null && count > 0;
    }
}
