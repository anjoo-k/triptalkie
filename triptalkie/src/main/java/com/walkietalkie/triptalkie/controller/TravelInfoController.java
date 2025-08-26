package com.walkietalkie.triptalkie.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.walkietalkie.triptalkie.DTO.TravelInfoListDTO;
import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.domain.CommonPage;
import com.walkietalkie.triptalkie.domain.Country;
import com.walkietalkie.triptalkie.domain.TravelInfo;
import com.walkietalkie.triptalkie.mapper.TravelInfoMapper;
import com.walkietalkie.triptalkie.service.CityService;
import com.walkietalkie.triptalkie.service.CountryService;
import com.walkietalkie.triptalkie.service.MemberService;
import com.walkietalkie.triptalkie.service.TravelInfoService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

@Controller
@RequestMapping("/travel-info")
public class TravelInfoController {
	
	private final MemberService memberService;
	private final TravelInfoService travelInfoService;
	private final CountryService countryService;
	private final CityService cityService;

	@Autowired
	public TravelInfoController(TravelInfoService travelInforService, MemberService memberService, CountryService countryService, CityService cityService) {
		this.travelInfoService = travelInforService;
		this.memberService = memberService;
		this.countryService = countryService;
		this.cityService = cityService;
		
	}

	@GetMapping("/register")
	public String TravelInfoRegisterPage(Model model) {
		model.addAttribute("travelInfo", new TravelInfo());
		// th:object가 사용가능 하도록 비여있는 TravelInfo 객체를 만들어서 전달한다.
		
		// 데이터베이스에서 나라/도시 리스트 가져오기.
		List<Country> countryList = travelInfoService.getAllCountries();
		List<City> cityList = travelInfoService.getAllCities();
		
		model.addAttribute("countryList", countryList);
		model.addAttribute("cityList", cityList);
		
		return "pages/travel-info/register";
	}

	@PostMapping("/register")
	public String TravelInfoRegister(TravelInfo travelInfo, HttpSession session) {
		System.out.println("tempMonth: " + travelInfo.getTempMonth());
		
	    if (travelInfo.getTempMonth() != null && !travelInfo.getTempMonth().isEmpty()) {
	        // "2025-08" → LocalDateTime 변환 (1일 00:00:00 기준)
	        travelInfo.setInfodate(LocalDate.parse(travelInfo.getTempMonth() + "-01")
	                                         .atStartOfDay());
	    }

	    System.out.println("infodate: " + travelInfo.getInfodate());
		
		travelInfoService.registerTravelInfo(travelInfo, session);
		return "redirect:/travel-info/list";
	}

	@GetMapping("/list")
	public String travelInfoListPage(@RequestParam(defaultValue = "1") int page,
	                                 @RequestParam(defaultValue = "5") int size,
	                                 Model model) {

	    CommonPage<TravelInfoListDTO> pageData = travelInfoService.getTravelInfoListPage(page, size);
	    // 서비스에서 페이지 데이터 가져오기
	    
	    model.addAttribute("travelInfoList", pageData.getContent());
	    model.addAttribute("pageData", pageData);

	    return "pages/travel-info/list";
	}

	@GetMapping("/detail/{idx}")
	public String TravelInfoDetailPage(@PathVariable("idx") long idx, Model model, HttpSession session) {
	    // 1. 조회수 증가
	    travelInfoService.increaseViewCount(idx);
		
	    // 2. 뷰에 데이터 전달을 위해서 model 객체에 저장
	    TravelInfo travelInfo = travelInfoService.getTravelInfoDetail(idx);
	    model.addAttribute("travelInfo", travelInfo);
	    String loginId = memberService.getLoginId(session);
	    // 로그인 정보 가져오기
	    model.addAttribute("loginId", loginId);
	    
	    
		return "pages/travel-info/detail";
	}

