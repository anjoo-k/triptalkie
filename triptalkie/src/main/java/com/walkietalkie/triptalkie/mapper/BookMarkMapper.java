package com.walkietalkie.triptalkie.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.walkietalkie.triptalkie.domain.Makemate;


@Mapper
public interface BookMarkMapper {

	int existBookmark(String memberId, long makemateIdx);

	void insertBookmark(String memberId, long makemateIdx);
	
	void deleteBookmark(String memberId, long makemateIdx);

	List<Makemate> findBookmarksWithMakemate(String memberId);
	

}
