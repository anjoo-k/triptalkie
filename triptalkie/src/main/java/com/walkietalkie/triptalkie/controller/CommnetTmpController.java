package com.walkietalkie.triptalkie.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.walkietalkie.triptalkie.domain.Comment;

@Controller
public class CommnetTmpController {
	
	@GetMapping("/comment")
	public String commentPage() {
		return "pages/comment-tmp";
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
