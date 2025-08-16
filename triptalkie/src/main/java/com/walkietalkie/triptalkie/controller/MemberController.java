package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	 * checkMemberById, checkMemberByNickname, checkMemberByPhonenumber, checkMemberByEmail
	 * 중복 확인 메서드
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
	@PostMapping("/registerMember")
	public String registerMember(Member member, Model model) {
		System.out.println("registerMember : " + member);
		memberService.register(member);
		model.addAttribute("nickname", member.getNickname());
		
		return "redirect:/registerMemberFin";
	}
	
	@GetMapping("/registerMemberFin")
	public String registerMemberFin(Model model) {
		System.out.println("가입 완");

		return "home";
	}

}