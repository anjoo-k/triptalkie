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
		travelReviewCommentMapper.registerComment(travelReivewCommentDTO);
		return travelReivewCommentDTO;
	}

	public List<TravelReviewCommentDTO> findAllReplyByIdx(Long idx) {
		return travelReviewCommentMapper.findAllReplyByIdx(idx);
	}

	public TravelReviewCommentDTO findByIdx(Integer idx) {
		return travelReviewCommentMapper.findByIdx(idx);
	}

	public void deleteCommentByIdx(Integer idx) {
		travelReviewCommentMapper.deleteCommentByIdx(idx);
		
	}

	public TravelReviewCommentDTO updateCommentByIdx(TravelReviewCommentDTO travelReviewCommentDTO) {
		System.out.println("updateCommentByIdx 매퍼 진입");
		System.out.println("넘어 온 값 : " + travelReviewCommentDTO);
		int updated = travelReviewCommentMapper.updateCommentByIdx(travelReviewCommentDTO);
		System.out.println("updated : " + updated);
		if (updated > 0) {
			return travelReviewCommentMapper.findByIdx(travelReviewCommentDTO.getIdx());
		}
		
		return null;
	}
}
