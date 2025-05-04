package com.example.chatfuria.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.chatfuria.services.OllamaService;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private OllamaService ollamaService;

    @PostMapping("/ask")
    public String ask(@RequestBody String body) throws Exception{
        return ollamaService.askOllama(body);
    }

    @PostMapping("/fallen")
    public String fallen(@RequestBody String body) throws Exception{
        return ollamaService.askFallen(body);
    }
}
