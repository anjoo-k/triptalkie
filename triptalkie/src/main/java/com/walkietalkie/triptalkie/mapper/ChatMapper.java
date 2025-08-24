package com.walkietalkie.triptalkie.mapper;

import com.walkietalkie.triptalkie.domain.ChatRoom;
import com.walkietalkie.triptalkie.domain.ChatMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ChatMapper {
  List<ChatRoom> findRoomsByMember(String memberId);
  ChatRoom findRoom(@Param("member1Id") String member1Id,
                    @Param("member2Id") String member2Id,
                    @Param("makamateIdx") Long makamateIdx);
  int registerChatRoom(ChatRoom room);
  int registerMessage(ChatMessage message);
  List<ChatMessage> findMessagesByRoom(Long chatroomId);
  int countUnreadMessages(@Param("chatroomId") Long chatroomId, @Param("memberId") String memberId);
}
