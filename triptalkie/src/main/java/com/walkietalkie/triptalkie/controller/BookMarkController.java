package com.walkietalkie.triptalkie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.walkietalkie.triptalkie.domain.Makemate;
import com.walkietalkie.triptalkie.service.BookMarkService;

@Controller
@RequestMapping("/bookmark")
public class BookMarkController {
	private final BookMarkService bookMarkService;

	public BookMarkController(BookMarkService bookMarkService) {
		this.bookMarkService = bookMarkService;
	}
	
	@PostMapping("toggle")
	@ResponseBody
	public boolean toggleBookmark(@RequestParam String memberId,@RequestParam long makemateIdx) {
		
		return bookMarkService.toggleBookmark(memberId, makemateIdx);
	}
	
	@GetMapping("/list")
	public String showBookmarks(@RequestParam String memberId, Model model) {
	    List<Makemate> bookmarks = bookMarkService.getBookMarkedMakemates(memberId);
	    model.addAttribute("bookmarks", bookmarks);
	    return "bookmark/list"; // 타임리프 템플릿 경로
	}
	
	@GetMapping("/test")
	public String testBookmarkPage(@RequestParam(required = false, defaultValue = "user1") String memberId,
	                               Model model) {
	    List<Makemate> bookmarks = bookMarkService.getBookMarkedMakemates(memberId);
	    model.addAttribute("bookmarks", bookmarks);
	    model.addAttribute("memberId", memberId); // JS에서 쓸 수 있게 전달
	    return "pages/bookmark/test";
	}


	
}
