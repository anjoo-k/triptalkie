package com.walkietalkie.triptalkie.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.walkietalkie.triptalkie.domain.Makemate;

@Mapper
public interface BookMarkMapper {

    // 북마크 존재 여부 확인
    int existBookmark(@Param("memberId") String memberId,
                      @Param("makemateIdx") long makemateIdx);

    // 북마크 최초 추가
    void insertBookmark(@Param("memberId") String memberId,
                        @Param("makemateIdx") long makemateIdx);

    // 북마크 삭제 (비활성화 구조로 전환 시 삭제는 잘 안 쓰겠지만 남겨둠)
    void deleteBookmark(@Param("memberId") String memberId,
                        @Param("makemateIdx") long makemateIdx);

    // 북마크 목록 조회
    List<Makemate> findBookmarksWithMakemate(@Param("memberId") String memberId);

    // 상태 업데이트 (1=활성, 0=비활성)
    void updateBookmarkState(@Param("memberId") String memberId,
                             @Param("makemateIdx") long makemateIdx,
                             @Param("state") boolean state);
}
