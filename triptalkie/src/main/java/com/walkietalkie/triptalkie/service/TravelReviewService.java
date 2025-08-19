package com.walkietalkie.triptalkie.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	/*
	 * 여행 리뷰 리스트 조회
	 */
	@Transactional(readOnly = true)
	public List<Map<String, Object>> findTravelreviewAllList() {
		return travelReviewMapper.findTravelreviewAllList();
	}

	public Map<String, Object> findTravelreviewByIdx(Long idx) {
		return travelReviewMapper.findTravelreviewByIdx(idx);
	}

	public int deleteTravelreviewByIdx(Long idx) {
		return travelReviewMapper.deleteTravelreviewByIdx(idx);
	}

	public Long registerTravelreview(TravelReview travelReview) {
		travelReviewMapper.registerTravelreview(travelReview);
		return travelReview.getIdx();
	}

	public void updateTravelreviewByIdxAndMemberId(TravelReview travelReview) {
		travelReviewMapper.updateTravelreviewByIdxAndMemberId(travelReview);

	}

	/*
	 * 페이지네이션(Map 리스트 기준)
	 */
	public CommonPage<Map<String, Object>> findTravelreviewPage(int page, int size) {
		int totalCount = travelReviewMapper.findTravelreviewAllCount();
		int totalPages = (int) Math.ceil((double) totalCount / size);

		int startRow = (page - 1) * size;

		// Map 기반으로 페이징 쿼리 호출
		List<Map<String, Object>> list = travelReviewMapper.findPaged(size, startRow);

		int pageBlock = 5; // 한 화면에 표시할 페이지 수
		int startPage = ((page - 1) / pageBlock) * pageBlock + 1;
		int endPage = Math.min(startPage + pageBlock - 1, totalPages);

		return new CommonPage<>(list, size, page, totalPages, startPage, endPage);
	}

	public List<TravelReview> findTravelreviewSearchUnited(String keyword, String country, String city,
			String concept) {
		System.out.println("service keyword: " + keyword + ", country: " + country + ", city: " + city + ", concept: " + concept);
		return travelReviewMapper.findTravelreviewSearchUnited(keyword, country, city, concept);
	}

}
