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
  /**
   * 메시지 수신 처리 메서드
   * 클라이언트가 보낸 메세지를 받고 DB에 저장 후 다른 모든 세션에 브로드캐스트
   */
  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    ChatMessage chatMsg = objectMapper.readValue(message.getPayload(), ChatMessage.class);
    chatService.addMessage(chatMsg);

    String broadcast = chatMsg.getMemberId() + ": " + chatMsg.getContent();
    for (WebSocketSession s : sessions) {
      if (s.isOpen()) s.sendMessage(new TextMessage(broadcast));
    }
  }

  /**
   * 연결 처리 종료 메서드
   */
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    sessions.remove(session);
  }
}
