package com.walkietalkie.triptalkie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

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

  List<TravelInfo> findTravelInfoTop3();

  List<TravelInfo> findTravelInfoByMemberId(String loginMember);
}
