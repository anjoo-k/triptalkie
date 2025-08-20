package com.walkietalkie.triptalkie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.service.CityService;

@Controller
@RequestMapping("/cities")
public class CityController {
	private final CityService cityService;

	public CityController(CityService cityService) {
		super();
		this.cityService = cityService;
	}
	
	/*
	 * 나라 코드에 따라 도시들 출력하는 메서드
	 */
	@GetMapping("/citiesAllList")
	public List<City> findCitiesAllList(@RequestParam String countryName) {
		return cityService.findCitiesByCountry(countryName);
	}

}
