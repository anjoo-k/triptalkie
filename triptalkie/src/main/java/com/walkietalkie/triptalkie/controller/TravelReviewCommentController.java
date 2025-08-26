package com.walkietalkie.triptalkie.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.walkietalkie.triptalkie.DTO.TravelReviewCommentDTO;
import com.walkietalkie.triptalkie.service.TravelReviewCommentService;

@Controller
@RequestMapping("/travelReviewComment")
public class TravelReviewCommentController {
	private final TravelReviewCommentService travelReviewCommentService;

	public TravelReviewCommentController(TravelReviewCommentService travelReviewCommentService) {
		super();
		this.travelReviewCommentService = travelReviewCommentService;
	}

	@PostMapping("/register")
	@ResponseBody
	public Map<String, Object> registerComment(@RequestBody TravelReviewCommentDTO travelReivewCommentDTO) {
		System.out.println("넘어 온 travelReivewCommentDTO : " + travelReivewCommentDTO);
		travelReviewCommentService.registerComment(travelReivewCommentDTO);

		TravelReviewCommentDTO savedComment = travelReviewCommentService.findByIdx(travelReivewCommentDTO.getIdx());

		Map<String, Object> result = new HashMap<>();
		result.put("message", "댓글이 등록되었습니다.");
		result.put("comment", savedComment);
		System.out.println("등록 완료 travelReviewCommentDTO : " + savedComment);
		return result;
	}

	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Object> deleteComment(@RequestParam Integer idx) {
		System.out.println("삭제 controller 진입");
		System.out.println("넘어 온 idx 값 : " + idx);
		Map<String, Object> resultMap = new HashMap<>();

		try {
			travelReviewCommentService.deleteCommentByIdx(idx);
			resultMap.put("success", true);
			resultMap.put("message", "댓글이 삭제되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("message", "삭제 중 오류가 발생했습니다.");
		}

		return resultMap;
	}

}
