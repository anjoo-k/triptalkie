package com.walkietalkie.triptalkie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.walkietalkie.triptalkie.domain.RatingList;
import com.walkietalkie.triptalkie.mapper.RatingListMapper;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/Rating")
public class RatingListController {
	private final RatingListMapper ratingListMapper;

	public RatingListController(RatingListMapper ratingListMapper) {
		this.ratingListMapper = ratingListMapper;
	}
	
	@GetMapping("/List")
	public String showMateList(HttpSession session, Model model) {
		
		String hostId = (String)session.getAttribute("loginId");
		List<RatingList> mates = ratingListMapper.findMateList(hostId);
		model.addAttribute("mates",mates);
		
		return null;
	}
	
	
	
}
