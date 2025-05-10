package com.example.finapp.BoundedContext.AiAssistant.Controller;

import com.example.finapp.BoundedContext.AiAssistant.DTO.ChatRequest;
import com.example.finapp.BoundedContext.AiAssistant.Service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final ChatService chatService;
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody ChatRequest request, @RequestHeader("Authorization") String token) {
        try {
            logger.info("Received message: {}", request.getMessage());

            String response = chatService.processUserMessage(request.getMessage(), token);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error processing message", e);
            return ResponseEntity.status(500).body("Sorry, an error occurred while processing your request.");
        }
    }
}
