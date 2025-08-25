package com.walkietalkie.triptalkie.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.DTO.TravelInfoListDTO;
import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.domain.CommonPage;
import com.walkietalkie.triptalkie.domain.Community;
import com.walkietalkie.triptalkie.domain.Country;
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

	// 내가 쓴 글 목록을 아이디로 조회하기 위한 메서드
	public List<TravelInfo> findTravelInfoByMemberId(String loginMember) {
		return travelInfoMapper.findTravelInfoByMemberId(loginMember);
	}
	
    /**
     * 모든 여행 정보 목록을 조회합니다.
     * @return TravelInfoListDTO 객체들의 리스트
     */
    public CommonPage<TravelInfoListDTO> getTravelInfoListPage(int page, int size) {
        // offset 계산
        int offset = (page - 1) * size;

        // 페이지 데이터 조회
        List<TravelInfoListDTO> content = travelInfoMapper.selectTravelInfoListPage(offset, size);

        // 전체 데이터 개수 조회
        int totalCount = travelInfoMapper.selectTravelInfoCount();
        int totalPage = (int) Math.ceil((double) totalCount / size);

        // 페이지 블록 계산
        int pageBlock = 5;
        int startPage = ((page - 1) / pageBlock) * pageBlock + 1;
        int endPage = Math.min(startPage + pageBlock - 1, totalPage);

        return new CommonPage<>(content, size, page, totalPage, startPage, endPage);
    }
    
    public void increaseViewCount(long idx) {
        travelInfoMapper.updateViewCount(idx);
    }
    
    public List<Country> getAllCountries() {
    	return travelInfoMapper.getAllCountries();
    }
    
    public List<City> getAllCities() {
    	return travelInfoMapper.getAllCities();
    }

}