	@GetMapping("/edit/{idx}")
	public String editPage(@PathVariable("idx") long idx, Model model, HttpSession session) {
	    TravelInfo travelInfo = travelInfoService.findTravelInfoIdx(idx);

	    if (travelInfo == null) {
	        return "redirect:/travel-info/list";
	    }

	    String loginId = (String) session.getAttribute("loginId");
	    if (!travelInfo.getMemberId().equals(loginId)) {
	        return "redirect:/travel-info/detail/" + idx;
	    }

	    // cityId → City 조회
	    City city = travelInfoService.findCityById(travelInfo.getCityId());

	    // city → countryId 조회
	    String selectedCountryId = null;
	    if (city != null) {
	        selectedCountryId = city.getCountryId();
	    }

	    // 나라 전체 목록 조회
	    List<Country> countryList = travelInfoService.getAllCountries();

	    model.addAttribute("travelInfo", travelInfo);
	    model.addAttribute("countryList", countryList);
	    model.addAttribute("selectedCountryId", selectedCountryId);

	    return "pages/travel-info/edit";
	}

	@PostMapping("/edit")
	public String editSubmit(TravelInfo travelInfo, HttpSession session, RedirectAttributes redirectAttributes) {
		// 1. 로그인 사용자 확인
		String loginId = memberService.getLoginId(session);

		// DB에서 원본 travelInfo 조회
		TravelInfo origin = travelInfoService.findTravelInfoIdx(travelInfo.getIdx());

		System.out.println("원본 데이터 : " + origin);

		// 로그인한 아이디와 글쓴이가 같은지 확인.
		if (!origin.getMemberId().equals(loginId)) {
			redirectAttributes.addFlashAttribute("msg", "작성자가 아닙니다.");
			return "redirect:/travel-info/detail/" + travelInfo.getIdx();
		}

		// 2. 년-월 -> LocalDateTime 변환
		if (travelInfo.getTempMonth() != null && !travelInfo.getTempMonth().isEmpty()) {
			YearMonth ym = YearMonth.parse(travelInfo.getTempMonth());
			travelInfo.setInfodate(ym.atDay(1).atStartOfDay());
		}

		travelInfo.setMemberId(loginId);

		System.out.println("업데이트 실행 데이터`: " + travelInfo);

		// 3. 업데이트
		int success = travelInfoService.updateTravelInfoByIdxAndMemberId(travelInfo, session);

		if (success > 0) {
			redirectAttributes.addFlashAttribute("msg", "글이 수정되었습니다.");
			return "redirect:/travel-info/detail/" + travelInfo.getIdx();
		} else {
			redirectAttributes.addFlashAttribute("msg", "글 수정에 실패했습니다.");
			return "redirect:/travel-info/edit/" + travelInfo.getIdx();
		}
	}

	@PostMapping("/delete/{idx}")
	public String deleteSubmit(@PathVariable("idx") Long idx, HttpSession session,
			RedirectAttributes redirectAttributes) {
		// 1. 현재 로그인한 사용자의 ID 가져오기
		String loginId = memberService.getLoginId(session);
		System.out.println("delete 기능 게시판 번호 : " + loginId);
		System.out.println("delete idx : " + idx);
		if (loginId == null) {
			redirectAttributes.addFlashAttribute("msg", "로그인이 필요합니다.");
			return "redirect:/member/loginPage"; // 로그인 페이지로 리다이렉트
		}

		// 2. Service에 삭제 작업 위임
		try {
			travelInfoService.deleteTravelInfoByIdx(idx, loginId);
			redirectAttributes.addFlashAttribute("msg", "게시물이 성공적으로 삭제되었습니다.");
		} catch (IllegalStateException e) {
			// 본인이 아닌 경우 등 예외 처리
			redirectAttributes.addFlashAttribute("msg", e.getMessage());
			return "redirect:/travel-info/detail/" + idx;
		}

		// 3. 삭제 후 목록 페이지로 리다이렉트
		return "redirect:/travel-info/list";
	}

}