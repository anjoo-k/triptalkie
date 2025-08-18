package com.walkietalkie.triptalkie.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.service.MemberService;

import jakarta.servlet.http.HttpSession;

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
    return "redirect:/member/loginPage";
  }

  /**
   * 로그인
   */
  @GetMapping("/loginPage")
  public String loginForm() {
    return "/pages/member/login"; // 로그인 페이지 템플릿
  }

  @PostMapping("/login")
  @ResponseBody
  public Map<String, Object> login(@RequestParam String id,
                                   @RequestParam String password,
                                   HttpSession session) {
      Map<String, Object> response = new HashMap<>();

      boolean success = memberService.login(id, password, session);

      if (success) {
          response.put("status", "success");
          response.put("redirectUrl", "/");
      } else {
          response.put("status", "fail");
          response.put("message", "아이디 또는 비밀번호가 올바르지 않습니다.");
      }

      return response;
  }

}