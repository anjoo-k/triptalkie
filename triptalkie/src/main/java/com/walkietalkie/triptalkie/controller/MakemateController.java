package com.walkietalkie.triptalkie.controller;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.walkietalkie.triptalkie.DTO.SearchCriteria;
import com.walkietalkie.triptalkie.domain.Makemate;
import com.walkietalkie.triptalkie.service.BookMarkService;
import com.walkietalkie.triptalkie.service.MakemateService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/makemate")
public class MakemateController {

	private final MakemateService makemateService;
	private final BookMarkService bookmarkService;

	public MakemateController(MakemateService makemateService, BookMarkService bookmarkService) {
		super();
		this.makemateService = makemateService;
		this.bookmarkService = bookmarkService;
	}
	
	// 글 목록 페이지
	@GetMapping("/list")
	public String makemateAllList(@RequestParam(defaultValue = "1")int page,
									@ModelAttribute SearchCriteria criteria, 
									Model model) {
		
		int size = 4; // 한 페이지 글 수
		Map<String, Object> result = makemateService.findMakematesAllList(page, size, criteria);
		
		model.addAttribute("page", result.get("commonPage"));
		model.addAttribute("combinedList", result.get("combinedList"));
		model.addAttribute("countryList", result.get("countryList"));
		model.addAttribute("cityList", result.get("cityList"));
		return "pages/make-mate/list";
	}
	
	// 글 상세 페이지
	@GetMapping("/detailPage/{makemateId}")
	public String detailMatematePage(@PathVariable Long makemateId, HttpSession session, Model model){
		
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";
		// 조회수 증가
		makemateService.increaseViewCount(makemateId);
		
		Map<String, Object> combinedMap = makemateService.findMakemateByIdx(makemateId, id);
		model.addAllAttributes(combinedMap);
		
		// 북마크 여부 확인 후 모델에 추가
	    boolean bookmarked = bookmarkService.isBookmarked(id, makemateId);
	    model.addAttribute("bookmarked", bookmarked);
		
		return "pages/make-mate/detail";
	}
	
	// 글쓰기 페이지
	@GetMapping("/registerPage")
	public String registerMatematePage(HttpSession session, Model model){
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";
		
		model.addAllAttributes(makemateService.findAllRegion());
		
		return "pages/make-mate/register";
	}
	
	// 글등록
	@PostMapping("/register")
	public String registerMatemate(HttpSession session, Makemate makemate,
									MultipartFile photo, Model model) throws IOException{
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";
		
		makemateService.registerMakemate(makemate, photo);
			
		return "redirect:/makemate/list";
	}
	
	// 글수정 페이지
	@GetMapping("/editPage/{makemateId}")
	public String updateMakematePage(@PathVariable Long makemateId, HttpSession session, Model model) throws AccessDeniedException{
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";
		
		Map<String, Object> combinedMap = makemateService.findMakemateByIdx(makemateId, id);
		Makemate makemate = (Makemate)combinedMap.get("makemate");
		
		if(!makemate.getMemberId().equals(id))
			throw new AccessDeniedException("본인 글만 수정 가능합니다.");

		model.addAllAttributes(combinedMap);
		return "pages/make-mate/edit";
	}
	
	// 글수정
	@PostMapping("/edit/{makemateId}")
	public String updateMakemate(@PathVariable Long makemateId, HttpSession session, Makemate makemate, MultipartFile photo) throws IOException {
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";
		
		if(!makemate.getMemberId().equals(id))
			throw new AccessDeniedException("본인 글만 수정 가능합니다.");
		makemate.setIdx(makemateId);
		makemateService.updateMakemate(makemate, photo);
		return "redirect:/makemate/detailPage/" + makemateId;
	}
	
	// 글삭제
	@PostMapping("/delete/{makemateId}")
	public String deleteMakemate(@PathVariable Long makemateId, HttpSession session) {
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";
		
		makemateService.deleteMakemateByIdx(id, makemateId);
		return "redirect:/makemate/list";
	}
	
	// makemate 신청
	@PostMapping("/apply/{makemateId}")
	public String applyMakemate(@PathVariable Long makemateId, HttpSession session) {
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";
		makemateService.applyMakemate(id, makemateId);
		return "redirect:/makemate/detailPage/" + makemateId;
	}
}
