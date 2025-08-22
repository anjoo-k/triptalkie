package com.walkietalkie.triptalkie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.domain.RatingList;

@Mapper
public interface RatingListMapper {

	List<RatingList> findMateList(String hostId);

}
