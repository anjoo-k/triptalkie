package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.walkietalkie.triptalkie.domain.MemberImage;
import com.walkietalkie.triptalkie.service.MemberImageService;
import com.walkietalkie.triptalkie.service.MemberService;

@Controller
@RequestMapping("/member/image")
public class MemberImageController {
	
	// [] 의존성 주입
	private final MemberImageService memberImageService;
	private final MemberService memberService;
	
	public MemberImageController(MemberImageService memberImageService, MemberService memberService) {
		this.memberImageService = memberImageService;
		this.memberService = memberService;
	}
	
	// 업로드 url
	// http://localhost:8080/member/image/upload
	@PostMapping("/upload")
	public String uploadProfileImage(@RequestParam("file") MultipartFile file, @RequestParam("memberId") String memberId) throws Exception {
		memberImageService.uploadImage(file, memberId);
		// 업로드 로직 수행
		
		return "redirect:/mypage/myinformation";
		// 업로드하고 mypage로 이동
	}
	
	// 조회 url : uuid
	// http://localhost:8080/member/image/view-uuid/{uuid}
	@GetMapping("/view-uuid/{uuid}")
	@ResponseBody
	public MemberImage getImageByUuid(@PathVariable String uuid) {
		return memberImageService.getImageByUuid(uuid);		
	}
	
	// 조회 url : idx
	// http://localhost:8080/member/image/view-idx/{idx}
	@GetMapping("/view-idx/{idx}")
	@ResponseBody
	public MemberImage getImageByIdx(@PathVariable Long idx) {
		return memberImageService.getImageByIdx(idx);
	}
	
	// 삭제 url
	// 
	@DeleteMapping("/delete-uuid/{uuid}")
	@ResponseBody
	public String deleteImageByUuid(@PathVariable String uuid) {
		boolean success = memberImageService.deleteImageByUuid(uuid);
		return success ? "success" : "fail";
	}
	
	@DeleteMapping("/delete-idx/{idx}")
	@ResponseBody
	public String deleteImageByIdx(@PathVariable Long idx) {
		boolean success = memberImageService.deleteImageByIdx(idx);
		return success ? "success" : "fail";
	}
	
}