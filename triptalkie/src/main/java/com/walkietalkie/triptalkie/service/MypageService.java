package com.walkietalkie.triptalkie.service;

import org.springframework.stereotype.Service;

import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.mapper.MypageMapper;

@Service
public class MypageService {
	
	private final MypageMapper mypageMapper;
	
	
	
	public MypageService(MypageMapper mypageMapper) {
		super();
		this.mypageMapper = mypageMapper;
	}



	public Member findMemberById(String id) {
		return mypageMapper.findMemberById(id);
	}

}
