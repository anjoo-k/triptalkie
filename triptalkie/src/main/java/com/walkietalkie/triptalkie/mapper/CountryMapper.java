package com.walkietalkie.triptalkie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.domain.Country;

@Mapper
public interface CountryMapper {

	List<Country> findCountryAllList();

}
