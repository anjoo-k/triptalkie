package com.walkietalkie.triptalkie.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.DTO.TravelInfoListDTO;
import com.walkietalkie.triptalkie.DTO.TravelInfoTop3DTO;
import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.domain.CommonPage;
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
	public TravelInfoService(TravelInfoMapper travelInfoMapper,
			MemberService memberService) {
		this.travelInfoMapper = travelInfoMapper;
		this.memberService = memberService;
	}

	// 여행 정보 글 등록
	public int registerTravelInfo(TravelInfo travelInfo, HttpSession session) {
		int result = 0;
		String loginId = memberService.getLoginId(session);
		String Nickname = memberService.getLoginNickname(session);

		// traveInfo 객체에 로그인한 사용자 id, nickname 넣기
		travelInfo.setMemberId(loginId);
		travelInfo.setMemberNickname(Nickname);

		result = travelInfoMapper.registerTravelInfo(travelInfo);

		return result;
	}

	// 여행 정보 리스트 찾기
	public List<TravelInfo> findTravelInfoAllList() {
		return travelInfoMapper.findTravelInfoAllList();
	}

	// 여행 정보 글 idx 기준으로 상세 정보 찾기
	@Transactional
	public TravelInfo findTravelInfoIdx(long idx) {

		return travelInfoMapper.findTravelInfoIdx(idx);
	}

	// 여행정보 글 수정
	@Transactional
	public int updateTravelInfoByIdxAndMemberId(TravelInfo travelInfo,
			HttpSession session) {
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

	@Transactional(readOnly = true)
	public List<TravelInfoTop3DTO> findTravelInfoTop3() {
		return travelInfoMapper.findTravelInfoTop3();
	}

	// 내가 쓴 글 목록을 아이디로 조회하기 위한 메서드
	public List<TravelInfo> findTravelInfoByMemberId(String loginMember) {
		return travelInfoMapper.findTravelInfoByMemberId(loginMember);
	}

	/**
	 * 모든 여행 정보 목록을 조회합니다.
	 * 
	 * @return TravelInfoListDTO 객체들의 리스트
	 */
	public CommonPage<TravelInfoListDTO> getTravelInfoListPage(int page,
			int size) {
		// offset 계산
		int offset = (page - 1) * size;

		// 페이지 데이터 조회
		List<TravelInfoListDTO> content = travelInfoMapper
				.selectTravelInfoListPage(offset, size);

		// 전체 데이터 개수 조회
		int totalCount = travelInfoMapper.selectTravelInfoCount();
		int totalPage = (int) Math.ceil((double) totalCount / size);

		int pageBlock = 5;
		// 페이지 블록 기본값

		int startPage = ((page - 1) / pageBlock) * pageBlock + 1;

		int endPage = Math.min(startPage + pageBlock - 1, totalPage);

		// [] 만약에 totalPage 개수가 총 페이지 숫자보다 작으면 -> 총 페이지 수만큼만 버튼 출력한다.
		if (totalPage <= pageBlock) {
			startPage = 1;
			endPage = totalPage;
		}

		return new CommonPage<>(content, size, page, totalPage, startPage,
				endPage);
	}

	@Transactional
	public void increaseViewCount(long idx) {
		travelInfoMapper.updateViewCount(idx);
	}

	public List<Country> getAllCountries() {
		return travelInfoMapper.getAllCountries();
	}

	public List<City> getAllCities() {
		return travelInfoMapper.getAllCities();
	}

	public City findCityById(String id) {
		return travelInfoMapper.findCityById(id);
	}

	public TravelInfo getTravelInfoDetail(Long idx) {
		TravelInfo travelInfo = travelInfoMapper.getTravelInfoDetail(idx);

		// DB infodate를 년-월 문자열로 변환해서 tempMonth에 저장
		if (travelInfo.getInfodate() != null) {
			travelInfo.setTempMonth(travelInfo.getInfodate()
					.format(DateTimeFormatter.ofPattern("yyyy-MM")));
		}

		return travelInfo;
	}

    // 검색 + 페이징
    public Map<String, Object> searchTravelInfo(String title, String infotype,
                                                String countryId, String cityId,
                                                int page, int size) {
        int offset = (page - 1) * size;

        List<Map<String, Object>> list = travelInfoMapper.searchTravelInfo(
                title, infotype, cityId, countryId, offset, size);

        int totalCount = travelInfoMapper.countSearchTravelInfo(title, infotype,
                cityId, countryId);
        int totalPage = (int) Math.ceil((double) totalCount / size);

        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("currentPage", page);
        result.put("size", size);
        result.put("totalPage", totalPage);

        return result;
    }
    


}