package com.walkietalkie.triptalkie.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.walkietalkie.triptalkie.domain.CommonPage;
import com.walkietalkie.triptalkie.domain.TravelReview;
import com.walkietalkie.triptalkie.mapper.TravelReviewMapper;

@Service
public class TravelReviewService {
	private final TravelReviewMapper travelReviewMapper;

	public TravelReviewService(TravelReviewMapper travelReviewMapper) {
		super();
		this.travelReviewMapper = travelReviewMapper;
	}

	public Map<String, Object> findTravelreviewByIdx(Long idx) {
		return travelReviewMapper.findTravelreviewByIdx(idx);
	}

	public int deleteTravelreviewByIdx(Long idx) {
		return travelReviewMapper.deleteTravelreviewByIdx(idx);
	}

	public Long registerTravelreview(TravelReview travelReview) {
		System.out.println("mateUse=" + travelReview.getMateUse());
		System.out.println("conceptType=" + travelReview.getConceptType());
		System.out.println("mateType=" + travelReview.getMateType());
		
		travelReview.setMateUse(travelReview.getMateType().equals("사용 안함") ? 0 : 1);

		List<String> allowedConcept = Arrays.asList("맛집/카페", "같이 여행", "술/친목", "액티비티/투어", "전시회/공연", "기타");
		if (!allowedConcept.contains(travelReview.getConceptType())) {
			throw new IllegalArgumentException("허용되지 않은 여행 컨셉입니다.");
		}

		if (travelReview.getMateType() != null && travelReview.getMateType().isEmpty()) {
			travelReview.setMateType(null);
		}

		travelReviewMapper.registerTravelreview(travelReview);
		return travelReview.getIdx();
	}

	public void updateTravelreviewByIdxAndMemberId(TravelReview travelReview) {
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

	public void incrementView(Long idx) {
		System.out.println("Service 호출, idx: " + idx);
		travelReviewMapper.incrementView(idx);
		System.out.println("Mapper 호출 후");
	}

}
