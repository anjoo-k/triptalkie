package com.walkietalkie.triptalkie.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.domain.Member;

@Mapper
public interface MypageMapper {
	
	Member findMemberById(String id);

	int updateMemberById(Member member);
}
