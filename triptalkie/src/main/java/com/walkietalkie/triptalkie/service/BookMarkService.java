package com.walkietalkie.triptalkie.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.walkietalkie.triptalkie.domain.Makemate;
import com.walkietalkie.triptalkie.mapper.BookMarkMapper;

@Service
public class BookMarkService {
    private final BookMarkMapper bookMarkMapper;

    public BookMarkService(BookMarkMapper bookMarkMapper) {
        this.bookMarkMapper = bookMarkMapper;
    }

    /**
     * 북마크 토글
     * 북마크 상태 업데이트
     * 존재하지 않으면 INSERT + state=1
     * 존재하면 state 값에 따라 활성/비활성 전환
     * @return true = 활성, false = 비활성
     */
	@SuppressWarnings("unused")
	public boolean toggleBookmark(String memberId, long makemateIdx) {
		Integer currentState = bookMarkMapper.existBookmark(memberId, makemateIdx);

		if (currentState == null) {
		    bookMarkMapper.insertBookmark(memberId, makemateIdx); // 최초 생성
		    return true;
		} else if (currentState == 0) {
		    bookMarkMapper.updateBookmarkState(memberId, makemateIdx, true);
		    return true;
		} else {
		    bookMarkMapper.updateBookmarkState(memberId, makemateIdx, false);
		    return false;
		}

    }
	
	//북마크 상태를 조회
    public boolean isBookmarked(String memberId, long makemateIdx) {
        Integer state = bookMarkMapper.existBookmark(memberId, makemateIdx);
        return state != null && state == 1;
    }
	

    /**
     * 사용자의 북마크된 메이트 목록 조회
     */
    public List<Makemate> getBookMarkedMakemates(String memberId) {
        return bookMarkMapper.findBookmarksWithMakemate(memberId);
    }
}
