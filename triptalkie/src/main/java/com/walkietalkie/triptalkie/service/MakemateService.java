package com.walkietalkie.triptalkie.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.domain.CommonPage;
import com.walkietalkie.triptalkie.domain.Country;
import com.walkietalkie.triptalkie.domain.Land;
import com.walkietalkie.triptalkie.domain.Makemate;
import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.mapper.MakemateMapper;

@Service
@Transactional
public class MakemateService {
	
	private final MakemateMapper makemateMapper;
	
	public MakemateService(MakemateMapper makemateMapper) {
		super();
		this.makemateMapper = makemateMapper;
	}
	
	// 글 목록 페이지
	public Map<String, Object> findMakematesAllList(int currentPage, int size) {
		// 페이지네이션
		if(currentPage < 1) currentPage = 1;

		int totalCount = makemateMapper.countMakemate(); // 전체 데이터 개수
		int offset = (currentPage - 1) * size;                   // 몇 번째부터 가져올지
		int totalPage = (int) Math.ceil((double) totalCount / size); // 전체 페이지 개수
		if(currentPage > totalPage) currentPage = totalPage;
		
		int blockSize = 3; // 아래에 있는 페이지네이션의 페이지 목록 갯수
		int startPage = ((currentPage - 1) / blockSize) * blockSize + 1;
		int endPage = Math.min(startPage + blockSize - 1, totalPage);
		if(totalCount == 0) {
			startPage = 1;
			endPage = 1;
			totalPage = 1;
		}
 
		List<Makemate> makeMateList = makemateMapper.findMakematesAllList(size, offset);
		CommonPage<Makemate> commonPage = new CommonPage<>(makeMateList, size, currentPage, totalPage, startPage, endPage);
		
		// list에서 보여줄 값 세팅 : makemate, member, city
		List<Map<String, Object>> combinedList = new ArrayList<>();

		for(Makemate makemate : makeMateList) {
			Member member = makemateMapper.findMemberById(makemate.getMemberId());
			City city = makemateMapper.findCityByIdx(makemate.getCityId());
			
		    Map<String, Object> combinedListMap = new HashMap<>();
		    combinedListMap.put("makemate", makemate);
		    combinedListMap.put("member", member);
		    combinedListMap.put("city", city);

		    combinedList.add(combinedListMap);
		}
		
		//  컨트롤러에서 받을 값 세팅 : 페이지네이션, member + city
		Map<String, Object> result = new HashMap<>();
		result.put("commonPage", commonPage);
		result.put("combinedList", combinedList);

		return result;
	}

	// 글 상세 페이지
	public Map<String, Object> findMakemateByIdx(int idx) {
		Makemate makemate = makemateMapper.findMakemateByIdx(idx);
		Member member = makemateMapper.findMemberById(makemate.getMemberId());
		City city = makemateMapper.findCityByIdx(makemate.getCityId());
		Country country = makemateMapper.findCountryByIdx(city.getCountryId());
		Land land = makemateMapper.findLandByIdx(country.getLandId());
		int numbersOfMembers = makemateMapper.findCountMemberByIdx(makemate.getIdx());

	    Map<String, Object> combinedMap = new HashMap<>();
	    combinedMap.put("makemate", makemate);
	    combinedMap.put("member", member);
	    combinedMap.put("city", city);
	    combinedMap.put("country", country);
	    combinedMap.put("land", land);
	    combinedMap.put("numbersOfMembers", numbersOfMembers);
	    
		return combinedMap;
	}
	
	// 조회수 증가
	public void increaseViewCount(int idx) {
		int result = makemateMapper.increaseViewCount(idx);
	}
	
	public Map<String, Object> findAllRegion() {
		List<City> city = makemateMapper.findAllCityName();
		List<Country> country = makemateMapper.findAllCountryName();
		List<Land> land = makemateMapper.findAllLandName();
		
		Map<String, Object> combinedMap = new HashMap<>();
		combinedMap.put("city", city);
		combinedMap.put("country", country);
		combinedMap.put("land", land);
		    
		return combinedMap;
	}

	public void registerMakemate(Makemate makemate) {
		if (makemate.getEnddate().isBefore(makemate.getStartdate())) {
		    throw new IllegalArgumentException("종료일은 시작일 이후여야 합니다.");
		}
		int result = makemateMapper.registerMakemate(makemate);
	}



}
