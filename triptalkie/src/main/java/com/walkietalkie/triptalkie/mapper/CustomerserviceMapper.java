package com.walkietalkie.triptalkie.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.walkietalkie.triptalkie.domain.Faq;
import com.walkietalkie.triptalkie.domain.Notice;

@Mapper
public interface CustomerserviceMapper {

	List<Faq> findAllFqa();

	int findCountFaq();

	List<Map<String, Object>> findFaqPaged(@Param("size") int size, @Param("startRow") int startRow);

	List<Notice> findNoticeAllList(int size, int offset);

	int countNotice();

	int increaseViewCount(Long noticeId);

	Notice findNoticeByIdx(Long noticeId);

	List<Faq> findFaqTop5();

	List<Notice> findNoticeTop5();

	int findCountQna();

	List<Map<String, Object>> findQnaPaged(int size, int startRow);

}
