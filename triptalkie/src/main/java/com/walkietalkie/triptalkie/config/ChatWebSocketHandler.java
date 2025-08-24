package com.walkietalkie.triptalkie.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walkietalkie.triptalkie.domain.ChatMessage;
import com.walkietalkie.triptalkie.service.ChatService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

  private final ChatService chatService;
  private final ObjectMapper objectMapper = new ObjectMapper();
  private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

  public ChatWebSocketHandler(ChatService chatService) {
    this.chatService = chatService;
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    sessions.add(session);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    ChatMessage chatMsg = objectMapper.readValue(message.getPayload(), ChatMessage.class);
    chatService.addMessage(chatMsg);

    String broadcast = chatMsg.getMemberId() + ": " + chatMsg.getContent();
    for (WebSocketSession s : sessions) {
      if (s.isOpen()) s.sendMessage(new TextMessage(broadcast));
    }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    sessions.remove(session);
  }
}
