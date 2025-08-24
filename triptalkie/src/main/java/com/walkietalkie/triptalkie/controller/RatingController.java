package com.walkietalkie.triptalkie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.walkietalkie.triptalkie.domain.Rating;
import com.walkietalkie.triptalkie.domain.Star;
import com.walkietalkie.triptalkie.service.RatingService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/rating")
public class RatingController {
	
	private final RatingService ratingService;
	
	
	public RatingController(RatingService ratingService) {
		super();
		this.ratingService = ratingService;
	}


	@GetMapping("/list")
	public String showMateList(HttpSession session, Model model) {
		
		String hostId = (String)session.getAttribute("loginId");
		List<Rating> mates = ratingService.findRatingList(hostId);
		model.addAttribute("mates",mates);
		
		return "/pages/ratinglist/list";
	}
	
	@PostMapping("/save")
    public String saveRating(@ModelAttribute Star star) {

        if (star.getRaterId().equals(star.getRatedId())) {
            throw new IllegalArgumentException("자기 자신을 평가할 수 없습니다.");
        }

        star.setIsrated(true);
        ratingService.saveRating(star);
        return "redirect:/ratinglist";
    }
	
	
	
}
