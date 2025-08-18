package com.walkietalkie.triptalkie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.walkietalkie.triptalkie.domain.Travelreview;
import com.walkietalkie.triptalkie.service.TravelReviewService;

@Controller
@RequestMapping("/travel-review")
public class TravelReviewController {
	private final TravelReviewService travelReviewService;

	public TravelReviewController(TravelReviewService travelReviewService) {
		super();
		this.travelReviewService = travelReviewService;
	}
	
	@GetMapping("/travelreview-list")
	public String findTravelreviewAllList (Model model){
		List<Travelreview> reviewList = travelReviewService.findTravelreviewAllList();
		 // 모델에 담아서 뷰에서 사용 가능하게 함
	    model.addAttribute("reviewList", reviewList);
	    
	    // templates/pages/travel-review/travelreview-list.html
	    return "pages/travel-review/travelreview-list";
	}
	
	@GetMapping("/write-review")
	public String writeReview() {
		return "pages/travel-review/write-review";
	}
	
	@GetMapping("/detail-review")
	public String detailReview() {
		return "pages/travel-review/detail-review";
	}
	
	
}
