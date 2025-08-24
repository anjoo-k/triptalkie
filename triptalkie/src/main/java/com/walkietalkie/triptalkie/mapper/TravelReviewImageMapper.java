package com.walkietalkie.triptalkie.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.domain.TravelReviewImage;

@Mapper
public interface TravelReviewImageMapper {

	int registerTravelReviewImage(TravelReviewImage travelReviewImage);

	TravelReviewImage findImageUrlByIdx(Long idx);

	void deleteReviewImageByIdx(long idx);

	void deleteReviewImageByUuid(String uuid);

}
