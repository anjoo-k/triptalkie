package com.walkietalkie.triptalkie.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.walkietalkie.triptalkie.domain.Faq;

@Mapper
public interface CustomerserviceMapper {

	List<Faq> findAllFqa();

	int findCountFaq();

	List<Map<String, Object>> findFaqPaged(@Param("size") int size, @Param("startRow") int startRow);

	List<Faq> findFaqTop5();

}
