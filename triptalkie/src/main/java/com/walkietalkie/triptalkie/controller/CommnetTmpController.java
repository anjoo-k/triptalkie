package com.walkietalkie.triptalkie.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.walkietalkie.triptalkie.domain.Comment;

@Controller
public class CommnetTmpController {
	
//	@GetMapping("/comment")
//	@ResponseBody
//	public Map<String, Object> commentPage() {
//		Map<String, Object> response = new HashMap<>();
//		List<Comment> comments = new ArrayList<>();
//		
//	    comments.add(new Comment(1, "첫 번째 댓글입니다.", LocalDateTime.of(2025,8,1,10,15,30), LocalDateTime.of(2025,8,1,10,15,30), "user01"));
//	    comments.add(new Comment(2, "두 번째 댓글입니다.", LocalDateTime.of(2025,8,1,11,20,10), LocalDateTime.of(2025,8,1,11,20,10), "user02"));
//	    comments.add(new Comment(3, "세 번째 댓글 테스트!", LocalDateTime.of(2025,8,2,9,5,45), LocalDateTime.of(2025,8,2,9,5,45), "user03"));
//	    comments.add(new Comment(4, "오늘 날씨 좋네요 ☀️", LocalDateTime.of(2025,8,2,14,30,0), LocalDateTime.of(2025,8,2,14,30,0), "user04"));
//	    comments.add(new Comment(5, "댓글 기능 구현 중입니다.", LocalDateTime.of(2025,8,3,8,50,12), LocalDateTime.of(2025,8,3,8,50,12), "user05"));
//	    comments.add(new Comment(6, "오 잘 되네요 👍", LocalDateTime.of(2025,8,3,9,15,25), LocalDateTime.of(2025,8,3,9,15,25), "user01"));
//	    comments.add(new Comment(7, "DB 연결 확인 완료", LocalDateTime.of(2025,8,4,12,0,0), LocalDateTime.of(2025,8,4,12,0,0), "user02"));
//	    comments.add(new Comment(8, "Ajax 테스트 중입니다.", LocalDateTime.of(2025,8,5,16,42,0), LocalDateTime.of(2025,8,5,16,42,0), "user03"));
//	    comments.add(new Comment(9, "댓글 페이징 처리 예정", LocalDateTime.of(2025,8,6,19,25,33), LocalDateTime.of(2025,8,6,19,25,33), "user04"));
//	    comments.add(new Comment(10, "마지막 더미 댓글 🎉", LocalDateTime.of(2025,8,7,21,10,55), LocalDateTime.of(2025,8,7,21,10,55), "user05"));
//
//	    response.put("success", true);
//	    response.put("data", comments);
//
//	    return response;
//	}
	
	// 댓글 등록
	@PostMapping("/comment")
	@ResponseBody
	public Map<String, Object> comment(@RequestBody Comment comment) {
	    Map<String, Object> response = new HashMap<>();
	    response.put("success", true);
	    response.put("data", Map.of("comment", comment.getContent())); // 가라 데이터
	    return response;
	}

}
