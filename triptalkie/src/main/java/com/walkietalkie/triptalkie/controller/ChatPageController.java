package com.walkietalkie.triptalkie.controller;

import com.walkietalkie.triptalkie.domain.ChatRoom;
import com.walkietalkie.triptalkie.service.ChatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChatPageController {

  private final ChatService chatService;

  public ChatPageController(ChatService chatService) {
    this.chatService = chatService;
  }

  // 채팅 신청 페이지
  @GetMapping("/chat/request")
  public String chatRequestPage(@RequestParam Long makamateIdx, Model model) {
    model.addAttribute("makamateIdx", makamateIdx);
    return "chat_request";
  }

  // 채팅 목록
  @GetMapping("/chat/list")
  public String chatListPage(@RequestParam String memberId, Model model) {
    List<ChatRoom> rooms = chatService.getChatRoomsByMember(memberId);
    model.addAttribute("rooms", rooms);
    model.addAttribute("memberId", memberId);
    return "chat_list";
  }

  // 채팅방 페이지
  @GetMapping("/chat/room")
  public String chatRoomPage(@RequestParam Long chatroomId,
                             @RequestParam String memberId,
                             Model model) {
    model.addAttribute("chatroomId", chatroomId);
    model.addAttribute("memberId", memberId);
    return "chatroom";
  }
}
