package com.walkietalkie.triptalkie.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.service.MemberImageService;
import com.walkietalkie.triptalkie.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;
	private final MemberImageService memberImageService;

	public MemberController(MemberService memberService, MemberImageService memberImageService) {
		super();
		this.memberService = memberService;
		this.memberImageService = memberImageService;
	}

	/*
	 * checkMemberById, checkMemberByNickname, checkMemberByPhonenumber,
	 * checkMemberByEmail 중복 확인 메서드
	 */
	@GetMapping("/checkMemberById")
	@ResponseBody
	public int checkMemberById(@RequestParam String id) {
		return memberService.checkMemberById(id);
	}

	@GetMapping("/checkMemberByNickname")
	@ResponseBody
	public int checkMemberByNickname(@RequestParam String nickname) {
		return memberService.checkMemberByNickname(nickname);
	}

	@GetMapping("/checkMemberByPhonenumber")
	@ResponseBody
	public int checkMemberByPhonenumber(@RequestParam String phonenumber) {
		return memberService.checkMemberByPhonenumber(phonenumber);
	}

	@GetMapping("/checkMemberByEmail")
	@ResponseBody
	public int checkMemberByEmail(@RequestParam String email) {
		return memberService.checkMemberByEmail(email);
	}

	/*
	 * 회원 가입
	 */
	@GetMapping("/registerPage")
	public String registerPage() {
		// return "pages/member/register";
		return "pages/member/register-profiletest";
		// 프로필 사진 기능이 추가된 회원가입 페이지
	}

	// 프로필 이미지 저장을 위해서 로직 추가
	@PostMapping("/register")
	public String register(Member member, @RequestParam("profileImage") MultipartFile profileImage) {
		int result = memberService.register(member);
		// 회원 가입 처리

		// 2. 회원 가입 성공 후 프로필 이미지 업로드
		if (!profileImage.isEmpty()) {
			try {
				memberImageService.uploadImage(profileImage, member.getId());
			} catch (IOException e) {
				e.printStackTrace();
				// 이미지 업로드 실패 시 로그 출력
				// 필요하면 에러 메시지 화면 전달 가능
			}
		}
		return "redirect:/member/loginPage";
	}

	/**
	 * 로그인
	 */
	@GetMapping("/loginPage")
	public String loginForm(@RequestParam(value = "error", required = false) String error, Model model) {
		if (error != null) {
			model.addAttribute("loginError", true);
		}
		return "/pages/member/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String id, @RequestParam String password, HttpSession session,
			RedirectAttributes redirectAttributes) {

		boolean loginSuccess = memberService.login(id, password, session);

		if (loginSuccess) {
			return "redirect:/";
		} else {
			// 로그인 실패
			redirectAttributes.addFlashAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
			return "redirect:/member/loginPage?error"; // 로그인 페이지로 리다이렉트 (error 파라미터 포함)
		}
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		memberService.logout(session);
		// 세션 전체 삭제 수행
		
		return "redirect:/";
		// 로그아웃 후 메인 페이지로 이동
	}
	
	// 멤버 프로필 확인
	@PostMapping("/profile")
	public String findProfile(@RequestParam String memberId, Model model) {
	    Member member = memberService.findMemberById(memberId);
	    model.addAttribute("member", member);
	    return "pages/member/memberInformation";
	}

}