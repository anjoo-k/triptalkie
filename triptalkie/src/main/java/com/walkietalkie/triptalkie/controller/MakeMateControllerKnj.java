package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/makemate")
public class MakeMateControllerKnj {

	@GetMapping("/list")
	public String makemateListPage() {
		return "pages/makemate/detail-makemate";
	}
}
