package com.walkietalkie.triptalkie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.walkietalkie.triptalkie.domain.Community;
import com.walkietalkie.triptalkie.service.CommunityService;
import com.walkietalkie.triptalkie.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/community")
public class CommunityController {
    private final CommunityService communityService;
    private final MemberService memberService;

    public CommunityController(CommunityService communityService, MemberService memberService) {
        this.communityService = communityService;
        this.memberService = memberService;
    }

    // 커뮤니티 목록 페이지
    @GetMapping("/list")
    public String communityList(Model model) {
        List<Community> communityList = communityService.findCommunityAllList();
        model.addAttribute("communityList", communityList);
        return "pages/community/list";
    }

    // 커뮤니티 글작성 페이지
    @GetMapping("/register")
    public String communityRegister(Model model) {
        model.addAttribute("community", new Community());
        return "pages/community/register";
    }

    // 커뮤니티 글 작성 처리
    @PostMapping("/register")
    public String registerCommunity(@ModelAttribute Community community,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
        String loingId = memberService.getLoginId(session);
        if (loingId == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }
        community.setMemberId(loingId);
        communityService.registerCommunity(community);
        return "redirect:/community/list";
    }

    // 커뮤니티 글 상세 보기
    @GetMapping("/detail-community/{idx}")
    public String communityDetail(@PathVariable("idx") long idx, Model model) {
        Community community = communityService.findCommunityByIdx(idx);
        model.addAttribute("community", community);
        return "pages/community/detail-community";
    }

    // 커뮤니티 글 수정 페이지
    @GetMapping("/update/{idx}")
    public String updateForm(@PathVariable("idx") long idx,
                             Model model,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        Community community = communityService.findCommunityByIdx(idx);
        String loingId = memberService.getLoginId(session);

        if (loingId == null || !loingId.equals(community.getMemberId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "수정 권한이 없습니다.");
            return "redirect:/community/detail-community/" + idx;
        }

        model.addAttribute("community", community);
        return "pages/community/edit-community";
    }

    // 커뮤니티 글 수정 처리
    @PostMapping("/update")
    public String updateCommunity(@ModelAttribute Community community,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {
        String loingId = memberService.getLoginId(session);
        if (loingId == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
            return "redirect:/member/login";
        }
        
        // ✅ DB에서 작성자 확인
        Community dbCommunity = communityService.findCommunityByIdx(community.getIdx());
        if (!loingId.equals(dbCommunity.getMemberId())) {
        		System.out.println("수정 권한 오류");
            redirectAttributes.addFlashAttribute("errorMessage", "수정 권한이 없습니다.");
            return "redirect:/community/detail-community/" + community.getIdx();
        }
        
        community.setMemberId(loingId);
        
        int rowsAffected = communityService.updateCommunityByIdxAndMemberId(community);
        System.out.println("업데이트 메서드 실행 성공!!!");
        if (rowsAffected == 0) {
            System.out.println("수정 실패: 조건에 맞는 데이터 없음");
        }
        return "redirect:/community/detail-community/" + community.getIdx();
    }

    // 커뮤니티 글 삭제 처리
    @PostMapping("/delete/{idx}")
    public String deleteCommunity(@PathVariable("idx") long idx,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {
        Community community = communityService.findCommunityByIdx(idx);
        String loingId = memberService.getLoginId(session);

        if (loingId == null || !loingId.equals(community.getMemberId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "삭제 권한이 없습니다.");
            return "redirect:/community/detail-community/" + idx;
        }
        communityService.deleteCommunityByIdx(idx);
        return "redirect:/community/list";
    }
}
