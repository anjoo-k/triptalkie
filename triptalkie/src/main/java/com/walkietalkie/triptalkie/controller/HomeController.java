package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Thymeleaf 연습을 위한 컨트롤러
@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		return "home";
	}

	@GetMapping("/test01")
	public String test(Model model) {
		return "test01";
	}

	@GetMapping("/signup")
	public String signup() {
		return "pages/member-2/signup";
	}
}
