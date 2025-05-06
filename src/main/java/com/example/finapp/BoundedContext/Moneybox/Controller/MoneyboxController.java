package com.example.finapp.BoundedContext.Moneybox.Controller;

import com.example.finapp.BoundedContext.Moneybox.DTO.Moneybox;
import com.example.finapp.BoundedContext.Moneybox.Repository.MoneyboxRepository;
import com.example.finapp.BoundedContext.Moneybox.Request.AddTransactionRequest;
import com.example.finapp.BoundedContext.Moneybox.Request.CreateMoneyboxRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * The MoneyboxController class handles HTTP requests related to the creation,
 * retrieval, deletion, and transactions of moneyboxes. It ensures that users
 * can only interact with moneyboxes they own by checking the user's authentication
 * information.
 */
@RestController
@RequestMapping("/api/v1/moneyboxes")
public class MoneyboxController {

    private final MoneyboxRepository moneyboxRepository;

    /**
     * Constructs a MoneyboxController instance.
     *
     * @param moneyboxRepository the repository used for database interactions.
     */
    @Autowired
    public MoneyboxController(MoneyboxRepository moneyboxRepository) {
        this.moneyboxRepository = moneyboxRepository;
    }

    /**
     * Creates a new moneybox based on the provided request.
     * The user is identified from the authentication context, and the moneybox is created for that user.
     *
     * @param request the CreateMoneyboxRequest containing the details of the new moneybox.
     */
    @PostMapping
    public void createMoneybox(@RequestBody CreateMoneyboxRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = (int) authentication.getPrincipal();

        moneyboxRepository.create(request, userId);
    }

    /**
     * Retrieves all moneyboxes associated with the authenticated user.
     *
     * @return a list of all moneyboxes.
     */
    @GetMapping
    public List<Moneybox> getAllMoneyboxes() {
        return moneyboxRepository.getAll();
    }

    /**
     * Deletes a moneybox by its ID.
     * Only the user who owns the moneybox can delete it.
     *
     * @param id the ID of the moneybox to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteMoneybox(@PathVariable int id) {
        moneyboxRepository.deleteById(id);
    }

    /**
     * Adds a transaction to a specific moneybox. The user must be authenticated and must own the moneybox
     * in order to add the transaction.
     *
     * @param moneyboxId the ID of the moneybox to which the transaction will be added.
     * @param request the AddTransactionRequest containing the details of the transaction.
     */
    @PostMapping("/{moneyboxId}/transactions")
    public void addTransactionToMoneybox(
            @PathVariable int moneyboxId,
            @RequestBody AddTransactionRequest request
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = (int) authentication.getPrincipal();

        if (moneyboxRepository.isMoneyboxBelongsToUser(moneyboxId, userId)) {
            moneyboxRepository.addTransactionToMoneybox(
                    moneyboxId,
                    request.getAmount(),
                    request.getDescription(),
                    request.getDate(),
                    userId
            );
        } else {
            throw new IllegalArgumentException("Moneybox does not belong to the user.");
        }
    }
}
