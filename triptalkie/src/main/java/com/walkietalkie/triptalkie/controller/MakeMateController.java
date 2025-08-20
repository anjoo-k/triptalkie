package com.walkietalkie.triptalkie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.walkietalkie.triptalkie.service.MakeMateService;

@Controller
@RequestMapping("/make-mate")
public class MakeMateController {

	private final MakeMateService makeMateService;

	public MakeMateController(MakeMateService makeMateService) {
		super();
		this.makeMateService = makeMateService;
	}
	
	@GetMapping("/list")	// 추후 전체 출력 + 검색 + 페이지네이션으로 변경 필요
	public String goList () {
		return "pages/make-mate/list";
	}
}
