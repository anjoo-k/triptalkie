package com.walkietalkie.triptalkie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.domain.TravelInfo;
import com.walkietalkie.triptalkie.mapper.TravelInfoMapper;

@Service
@Transactional
public class TravelInfoService {
  private final TravelInfoMapper travelInfoMapper;
  
  // [] 의존성 주입
  public TravelInfoService(TravelInfoMapper travelInfoMapper) {
    this.travelInfoMapper = travelInfoMapper;
  }
  
  // 여행 정보 글 등록
  public int registerTravelInfo(TravelInfo travelInfo) {
    int result = travelInfoMapper.registerTravelInfo(travelInfo);
    
    return result;
  }
  
    
  // 여행 정보 리스트 찾기
  public List<TravelInfo> findTravelInfoAllList() {
    return travelInfoMapper.findTravelInfoAllList();
  }
    
  // 여행 정보 글 idx 기준으로 상세 정보 찾기
  TravelInfo findTravelInfoIdx(long idx) {
    return travelInfoMapper.findTravelInfoIdx(idx);
  }
  
  
  // 여행정보 글 업데이트
  int updateTravelInfoByIdxAndMemberId(TravelInfo travelInfo) {
    int result = travelInfoMapper.updateTravelInfoByIdxAndMemberId(travelInfo);
    
    return result;
  }
  
  
  // 여행정보 글 삭제
  public int deleteTravelInfoByIdx(long idx) {
    int result = travelInfoMapper.deleteTravelInfoByIdx(idx);
    
    return result;
  }
  
}
