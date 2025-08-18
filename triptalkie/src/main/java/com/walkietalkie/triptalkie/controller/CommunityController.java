package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CommunityController {
	@GetMapping("/board/community")
	public String BoardWriteForm() {
		return "pages/community/boardWrite";
	}
}
