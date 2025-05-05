package com.example.finapp.BoundedContext.Moneybox.Service;

import com.example.finapp.BoundedContext.Moneybox.DTO.Moneybox;
import com.example.finapp.BoundedContext.Moneybox.Repository.MoneyboxRepository;
import com.example.finapp.BoundedContext.Moneybox.Request.CreateMoneyboxRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoneyboxService {
    private final MoneyboxRepository moneyboxRepository;

    @Autowired
    public MoneyboxService(MoneyboxRepository moneyboxRepository) {
        this.moneyboxRepository = moneyboxRepository;
    }

    public Moneybox createMoneybox(CreateMoneyboxRequest request) {
        Moneybox moneybox = new Moneybox();
        moneybox.setName(request.getName());
        moneybox.setGoal(request.getGoal());
        moneybox.setTargetDate(request.getTargetDate());
        moneybox.setCurrentAmount(BigDecimal.ZERO);

        moneybox = moneyboxRepository.save(moneybox);

        return convertToDTO(moneybox);
    }

    public List<Moneybox> getAllMoneyboxes() {
        List<Moneybox> moneyboxes = moneyboxRepository.findAll();
        return moneyboxes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public void deleteMoneybox(Long id) {
        moneyboxRepository.deleteById(id);
    }

    private Moneybox convertToDTO(Moneybox moneybox) {
        Moneybox dto = new Moneybox();
        dto.setId(moneybox.getId());
        dto.setName(moneybox.getName());
        dto.setGoal(moneybox.getGoal());
        dto.setCurrentAmount(moneybox.getCurrentAmount());
        dto.setTargetDate(moneybox.getTargetDate());
        return dto;
    }
}
