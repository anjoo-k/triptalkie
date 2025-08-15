package com.walkietalkie.triptalkie.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.domain.Member;

@Mapper
public interface MemberMapper {

	void register(Member member);

}
