package com.walkietalkie.triptalkie.test.travelreview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.domain.TravelReview;
import com.walkietalkie.triptalkie.mapper.TravelReviewMapper;
import com.walkietalkie.triptalkie.service.TravelReviewService;

//Spring JUnit Test 설정 
@SpringBootTest
//테스트 후 자동 롤백 설정 
@Transactional
public class TravelReivewServiceTest {
	
	@Autowired
	private TravelReviewService travelReviewService;
	@Autowired
	private TravelReviewMapper travelReviewMapper;
//	@Autowired
//	private Logger logger = LoggerFactory.getLogger(NamMemberServiceTest.class);
	
//	@Test
//	void testTravelReviewRegister() {
//		// 테스트용 Travelreview 객체 생성
//        TravelReview travelreview = new TravelReview();
//        travelreview.setTitle("테스트 여행 후기");
//        travelreview.setCreatedAt(LocalDateTime.now());
//        travelreview.setUpdatedAt(LocalDateTime.now());
//        travelreview.setMateUse(1);
//        travelreview.setConceptType("맛집/카페");
//        travelreview.setMateType("전체 메이트");
//        travelreview.setContent("이번 여행 정말 즐거웠어요!");
//        travelreview.setCityId("FR_PAR");
//        travelreview.setMemberId("user1");
//
//        // 서비스 호출
////        Long newIdx = travelReviewService.registerTravelreview(travelreview);
//
//        // 새로 등록된 idx가 null이 아닌지 확인
//        assertEquals(true, newIdx != null, "후기 등록 성공 시 새로운 idx가 반환되어야 함");
//        
//        System.out.println("들어간 정보 : " + travelreview);
//	}
//	
//	@Test
//	void testTravelReviewUpdate() {
//		// 1. 기존 리뷰 등록
//	    TravelReview travelreview = new TravelReview();
//	    travelreview.setTitle("테스트 여행 후기");
//	    travelreview.setMateUse(0);
//	    travelreview.setConceptType("맛집/카페");
//	    travelreview.setMateType("전체 메이트");
//	    travelreview.setContent("이번 여행 정말 즐거웠어요!");
//	    travelreview.setCityId("FR_PAR");
//	    travelreview.setMemberId("user1");
//	    
////	    Long idx = travelReviewService.registerTravelreview(travelreview);
//	    assertNotNull(idx);
//
//	    // 2. 수정할 내용 설정
//	    travelreview.setIdx(idx);  // 반드시 idx 지정
//	    travelreview.setTitle("업데이트된 제목");
//	    travelreview.setContent("업데이트된 내용");
//	    travelreview.setMateUse(1);
//	    
//	    // 3. 업데이트 호출
////	    travelReviewService.updateTravelreviewByIdxAndMemberId(travelreview);
//
//	    // 4. DB에서 다시 조회해서 확인
//	    Map<String, Object> updated = travelReviewService.findTravelreviewByIdx(idx);
//	    assertEquals("업데이트된 제목", ((TravelReview) updated).getTitle());
//	    assertEquals(true, ((TravelReview) updated).getMateUse());
//	    assertEquals("업데이트된 내용", ((TravelReview) updated).getContent());
//
//	    System.out.println("업데이트 후 정보 : " + updated);
//	}

}
