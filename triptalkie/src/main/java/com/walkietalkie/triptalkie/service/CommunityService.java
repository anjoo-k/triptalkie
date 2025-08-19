package com.walkietalkie.triptalkie.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.walkietalkie.triptalkie.domain.Community;
import com.walkietalkie.triptalkie.mapper.CommunityMapper;

@Service
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
	
}
