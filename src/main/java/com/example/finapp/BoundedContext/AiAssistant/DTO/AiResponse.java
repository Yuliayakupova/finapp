package com.example.finapp.BoundedContext.AiAssistant.DTO;

/**
 * Data Transfer Object (DTO) for the response from the AI assistant.
 */
public class AiResponse {
    private String result;

    public AiResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
