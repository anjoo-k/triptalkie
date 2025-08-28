package com.walkietalkie.triptalkie.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.walkietalkie.triptalkie.domain.CommonPage;
import com.walkietalkie.triptalkie.domain.Notice;
import com.walkietalkie.triptalkie.service.CustomerserviceService;

@Controller
@RequestMapping("/customerservice")
public class CustomerserviceController {
	private final CustomerserviceService customerserviceService;

	public CustomerserviceController(CustomerserviceService customerserviceService) {
		super();
		this.customerserviceService = customerserviceService;
	}

	// 공지사항 페이지
	@GetMapping("/notice")
	public String noticePage(@RequestParam(defaultValue = "1")int page, Model model) {

		int size = 10;
		Map<String, Object> result = customerserviceService.findNoticeAllList(page, size);
		model.addAttribute("active", "notice");
		model.addAttribute("page", result.get("commonPage"));
		model.addAttribute("noticeList", result.get("combinedList"));
		return "pages/customerservice/notice";
	}
	
	// 공지사항 글상세 페이지
	@GetMapping("/notice/detailPage/{noticeId}")
	public String noticeDetailPage(@PathVariable Long noticeId,  Model model) {
		model.addAttribute("active", "notice");
		
		// 조회수 증가
		customerserviceService.increaseViewCount(noticeId);
		
		Notice notice = customerserviceService.findNoticeByIdx(noticeId);
		model.addAttribute("notice", notice);
		
		return "pages/customerservice/notice-detail";
	}

	// faq 페이지
	@GetMapping("/faq")
	public String findAllFaq(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			Model model) {
		model.addAttribute("active", "faq");
		CommonPage<Map<String, Object>> pageData = customerserviceService.findFaqPage(page, size);
		model.addAttribute("faqList", pageData.getContent());
		model.addAttribute("pageData", pageData); 
		return "pages/customerservice/faq";
	}

	// qna 페이지
	@GetMapping("/qna")
	public String qnaTest(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10") int size, Model model) {
		CommonPage<Map<String, Object>> pageData = customerserviceService.findQnaPage(page, size);
		model.addAttribute("active", "qna");
		model.addAttribute("qnaList", pageData.getContent()); 
		model.addAttribute("pageData", pageData);
		return "pages/customerservice/qna";
	}
}
