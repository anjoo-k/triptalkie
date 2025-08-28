package com.walkietalkie.triptalkie.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.domain.MemberImage;

@Mapper
// Mapper 파일이므로 Mapper 어노테이션 사용
public interface MemberImageMapper {
	// Mapper는 class가 아니라 interface로 구현

	int registerMemberImage(MemberImage memberImage);
	// 멤버프로필 등록 메서드

	MemberImage findByMemberImageById(String memberId);
	// 멤버프로필 조회 메서드 : Id로
	
	MemberImage findByUuid(String uuid);
	// 조회 : 외부 API 용도
	MemberImage findByIdx(Long idx);
	// 조회 : 내부 DB 관리용도

	int deleteByUuid(String uuid);

	// 삭제 : 외부 API 용도
	int deleteByIdx(Long idx);
	// 삭제 : 내부 DB 관리용
}