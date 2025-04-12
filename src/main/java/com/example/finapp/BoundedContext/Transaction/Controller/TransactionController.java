package com.example.finapp.BoundedContext.Transaction.Controller;

import com.example.finapp.BoundedContext.Transaction.DTO.Transaction;
import com.example.finapp.BoundedContext.Transaction.Repository.TransactionRepository;
import com.example.finapp.BoundedContext.Transaction.Request.CreateTransactionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
