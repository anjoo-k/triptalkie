package com.walkietalkie.triptalkie.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.walkietalkie.triptalkie.domain.CommonPage;
import com.walkietalkie.triptalkie.domain.Faq;
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

}
