package com.walkietalkie.triptalkie.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.domain.CommonPage;
import com.walkietalkie.triptalkie.domain.Country;
import com.walkietalkie.triptalkie.domain.Faq;
import com.walkietalkie.triptalkie.domain.Land;
import com.walkietalkie.triptalkie.domain.Notice;
import com.walkietalkie.triptalkie.mapper.CustomerserviceMapper;

@Service
public class CustomerserviceService {
	private final CustomerserviceMapper customerserviceMapper;

	public CustomerserviceService(CustomerserviceMapper customerserviceMapper) {
		super();
		this.customerserviceMapper = customerserviceMapper;
	}

	public List<Faq> findAllFaq() {
		return customerserviceMapper.findAllFqa();
	}

	public CommonPage<Map<String, Object>> findFaqPage(int page, int size) {
		// page, size 최소값 체크
		if (page < 1)
			page = 1;
		if (size < 1)
			size = 5;

		int totalCount = customerserviceMapper.findCountFaq();
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
		List<Map<String, Object>> list = customerserviceMapper.findFaqPaged(size, startRow);

		int pageBlock = 5; // 한 화면에 표시할 페이지 수
		int startPage = ((page - 1) / pageBlock) * pageBlock + 1;
		int endPage = Math.min(startPage + pageBlock - 1, totalPages);

		return new CommonPage<>(list, size, page, totalPages, startPage, endPage);
	}

	public List<Faq> findFaqTop5() {
		return customerserviceMapper.findFaqTop5();
	}

	public Map<String, Object> findNoticeAllList(int currentPage, int size) {
		if (currentPage < 1)
			currentPage = 1;

		int totalCount = customerserviceMapper.countNotice(); // 전체 데이터 개수
		int offset = (currentPage - 1) * size; // 몇 번째부터 가져올지
		int totalPage = (int) Math.ceil((double) totalCount / size); // 전체 페이지 개수
		if (currentPage > totalPage)
			currentPage = totalPage;

		int blockSize = 3; // 아래에 있는 페이지네이션의 페이지 목록 갯수
		int startPage = ((currentPage - 1) / blockSize) * blockSize + 1;
		int endPage = Math.min(startPage + blockSize - 1, totalPage);
		if (totalCount == 0) {
			currentPage = 1;
			startPage = 1;
			endPage = 1;
			totalPage = 1;
		}
		List<Notice> noticeList = customerserviceMapper.findNoticeAllList(size, offset);
		CommonPage<Notice> commonPage = new CommonPage<>(noticeList, size, currentPage, totalPage, startPage, endPage);

		List<Map<String, Object>> combinedList = new ArrayList<>();
		for (Notice notice : noticeList) {
			Map<String, Object> combinedListMap = new HashMap<>();
			combinedListMap.put("noticeList", notice);
			combinedList.add(combinedListMap);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("commonPage", commonPage);
		result.put("combinedList", combinedList);

		return result;
	}

	public void increaseViewCount(Long noticeId) {
		int result = customerserviceMapper.increaseViewCount(noticeId);
	}

	public Notice findNoticeByIdx(Long noticeId) {
		Notice notice = customerserviceMapper.findNoticeByIdx(noticeId);
		return notice;
	}

	public List<Notice> findNoticeTop5() {
		return customerserviceMapper.findNoticeTop5();
	}

	public CommonPage<Map<String, Object>> findQnaPage(int page, int size) {
		// page, size 최소값 체크
		if (page < 1)
			page = 1;
		if (size < 1)
			size = 5;

		int totalCount = customerserviceMapper.findCountQna();
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
		List<Map<String, Object>> list = customerserviceMapper.findQnaPaged(size, startRow);

		int pageBlock = 5; // 한 화면에 표시할 페이지 수
		int startPage = ((page - 1) / pageBlock) * pageBlock + 1;
		int endPage = Math.min(startPage + pageBlock - 1, totalPages);

		return new CommonPage<>(list, size, page, totalPages, startPage, endPage);
	}

}
