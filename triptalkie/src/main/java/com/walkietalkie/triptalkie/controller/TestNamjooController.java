package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.service.MemberService;

@Controller
public class TestNamjooController {
	
	private final MemberService memberService;
	
	public TestNamjooController(MemberService memberService) {
		super();
		this.memberService = memberService;
	}

	 @GetMapping("/member/signup")
	  public String test() {
	    return "pages/member/signup-knj";
	  }
	 
	 @PostMapping("/member/signuptest")
	  public String signupTest(Member member) {
		 System.out.println(member);
		int isSuccess = memberService.register(member);
	    return "home";
	  }
}
