package com.walkietalkie.triptalkie.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.walkietalkie.triptalkie.controller.MakemateChatController;
import com.walkietalkie.triptalkie.domain.Rating;
import com.walkietalkie.triptalkie.domain.Star;
import com.walkietalkie.triptalkie.mapper.RatingMapper;

@Service
public class RatingService {

    private final MakemateChatController makemateChatController;
    private final RatingMapper ratingMapper;
	
	public RatingService(RatingMapper ratingMapper, MakemateChatController makemateChatController) {
		this.ratingMapper = ratingMapper;
		this.makemateChatController = makemateChatController;
	}
	
	//깡총지수 목록
	public List<Rating> findRatingList(String hostId) {
		return ratingMapper.findMateList(hostId);
	}

	public void saveRating(Star star) {
		ratingMapper.insertRating(star);
		System.out.println(star.getRating());
		
		
	}

	public void reRating(Star star) {
		ratingMapper.updateRating(star);
	}
	

}
