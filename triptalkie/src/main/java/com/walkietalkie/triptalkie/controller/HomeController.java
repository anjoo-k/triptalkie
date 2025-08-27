package com.walkietalkie.triptalkie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.walkietalkie.triptalkie.DTO.TravelReviewTop3DTO;
import com.walkietalkie.triptalkie.domain.Faq;
import com.walkietalkie.triptalkie.domain.TravelInfo;
import com.walkietalkie.triptalkie.service.CustomerserviceService;
import com.walkietalkie.triptalkie.service.TravelInfoService;
import com.walkietalkie.triptalkie.service.TravelReviewService;

// Thymeleaf 연습을 위한 컨트롤러
@Controller
public class HomeController {

	private final TravelReviewService travelReviewService;
	private final TravelInfoService travelInfoService;
	private final CustomerserviceService customerserviceService;
	
	public HomeController(TravelReviewService travelReviewService, TravelInfoService travelInfoService,
			CustomerserviceService customerserviceService) {
		super();
		this.travelReviewService = travelReviewService;
		this.travelInfoService = travelInfoService;
		this.customerserviceService = customerserviceService;
	}

	@GetMapping("/")
	public String home(Model model) {
		
		List<TravelReviewTop3DTO> topReviews = travelReviewService.findTravelreviewTop3();
		model.addAttribute("topReviews", topReviews);
		
		List<TravelInfo> topInfos = travelInfoService.findTravelInfoTop3();
		model.addAttribute("topInfos", topInfos);
		
		List<Faq> faqList = customerserviceService.findFaqTop5();
		model.addAttribute("faqList", faqList);
		
		return "home";
	}



	
}
