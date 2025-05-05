package com.example.finapp.BoundedContext.Moneybox.Controller;

import com.example.finapp.BoundedContext.Moneybox.DTO.Moneybox;
import com.example.finapp.BoundedContext.Moneybox.Request.CreateMoneyboxRequest;
import com.example.finapp.BoundedContext.Moneybox.Request.UpdateMoneyboxRequest;
import com.example.finapp.BoundedContext.Moneybox.Service.MoneyboxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/moneyboxes")
public class MoneyboxController {
    private final MoneyboxService moneyboxService;

    @Autowired
    public MoneyboxController(MoneyboxService moneyboxService) {
        this.moneyboxService = moneyboxService;
    }

    @PostMapping
    public Moneybox createMoneybox(@RequestBody CreateMoneyboxRequest request) {
        return moneyboxService.createMoneybox(request);
    }

    @GetMapping
    public List<Moneybox> getAllMoneyboxes() {
        return moneyboxService.getAllMoneyboxes();
    }

    @DeleteMapping("/{id}")
    public void deleteMoneybox(@PathVariable Long id) {
        moneyboxService.deleteMoneybox(id);
    }
}