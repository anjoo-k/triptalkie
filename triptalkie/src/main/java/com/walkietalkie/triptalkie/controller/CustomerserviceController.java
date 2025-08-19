package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customerservice")
public class CustomerserviceController {
	
	// 공지사항 페이지
	@GetMapping("/notice")
	public String noticeTest(Model model) {
		model.addAttribute("active", "notice");
		return "pages/customerservice/notice";
	}
	
	// faq 페이지
	@GetMapping("/faq")
	public String faqTest(Model model) {
		model.addAttribute("active", "faq");
		return "pages/customerservice/faq";
	}
	
	// qna 페이지
	@GetMapping("/qna")
	public String qnaTest(Model model) {
		model.addAttribute("active", "qna");
		return "pages/customerservice/qna";
	}
}
