package com.example.finapp.BoundedContext.AiAssistant.Controller;

import com.example.finapp.BoundedContext.AiAssistant.DTO.AiRequest;
import com.example.finapp.BoundedContext.AiAssistant.DTO.AiResponse;
import com.example.finapp.BoundedContext.AiAssistant.Service.AiSuggestionService;
import org.springframework.web.bind.annotation.*;

/**
 * Controller that handles AI-related requests.
 */
@RestController
@RequestMapping("/api/v1/ai")
public class AiSuggestionController {

    private final AiSuggestionService aiSuggestionService;

    public AiSuggestionController(AiSuggestionService aiSuggestionService) {
        this.aiSuggestionService = aiSuggestionService;
    }

    /**
     * Endpoint for getting suggestions from the AI assistant.
     *
     * @param request the input request containing the user's prompt
     * @return the AI-generated response
     */
    @PostMapping("/suggest")
    public AiResponse getSuggestion(@RequestBody AiRequest request) {
        String result = aiSuggestionService.getSuggestions(request.getPrompt());
        return new AiResponse(result);
    }
}
