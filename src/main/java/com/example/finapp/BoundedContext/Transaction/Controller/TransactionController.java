package com.example.finapp.BoundedContext.Transaction.Controller;

import com.example.finapp.BoundedContext.Transaction.DTO.Transaction;
import com.example.finapp.BoundedContext.Transaction.Repository.TransactionRepository;
import com.example.finapp.BoundedContext.Transaction.Request.CreateTransactionRequest;
import com.example.finapp.BoundedContext.Transaction.Request.UpdateTransactionRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionRepository repository;

    public TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Transaction> getAll() {
        return repository.getAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateTransactionRequest request) {
        repository.create(request);
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
