package com.walkietalkie.triptalkie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.DTO.SearchCriteria;
import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.domain.Country;
import com.walkietalkie.triptalkie.domain.Makemate;
import com.walkietalkie.triptalkie.domain.Member;

@Mapper
public interface MypageMapper {
	
	Member findMemberById(String id);

	int updateMemberById(Member member);

	String checkPassword(String id);
	
	// 내 여행 리스트
	int countMakemate();
	List<Makemate> findMakematesAllListByMemberId(int size, int offset, String memberId);
	City findCityByIdx(String cityId);
	Country findCountryByIdx(String countryId);
	
}
