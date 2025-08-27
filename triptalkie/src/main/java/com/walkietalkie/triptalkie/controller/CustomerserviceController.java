package com.walkietalkie.triptalkie.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.walkietalkie.triptalkie.domain.CommonPage;
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
	public String noticeTest(Model model) {
		model.addAttribute("active", "notice");
		return "pages/customerservice/notice";
	}

	// faq 페이지
	@GetMapping("/faq")
	public String findAllFaq(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size,
			Model model) {

		CommonPage<Map<String, Object>> pageData = customerserviceService.findFaqPage(page, size);
//		List<Faq> faqList = customerserviceService.findAllFaq();
		// 모델에 데이터 넣기
		model.addAttribute("faqList", pageData.getContent()); // 실제 데이터 리스트
		model.addAttribute("pageData", pageData); // 페이징 정보 전체
		return "pages/customerservice/faq";
	}

	// qna 페이지
	@GetMapping("/qna")
	public String qnaTest(Model model) {
		model.addAttribute("active", "qna");
		return "pages/customerservice/qna";
	}
}
