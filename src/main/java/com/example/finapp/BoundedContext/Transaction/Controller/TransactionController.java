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

/**
 * Controller responsible for handling HTTP requests related to transactions.
 * Provides endpoints to create, retrieve, update, delete, and filter transactions.
 */
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionRepository repository;
    private final CategoryRepository categoryRepository;
    private final LimitRepository limitRepository;

    /**
     * Constructs a TransactionController with the required repositories.
     *
     * @param transactionRepository repository for managing transactions
     * @param categoryRepository    repository for verifying category ownership
     * @param limitRepository       repository for updating spending limits
     */
    public TransactionController(TransactionRepository transactionRepository, CategoryRepository categoryRepository, LimitRepository limitRepository) {
        this.repository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.limitRepository = limitRepository;
    }

    /**
     * Retrieves all transactions.
     *
     * @return list of all transactions
     */
    @GetMapping
    public List<Transaction> getAll() {
        return repository.getAll();
    }

    /**
     * Creates a new transaction if the category belongs to the authenticated user.
     * Also updates the used amount in the limit for the specified category.
     *
     * @param request the transaction creation request
     * @return response indicating success or failure
     */
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

    /**
     * Deletes a transaction by its ID.
     *
     * @param id the ID of the transaction to delete
     * @return response confirming deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        repository.deleteById(id);
        return ResponseEntity.ok("Transaction deleted");
    }

    /**
     * Filters transactions based on optional criteria such as date range, amount range, and category.
     *
     * @param startDate start date for filtering (optional)
     * @param endDate   end date for filtering (optional)
     * @param minAmount minimum transaction amount (optional)
     * @param maxAmount maximum transaction amount (optional)
     * @param category  category ID for filtering (optional)
     * @return list of filtered transactions
     */
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

    /**
     * Retrieves a transaction by its ID.
     *
     * @param id the ID of the transaction
     * @return the transaction, if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable int id) {
        Transaction transaction = repository.findById(id);
        return ResponseEntity.ok(transaction);
    }

    /**
     * Updates an existing transaction.
     *
     * @param id      the ID of the transaction to update
     * @param request the updated transaction data
     * @return response confirming update
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable int id,
            @RequestBody UpdateTransactionRequest request
    ) {
        repository.update(id, request);
        return ResponseEntity.ok("Transaction updated");
    }
}
