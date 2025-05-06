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


@Repository
public class CategoryRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SqlLoader sqlLoader;
    private static final Logger log = LoggerFactory.getLogger(CategoryRepository.class);


    public CategoryRepository(JdbcTemplate jdbcTemplate, SqlLoader sqlLoader) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlLoader = sqlLoader;
    }

    public boolean existsByNameAndType(String name, String type) {
        String sql = sqlLoader.load("queries/category/exists_by_name_and_type.sql");
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name, type);
        return count != null && count > 0;
    }

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

    public void create(CreateCategoryRequest request) {
        if (existsByNameAndType(request.getName(), request.getType())) {
            throw new IllegalArgumentException("Category already exists.");
        }

        String sql = sqlLoader.load("queries/category/insert.sql");
        jdbcTemplate.update(sql, request.getName(), request.getType());
    }

    public void update(int id, UpdateCategoryRequest request) {
        String sql = sqlLoader.load("queries/category/update.sql");
        jdbcTemplate.update(
                sql,
                request.getName(),
                request.getType(),
                id
        );
    }

    public void delete(int id) {
        String sql = sqlLoader.load("queries/category/delete.sql");
        jdbcTemplate.update(sql, id);
    }

    public void createCustomCategory(String name, String type, int userId) {
        String sql = sqlLoader.load("queries/category/insert_custom.sql");
        jdbcTemplate.update(sql, name, type, userId);
    }

    public void deleteCustomCategory(int id) {
        String sql = sqlLoader.load("queries/category/delete_custom.sql");
        jdbcTemplate.update(sql, id);
    }

    public void updateCustomCategory(int id, String name, String type, int userId) {
        String sql = sqlLoader.load("queries/category/update_custom.sql");
        jdbcTemplate.update(sql, name, type, userId, id);
    }

    public boolean isCategoryBelongsToUser(int category_id, int userId) {
        String sql = sqlLoader.load("queries/category/check_category_belongs_to_user.sql");
        log.debug("Executing SQL: {} with parameters category_id = {}, userId = {}", sql, category_id, userId);
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, category_id, userId);
        log.debug("Result count: {}", count);
        return count != null && count > 0;
    }
}
