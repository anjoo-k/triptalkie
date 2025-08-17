package com.walkietalkie.triptalkie.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.domain.Travelreview;

@Service
public class TravelReviewService {

	/*
	 *  여행 리뷰 리스트 조회
	 */
	@Transactional(readOnly = true)
	public List<Travelreview> findTravelreviewAllList() {
		return null;
	}

}
