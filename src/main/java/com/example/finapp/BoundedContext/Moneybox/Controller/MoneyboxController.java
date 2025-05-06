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

    @DeleteMapping("/{id}")
    public void deleteMoneybox(@PathVariable int id) {
        moneyboxRepository.deleteById(id);
    }

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