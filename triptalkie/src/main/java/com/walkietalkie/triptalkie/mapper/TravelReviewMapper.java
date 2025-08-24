package com.walkietalkie.triptalkie.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.walkietalkie.triptalkie.domain.TravelReview;
import com.walkietalkie.triptalkie.domain.TravelReviewTop3DTO;

@Mapper
public interface TravelReviewMapper {

	Map<String, Object> findTravelreviewByIdx(Long idx);

	int deleteTravelreviewByIdx(Long idx);

	Long registerTravelreview(TravelReview travelreview);

	void updateTravelreviewByIdxAndMemberId(TravelReview travelreview);
	
	int findTravelreviewAllCount();

	List<Map<String, Object>> findPaged(@Param("size") int size, @Param("startRow") int startRow, String keyword, String countryId, String cityId, String conceptType);

	int findCountByConditions(String keyword, String countryId, String cityId, String conceptType);

	void incrementView(Long idx);

	List<TravelReviewTop3DTO> findTravelreviewTop3ByView();
}
