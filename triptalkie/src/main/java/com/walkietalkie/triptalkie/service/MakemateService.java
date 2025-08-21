package com.walkietalkie.triptalkie.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.domain.CommonPage;
import com.walkietalkie.triptalkie.domain.Makemate;
import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.mapper.MakemateMapper;

@Service
public class MakemateService {
	
	private final MakemateMapper makemateMapper;
	
	public MakemateService(MakemateMapper makemateMapper) {
		super();
		this.makemateMapper = makemateMapper;
	}

	public List<Map<String, Object>> findMakematesAllList(int currentPage, int size) {
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
		CommonPage<Makemate> CommonPage = new CommonPage<>(makeMateList, size, currentPage, totalPage, startPage, endPage);
		
		List<Map<String, Object>> combinedList = new ArrayList<>();

		for(Makemate makemate : makeMateList) {
			Member member = makemateMapper.findMemberById(makemate.getMemberId());
			City city = makemateMapper.findCityByIdx(makemate.getCityId());
			
		    Map<String, Object> row = new HashMap<>();
		    row.put("makemate", makemate);
		    row.put("member", member);
		    row.put("city", city);

		    combinedList.add(row);
		}
		
//		Map<String, Object> combinePageMemberCity = new HashMap<>();
//		combinePageMemberCity.put("CommonPage", CommonPage);
//		combinePageMemberCity.put("member", member);
//		combinePageMemberCity.put("city", city);

		return combinedList;
	}

}
