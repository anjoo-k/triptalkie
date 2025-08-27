package com.walkietalkie.triptalkie.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.domain.TravelInfoImage;
@Mapper
public interface TravelInfoImageMapper {

	int registerTravelInfoImage(TravelInfoImage travelInfoImage);
	// 멤버프로필 등록 메서드
	
	TravelInfoImage findTravelInfoImageByIdx(Long travelinfoIdx);
	// 글에 해당되는 이미지 조회 메서드 : 글 idx로
	
	TravelInfoImage findByUuid(String uuid);
	// 조회 : 외부 API 용도
	TravelInfoImage findByIdx(Long idx);
	// 조회 : 내부 DB 관리용도

	int deleteByUuid(String uuid);
	// 삭제 : 외부 API 용도
	int deleteByIdx(Long idx);
	// 삭제 : 내부 DB 관리용
}
