package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.service.MypageService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customerservice")
public class TestNamjooController {
	
	private final MypageService mypageService;
	
	public TestNamjooController(MypageService mypageService) {
		super();
		this.mypageService = mypageService;
	}
	 
	@GetMapping("/notice")
	public String noticeTest(Model model) {
		model.addAttribute("active", "notice");
		return "pages/customerservice/notice";
	}
	
	@GetMapping("/faq")
	public String faqTest(Model model) {
		model.addAttribute("active", "faq");
		return "pages/customerservice/faq";
	}
	
	@GetMapping("/qna")
	public String qnaTest(Model model) {
		model.addAttribute("active", "qna");
		return "pages/customerservice/qna";
	}
	@GetMapping("/mypage")
	public String mypageTest(Model model) { //HttpSession session, 
//		Member member = (Member)session.getAttribute("id");
//		if(member == null)
//			return "redirect:/login";
//		model.addAttribute(mypageService.findMemberById(member.getId()));
		Member member = mypageService.findMemberById("test");
		model.addAttribute("member", member);
		return "pages/member/mypage";
	}
}
