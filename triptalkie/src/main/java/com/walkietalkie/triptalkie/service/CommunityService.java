package com.walkietalkie.triptalkie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walkietalkie.triptalkie.domain.Community;
import com.walkietalkie.triptalkie.mapper.CommunityMapper;

@Service
public class CommunityService {
	private final CommunityMapper communityMapper;
	@Autowired
	public CommunityService(CommunityMapper communityMapper) {
		this.communityMapper = communityMapper;
	}

	public int registerCommunity(Community community) {
		return communityMapper.register(community);
	}

	public Community findCommunityByIdx(long idx) {
		return communityMapper.findCommunityByIdx(idx);
	}
	
}
