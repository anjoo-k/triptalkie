package com.walkietalkie.triptalkie.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.domain.TravelReview;
import com.walkietalkie.triptalkie.mapper.TravelReviewMapper;

@Service
public class TravelReviewService {
	private final TravelReviewMapper travelReviewMapper;

	public TravelReviewService(TravelReviewMapper travelReviewMapper) {
		super();
		this.travelReviewMapper = travelReviewMapper;
	}

	/*
	 *  여행 리뷰 리스트 조회
	 */
	@Transactional(readOnly = true)
	public List<TravelReview> findTravelreviewAllList() {
		return travelReviewMapper.findTravelreviewAllList();
	}

	public TravelReview findTravelreviewByIdx(Long idx) {
		return travelReviewMapper.findTravelreviewByIdx(idx);
	}

	public int deleteTravelreviewByIdx(Long idx) {
		return travelReviewMapper.deleteTravelreviewByIdx(idx);
	}

	public Long registerTravelreview(TravelReview travelreview) {
		travelReviewMapper.registerTravelreview(travelreview);
		return travelreview.getIdx();
	}

	public void updateTravelreviewByIdxAndMemberId(TravelReview travelreview) {
		travelReviewMapper.updateTravelreviewByIdxAndMemberId(travelreview);
		
	}
	
	

}
