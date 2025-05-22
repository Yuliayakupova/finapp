package com.example.finapp.BoundedContext.UserManagment.Controller;

import com.example.finapp.BoundedContext.UserManagment.DTO.User;
import com.example.finapp.BoundedContext.UserManagment.Repository.UserRepository;
import com.example.finapp.BoundedContext.UserManagment.Request.UpdateUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing user-related operations such as create, update, and delete.
 * Provides endpoints for managing users in the system.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserRepository repository;

    /**
     * Constructs a UserController with the specified UserRepository.
     *
     * @param repository the repository used for user-related operations
     */
    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Creates a new user in the system.
     *
     * @param user the user to be created
     * @return a ResponseEntity indicating the result of the operation
     */
    @PostMapping
    public ResponseEntity<String> create(@RequestBody User user) {
        repository.create(user);
        return ResponseEntity.ok("User created");
    }

    /**
     * Updates an existing user in the system.
     *
     * @param id the ID of the user to be updated
     * @param request the updated user details
     * @return a ResponseEntity indicating the result of the operation
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        request.setId(id); // the id field is added to UpdateUserRequest
        repository.update(request);
        return ResponseEntity.ok("User updated");
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to be deleted
     * @return a ResponseEntity indicating the result of the operation
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        repository.delete(id);
        return ResponseEntity.ok("User deleted");
    }
}
