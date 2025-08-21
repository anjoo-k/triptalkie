package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.walkietalkie.triptalkie.service.BookMarkService;

@Controller
@RequestMapping("/bookmark")
public class BookMarkController {
	private BookMarkService bookMarkService;

	public BookMarkController(BookMarkService bookMarkService) {
		this.bookMarkService = bookMarkService;
	}
	
	@PostMapping("toggle")
	@ResponseBody
	public boolean toggleBookmark(@RequestParam String memberId,@RequestParam long makemateIdx,RedirectAttributes redirectAttributes) {
		
		return bookMarkService.toggleBookmark(memberId, makemateIdx);
	}
	
	@
	
}
