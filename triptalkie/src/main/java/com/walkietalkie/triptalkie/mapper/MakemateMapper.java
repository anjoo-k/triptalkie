package com.walkietalkie.triptalkie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.domain.Country;
import com.walkietalkie.triptalkie.domain.Land;
import com.walkietalkie.triptalkie.domain.Makemate;
import com.walkietalkie.triptalkie.domain.Member;

@Mapper
public interface MakemateMapper {

	int countMakemate();

	List<Makemate> findMakematesAllList(int size, int offset);

	Member findMemberById(String memberId);

	City findCityByIdx(String cityId);

	Country findCountryByIdx(String countryId);

	Land findLandByIdx(String landId);

	Makemate findMakemateByIdx(long idx);

	int increaseViewCount(long idx);

	int findCountMemberByIdx(long idx);

}
