package com.walkietalkie.triptalkie.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.domain.TravelInfo;
import com.walkietalkie.triptalkie.mapper.TravelInfoMapper;

import jakarta.servlet.http.HttpSession;

@Service
@Transactional
public class TravelInfoService {

    private final MemberService memberService;
  private final TravelInfoMapper travelInfoMapper;
  
  // [] 의존성 주입
  public TravelInfoService(TravelInfoMapper travelInfoMapper, MemberService memberService) {
    this.travelInfoMapper = travelInfoMapper;
    this.memberService = memberService;
  }
  
  // 여행 정보 글 등록
  public int registerTravelInfo(TravelInfo travelInfo, HttpSession session) {
    int result = 0;
    String currentMemberId = memberService.getLoginId(session);
    String currentgetMemberNickname = memberService.getLoginNickname(session);
    
    // traveInfo 객체에 로그인한 사용자 id, nickname 넣기
    travelInfo.setMemberId(currentMemberId);
    travelInfo.setMemberNickname(currentgetMemberNickname);
    
    result = travelInfoMapper.registerTravelInfo(travelInfo);
    
    return result;
  }
  
    
  // 여행 정보 리스트 찾기
  public List<TravelInfo> findTravelInfoAllList() {
    return travelInfoMapper.findTravelInfoAllList();
  }
    
  // 여행 정보 글 idx 기준으로 상세 정보 찾기
  public TravelInfo findTravelInfoIdx(long idx) {
    // 조회수 증가 로직 추가해야 함
    
    return travelInfoMapper.findTravelInfoIdx(idx);
  }
  
  
  // 여행정보 글 수정
  @Transactional
  public int updateTravelInfoByIdxAndMemberId(TravelInfo travelInfo, HttpSession session) {
    // 세션에서 로그인한 사용자 ID 가져오기
    String loginId = (String) session.getAttribute("loginId");
    if (loginId == null || !loginId.equals(travelInfo.getMemberId())) {
      return 0; // 작성자가 아니면 수정 실패
    }

    // 수정일자 업데이트
    travelInfo.setUpdatedAt(LocalDateTime.now());

    return travelInfoMapper.updateTravelInfoByIdxAndMemberId(travelInfo);
  }
  
  
  // 여행정보 글 삭제
  public int deleteTravelInfoByIdx(Long idx, String loginId) {
    int result = travelInfoMapper.deleteTravelInfoByIdx(idx, loginId);

    
    return result;
  }

  public List<TravelInfo> findTravelInfoTop3() {
	return travelInfoMapper.findTravelInfoTop3();
  }
  
}
