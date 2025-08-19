package com.walkietalkie.triptalkie.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.walkietalkie.triptalkie.controller.TravelInfoController;
import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.mapper.MypageMapper;

@Service
@Transactional
public class MypageService {

    private final TravelInfoController travelInfoController;
	
	private final MypageMapper mypageMapper;
	  private final BCryptPasswordEncoder passwordEncoder; // 비밀번호 암호화

	public MypageService(MypageMapper mypageMapper, BCryptPasswordEncoder passwordEncoder, TravelInfoController travelInfoController) {
		super();
		this.mypageMapper = mypageMapper;
		this.passwordEncoder = passwordEncoder;
		this.travelInfoController = travelInfoController;
	}

	@Transactional(readOnly = true)
	public Member findMemberById(String id) {
		return mypageMapper.findMemberById(id);
	}

	public int updateMemberById(Member member) {
		return mypageMapper.updateMemberById(member);
	}

	public Boolean checkPassword(String id, String password) {
		String passwordResult = mypageMapper.checkPassword(id);
		return passwordEncoder.matches(password, passwordResult);
	}

}
