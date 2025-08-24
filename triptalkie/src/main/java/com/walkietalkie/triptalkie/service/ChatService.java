package com.walkietalkie.triptalkie.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.domain.ChatMessage;
import com.walkietalkie.triptalkie.domain.ChatRoom;
import com.walkietalkie.triptalkie.mapper.ChatMapper;

@Service
public class ChatService {

	// [] 의존성 주입
	private final ChatMapper chatMapper;

	public ChatService(ChatMapper chatMapper) {
		this.chatMapper = chatMapper;
	}

	// 채팅방 생성 또는 조회
	@Transactional
	public ChatRoom createOrGetChatRoom(Long makemateIdx, String member1Id,
			String member2Id) {
		ChatRoom room = chatMapper.findRoom(member1Id, member2Id, makemateIdx);
		if (room == null) {
			ChatRoom newRoom = new ChatRoom();
			newRoom.setMember1Id(member1Id);
			newRoom.setMember2Id(member2Id);
			newRoom.setMakemateIdx(makemateIdx);
			chatMapper.registerChatRoom(newRoom); // useGeneratedKeys="true"
			if (newRoom.getIdx() == 0)
				throw new IllegalStateException("채팅방 IDX 생성 실패");
			room = newRoom;
		}
		return room;
	}

	// 채팅방 리스트
	public List<ChatRoom> findRoomByMemberId(String memberId) {
		return chatMapper.findRoomsByMember(memberId);
	}

	// 메시지 추가
	@Transactional
	public void addMessage(ChatMessage message) {
		ChatRoom room = chatMapper.findRoomByChatRoomIdx(message.getChatroomIdx());
		if (room == null)
			throw new IllegalArgumentException("존재하지 않는 채팅방입니다.");
		chatMapper.registerMessage(message);
	}

	// 채팅방 메시지 조회
	public List<ChatMessage> getMessages(Long chatroomIdx) {
	    return chatMapper.findMessagesByRoom(chatroomIdx);
	}

	// 안 읽은 메시지 카운트
	public int countUnread(Long chatroomId, String memberId) {
		return chatMapper.countUnreadMessages(chatroomId, memberId);
	}

	// 회원이 속한 채팅방 목록
	public List<ChatRoom> getChatRoomsByMember(String memberId) {
		return chatMapper.findRoomsByMember(memberId);
	}

	public ChatRoom findRoomByChatRoomIdx(Long chatroomIdx) {
		// TODO Auto-generated method stub
		return chatMapper.findRoomByChatRoomIdx(chatroomIdx);
	}

}