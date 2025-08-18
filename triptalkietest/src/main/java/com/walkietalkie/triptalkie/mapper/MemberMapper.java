package com.walkietalkie.triptalkie.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
	Integer findMemberbyIdandPassword(@Param("id") String id, @Param("password")String password);
}
