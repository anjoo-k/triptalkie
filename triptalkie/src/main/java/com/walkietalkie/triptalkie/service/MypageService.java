package com.walkietalkie.triptalkie.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.DTO.MyPostsDTO;
import com.walkietalkie.triptalkie.controller.TravelInfoController;
import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.mapper.MypageMapper;

@Service
@Transactional
public class MypageService {

	private final TravelInfoController travelInfoController;
	private final CommunityService communityService;
	private final TravelInfoService travelInfoService;
	private final TravelReviewService travelReviewService;

	private final MypageMapper mypageMapper;
	private final BCryptPasswordEncoder passwordEncoder; // 비밀번호 암호화

	
	public MypageService(TravelInfoController travelInfoController, CommunityService communityService,
			TravelInfoService travelInfoService, TravelReviewService travelReviewService, MypageMapper mypageMapper,
			BCryptPasswordEncoder passwordEncoder) {
		super();
		this.travelInfoController = travelInfoController;
		this.communityService = communityService;
		this.travelInfoService = travelInfoService;
		this.travelReviewService = travelReviewService;
		this.mypageMapper = mypageMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional(readOnly = true)
	public Member findMemberById(String id) {
		return mypageMapper.findMemberById(id);
	}

	public int updateMemberById(Member member) {
//		int result = ;
		// result가 1 이상이면 findMemberById 실행
		System.out.println(member);
		return mypageMapper.updateMemberById(member);
	}

	public Boolean checkPassword(String id, String password) {
		String passwordResult = mypageMapper.checkPassword(id);
		return passwordEncoder.matches(password, passwordResult);
	}

	@Transactional(readOnly = true)
	public MyPostsDTO findMyPosts(String loginMember) {
		MyPostsDTO myPosts = new MyPostsDTO();
		myPosts.setCommunityList(communityService.findCommunityByMemberId(loginMember));
		myPosts.setTravelInfoList(travelInfoService.findTravelInfoByMemberId(loginMember));
		myPosts.setTravelReviewList(travelReviewService.findtravelReviewByMemberId(loginMember));

		
		return myPosts;
	}

}
