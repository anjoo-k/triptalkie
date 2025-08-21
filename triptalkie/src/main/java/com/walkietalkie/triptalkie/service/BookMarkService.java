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
	
	// 북마크 추가 및 삭제
	public boolean toggleBookmark(String memberId, long makemateIdx) {
		
		if(bookMarkMapper.existBookmark(memberId, makemateIdx)==0){ // 북마크 안되어있으면 북마크 추가
			bookMarkMapper.insertBookmark(memberId,makemateIdx);
			return true;
			
		}else {
			bookMarkMapper.deleteBookmark(memberId,makemateIdx); // 북마크 삭제
			return false;
		}
	}
	
	public List<Makemate> getBookMarkedMakemates(String memberId){
		return bookMarkMapper.findBookmarksWithMakemate(memberId);
	}
	
	
	
}
