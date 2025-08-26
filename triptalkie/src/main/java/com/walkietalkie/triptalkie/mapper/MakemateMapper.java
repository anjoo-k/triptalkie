package com.walkietalkie.triptalkie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.DTO.SearchCriteria;
import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.domain.Country;
import com.walkietalkie.triptalkie.domain.Land;
import com.walkietalkie.triptalkie.domain.Makemate;
import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.domain.Memberlist;

@Mapper
public interface MakemateMapper {

	int countMakemate(SearchCriteria criteria);

	List<Makemate> findMakematesAllList(int size, int offset, SearchCriteria criteria);

	Member findMemberById(String memberId);

	City findCityByIdx(String cityId);

	Country findCountryByIdx(String countryId);

	Land findLandByIdx(String landId);

	Makemate findMakemateByIdx(long makemateId);

	int increaseViewCount(long makemateId);

	int findCountMemberByIdx(long makemateId);

	int registerMakemate(Makemate makemate);

	List<City> findAllCityName();

	List<Country> findAllCountryName();

	List<Land> findAllLandName();

	int updateMakemate(Makemate makemate);

	int deleteMakemateByIdx(String memberId, Long makemateId);

	int registerMemberlist(long makemateIdx, String memberId);

	List<Memberlist> findAllMemberlist(Long makemateId);

}
