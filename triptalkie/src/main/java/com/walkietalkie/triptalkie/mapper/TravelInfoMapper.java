package com.walkietalkie.triptalkie.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.walkietalkie.triptalkie.DTO.TravelInfoListDTO;
import com.walkietalkie.triptalkie.DTO.TravelInfoTop3DTO;
import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.domain.Country;
import com.walkietalkie.triptalkie.domain.TravelInfo;

@Mapper
public interface TravelInfoMapper {
	int registerTravelInfo(TravelInfo travelInfo);
	// 여행 정보 글 등록

	List<TravelInfo> findTravelInfoAllList();
	// 여행 정보 리스트 찾기

	TravelInfo findTravelInfoIdx(long idx);
	// 여행 정보 글 idx 기준으로 상세 정보 찾기

	int updateTravelInfoByIdxAndMemberId(TravelInfo travelInfo);
	// 여행정보 글 업데이트

	int deleteTravelInfoByIdx(Long idx, String memberId);
	// 여행정보 글 삭제

	List<TravelInfoTop3DTO> findTravelInfoTop3();

	List<TravelInfo> findTravelInfoByMemberId(String loginMember);

	List<TravelInfoListDTO> selectTravelInfoListPage(int offset, int size);

	int selectTravelInfoCount();

	int updateViewCount(long idx);

	List<Country> getAllCountries();

	List<City> getAllCities();

	City findCityById(String id);

	TravelInfo getTravelInfoDetail(Long idx);
	
	List<TravelInfo> searchTravelInfo2(@Param("params") Map<String, Object> params, @Param("offset") int offset,@Param("limit") int limit);
	// 검색 + 페이지 관련 메서드 : 개선판 나오면 삭제
	
	int countSearchTravelInfo2(@Param("params") Map<String, Object> params);
	// 검색 + 페이징 관련 메서드 : 개선판 나오면 삭제
	
	List<TravelInfo> searchTravelInfo(Map<String, Object> params, int offset, int limit);
	// 검색 + 페이지 관련 메서드
	
    int countSearchTravelInfo(Map<String, Object> params);
    // 검색 + 페이지 관련 메서드
}