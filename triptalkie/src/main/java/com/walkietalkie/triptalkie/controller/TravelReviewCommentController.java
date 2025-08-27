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
		System.out.println("등록 완료 travelReviewCommentDTO : " + savedComment);
		return result;
	}
	
	@GetMapping("/findByIdx")
	@ResponseBody
	public Map<String, Object> findCommentByIdx(@RequestParam Integer idx) {
		System.out.println("findCommentByIdx 진입 " + idx);
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
	
	@PostMapping("/update")
	@ResponseBody
	public Map<String, Object> updateCommentByIdx(@RequestBody TravelReviewCommentDTO travelReviewCommentDTO) {
		System.out.println("수정 controller 진입");
		System.out.println("넘어 온 idx 값 : " + travelReviewCommentDTO);
	    System.out.println("DTO 확인: " + travelReviewCommentDTO.getIdx() + ", " + travelReviewCommentDTO.getContent());

		
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
