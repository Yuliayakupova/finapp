package com.example.finapp.boundedContext;

import com.example.finapp.BoundedContext.Category.Controller.CategoryController;
import com.example.finapp.BoundedContext.Category.DTO.Category;
import com.example.finapp.BoundedContext.Category.Repository.CategoryRepository;
import com.example.finapp.BoundedContext.Category.Request.CreateCategoryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryControllerTest {

    private CategoryRepository repository;
    private CategoryController controller;

    @BeforeEach
    void setup() {
        repository = mock(CategoryRepository.class);
        controller = new CategoryController(repository);
    }

    @Test
    void testGetAll() {
        List<Category> mockCategories = Arrays.asList(
                new Category(1, "Food", "Expense"),
                new Category(2, "Salary", "Income")
        );

        when(repository.findAll()).thenReturn(mockCategories);

        List<Category> result = controller.getAll();

        assertEquals(2, result.size());
        assertEquals("Food", result.get(0).getName());

        verify(repository, times(1)).findAll();
    }

    @Test
    void testCreateCategory_Success() {
        CreateCategoryRequest request = new CreateCategoryRequest("Travel", "Expense");

        doNothing().when(repository).create(request);

        ResponseEntity<?> response = controller.create(request);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Category created", response.getBody());

        verify(repository, times(1)).create(request);
    }

    @Test
    void testCreateCategory_Failure() {
        CreateCategoryRequest request = new CreateCategoryRequest("Travel", "Expense");

        doThrow(new IllegalArgumentException("Category already exists."))
                .when(repository).create(request);

        ResponseEntity<?> response = controller.create(request);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Category already exists.", response.getBody());

        verify(repository, times(1)).create(request);
    }
}
