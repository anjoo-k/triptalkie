package com.walkietalkie.triptalkie.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.domain.Community;

@Mapper
public interface CommunityMapper {

	int register(Community community);

	Community findCommunityByIdx(long idx);
	
}
