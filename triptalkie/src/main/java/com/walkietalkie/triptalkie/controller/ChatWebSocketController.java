package com.walkietalkie.triptalkie.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.walkietalkie.triptalkie.domain.ChatMessage;
import com.walkietalkie.triptalkie.service.ChatService;

@Controller
public class ChatWebSocketController {

    private final ChatService chatService;

    public ChatWebSocketController(ChatService chatService) {
        this.chatService = chatService;
    }

//    @MessageMapping("/send") // 클라이언트 send 경로
//    @SendTo("/topic/chatroom") // 구독 경로
//    public ChatMessage handleMessage(ChatMessage message) {
//        chatService.addMessage(message); // DB 저장
//        return message; // 구독자에게 전달
//    }
}
