package com.walkietalkie.triptalkie.controller;

import java.util.List;
import com.walkietalkie.triptalkie.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.domain.Country;
import com.walkietalkie.triptalkie.service.CityService;

@Controller
@RequestMapping("/cities")
public class CityController {

	private final CityService cityService;
	private final CountryService countryService;

	public CityController(CityService cityService, CountryService countryService) {
		super();
		this.cityService = cityService;
		this.countryService = countryService;
	}

	/*
	 * 나라 코드에 따라 도시들 출력하는 메서드
	 */
	@GetMapping("/findCitiesByCountry")
	@ResponseBody
	public List<City> findCitiesByCountry(@RequestParam String countryId) {
		Country country = countryService.findCountryNameById(countryId); // AU -> Australia
		System.out.println("cityService.findCitiesByCountry(country.getName()) : " + cityService.findCitiesByCountry(country.getName()));
	    return cityService.findCitiesByCountry(country.getName());
	}

}
