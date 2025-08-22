package com.walkietalkie.triptalkie.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.domain.MemberImage;
import com.walkietalkie.triptalkie.service.MemberImageService;
import com.walkietalkie.triptalkie.service.MemberService;
import com.walkietalkie.triptalkie.service.MypageService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	private final MypageService mypageService;
	private final MemberImageService memberImageService;
	private final MemberService memberService;

	public MypageController(MypageService mypageService, MemberImageService memberImageService,
			MemberService memberService) {
		super();
		this.mypageService = mypageService;
		this.memberImageService = memberImageService;
		this.memberService = memberService;
	}

	// 마이페이지 화면
	@GetMapping("/myinformation")
	public String mypage(HttpSession session, Model model) {
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";

		Member member = mypageService.findMemberById(id);

		// 등록된 이미지 URL 가져오기
		String profileImageUrl = memberImageService.getImageUrlByMemberId(id);

		model.addAttribute("member", member);
		model.addAttribute("profileImageUrl", profileImageUrl);
		// model 객체를 사용해서 profileImageUrl 값 저장, 뷰로 전달된다.

		return "pages/mypage/myinformation-profiletest";
	}

	// 내 정보 수정 화면
	@GetMapping("/update-myinfo")
	public String myinfoupdatePageTest(HttpSession session, Model model) {
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";

		Member member = mypageService.findMemberById(id);
		model.addAttribute("member", member);
		return "pages/mypage/update-myinfo";
	}

	// 내정보 수정 처리
	@PostMapping("/update-myinfo")
	public String myinfoupdateTest(HttpSession session, Member member) {
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";

		member.setId(id);
		mypageService.updateMemberById(member);
		return "redirect:/mypage/myinformation";
	}

	// 비밀번호 체크 화면
	@GetMapping("/password-check")
	public String checkPasswordPage() {
		return "pages/mypage/password-check";
	}

	// 비밀번호 췍해서 맞으면 업데이트 내정보 페이지로
	// 비밀번호 체크 처리
	@PostMapping("/password-check")
	public String checkPassword(HttpSession session, String password, RedirectAttributes ra) throws Exception {
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";

		Boolean result = mypageService.checkPassword(id, password);
		if (result) {
			return "redirect:/mypage/update-myinfo";
		} else {
			ra.addFlashAttribute("error", true);
			return "redirect:/mypage/password-check";
		}
	}


	@PostMapping("/updateProfileImage")
	public String updateProfileImage(@RequestParam("profileImage") MultipartFile profileImage, Model model,
			HttpSession session) throws IOException {

		// 1. 업로드 파일이 없으면 아무 작업도 하지 않고 마이페이지로 리다이렉트
		if (profileImage == null || profileImage.isEmpty()) {
			return "redirect:/mypage/myinformation";
		}

		String loginId = memberService.getLoginId(session);

		// 1. 기존 이미지 삭제
		memberImageService.deleteImageByMemberId(loginId);

		// 2. 새 이미지 업로드
		MemberImage newImage = memberImageService.uploadImage(profileImage, loginId);

		// 새 이미지 URL 조회
		String profileImageUrl = memberImageService.getImageUrlByMemberId(loginId);

		// 뷰에 전달
	    session.setAttribute("profileImageUrl", profileImageUrl);

		return "redirect:/mypage/myinformation";
	}

}
