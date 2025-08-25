package com.walkietalkie.triptalkie.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.domain.Community;
import com.walkietalkie.triptalkie.mapper.CommunityMapper;

@Service
@Transactional
public class CommunityService {
	private final CommunityMapper communityMapper;
	public CommunityService(CommunityMapper communityMapper) {
		this.communityMapper = communityMapper;
	}

	public int registerCommunity(Community community) {
		return communityMapper.register(community);
	}

	public Community findCommunityByIdx(long idx) {
		
		return communityMapper.findByIdx(idx);
	}

	public List<Community> findCommunityAllList() {
		return communityMapper.findAllList();
	}

	public int updateCommunityByIdxAndMemberId(Community community) {
		return communityMapper.updateCommunity(community);
	}
	public int deleteCommunityByIdx(long idx) {
	    return communityMapper.deleteCommunity(idx);
	}

	// 내가 쓴 글 목록을 아이디로 조회하기 위한 메서드
	public List<Community> findCommunityByMemberId(String loginMember) {
		return communityMapper.findCommunityByMemberId(loginMember);
	}

	
}
