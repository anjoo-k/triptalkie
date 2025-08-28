package com.walkietalkie.triptalkie.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.walkietalkie.triptalkie.DTO.TravelReviewCommentDTO;
import com.walkietalkie.triptalkie.service.TravelReviewCommentService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/travelReviewComment")
public class TravelReviewCommentController {
	private final TravelReviewCommentService travelReviewCommentService;

	public TravelReviewCommentController(TravelReviewCommentService travelReviewCommentService) {
		super();
		this.travelReviewCommentService = travelReviewCommentService;
	}

	/*
	 * 댓글 등록
	 */
	@PostMapping("/register")
	@ResponseBody
	public Map<String, Object> registerComment(@RequestBody TravelReviewCommentDTO travelReivewCommentDTO, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		String loginMember = (String) session.getAttribute("loginId");
		// 세션이 비어있을 경우 에러 처리 추가
		if (loginMember == null) {
			result.put("success", false);
			result.put("message", "로그인이 필요합니다.");
			return result;
		}
		
		travelReviewCommentService.registerComment(travelReivewCommentDTO);

		TravelReviewCommentDTO savedComment = travelReviewCommentService.findByIdx(travelReivewCommentDTO.getIdx());

		result.put("success", true);
		result.put("message", "댓글이 등록되었습니다.");
		result.put("comment", savedComment);
		return result;
	}
	
	/*
	 * 댓글 조회
	 */
	@GetMapping("/findByIdx")
	@ResponseBody
	public Map<String, Object> findCommentByIdx(@RequestParam Integer idx) {
		Map<String, Object> resultMap = new HashMap<>();

		try {
			TravelReviewCommentDTO comment = travelReviewCommentService.findByIdx(idx);
			resultMap.put("success", true);
			resultMap.put("comment", comment);
			resultMap.put("message", "댓글을 조회 하였습니다.");

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("message", "삭제 중 오류가 발생했습니다.");
		}
		
		System.out.println("조회 시 수정할 데이터 값 : " + resultMap);
		
		return resultMap;
	}
	
	/*
	 * 댓글 수정
	 */
	@PostMapping("/update")
	@ResponseBody
	public Map<String, Object> updateCommentByIdx(@RequestBody TravelReviewCommentDTO travelReviewCommentDTO) {
		
		Map<String, Object> resultMap = new HashMap<>();

		try {
            TravelReviewCommentDTO updatedComment = travelReviewCommentService.updateCommentByIdx(travelReviewCommentDTO);

			if (updatedComment != null) {
                resultMap.put("success", true);
                resultMap.put("comment", updatedComment);
            } else {
                resultMap.put("success", false);
                resultMap.put("message", "댓글 수정 실패");
            }

		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("success", false);
			resultMap.put("message", "삭제 중 오류가 발생했습니다.");
		}
		
		return resultMap;
	}

	/*
	 * 댓글 삭제
	 */
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Object> deleteComment(@RequestParam Integer idx) {
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
