package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		super();
		this.memberService = memberService;
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
		return "pages/member/register";
	}
	
	
	@PostMapping("/register")
	public String register(Member member) {
		int result = memberService.register(member);
		return "redirect:/login";
	}

	/*
	 * 로그인
	 */
	@GetMapping("/loginPage")
	public String loginPage() {
		return "pages/member/login";
	}
	
	@PostMapping("/login")
	public String login() {
		
		return "redirect:/home";
	}

}