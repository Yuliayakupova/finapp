package com.example.finapp.BoundedContext.Transaction.Controller;

import com.example.finapp.BoundedContext.Category.Repository.CategoryRepository;
import com.example.finapp.BoundedContext.Limit.Repository.LimitRepository;
import com.example.finapp.BoundedContext.Transaction.DTO.Transaction;
import com.example.finapp.BoundedContext.Transaction.Repository.TransactionRepository;
import com.example.finapp.BoundedContext.Transaction.Request.CreateTransactionRequest;
import com.example.finapp.BoundedContext.Transaction.Request.UpdateTransactionRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
    private final CategoryRepository categoryRepository;
    private final LimitRepository limitRepository;

    public TransactionController(TransactionRepository transactionRepository, CategoryRepository categoryRepository, LimitRepository limitRepository) {
        this.repository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.limitRepository = limitRepository;
    }

    @GetMapping
    public List<Transaction> getAll() {
        return repository.getAll();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateTransactionRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = (int) authentication.getPrincipal();

        if (!categoryRepository.isCategoryBelongsToUser(request.getCategoryId(), userId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Category does not belong to the user.");
        }

        repository.create(request, userId);
        limitRepository.increaseUsedAmount(request.getAmount(), userId, request.getCategoryId());

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
            @RequestParam(required = false) int category
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
