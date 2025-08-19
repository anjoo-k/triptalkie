package com.walkietalkie.triptalkie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.domain.Community;

@Mapper
public interface CommunityMapper {

	int register(Community community);

	Community findByIdx(long idx);

	List<Community> findAllList();
	
}
