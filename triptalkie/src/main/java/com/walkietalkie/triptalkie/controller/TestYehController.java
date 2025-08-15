package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestYehController {

	 @GetMapping("/member-2/signup")
	  public String test() {
	    return "pages/member-2/signup";
	  }
//	 
//	 @PostMapping("/member/signuptest")
//	  public String signupTest(Member member) {
//		System.out.println(member);
//	    return "home";
//	  }
}
