package com.example.finapp.BoundedContext.Category.Controller;

import com.example.finapp.BoundedContext.Category.DTO.Category;
import com.example.finapp.BoundedContext.Category.Repository.CategoryRepository;
import com.example.finapp.BoundedContext.Category.Request.CreateCategoryRequest;
import com.example.finapp.BoundedContext.Category.Request.UpdateCategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The CategoryController class is responsible for handling incoming HTTP requests
 * related to categories. It provides endpoints for creating, updating, retrieving,
 * and deleting categories. It interacts with the CategoryRepository to perform
 * these operations on the database.
 */
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {

    private final CategoryRepository repository;

    /**
     * Constructs a CategoryController with the specified CategoryRepository.
     *
     * @param repository the CategoryRepository to interact with the database.
     */
    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    /**
     * Endpoint to retrieve all categories.
     *
     * @return a list of all categories.
     */
    @GetMapping
    public List<Category> getAll() {
        return repository.findAll();
    }

    /**
     * Endpoint to create a new category.
     *
     * @param request the data for the category to be created.
     * @return a response indicating the result of the creation process.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateCategoryRequest request) {
        try {
            repository.create(request);
            return ResponseEntity.ok("Category created");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Endpoint to create a custom category for the authenticated user.
     *
     * @param categoryRequest the data for the custom category to be created.
     * @return a response indicating the result of the custom category creation process.
     */
    @PostMapping("/custom")
    public ResponseEntity<String> createCustomCategory(@RequestBody CreateCategoryRequest categoryRequest) {
        // Retrieve the current authenticated user's ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = (int) authentication.getPrincipal();

        repository.createCustomCategory(categoryRequest.getName(), categoryRequest.getType(), userId);

        return ResponseEntity.ok("Custom category created");
    }

    /**
     * Endpoint to retrieve a category by its ID.
     *
     * @param id the ID of the category to be retrieved.
     * @return the category with the specified ID.
     */
    @GetMapping("/{id}")
    public Category getById(@PathVariable int id) {
        return repository.findById(id);
    }

    /**
     * Endpoint to update an existing category.
     *
     * @param id the ID of the category to be updated.
     * @param request the new data for the category.
     * @return a response indicating the result of the update process.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody UpdateCategoryRequest request) {
        repository.update(id, request);
        return ResponseEntity.ok("Category updated");
    }

    /**
     * Endpoint to delete a category by its ID.
     *
     * @param id the ID of the category to be deleted.
     * @return a response indicating the result of the deletion process.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        repository.delete(id);
        return ResponseEntity.ok("Category deleted");
    }

    /**
     * Endpoint to delete a custom category by its ID.
     *
     * @param id the ID of the custom category to be deleted.
     * @return a response indicating the result of the deletion process.
     */
    @DeleteMapping("/custom/{id}")
    public ResponseEntity<String> deleteCustomCategory(@PathVariable int id) {
        repository.deleteCustomCategory(id);
        return ResponseEntity.ok("Category deleted");
    }
}
