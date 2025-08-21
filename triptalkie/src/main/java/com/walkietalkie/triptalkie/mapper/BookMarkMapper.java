package com.walkietalkie.triptalkie.mapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface BookMarkMapper {

	int existBookmark(String memberId, long makemateIdx);

	void insertBookmark(String memberId, long makemateIdx);
	
	void deleteBookmark(String memberId, long makemateIdx);

	void findBookmarksWithMakemate(String memberId);
	

}
