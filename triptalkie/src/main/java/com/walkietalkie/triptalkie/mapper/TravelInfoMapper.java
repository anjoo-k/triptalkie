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

	// 검색 + 페이징 (Map 반환)
	List<Map<String, Object>> searchTravelInfo(String title, String infotype,
			String cityId, String countryId, int offset, int size);

	// 검색 결과 총 개수
	int countSearchTravelInfo(String title, String infotype, String cityId,
			String countryId);

}