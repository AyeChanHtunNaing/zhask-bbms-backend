package com.bbms.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.bbms.model.ChatMessage;

@Controller
public class ChatController {

    @MessageMapping("/send")
    @SendTo("/topic/chat")
    public ChatMessage sendMessage(ChatMessage message) {
        return message;
    }
}