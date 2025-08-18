package com.walkietalkie.triptalkie.service;
import org.springframework.stereotype.Service;
import com.walkietalkie.triptalkie.mapper.MemberMapper;

@Service
public class MemberService {
	private final MemberMapper memberMapper;

	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	 public int login(String id, String password) {
	        return memberMapper.findMemberbyIdandPassword(id, password);     
	    }
	

}
