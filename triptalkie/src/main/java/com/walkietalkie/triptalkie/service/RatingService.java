package com.walkietalkie.triptalkie.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.controller.MakemateChatController;
import com.walkietalkie.triptalkie.domain.Makemate;
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
		List<Rating> originalList = ratingMapper.findMateList(hostId);
		List<Rating> filteredList = new ArrayList<>();
		
		for(Rating rating : originalList) {
			if (!rating.getMemberId().equals(hostId)) {
				filteredList.add(rating);
			}
			
		}
		
		return filteredList;
	}

	public void saveRating(Star star) {
		ratingMapper.insertRating(star);
		System.out.println("***별점 : "+star.getRating());
		rateMember(star);
	}
	
    @Transactional
    public void rateMember(Star star) {
        // 1. star 테이블 업데이트
        ratingMapper.updateRating(star);
        
        double ratingDouble = star.getRating();  // 이미 double
        int ratingInt = (int) Math.round(ratingDouble); // 5.0 → 5, 3.0 → 3
        


        // 2. member credit 업데이트
        int creditChange;
        switch (ratingInt) {
            case 1 -> creditChange = -2;
            case 2 -> creditChange = -1;
            case 3 -> creditChange = 0;
            case 4 -> creditChange = 1;
            case 5 -> creditChange = 2;
            default -> creditChange = 0;
        }

        ratingMapper.updateMemberCredit(star.getRatedId(), creditChange);
    }

	public void reRating(Star star) {
		ratingMapper.updateRating(star);
	}
	

}
