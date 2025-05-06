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

/**
 * The LimitController class handles HTTP requests for limit-related operations.
 * It includes creating, updating, deleting, and retrieving limits for the authenticated user.
 */
@RestController
@RequestMapping("/api/v1/limits")
public class LimitController {

    private final LimitRepository limitRepository;

    /**
     * Constructor for LimitController that initializes the LimitRepository.
     *
     * @param limitRepository the repository that interacts with the database for limit operations.
     */
    public LimitController(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    /**
     * Creates a new limit for the authenticated user.
     *
     * @param request the details of the limit to be created.
     * @return a ResponseEntity indicating the success of the operation.
     */
    @PostMapping
    public ResponseEntity<Void> createLimit(@RequestBody CreateLimitRequest request) {
        // Get the authenticated user's ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = (int) authentication.getPrincipal();

        // Create the limit in the repository
        limitRepository.create(request, userId);
        return ResponseEntity.ok().build(); // Return a 200 OK response
    }

    /**
     * Updates an existing limit for the specified user.
     *
     * @param limitId the ID of the limit to be updated.
     * @param request the new details for the limit.
     * @param userId the ID of the user making the request.
     * @return a ResponseEntity indicating the success of the operation.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLimit(@PathVariable("id") int limitId,
                                            @RequestBody CreateLimitRequest request,
                                            @RequestHeader("userId") int userId) {
        // Update the limit in the repository
        limitRepository.update(limitId, request, userId);
        return ResponseEntity.ok().build(); // Return a 200 OK response
    }

    /**
     * Deletes a specific limit for the specified user.
     *
     * @param limitId the ID of the limit to be deleted.
     * @param userId the ID of the user making the request.
     * @return a ResponseEntity indicating the success of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLimit(@PathVariable("id") int limitId, @RequestHeader("userId") int userId) {
        // Delete the limit from the repository
        limitRepository.delete(limitId, userId);
        return ResponseEntity.noContent().build(); // Return a 204 No Content response
    }

    /**
     * Retrieves all the limits for the authenticated user.
     *
     * @return a ResponseEntity containing a list of the user's limits.
     */
    @GetMapping
    public ResponseEntity<List<Limit>> getUserLimits() {
        // Get the authenticated user's ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = (int) authentication.getPrincipal();

        // Retrieve the limits for the user from the repository
        List<Limit> limits = limitRepository.getByUser(userId);
        return ResponseEntity.ok(limits); // Return a 200 OK response with the list of limits
    }
}
