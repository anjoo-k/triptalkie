package com.walkietalkie.triptalkie.controller;

import java.util.List;
import java.util.Map;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.walkietalkie.triptalkie.domain.ChatMessage;
import com.walkietalkie.triptalkie.domain.ChatRoom;
import com.walkietalkie.triptalkie.domain.Makemate;
import com.walkietalkie.triptalkie.service.ChatService;
import com.walkietalkie.triptalkie.service.MakemateService;
import com.walkietalkie.triptalkie.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/makemate/chat")
public class MakemateChatController {

	// [] 의존성 주입
	private final ChatService chatService;
	private final MemberService memberService;
	private final MakemateService makemateService;
	private final SimpMessagingTemplate messagingTemplate; // 추가

	public MakemateChatController(ChatService chatService, MemberService memberService, MakemateService makemateService,
			SimpMessagingTemplate messagingTemplate) {
		this.chatService = chatService;
		this.memberService = memberService;
		this.makemateService = makemateService;
		this.messagingTemplate = messagingTemplate;
	}

	// 채팅 신청 페이지 이동
	// http://localhost:8080/makemate/chat/requestpage
	@GetMapping("/requestpage")
	public String chatRequestPage(Long makemateIdx, HttpSession session, Model model) {

		return "pages/makemate-chat/request-chat";
		// 뷰 page로 이동
	}


	// 채팅방 생성 요청 처리 (리다이렉션으로 분리)
	// http://localhost:8080/makemate/chat/reques
	// 채팅 요청 받음
	// 동작을 위해서 값 3개 필요 loginId, makemateIdx, makemate.member_id
	//
	@PostMapping("/request")
	public RedirectView chatRequest(@RequestParam Long makemateIdx, HttpSession session, RedirectAttributes ra) {
	
		String loginId = memberService.getLoginId(session);
		// 로그인 아이디 가져오기

		Map<String, Object> combinedMap = makemateService.findMakemateByIdx(makemateIdx, loginId);
		Makemate makemate = (Makemate) combinedMap.get("makemate");
		
		String makemateHostId = makemate.getMemberId();
		// makemate 여행리더 id 가져오기
		
		
		// 만약 로그인 아이디와 방장의 아이디가 같으면 채팅방 생성 불가 -> 본인 글이므로
		// try-catch 처리 : 오류 발생 시키고 상세 페이지 이동
		try {
			if(loginId.equals(makemateHostId)) {
				ra.addFlashAttribute("errorMessage", "자기 자신과 채팅방을 만들 수 없습니다.");
	            RedirectView redirectView = new RedirectView();
				redirectView.setUrl("/makemate/detailPage/" + makemateIdx);
				return redirectView;
			}
		}catch (Exception e) {
	        ra.addFlashAttribute("errorMessage", "채팅방 생성 중 오류가 발생했습니다.");

			RedirectView redirectView = new RedirectView();
			redirectView.setUrl("/makemate/detailPage/" + makemateIdx);
			return redirectView;
		}
		


		ChatRoom room = chatService.createOrGetChatRoom(makemateIdx, makemateHostId, loginId);
		// makemateidx, 방장 id, 로그인 한 사람 id 받아서 채팅방 생성

		System.out.println("채팅방 idx : " + room.getIdx());

		// PRG 패턴 적용: 채팅방 URL로 리다이렉트
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/makemate/chat/room?chatroomIdx=" + room.getIdx());
		return redirectView;
	}

	// 채팅방 생성 요청 처리 (리다이렉션으로 분리)
	// http://localhost:8080/makemate/chat/request2
	@PostMapping("/request2")
	public RedirectView chatRequest_backup(@RequestParam Long makemateIdx, @RequestParam String targetMemberId,
			HttpSession session) {
		String loginId = memberService.getLoginId(session);

		ChatRoom room = chatService.createOrGetChatRoom(makemateIdx, loginId, targetMemberId);

		System.out.println("채팅방 idx : " + room.getIdx());

		// PRG 패턴 적용: 채팅방 URL로 리다이렉트
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/makemate/chat/room?chatroomIdx=" + room.getIdx());
		return redirectView;
	}

	// 채팅방 페이지를 보여주는 새로운 GET 메서드
	@GetMapping("/room")
	public String showChatRoom(@RequestParam Long chatroomIdx, HttpSession session, Model model) {
		String loginId = memberService.getLoginId(session);
		if (loginId == null) {
			return "redirect:/member/login";
		}

		ChatRoom room = chatService.findRoomByChatRoomIdx(chatroomIdx);
		if (room == null) {
			// 채팅방이 존재하지 않으면 에러 페이지 또는 목록 페이지로 리다이렉트
			return "redirect:/makemate/chat/list";
		}

		// 중요한 부분: 현재 로그인한 사용자가 이 채팅방의 참여자인지 확인
		if (!room.getMember1Id().equals(loginId) && !room.getMember2Id().equals(loginId)) {
			// 참여자가 아니면 접근 거부 또는 목록 페이지로 리다이렉트
			System.out.println("접근 권한 없음: " + loginId + "는 해당 채팅방의 멤버가 아닙니다.");
			return "redirect:/makemate/chat/list";
		}

		model.addAttribute("chatroomIdx", room.getIdx());
		model.addAttribute("loginId", loginId);
		model.addAttribute("chatRoom", room);

		return "pages/makemate-chat/chatroom-chat";
	}

	// 채팅 목록
	// http://localhost:8080/makemate/chat/list
	@GetMapping("/list")
	public String chatListPage(HttpSession session, Model model) {
		String loginId = memberService.getLoginId(session);
		if (loginId == null) {
			// 로그인 상태가 아니면 로그인 페이지로 리다이렉트
			return "redirect:/member/login"; // 예시 URL, 실제 로그인 페이지 URL로 변경
		}

		List<ChatRoom> rooms = chatService.getChatRoomsByMember(loginId);
		model.addAttribute("rooms", rooms);
		model.addAttribute("loginId", loginId);
		return "pages/makemate-chat/list-chat";
	}

	// WebSocket 메시지 처리
	@MessageMapping("/send") // 클라이언트에서 /app/send로 보냄
	public void handleMessage(ChatMessage message) {
		// 메시지의 chatroomIdx가 존재하는지 확인
		ChatRoom room = chatService.findRoomByChatRoomIdx(message.getChatroomIdx());
		if (room == null)
			throw new IllegalArgumentException("존재하지 않는 채팅방입니다.");

		chatService.addMessage(message);

		// 특정 채팅방을 구독한 클라이언트에게만 메시지 전송
		messagingTemplate.convertAndSend("/topic/chatroom/" + message.getChatroomIdx(), message);
	}

	// 채팅방 메시지 조회 (AJAX)
	@GetMapping("/messages")
	@ResponseBody
	public List<ChatMessage> getMessages(@RequestParam Long chatroomIdx) {
		return chatService.getMessages(chatroomIdx);
	}
}
