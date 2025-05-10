package com.example.finapp.BoundedContext.AiAssistant.Service;

import com.example.finapp.BoundedContext.Category.DTO.Category;
import com.example.finapp.BoundedContext.Category.Repository.CategoryRepository;
import com.example.finapp.BoundedContext.Transaction.DTO.Transaction;
import com.example.finapp.BoundedContext.Transaction.Repository.TransactionRepository;
import com.example.finapp.SharedContext.Security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.net.URI;
import org.json.JSONObject;

@Service
public class ChatService {

    @Value("${spring.ai.huggingface.api-key}")
    private String huggingFaceApiKey;

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final JwtService jwtService;

    public ChatService(TransactionRepository transactionRepository, CategoryRepository categoryRepository,
                       JwtService jwtService) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.jwtService = jwtService;
    }

    public String processUserMessage(String message, String tokenHeader) {
        String token = tokenHeader.replace("Bearer ", "").trim();

        int userId = jwtService.getUserIdFromToken(token);
        logger.info("Processing user message for userId: {}", userId);

        List<Transaction> transactions = transactionRepository.findAllByUserId(userId);

        StringBuilder context = new StringBuilder("User financial summary:\n");

        transactions.stream()
                .limit(10)
                .forEach(t -> {
                    Category category = categoryRepository.findById(t.getCategory());
                    String categoryType = category != null ? category.getType() : "Unknown";

                    context.append("• ")
                            .append(category != null ? category.getName() : "Unknown category")
                            .append(" (Type: ").append(categoryType)
                            .append("): ")
                            .append(t.getAmount())
                            .append(" on ")
                            .append(t.getCreatedAt())
                            .append("\n");
                });

        context.append("\nUser question: ").append(message);

        return askHuggingFace(context.toString());
    }

    private String askHuggingFace(String prompt) {
        logger.info("Sending request to Hugging Face with prompt: {}", prompt);

        HttpClient client = HttpClient.newHttpClient();

        JSONObject json = new JSONObject();
        json.put("inputs", prompt);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api-inference.huggingface.co/models/gpt-2"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + huggingFaceApiKey)
                .POST(HttpRequest.BodyPublishers.ofString(json.toString()))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            logger.info("Received response from Hugging Face. Status code: {}", response.statusCode());
            if (response.statusCode() != 200) {
                logger.error("Error from Hugging Face API: {}", response.body());
                return "Sorry, I couldn't process your request.";
            }

            JSONObject responseJson = new JSONObject(response.body());
            return responseJson.getJSONArray("generated_text").getString(0);

        } catch (Exception e) {
            logger.error("Error while making request to Hugging Face", e);
            return "Sorry, I couldn't process your request.";
        }
    }
}
