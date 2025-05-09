package com.example.finapp.BoundedContext.Moneybox.Controller;

import com.example.finapp.BoundedContext.Moneybox.DTO.Moneybox;
import com.example.finapp.BoundedContext.Moneybox.Repository.MoneyboxRepository;
import com.example.finapp.BoundedContext.Moneybox.Request.AddTransactionRequest;
import com.example.finapp.BoundedContext.Moneybox.Request.CreateMoneyboxRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/moneyboxes")
public class MoneyboxController {

    private final MoneyboxRepository moneyboxRepository;

    @Autowired
    public MoneyboxController(MoneyboxRepository moneyboxRepository) {
        this.moneyboxRepository = moneyboxRepository;
    }

    @PostMapping
    public void createMoneybox(@RequestBody CreateMoneyboxRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = (int) authentication.getPrincipal();
        moneyboxRepository.create(request, userId);
    }

    @GetMapping
    public List<Moneybox> getAllMoneyboxes() {
        return moneyboxRepository.getAll();
    }

    @DeleteMapping("/{id}/return")
    public ResponseEntity<String> deleteMoneyboxAndReturnFunds(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = (int) authentication.getPrincipal();

        try {
            moneyboxRepository.deleteMoneybox(id, userId);
            return ResponseEntity.ok("Moneybox deleted and funds returned to income.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping("/{moneyboxId}/transactions")
    public ResponseEntity<String> addTransactionToMoneybox(
            @PathVariable int moneyboxId,
            @RequestBody AddTransactionRequest request
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = (int) authentication.getPrincipal();

        if (!moneyboxRepository.isMoneyboxBelongsToUser(moneyboxId, userId)) {
            return ResponseEntity.status(403).body("Moneybox does not belong to the user.");
        }

        BigDecimal availableBudget = moneyboxRepository.getUserIncome(userId);
        if (request.getAmount().compareTo(availableBudget) > 0) {
            return ResponseEntity.badRequest().body("Amount exceeds available budget.");
        }

        moneyboxRepository.addTransactionToMoneybox(
                moneyboxId,
                request.getAmount(),
                request.getDescription(),
                request.getDate(),
                userId
        );

        return ResponseEntity.ok("Transaction added to moneybox.");
    }
}
