package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.walkietalkie.triptalkie.domain.Member;

@Controller
public class TestNamjooController {

	 @GetMapping("/member/signup")
	  public String test() {
	    return "pages/member/signup";
	  }
	 
	 @PostMapping("/member/signuptest")
	  public String signupTest(Member member) {
		System.out.println(member);
	    return "home";
	  }
}
