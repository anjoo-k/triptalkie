package com.walkietalkie.triptalkie.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.domain.CommonPage;
import com.walkietalkie.triptalkie.domain.Country;
import com.walkietalkie.triptalkie.domain.TravelReview;
import com.walkietalkie.triptalkie.service.CityService;
import com.walkietalkie.triptalkie.service.CountryService;
import com.walkietalkie.triptalkie.service.TravelReviewService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/travel-review")
public class TravelReviewController {
	private final TravelReviewService travelReviewService;
	private final CountryService countryService;
	private final CityService cityService;

	public TravelReviewController(TravelReviewService travelReviewService, CountryService countryService,
			CityService cityService) {
		super();
		this.travelReviewService = travelReviewService;
		this.countryService = countryService;
		this.cityService = cityService;
	}

	@GetMapping("/findTravelreviewAllList")
	public String findTravelreviewAllList(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(required = false) String keyword,
			@RequestParam(required = false) String countryId, @RequestParam(required = false) String cityId,
			@RequestParam(required = false) String conceptType, Model model) {

		// 서비스에서 바로 CommonPage 객체 생성
		CommonPage<Map<String, Object>> pageData = travelReviewService.findTravelreviewPage(page, size, keyword,
				countryId, cityId, conceptType);

		// 모델에 데이터 넣기
		model.addAttribute("reviewList", pageData.getContent()); // 실제 데이터 리스트
		model.addAttribute("pageData", pageData); // 페이징 정보 전체

		List<Country> countryList = countryService.findCountryAllList();
		System.out.println("CountryList : " + countryList);
		model.addAttribute("countryList", countryList);

		return "pages/travel-review/findTravelreviewAllList";
	}

	@GetMapping("/detail-review/{idx}")
	public String findTravelreviewByIdx(@PathVariable Long idx, Model model, HttpSession session) {
		String id = (String) session.getAttribute("memberId");
		model.addAttribute("memberId", id);

		Map<String, Object> travelreview = travelReviewService.findTravelreviewByIdx(idx);

		model.addAttribute("travelreview", travelreview);
		return "pages/travel-review/detail-review";
	}

	@GetMapping("/registerReviewPage")
	public String registerReviewPage(Model model, HttpSession session) {
		String loginMember = (String) session.getAttribute("memberId");
//		model.addAttribute("memberId", id);
		// 세션이 비어있을 경우 에러 처리 추가
		if (loginMember == null) {
			return "redirect:/pages/member/login";
		}

		List<City> cityList = cityService.findCityAllList();
		model.addAttribute("cityList", cityList);

		return "pages/travel-review/register-review";
	}

	@PostMapping("/registerTravelreview")
	public String registerTravelreview(TravelReview travelReview) {

		if (!(travelReview.getMateType() == null)) {
			travelReview.setMateUse(true);
		}

		Long newIdx = travelReviewService.registerTravelreview(travelReview);

		return "redirect:/travel-review/detail-review/" + newIdx;
	}

	@GetMapping("/edit-review/{idx}")
	public String editReviewPage(@PathVariable Long idx, HttpSession session, Model model) {
		Map<String, Object> travelReview = travelReviewService.findTravelreviewByIdx(idx);

		String loginMember = (String) session.getAttribute("memberId");
		String travelReviewMemberId = (String) travelReview.get("memberId");

		if (!travelReviewMemberId.equals(loginMember)) {
			return "error/403";
		}

		List<City> cityList = cityService.findCityAllList();
		model.addAttribute("cityList", cityList);

		Map<String, Object> travelreview = travelReviewService.findTravelreviewByIdx(idx);
		model.addAttribute("travelreview", travelreview);

		return "pages/travel-review/edit-review";
	}

	@PostMapping("/updateTravelreviewByIdxAndMemberId")
	public String updateTravelreviewByIdxAndMemberId(@ModelAttribute TravelReview travelReview, HttpSession session,
			RedirectAttributes redirectAttributes) {

		String loginMember = (String) session.getAttribute("memberId");
		String travelReviewMemberId = travelReview.getMemberId(); // 객체 접근

	    if (!travelReviewMemberId.equals(loginMember)) {
	        return "error/403";	// 에러 처리
	    }
	    
		if (!(travelReview.getMateType() == null)) {
			travelReview.setMateUse(true);
		}
		travelReviewService.updateTravelreviewByIdxAndMemberId(travelReview);

		return "redirect:/travel-review/detail-review/" + travelReview.getIdx();
	}

	@GetMapping("/deleteTravelreviewByIdx")
	@ResponseBody
	public int deleteTravelreviewByIdx(@RequestParam Long idx) {

		return travelReviewService.deleteTravelreviewByIdx(idx);
	}

}
