package com.walkietalkie.triptalkie.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.DTO.MyPostsDTO;
import com.walkietalkie.triptalkie.DTO.SearchCriteria;
import com.walkietalkie.triptalkie.controller.TravelInfoController;
import com.walkietalkie.triptalkie.domain.City;
import com.walkietalkie.triptalkie.domain.CommonPage;
import com.walkietalkie.triptalkie.domain.Country;
import com.walkietalkie.triptalkie.domain.Makemate;
import com.walkietalkie.triptalkie.domain.MakemateImage;
import com.walkietalkie.triptalkie.domain.Member;
import com.walkietalkie.triptalkie.mapper.MypageMapper;

@Service
@Transactional
public class MypageService {

	private final TravelInfoController travelInfoController;
	private final CommunityService communityService;
	private final TravelInfoService travelInfoService;
	private final TravelReviewService travelReviewService;
	private final MakemateImageService makemateImageService;

	private final MypageMapper mypageMapper;
	private final BCryptPasswordEncoder passwordEncoder; // 비밀번호 암호화

	public MypageService(TravelInfoController travelInfoController, CommunityService communityService,
			TravelInfoService travelInfoService, TravelReviewService travelReviewService, MypageMapper mypageMapper,
			BCryptPasswordEncoder passwordEncoder, MakemateImageService makemateImageService) {
		super();
		this.travelInfoController = travelInfoController;
		this.communityService = communityService;
		this.travelInfoService = travelInfoService;
		this.travelReviewService = travelReviewService;
		this.mypageMapper = mypageMapper;
		this.passwordEncoder = passwordEncoder;
		this.makemateImageService = makemateImageService;
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

	// 내 여행 리스트
	public Map<String, Object> findMakematesAllListByMemberId(int currentPage, int size, String loginId) {
		// 페이지네이션
		if (currentPage < 1)
			currentPage = 1;

		int totalCount = mypageMapper.countMakemate(); // 전체 데이터 개수
		int offset = (currentPage - 1) * size; // 몇 번째부터 가져올지
		int totalPage = (int) Math.ceil((double) totalCount / size); // 전체 페이지 개수
		if (currentPage > totalPage)
			currentPage = totalPage;

		int blockSize = 3; // 아래에 있는 페이지네이션의 페이지 목록 갯수
		int startPage = ((currentPage - 1) / blockSize) * blockSize + 1;
		int endPage = Math.min(startPage + blockSize - 1, totalPage);
		if (totalCount == 0) {
			currentPage = 1;
			startPage = 1;
			endPage = 1;
			totalPage = 1;
		}

		List<Makemate> makeMateList = mypageMapper.findMakematesAllListByMemberId(size, offset, loginId);
		CommonPage<Makemate> commonPage = new CommonPage<>(makeMateList, size, currentPage, totalPage, startPage,
				endPage);

		// list에서 보여줄 값 세팅 : makemate, member, city
		List<Map<String, Object>> combinedList = new ArrayList<>();

		for (Makemate makemate : makeMateList) {
			Member member = mypageMapper.findMemberById(makemate.getMemberId());
			City city = mypageMapper.findCityByIdx(makemate.getCityId());
			Country country = mypageMapper.findCountryByIdx(city.getCountryId());
			MakemateImage photo = makemateImageService.findImageByMakemateIdx(makemate.getIdx());

			Map<String, Object> combinedListMap = new HashMap<>();
			combinedListMap.put("makemate", makemate);
			combinedListMap.put("member", member);
			combinedListMap.put("city", city);
			combinedListMap.put("country", country);
			combinedListMap.put("photo", photo);

			combinedList.add(combinedListMap);
		}

		// 컨트롤러에서 받을 값 세팅 : 페이지네이션, member + city
		Map<String, Object> result = new HashMap<>();
		result.put("commonPage", commonPage);
		result.put("combinedList", combinedList);

		return result;
	}

}
