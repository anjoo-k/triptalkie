package com.walkietalkie.triptalkie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.walkietalkie.triptalkie.domain.TravelReview;
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
		List<TravelReview> reviewList = travelReviewService.findTravelreviewAllList();
	    model.addAttribute("reviewList", reviewList);
	    
	    return "pages/travel-review/travelreview-list";
	}
	
	@GetMapping("/detail-review/{idx}")
	public String findTravelreviewByIdx (@PathVariable Long idx, Model model) {
		TravelReview travelreview = travelReviewService.findTravelreviewByIdx(idx);
		model.addAttribute("travelreview", travelreview);
		return "pages/travel-review/detail-review";
	}
	
	@GetMapping("/writeReviewPage")
	public String writeReviewPage() {
		return "pages/travel-review/write-review";
	}
	
	@PostMapping("/registerTravelreview")
	public String registerTravelreview (TravelReview travelreview) {
		
		Long newIdx = travelReviewService.registerTravelreview(travelreview);
		
		return "redirect:/pages/travel-review/detail-review/" + newIdx;
	}
	
	@GetMapping("/travel-review/edit-review/{idx}")
	public String editReviewPage(@PathVariable Long idx, Model model) {
		TravelReview travelreview = travelReviewService.findTravelreviewByIdx(idx);
	    model.addAttribute("travelreview", travelreview);
	    return "travel-review/edit-review";
	}
	
	@PostMapping("/updateTravelreviewByIdxAndMemberId")
	public String updateTravelreviewByIdxAndMemberId(@ModelAttribute TravelReview travelreview, RedirectAttributes redirectAttributes) {
		travelReviewService.updateTravelreviewByIdxAndMemberId(travelreview);
		
		return "redirect:/pages/travel-review/detail-review/" + travelreview.getIdx();
	}
	
	@DeleteMapping("/deleteTravelreviewByIdx")
	public int deleteTravelreviewByIdx (Long idx) {
		
		return travelReviewService.deleteTravelreviewByIdx(idx);
	}
}
