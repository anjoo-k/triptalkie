package com.walkietalkie.triptalkie.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.DTO.TravelReviewCommentDTO;

@Mapper
public interface TravelReviewCommentMapper {

	void registerComment(TravelReviewCommentDTO travelReivewCommentDTO);

}
