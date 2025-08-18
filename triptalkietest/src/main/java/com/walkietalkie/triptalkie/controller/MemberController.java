package com.walkietalkie.triptalkie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.walkietalkie.triptalkie.service.MemberService;


@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	 @GetMapping("/login")
	    public String loginForm(@RequestParam(value = "msg", required = false) String msg,
	                            org.springframework.ui.Model model) {
	        if (msg != null) model.addAttribute("msg", msg);
	        return "pages/member/login";  // templates/pages/member/login.html
	    }
	
	@PostMapping("/login")
	public String login(@RequestParam("id") String id, @RequestParam("password") String password, Model model) {
		int result = memberService.login(id, password);
		if(result>0) {
			model.addAttribute("msg","로그인 성공!");
			return "home";
		}else {
            model.addAttribute("msg", "아이디 또는 비밀번호가 틀렸습니다.");
            return "login"; // 로그인 실패 → 다시 login.html
        }
	}
	
}