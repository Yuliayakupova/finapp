package com.example.finapp.BoundedContext.AiAssistant.DTO;

/**
 * Data Transfer Object (DTO) for the request made to the AI assistant.
 */
public class AiRequest {
    private String prompt;

    public AiRequest() {}

    public AiRequest(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
}
