package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/makemate-chat")
public class MakemateChatController {

	@GetMapping("/chat")
	public String chatPage() {
		return "pages/makemate-chat/chat";
	}
}
