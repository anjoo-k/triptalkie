package com.walkietalkie.triptalkie.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.domain.Country;
import com.walkietalkie.triptalkie.mapper.CountryMapper;

@Service
public class CountryService {
	private final CountryMapper countryMapper;

	public CountryService(CountryMapper countryMapper) {
		super();
		this.countryMapper = countryMapper;
	}

	@Transactional(readOnly = true)
	public List<Country> findCountryAllList() {
		return countryMapper.findCountryAllList();
	}
	
	
}
