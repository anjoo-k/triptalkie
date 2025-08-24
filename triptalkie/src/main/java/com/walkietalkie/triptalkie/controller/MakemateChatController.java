package com.walkietalkie.triptalkie.controller;

import java.util.List;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.walkietalkie.triptalkie.domain.ChatMessage;
import com.walkietalkie.triptalkie.domain.ChatRoom;
import com.walkietalkie.triptalkie.service.ChatService;
import com.walkietalkie.triptalkie.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/makemate/chat")
public class MakemateChatController {

	// [] 의존성 주입
	private final ChatService chatService;
	private final MemberService memberService;

	public MakemateChatController(ChatService chatService,
			MemberService memberService) {
		this.chatService = chatService;
		this.memberService = memberService;
	}

	// 채팅 신청 페이지 이동
	// http://localhost:8080/makemate/chat/requestpage
	@GetMapping("/requestpage")
	public String chatRequestPage(Long makemateIdx, HttpSession session,
			Model model) {

		return "pages/makemate-chat/request-chat";
		// 뷰 page로 이동
	}

	// 채팅 신청 요청 처리 (채팅방 생성 or 조회 후 채팅방 페이지 이동)
	// http://localhost:8080/makemate/chat/request
	@PostMapping("/request")
	public String chatRequest(@RequestParam Long makamateIdx,
			@RequestParam String targetMemberId, HttpSession session,
			Model model) {
		String loginId = memberService.getLoginId(session);
		// 로그인한 ID 반환받아서 변수에 저장

		ChatRoom room = chatService.createOrGetChatRoom(makamateIdx, loginId,
				targetMemberId);
		// 채팅방을 만들거나 이미 있으면 채팅방 객체를 반환하는 createOrGetChatRoom 매서드 사용

		model.addAttribute("chatroomIdx", room.getIdx());
		// room 객체에서 chatroomIdx 받아옴
		model.addAttribute("loginId", loginId);
		model.addAttribute("chatRoom", room); // 필요하면 전체 객체 전달
		System.out.println("채팅방 idx : " + room.getIdx());

		return "pages/makemate-chat/chatroom-chat";
		// model 객체에 저장된 chatroomIdx, loginId 변수의 값을 이 페이지에 전달
		// 컨트롤러 url로 이동
	}

	// 채팅 목록
	// http://localhost:8080/makemate/chat/list
	@GetMapping("/list")
	public String chatListPage(@RequestParam String loginId, Model model) {
		List<ChatRoom> rooms = chatService.getChatRoomsByMember(loginId);
		model.addAttribute("rooms", rooms);
		model.addAttribute("loginId", loginId);
		return "pages/makemate-chat/list-chat";
	}
	
    // WebSocket 메시지 처리
    @MessageMapping("/send")
    @SendTo("/topic/chatroom")
    public ChatMessage handleMessage(ChatMessage message) {
        // 메시지의 chatroomIdx가 존재하는지 확인
        ChatRoom room = chatService.findRoomByChatRoomIdx(message.getChatroomIdx());
        if(room == null) throw new IllegalArgumentException("존재하지 않는 채팅방입니다.");

        chatService.addMessage(message);
        return message; // 구독자에게 전달
    }

 // 채팅방 메시지 조회 (AJAX)
    @GetMapping("/messages")
    @ResponseBody
    public List<ChatMessage> getMessages(@RequestParam Long chatroomIdx) {
        return chatService.getMessages(chatroomIdx);
    }
}
