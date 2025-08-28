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
		System.out.println("hostId = " + hostId);
		
		// loginId 가 세션에 없는 경우 방어
	    if (hostId == null) {
	        model.addAttribute("mates", List.of()); // 빈 리스트 넘겨주기
	        return "/pages/ratinglist/list";
	    }
	    
	    List<Rating> mates = ratingService.findRatingList(hostId);
	    
	    // mates 가 null 인 경우도 방어
	    if (mates == null) {
	        mates = List.of();
	    }
	    
	    System.out.println("mates = " + mates);
	    model.addAttribute("mates", mates);

		return "/pages/ratinglist/list";
	}
	
	@PostMapping("/save")
    public String saveRating(@ModelAttribute Star star) {

        if (star.getRaterId().equals(star.getRatedId())) {
            throw new IllegalArgumentException("자기 자신을 평가할 수 없습니다.");
        }

        star.setIsrated(true);
        ratingService.saveRating(star);
        System.out.println("makemateIdx: "+star.getMakemateIdx());
        return "redirect:/rating/list";
    }
	
	@PostMapping("/update")
	public String updateRating(@ModelAttribute Star star) {
		 if (star.getRaterId().equals(star.getRatedId())) {
		        throw new IllegalArgumentException("자기 자신을 평가할 수 없습니다.");
		    }
		 
		 star.setIsrated(true);
		 ratingService.reRating(star);
		 return "redirect:/rating/list";
	}
	
}
