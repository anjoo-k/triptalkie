package com.walkietalkie.triptalkie.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walkietalkie.triptalkie.domain.ChatRoom;
import com.walkietalkie.triptalkie.mapper.ChatMapper;

@Service
public class ChatService {

	// [] 의존성 주입
	private final ChatMapper chatMapper;
	
	public ChatService(ChatMapper chatMapper) {
		this.chatMapper = chatMapper;	
	}
	
	public List<ChatRoom> getChatRoomsByMember(String MemberId) {
		
		return chatMapper.findRoomsByMember(MemberId);
	}
	
	public ChatRoom createOrGetRoom(String member1Id, String member2Id, Long makemateIdx) {
	    ChatRoom room = chatMapper.findRoom(member1Id, member2Id, makemateIdx);
	    if (room == null) {
	    	room = new ChatRoom();
	        room.setMember1Id(member1Id);
	        // room 객체에 멤버 1의 변수값를 넣는다.
	        room.setMember2Id(member2Id);
	        // room 객체에 멤버 2에 변수값을 넣는다.
	        room.setMakemateIdx(makemateIdx);
	        // room 객체에 메이크메이트 idx 변수값을 넣는다.
	        chatMapper.insertChatRoom(room);
	    }
	    
	    
	    return room;
	}
	
}

