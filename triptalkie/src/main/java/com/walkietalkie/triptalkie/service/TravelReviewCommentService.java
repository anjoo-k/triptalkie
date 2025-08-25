package com.walkietalkie.triptalkie.service;

import org.springframework.stereotype.Service;

import com.walkietalkie.triptalkie.DTO.TravelReviewCommentDTO;
import com.walkietalkie.triptalkie.mapper.TravelReviewCommentMapper;

@Service
public class TravelReviewCommentService {
	private final TravelReviewCommentMapper travelReviewCommentMapper; 
	
	public TravelReviewCommentService(TravelReviewCommentMapper travelReviewCommentMapper) {
		super();
		this.travelReviewCommentMapper = travelReviewCommentMapper;
	}

	public TravelReviewCommentDTO registerComment(TravelReviewCommentDTO travelReivewCommentDTO) {
		System.out.println("댓글 매퍼 진입");
		travelReviewCommentMapper.registerComment(travelReivewCommentDTO);
		return travelReivewCommentDTO;
	}

}
