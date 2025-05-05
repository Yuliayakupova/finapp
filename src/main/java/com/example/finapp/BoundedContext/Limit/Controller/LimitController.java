package com.example.finapp.BoundedContext.Limit.Controller;

import com.example.finapp.BoundedContext.Limit.DTO.Limit;
import com.example.finapp.BoundedContext.Limit.Repository.LimitRepository;
import com.example.finapp.BoundedContext.Limit.Request.CreateLimitRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/limits")
public class LimitController {
    private final LimitRepository limitRepository;

    public LimitController(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    @PostMapping
    public ResponseEntity<Void> createLimit(@RequestBody CreateLimitRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = (int) authentication.getPrincipal();

        limitRepository.create(request, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLimit(@PathVariable("id") int limitId,
                                            @RequestBody CreateLimitRequest request,
                                            @RequestHeader("userId") int userId) {
        limitRepository.update(limitId, request, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLimit(@PathVariable("id") int limitId, @RequestHeader("userId") int userId) {
        limitRepository.delete(limitId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Limit>> getUserLimits() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = (int) authentication.getPrincipal();

        List<Limit> limits = limitRepository.getByUser(userId);
        return ResponseEntity.ok(limits);
    }
}
