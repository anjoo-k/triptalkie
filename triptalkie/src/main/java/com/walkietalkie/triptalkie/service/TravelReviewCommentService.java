package com.walkietalkie.triptalkie.service;

import java.util.List;

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

	public List<TravelReviewCommentDTO> findAllReplyByIdx(Long idx) {
		System.out.println("findAllReplyByIdx 매퍼 진입");
		System.out.println("넘어 온 값 : " + idx);
		return travelReviewCommentMapper.findAllReplyByIdx(idx);
	}

	public TravelReviewCommentDTO findByIdx(Integer idx) {
		System.out.println("findByIdx 매퍼 진입");
		System.out.println("넘어 온 값 : " + idx);
		return travelReviewCommentMapper.findByIdx(idx);
	}

	public void deleteCommentByIdx(Integer idx) {
		travelReviewCommentMapper.deleteCommentByIdx(idx);
		
	}

}
