package com.walkietalkie.triptalkie.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.walkietalkie.triptalkie.domain.TravelReview;

@Mapper
public interface TravelReviewMapper {

	List<Map<String, Object>> findTravelreviewAllList();

	Map<String, Object> findTravelreviewByIdx(Long idx);

	int deleteTravelreviewByIdx(Long idx);

	Long registerTravelreview(TravelReview travelreview);

	void updateTravelreviewByIdxAndMemberId(TravelReview travelreview);
	
	int findTravelreviewAllCount();

	List<Map<String, Object>> findPaged(@Param("size") int size, @Param("startRow") int startRow);

}
