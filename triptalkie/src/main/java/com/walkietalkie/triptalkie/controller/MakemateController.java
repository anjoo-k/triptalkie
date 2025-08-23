package com.walkietalkie.triptalkie.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.walkietalkie.triptalkie.domain.Makemate;
import com.walkietalkie.triptalkie.service.MakemateService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/makemate")
public class MakemateController {

	private final MakemateService makemateService;
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public MakemateController(MakemateService makemateService) {
		super();
		this.makemateService = makemateService;
	}
	
	/*
	 * 페이지네이션
	 * currentPage : 현재 페이지 번호
	 * size : 페이지 크기(한 페이지에 몇 개 보여줄 것인가)
	 * offset : DB에서 몇 번째 데이터부터 가져올 것인가(페이지마다 시작하는게 다르니까, (page-1)*size)
	 * totalCount : 전체 데이터 갯수
	 * totalPages : 총 페이지 수(totalCount가 212개라면, totalCount/size = 43)
	 * startPage/endPage : 페이지 버튼 범위(1~3, 4~6 이런식)
	 * content : 실제 데이터 목록(2페이지라면 6~10번 데이터)
	 * 
	 	- `총 게시글: 212개`
		- `page = 3`
		- `size = 5`
		
		👉 계산
		- offset = `(3-1)*5 = 10` → 11번째부터 가져옴
		- content = 11~15번째 게시글
		- totalPages = `Math.ceil(212/5) = 43`
		- startPage = 1, endPage = 3 (버튼 [1~3] 보여줌)
		 */
	
	// 글 목록 페이지
	@GetMapping("/list")	// 추후 전체 출력 + 검색 + 페이지네이션으로 변경 필요
	public String makemateAllList(@RequestParam(defaultValue = "1") int currentPage,
									@RequestParam(defaultValue = "4") int size,
									Model model) {
		
		Map<String, Object> result = makemateService.findMakematesAllList(currentPage, size);
		model.addAttribute("page", result.get("commonPage"));
		model.addAttribute("combinedList", result.get("combinedList"));
		return "pages/make-mate/list";
	}
	
	// 글 상세 페이지
	// 파티원 다차면 state 모집완료로 바꾸고 더 신청 못하게 해야함
	// 파티원 채울 때 리더는 어떻게 처리해야하나? - 채팅에서 해야하네
	@GetMapping("/detailPage/{idx}")
	public String detailMatematePage(@PathVariable int idx, HttpSession session, Model model){
		// 1. makemate 정보 o, 2. member 정보(글쓴이) o, 
		// 3. memberlist 정보-join 두 번 묶어서 사진만 내려보내주면 되는건가?, 
		// 4.  land, country, city 정보 o, 5. bookmark 정보
		// 6. viewCount 증가
		
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";
		// 조회수 증가
		makemateService.increaseViewCount(idx);
		
		// makemate, member, land, country, city 
		Map<String, Object> combinedMap = makemateService.findMakemateByIdx(idx);
		model.addAllAttributes(combinedMap);
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
	public String registerMatemate(HttpSession session, Makemate makemate, Model model){
		String id = (String) session.getAttribute("loginId");
		if (id == null)
			return "redirect:/member/loginPage";
		
		try {
			logger.info("{}", makemate);
			makemateService.registerMakemate(makemate);
		}catch(IllegalArgumentException e){
			model.addAttribute("errorMessage", e.getMessage());
			return "pages/makemate/register";
		}
		
		return "redirect:/makemate/list";
	}
}
