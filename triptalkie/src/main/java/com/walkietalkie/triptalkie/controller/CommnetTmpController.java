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
	
	// 댓글 등록
	@GetMapping("/comment")
	public String comment() {
	    return "pages/comment-tmp";
	}
	
	@GetMapping("/comment/list")
	@ResponseBody
	public Map<String, Object> commentPage() {
	    Map<String, Object> result = new HashMap<>();
	    List<Map<String, Object>> comments = new ArrayList<>();

	    Map<String, Object> c1 = new HashMap<>();
	    c1.put("idx", 1);
	    c1.put("content", "첫 번째 댓글입니다.");
	    c1.put("createdAt", LocalDateTime.of(2025, 8, 1, 10, 0));
	    c1.put("updatedAt", LocalDateTime.of(2025, 8, 1, 10, 0));
	    c1.put("memberId", "user01");
	    comments.add(c1);

	    Map<String, Object> c2 = new HashMap<>();
	    c2.put("idx", 2);
	    c2.put("content", "두 번째 댓글입니다.");
	    c2.put("createdAt", LocalDateTime.of(2025, 8, 1, 10, 5));
	    c2.put("updatedAt", LocalDateTime.of(2025, 8, 1, 10, 5));
	    c2.put("memberId", "user02");
	    comments.add(c2);

	    Map<String, Object> c3 = new HashMap<>();
	    c3.put("idx", 3);
	    c3.put("content", "세 번째 댓글입니다.");
	    c3.put("createdAt", LocalDateTime.of(2025, 8, 1, 10, 10));
	    c3.put("updatedAt", LocalDateTime.of(2025, 8, 1, 10, 10));
	    c3.put("memberId", "user03");
	    comments.add(c3);

	    Map<String, Object> c4 = new HashMap<>();
	    c4.put("idx", 4);
	    c4.put("content", "네 번째 댓글입니다.");
	    c4.put("createdAt", LocalDateTime.of(2025, 8, 1, 10, 15));
	    c4.put("updatedAt", LocalDateTime.of(2025, 8, 1, 10, 15));
	    c4.put("memberId", "user04");
	    comments.add(c4);

	    Map<String, Object> c5 = new HashMap<>();
	    c5.put("idx", 5);
	    c5.put("content", "다섯 번째 댓글입니다.");
	    c5.put("createdAt", LocalDateTime.of(2025, 8, 1, 10, 20));
	    c5.put("updatedAt", LocalDateTime.of(2025, 8, 1, 10, 20));
	    c5.put("memberId", "user05");
	    comments.add(c5);

	    // 여러개 더 넣고 싶으면 계속 comments.add(…) 추가하면 됨

	    result.put("success", true);
	    result.put("data", comments);
	    return result;
	}
	
	
	
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
