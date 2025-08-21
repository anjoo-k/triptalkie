package com.walkietalkie.triptalkie.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.walkietalkie.triptalkie.service.MakemateService;

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
	
	// makemate 리스트 페이지
	@GetMapping("/list")	// 추후 전체 출력 + 검색 + 페이지네이션으로 변경 필요
	public String makemateAllList(@RequestParam(defaultValue = "1") int currentPage,
									@RequestParam(defaultValue = "4") int size,
									Model model) {
		
		Map<String, Object> result = makemateService.findMakematesAllList(currentPage, size);
		model.addAttribute("page", result.get("commonPage"));
		model.addAttribute("combinedList", result.get("combinedList"));
		logger.info("{}", result.get("combinedList"));
		return "pages/make-mate/list";
	}
	
	// 글 상세 페이지
	@GetMapping("/detailPage")
	public String detailMatematePage(){
		return "pages/make-mate/detail";
	}
	
	// 글쓰기 페이지
	@GetMapping("/registerPage")
	public String registerMatematePage(){
		return "pages/make-mate/register";
	}
}
