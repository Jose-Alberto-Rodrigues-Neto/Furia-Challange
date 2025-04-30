package com.example.chatfuria.controllers;

import com.example.chatfuria.services.OllamaService;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private OllamaService ollamaService;

    @PostMapping("/ask")
    public String ask(@RequestBody String body) throws Exception{
        return ollamaService.askOllama(body);
    }
}
