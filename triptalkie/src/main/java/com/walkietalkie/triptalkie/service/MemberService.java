package com.walkietalkie.triptalkie.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.mapper.MemberMapper;

@Service
public class MemberService {
	
	private final BCryptPasswordEncoder passwordEncoder;
	private final MemberMapper memberMapper;
	
	public MemberService(BCryptPasswordEncoder passwordEncoder, MemberMapper memberMapper) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.memberMapper = memberMapper;
	}

	public int register(Member member) {
		String hashedPassword = passwordEncoder.encode(member.getPassword());
		member.setPassword(hashedPassword);
		member.setCredit(50.0);
		return memberMapper.register(member);
	}

}
