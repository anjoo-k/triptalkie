package com.walkietalkie.triptalkie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.domain.TravelReview;

@Mapper
public interface TravelReviewMapper {

	List<TravelReview> findTravelreviewAllList();

	TravelReview findTravelreviewByIdx(Long idx);

	int deleteTravelreviewByIdx(Long idx);

	Long registerTravelreview(TravelReview travelreview);

	void updateTravelreviewByIdxAndMemberId(TravelReview travelreview);

}
