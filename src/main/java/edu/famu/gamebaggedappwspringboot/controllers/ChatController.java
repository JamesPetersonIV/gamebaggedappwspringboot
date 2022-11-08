package edu.famu.gamebaggedappwspringboot.controllers;


import edu.famu.gamebaggedappwspringboot.models.Chat;
import edu.famu.gamebaggedappwspringboot.services.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RequestMapping("/api/chat")
@RestController
public class ChatController {
    ChatService chatService;

    public ChatController(ChatService service) {
        this.chatService = service;
    }

    //
    @GetMapping("/users/{id}")
    public ArrayList<Chat> getChatByUsers (@PathVariable(name="id") String id) throws ExecutionException, InterruptedException {
        return chatService.retrieveChatByUsers(id);
    }
}
