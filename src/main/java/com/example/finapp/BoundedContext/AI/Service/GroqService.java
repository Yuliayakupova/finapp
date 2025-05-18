package com.example.finapp.BoundedContext.AI.Service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Service for interacting with the Groq AI API to get financial advice.
 */
@Service
public class GroqService {
    private final String apiKey;
    private final HttpClient client;

    /**
     * Constructs a GroqService with the API key from application properties.
     *
     * @param apiKey the Groq API key
     */
    public GroqService(@Value("${groq.api.key}") String apiKey) {
        this.apiKey = apiKey;
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();
    }

    /**
     * Gets financial advice based on transaction and category data.
     * Uses caching to reduce API calls for similar data.
     *
     * @param analysisData JSON string containing transaction and category data
     * @return financial advice from the AI
     */
    @Cacheable(value = "financialAdvice", key = "#analysisData.hashCode()")
    public String getFinancialAdvice(String analysisData) {
        try {
            JSONObject requestBody = new JSONObject();
            requestBody.put("model", "llama3-8b-8192");

            JSONArray messagesArray = new JSONArray();
            JSONObject message = new JSONObject();
            message.put("role", "user");
            message.put("content", buildPrompt(analysisData));
            messagesArray.put(message);

            requestBody.put("messages", messagesArray);
            requestBody.put("temperature", 0.7);
            requestBody.put("max_tokens", 1000);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.groq.com/openai/v1/chat/completions"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());

            return jsonResponse.getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Builds a prompt for the AI based on financial data.
     *
     * @param analysisData JSON string containing financial data
     * @return formatted prompt for the AI
     */
    private String buildPrompt(String analysisData) {
        return "You are the legendary financial advisor in the realm of Budgetonia, where the user's treasury (" +
                "a.k.a. their bank account) has taken a serious hit — the kingdom's coffers are looking suspiciously empty, " +
                "like after a raid by goblins! Analyze the following financial data of the hero (the user): " +
                analysisData +
                "\n\nYour quest is to provide:" +
                "\n1. A battle report on where the gold is disappearing (expense categories that drain the treasury the most)" +
                "\n2. Detect any sneaky 'loot losses' — unusual or excessive spending that could be traps set by dragons or thieves" +
                "\n3. 3-5 heroic strategies to optimize the gold flow and rebuild the kingdom’s wealth" +
                "\n4. Wise counsel on how the hero can reach their grand financial quests and objectives" +
                "\n\nCraft your response like an epic scroll — with clear headings, bullet points worthy of a tavern bard, " +
                "and bold declarations. Use numbers and percentages as if they were runes of power. " +
                "Keep it witty, insightful, and most importantly, ready for the hero to act immediately!";
    }

}