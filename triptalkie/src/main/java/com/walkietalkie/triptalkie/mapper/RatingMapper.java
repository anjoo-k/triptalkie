package com.walkietalkie.triptalkie.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.walkietalkie.triptalkie.domain.Rating;
import com.walkietalkie.triptalkie.domain.Star;

@Mapper
public interface RatingMapper {

	List<Rating> findMateList(String hostId);

	void insertRating(Star star);
	
	void updateRating(Star star);
	

	void updateMemberCredit(String ratedId, int creditChange);

}
