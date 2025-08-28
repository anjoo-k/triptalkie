package com.walkietalkie.triptalkie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.walkietalkie.triptalkie.domain.Community;

@Mapper
public interface CommunityMapper {

	int register(Community community);

	Community findCommunityByIdx(long idx);

	Community findByIdx(long idx);

	List<Community> findAllList();

	int updateCommunity(Community community);

	int deleteCommunity(long idx);

	List<Community> findCommunityByMemberId(String loginMember);

	int increaseViewCount(long idx);
	
	int countAll();
	
    List<Community> findPaged(@Param("size") int size, @Param("startRow") int startRow);

	int countByTitle(@Param("search") String search);

	List<Community> findPagedByTitle(@Param("size") int size, 
									 @Param("startRow") int startRow, 
									 @Param("search") String search);
	
	
}
