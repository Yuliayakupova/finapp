package com.example.finapp.BoundedContext.UserManagment.Controller;

import com.example.finapp.BoundedContext.UserManagment.DTO.User;
import com.example.finapp.BoundedContext.UserManagment.Repository.UserRepository;
import com.example.finapp.BoundedContext.UserManagment.Request.UpdateUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody User user) {
        repository.create(user);
        return ResponseEntity.ok("User created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        repository.update(request);
        return ResponseEntity.ok("User updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        repository.delete(id);
        return ResponseEntity.ok("User deleted");
    }
}
