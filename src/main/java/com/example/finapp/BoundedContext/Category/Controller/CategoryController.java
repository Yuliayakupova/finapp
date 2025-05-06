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

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryRepository repository;

    public CategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Category> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateCategoryRequest request) {
        try {
            repository.create(request);
            return ResponseEntity.ok("Category created");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/custom")
    public ResponseEntity<String> createCustomCategory(@RequestBody CreateCategoryRequest categoryRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = (int) authentication.getPrincipal();

        repository.createCustomCategory(categoryRequest.getName(), categoryRequest.getType(), userId);

        return ResponseEntity.ok("Custom category created");
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody UpdateCategoryRequest request) {
        repository.update(id, request);
        return ResponseEntity.ok("Category updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        repository.delete(id);
        return ResponseEntity.ok("Category deleted");
    }

    @DeleteMapping("/custom/{id}")
    public ResponseEntity<String> deleteCustomCategory(@PathVariable int id) {
        repository.deleteCustomCategory(id);
        return ResponseEntity.ok("Category deleted");
    }
}
