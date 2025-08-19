package com.walkietalkie.triptalkie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.walkietalkie.triptalkie.domain.Community;
import com.walkietalkie.triptalkie.service.CommunityService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/community")
public class CommunityController {
	private final CommunityService communityService;

	public CommunityController(CommunityService communityService) {

		this.communityService = communityService;
	}
//    private final CommunityService communityService;
//
//    @Autowired
//    public CommunityController(CommunityService communityService) {
//        this.communityService = communityService;
//    }

	// http://localhost:8080/community/list
	// 커뮤니티 목록 페이지
	@GetMapping("/list")
    public String communityList(Model model) {
        List<Community> communityList = communityService.findCommunityAllList();
        model.addAttribute("communityList", communityList);
        return "pages/community/list";
    }

	
	// http://localhost:8080/community/boardWrite
	// 커뮤니티 글작성 페이지
	@GetMapping("/register")
	public String communityRegister(Model model) {
		model.addAttribute("community",new Community()); // 바인딩 객체 추가
		return "pages/community/register";
	}

	// 커뮤니티 글 작성 처리
	@PostMapping("/register")
	public String registerCommunity(@ModelAttribute Community community, HttpSession session, RedirectAttributes redirectAttributes) {
		String memberId = (String) session.getAttribute("loggedInMemberId");
//		if (memberId == null) {
//			redirectAttributes.addFlashAttribute("errorMessage", "로그인이 필요합니다.");
//			return "redirect:/member/login";
//		}
		community.setMember_id("M001");
		communityService.registerCommunity(community);
		return "redirect:/community/list";
	}

    // 커뮤니티 글 상세 보기
    @GetMapping("/detail-community/{idx}")
    public String communityDetail(@PathVariable("idx") long idx, Model model) {
        Community community = communityService.findCommunityByIdx(idx);
        model.addAttribute("community", community);
        return "pages/community/detail-community"; // detail.html 파일을 새로 만들어야 합니다.
    }

	//커뮤니티 글 수정 페이지
    @GetMapping("/update/{idx}")
    public String updateForm(@PathVariable("idx") long idx, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Community community = communityService.findCommunityByIdx(idx);
        String memberId = (String) session.getAttribute("loggedInMemberId");

        if (memberId == null || !memberId.equals(community.getMemberId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "수정 권한이 없습니다.");
            return "redirect:/community/detail-community/" + idx;
        }

        model.addAttribute("community", community);
        return "pages/community/edit-community";
    }

    // 커뮤니티 글 수정 처리
    @PostMapping("/update")
    public String updateCommunity(@ModelAttribute Community community, HttpSession session, RedirectAttributes redirectAttributes) {
        String memberId = (String) session.getAttribute("loggedInMemberId");
        if (memberId == null || !memberId.equals(community.getMemberId())) {
            redirectAttributes.addFlashAttribute("errorMessage", "수정 권한이 없습니다.");
            return "redirect:/community/detail-community/" + community.getIdx();
        }
        communityService.updateCommunityByIdxAndMemberId(community);
        return "redirect:/community/detail-community/" + community.getIdx();
    }

//    // 커뮤니티 글 삭제 처리
//    @GetMapping("/delete/{idx}")
//    public String deleteCommunity(@PathVariable("idx") long idx, HttpSession session, RedirectAttributes redirectAttributes) {
//        Community community = communityService.findCommunityByIdx(idx);
//        String memberId = (String) session.getAttribute("loggedInMemberId");
//
//        if (memberId == null || !memberId.equals(community.getMemberId())) {
//            redirectAttributes.addFlashAttribute("errorMessage", "삭제 권한이 없습니다.");
//            return "redirect:/community/detail/" + idx;
//        }
//        communityService.deleteCommunityByIdx(idx);
//        return "redirect:/community/list";
//    }
}
