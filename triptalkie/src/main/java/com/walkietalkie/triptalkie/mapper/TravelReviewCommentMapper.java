package com.walkietalkie.triptalkie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.DTO.TravelReviewCommentDTO;

@Mapper
public interface TravelReviewCommentMapper {

	void registerComment(TravelReviewCommentDTO travelReivewCommentDTO);

	List<TravelReviewCommentDTO> findAllReplyByIdx(Long travelReviewIdx);

	TravelReviewCommentDTO findByIdx(Integer idx);

	void deleteCommentByIdx(Integer idx);

	int updateCommentByIdx(TravelReviewCommentDTO travelReviewCommentDTO);

}
