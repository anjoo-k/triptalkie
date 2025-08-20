package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.service.MypageService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mypage")
public class MypageController {
	
	private final MypageService mypageService;
	
	public MypageController(MypageService mypageService) {
		super();
		this.mypageService = mypageService;
	}
	 

	// 마이페이지 화면
	@GetMapping("/myinformation")
	public String mypage(HttpSession session, Model model) {
		String id = (String)session.getAttribute("loginId");
		if(id == null)
			return "redirect:/member/loginPage";
		
		Member member = mypageService.findMemberById(id);
		model.addAttribute("member", member);
		return "pages/mypage/myinformation";
	}
	
	// 내 정보 수정 화면
	@GetMapping("/update-myinfo")
	public String myinfoupdatePageTest(HttpSession session, Model model) {
		String id = (String)session.getAttribute("loginId");
		if(id == null)
			return "redirect:/member/loginPage";
		
		Member member = mypageService.findMemberById(id);
		model.addAttribute("member", member);
		return "pages/mypage/update-myinfo";
	}
	
	// 내정보 수정 처리 : 리다이렉트 하면 model 값은 리다이렉트하는 페이지로 넘어가지 않는다.
	@PostMapping("/update-myinfo")
	public String myinfoupdateTest(HttpSession session, Member member) {
		String id = (String)session.getAttribute("loginId");
		if(id == null)
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
		String id = (String)session.getAttribute("loginId");
		if(id == null)
			return "redirect:/member/loginPage";
		
		Boolean result = mypageService.checkPassword(id, password);
		if(result) {
			return "redirect:/mypage/update-myinfo";
		}    
		else {
			ra.addFlashAttribute("error", true);
			return "redirect:/mypage/password-check";
		}
	}
}
