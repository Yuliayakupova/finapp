package com.example.finapp.BoundedContext.AiAssistant.Service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AiSuggestionService {

    private final ChatClient chatClient;

    @Autowired
    public AiSuggestionService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    /**
     * Method to get a suggestion from the AI model based on a prompt.
     * @param prompt The user input that will be sent to the AI.
     * @return The AI's response as a String.
     */
    public String getSuggestion(String prompt) {
        // Construct a request to the AI client
        ChatClient.ChatClientRequestSpec requestSpec = chatClient.prompt(prompt);
        ChatClient.CallResponseSpec response = requestSpec.call();
        return response.content();
    }
}
