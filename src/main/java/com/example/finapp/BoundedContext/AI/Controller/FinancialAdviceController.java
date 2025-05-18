package com.example.finapp.BoundedContext.AI.Controller;

import com.example.finapp.BoundedContext.AI.DTO.FinancialAdviceResponse;
import com.example.finapp.BoundedContext.AI.Service.GroqService;
import com.example.finapp.BoundedContext.Category.DTO.Category;
import com.example.finapp.BoundedContext.Category.Repository.CategoryRepository;
import com.example.finapp.BoundedContext.Transaction.DTO.Transaction;
import com.example.finapp.BoundedContext.Transaction.Repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller for AI-powered financial advice.
 */
@RestController
@RequestMapping("/api/v1/financial-advice")
public class FinancialAdviceController {
    private static final Logger logger = LoggerFactory.getLogger(FinancialAdviceController.class);
    private final GroqService groqService;
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final ObjectMapper objectMapper;

    /**
     * Constructs a FinancialAdviceController with required dependencies.
     *
     * @param groqService AI service for financial advice
     * @param transactionRepository repository for transaction data
     * @param categoryRepository repository for category data
     * @param objectMapper JSON object mapper
     */
    public FinancialAdviceController(
            GroqService groqService,
            TransactionRepository transactionRepository,
            CategoryRepository categoryRepository,
            ObjectMapper objectMapper) {
        this.groqService = groqService;
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.objectMapper = objectMapper;
    }

    /**
     * Gets AI-powered financial advice based on user's transaction history.
     *
     * @param startDate optional start date for filtering transactions
     * @param endDate optional end date for filtering transactions
     * @return financial advice response
     */
    @GetMapping
    public ResponseEntity<FinancialAdviceResponse> getFinancialAdvice(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        logger.info(">>> Advice requested");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int userId = Integer.parseInt(authentication.getPrincipal().toString());

        // Get user's transactions
        List<Transaction> transactions;
        if (startDate != null || endDate != null) {
            transactions = transactionRepository.filter(userId,startDate, endDate, null, null, 0);
        } else {
            transactions = transactionRepository.getAllByUserId(userId);
        }

        // Get user's expense categories
        List<Category> expenseCategories = categoryRepository.getExpensesByUser(userId);

        // Prepare data for AI analysis
        Map<String, Object> analysisData = prepareAnalysisData(transactions, expenseCategories);

        try {
            // Convert data to JSON for the AI service
            String analysisDataJson = objectMapper.writeValueAsString(analysisData);

            // Get advice from AI
            String advice = groqService.getFinancialAdvice(analysisDataJson);

            return ResponseEntity.ok(new FinancialAdviceResponse(advice));
        } catch (JsonProcessingException e) {
            return ResponseEntity.internalServerError().body(
                    new FinancialAdviceResponse("Error: " + e.getMessage())
            );
        }
    }

    /**
     * Prepares transaction and category data for AI analysis.
     *
     * @param transactions list of user transactions
     * @param categories list of user expense categories
     * @return map of prepared data for analysis
     */
    private Map<String, Object> prepareAnalysisData(List<Transaction> transactions, List<Category> categories) {
        Map<String, Object> analysisData = new HashMap<>();

        // Add transactions
        analysisData.put("transactions", transactions);

        // Add categories
        analysisData.put("categories", categories);

        // Calculate spending by category
        Map<Integer, BigDecimal> spendingByCategory = new HashMap<>();
        for (Transaction transaction : transactions) {
            int categoryId = transaction.getCategory();
            BigDecimal amount = transaction.getAmount();

            spendingByCategory.put(
                    categoryId,
                    spendingByCategory.getOrDefault(categoryId, BigDecimal.ZERO).add(amount)
            );
        }

        // Create category spending summary with names
        List<Map<String, Object>> categorySummary = categories.stream()
                .filter(category -> spendingByCategory.containsKey(category.getId()))
                .map(category -> {
                    Map<String, Object> summary = new HashMap<>();
                    summary.put("id", category.getId());
                    summary.put("name", category.getName());
                    summary.put("type", category.getType());
                    summary.put("totalSpent", spendingByCategory.get(category.getId()));
                    return summary;
                })
                .collect(Collectors.toList());

        analysisData.put("categorySummary", categorySummary);

        // Calculate total spending
        BigDecimal totalSpending = transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        analysisData.put("totalSpending", totalSpending);

        // Add time period info
        if (!transactions.isEmpty()) {
            LocalDateTime earliestTransaction = transactions.stream()
                    .map(Transaction::getCreatedAt)
                    .min(LocalDateTime::compareTo)
                    .orElse(null);

            LocalDateTime latestTransaction = transactions.stream()
                    .map(Transaction::getCreatedAt)
                    .max(LocalDateTime::compareTo)
                    .orElse(null);

            analysisData.put("periodStart", earliestTransaction);
            analysisData.put("periodEnd", latestTransaction);
        }

        return analysisData;
    }
}