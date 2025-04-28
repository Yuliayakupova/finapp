package com.example.finapp.BoundedContext.Transaction.Controller;

import com.example.finapp.BoundedContext.Transaction.DTO.Transaction;
import com.example.finapp.BoundedContext.UserManagment.DTO.User;
import com.example.finapp.BoundedContext.Transaction.Repository.TransactionRepository;
import com.example.finapp.BoundedContext.Transaction.Request.CreateTransactionRequest;
import com.example.finapp.BoundedContext.Transaction.Request.UpdateTransactionRequest;
import com.example.finapp.BoundedContext.UserManagment.Repository.UserRepository;
import com.example.finapp.SharedContext.Service.AuthenticationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionRepository repository;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    public TransactionController(TransactionRepository transactionRepository,
                                 AuthenticationService authenticationService,
                                 UserRepository userRepository) {
        this.repository = transactionRepository;
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Transaction> getAll() {
        return repository.getAll();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateTransactionRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email);
        int userId = user.getUserId();

        repository.create(request, userId);

        return ResponseEntity.ok("Transaction created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Transaction deleted");
    }

    @GetMapping("/filter")
    public List<Transaction> filter(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @RequestParam(required = false) BigDecimal minAmount,
            @RequestParam(required = false) BigDecimal maxAmount,
            @RequestParam(required = false) String category
    ) {
        return repository.filter(startDate, endDate, minAmount, maxAmount, category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        Transaction transaction = repository.findById(id);
        return ResponseEntity.ok(transaction);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody UpdateTransactionRequest request
    ) {
        repository.update(id, request);
        return ResponseEntity.ok("Transaction updated");
    }

}
