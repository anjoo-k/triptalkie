package com.walkietalkie.triptalkie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.domain.City;

@Mapper
public interface CityMapper {

	List<City> findCityAllList();

}
