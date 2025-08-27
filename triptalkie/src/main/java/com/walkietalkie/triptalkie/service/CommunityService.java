package com.walkietalkie.triptalkie.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walkietalkie.triptalkie.domain.CommonPage;
import com.walkietalkie.triptalkie.domain.Community;
import com.walkietalkie.triptalkie.mapper.CommunityMapper;

@Service
@Transactional
public class CommunityService {
	private final CommunityMapper communityMapper;
	public CommunityService(CommunityMapper communityMapper) {
		this.communityMapper = communityMapper;
	}
	//글 등록
	public int registerCommunity(Community community) {
		return communityMapper.register(community);
	}
	//커뮤니티 글 조회
	public Community findCommunityByIdx(long idx) {
		return communityMapper.findByIdx(idx);
	}
	//모든 커뮤니티 글 조회
	public List<Community> findCommunityAllList() {
		return communityMapper.findAllList();
	}
	//커뮤니티 글 수정
	public int updateCommunityByIdxAndMemberId(Community community) {
		return communityMapper.updateCommunity(community);
	}
	//커뮤니티 글 삭제
	public int deleteCommunityByIdx(long idx) {
	    return communityMapper.deleteCommunity(idx);
	}
	// 내가 쓴 글 목록을 아이디로 조회하기 위한 메서드
	public List<Community> findCommunityByMemberId(String loginMember) {
		return communityMapper.findCommunityByMemberId(loginMember);
	}
	// 조회수 증가
	public void increaseViewCount(long idx) {
		int result = communityMapper.increaseViewCount(idx);
	}
	// 페이징 조회
    public CommonPage<Community> findCommunityPage(int page, int size) {
        if (page < 1) page = 1;
        if (size < 1) size = 10;

        int totalCount = communityMapper.countAll();
        int totalPages = (int) Math.ceil((double) totalCount / size);

        if (totalCount == 0) {
            totalPages = 0;
            page = 0;
        } else if (page > totalPages) {
            page = totalPages;
        }

        int startRow = (page > 0) ? (page - 1) * size : 0;

        List<Community> list = communityMapper.findPaged(size, startRow);

        int pageBlock = 4; // 페이지 블록 크기
        int startPage = ((page - 1) / pageBlock) * pageBlock + 1;
        int endPage = Math.min(startPage + pageBlock - 1, totalPages);

        return new CommonPage<>(list, size, page, totalPages, startPage, endPage);
    }
    // 검색어 조회
    public CommonPage<Community> findCommunityPageByTitle(int page, int size, String search) {
        if (page < 1) page = 1;
        if (size < 1) size = 10;

        // 검색 조건에 맞는 전체 게시글 개수
        int totalCount = communityMapper.countByTitle(search);
        int totalPages = (int) Math.ceil((double) totalCount / size);

        if (totalCount == 0) {
            totalPages = 0;
            page = 0;
        } else if (page > totalPages) {
            page = totalPages;
        }

        int startRow = (page > 0) ? (page - 1) * size : 0;

        // 검색 조건에 맞는 게시글 리스트 조회
        List<Community> list = communityMapper.findPagedByTitle(size, startRow, search);

        int pageBlock = 4;
        int startPage = ((page - 1) / pageBlock) * pageBlock + 1;
        int endPage = Math.min(startPage + pageBlock - 1, totalPages);

        return new CommonPage<>(list, size, page, totalPages, startPage, endPage);
    }

	
}
