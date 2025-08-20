package com.walkietalkie.triptalkie.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.mapper.CityMapper;

@Service
public class CityService {
	private final CityMapper cityMapper;

	public CityService(CityMapper cityMapper) {
		super();
		this.cityMapper = cityMapper;
	}
	
	@Transactional(readOnly = true)
	public List<City> findCityAllList() {
		return cityMapper.findCityAllList();
	}

	/*
	 *  나라 코드에 따라 도시들 출력하는 메서드
	 */
	public List<City> findCitiesByCountry(String countryName) {
		return cityMapper.findCitiesByCountry(countryName);
	}

}
