package com.walkietalkie.triptalkie.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.walkietalkie.triptalkie.DTO.TravelReviewTop3DTO;
import com.walkietalkie.triptalkie.domain.CommonPage;
import com.walkietalkie.triptalkie.domain.TravelReview;
import com.walkietalkie.triptalkie.mapper.TravelReviewMapper;

import jakarta.servlet.http.HttpSession;

@Service
public class TravelReviewService {
	private final TravelReviewMapper travelReviewMapper;
	private final TravelReviewImageService travelReviewImageService;

	public TravelReviewService(TravelReviewMapper travelReviewMapper,
			TravelReviewImageService travelReviewImageService) {
		super();
		this.travelReviewMapper = travelReviewMapper;
		this.travelReviewImageService = travelReviewImageService;
	}

	/**
	 * 여행 후기 상세 조회
	 * 
	 * @param idx
	 * @return
	 */
	public Map<String, Object> findTravelreviewByIdx(Long idx) {
		return travelReviewMapper.findTravelreviewByIdx(idx);
	}

	/*
	 * 여행 후기 삭제
	 * 사진이 첨부되어있는 게시글일 경우 사진까지 트랜잭션을 이용해 삭제한다.
	 */
	@Transactional
	public int deleteTravelreviewByIdx(Long idx) {
		travelReviewImageService.deleteReviewImageByIdx(idx);
		
		return travelReviewMapper.deleteTravelreviewByIdx(idx);
	}

	/*
	 * 여행 후기 작성
	 */
	public Long registerTravelreview(TravelReview travelReview, HttpSession session) {
		String loginMember = (String) session.getAttribute("loginId");

		travelReview.setMemberId(loginMember);

		travelReview.setMateUse(travelReview.getMateType().equals("사용 안함") ? 0 : 1);

		List<String> allowedConcept = Arrays.asList("맛집/카페", "술/친목", "액티비티/투어", "전시회/공연", "기타");
		if (!allowedConcept.contains(travelReview.getConceptType())) {
			throw new IllegalArgumentException("허용되지 않은 여행 컨셉입니다.");
		}

		if (travelReview.getMateType() != null && travelReview.getMateType().isEmpty()) {
			travelReview.setMateType(null);
		}

		travelReviewMapper.registerTravelreview(travelReview);
		return travelReview.getIdx();
	}

	/*
	 * 여행 후기 수정
	 */
	public void updateTravelreviewByIdxAndMemberId(TravelReview travelReview, String loginMember, MultipartFile file) {
		Map<String, Object> originalReview = travelReviewMapper.findTravelreviewByIdx(travelReview.getIdx());
		String originalMemberId = (String) originalReview.get("memberId");

		if (!loginMember.equals(originalMemberId)) {
			throw new IllegalArgumentException("본인 글만 수정할 수 있습니다.");
		}

		travelReview.setMateUse(travelReview.getMateType().equals("사용 안함") ? 0 : 1);

		List<String> allowedConcept = Arrays.asList("맛집/카페", "술/친목", "액티비티/투어", "전시회/공연", "기타");
		if (!allowedConcept.contains(travelReview.getConceptType())) {
			throw new IllegalArgumentException("허용되지 않은 여행 컨셉입니다.");
		}

		if (travelReview.getMateType() != null && travelReview.getMateType().isEmpty()) {
			travelReview.setMateType(null);
		}
		
	    travelReview.setMemberId(loginMember);
	    
	    if (file != null && !file.isEmpty()) {
	    	try {
				travelReviewImageService.updateReviewImage(travelReview.getIdx(), file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		travelReviewMapper.updateTravelreviewByIdxAndMemberId(travelReview);

	}

	/*
	 * 페이지네이션(Map 리스트 기준)
	 */
	public CommonPage<Map<String, Object>> findTravelreviewPage(int page, int size, String keyword, String countryId,
			String cityId, String conceptType) {
		// page, size 최소값 체크
		if (page < 1)
			page = 1;
		if (size < 1)
			size = 5;

		int totalCount = travelReviewMapper.findCountByConditions(keyword, countryId, cityId, conceptType);
		int totalPages = (int) Math.ceil((double) totalCount / size);

		// 검색 결과 0건이면 totalPages 0으로 처리
		if (totalCount == 0) {
			totalPages = 0;
			page = 0; // currentPage도 0
		} else {
			if (page > totalPages)
				page = totalPages;
		}

		int startRow = (page > 0) ? (page - 1) * size : 0;

		// Map 기반으로 페이징 쿼리 호출
		List<Map<String, Object>> list = travelReviewMapper.findPaged(size, startRow, keyword, countryId, cityId,
				conceptType);

		int pageBlock = 5; // 한 화면에 표시할 페이지 수
		int startPage = ((page - 1) / pageBlock) * pageBlock + 1;
		int endPage = Math.min(startPage + pageBlock - 1, totalPages);

		return new CommonPage<>(list, size, page, totalPages, startPage, endPage);
	}

	/*
	 * 여행 후기 게시글 조회수 증가
	 */
	public void incrementView(Long idx) {
		travelReviewMapper.incrementView(idx);
	}

	/*
	 * 메인에 출력할 조회수 top 3
	 */
	@Transactional(readOnly = true)
	public List<TravelReviewTop3DTO> findTravelreviewTop3() {
		return travelReviewMapper.findTravelreviewTop3ByView();
	}

	/*
	 *  내가 쓴 글 목록을 아이디로 조회하기 위한 메서드
	 */
	@Transactional(readOnly = true)
	public List<TravelReview> findtravelReviewByMemberId(String loginMember) {
		return travelReviewMapper.findtravelReviewByMemberId(loginMember);
	}

}
