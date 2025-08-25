package com.walkietalkie.triptalkie.DTO;

import java.util.List;

import com.walkietalkie.triptalkie.domain.Community;
import com.walkietalkie.triptalkie.domain.TravelInfo;
import com.walkietalkie.triptalkie.domain.TravelReview;

public class MyPostsDTO {
	private List<Community> communityList;
	private List<TravelInfo> travelInfoList;
	private List<TravelReview> travelReviewList;
	
	public MyPostsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MyPostsDTO(List<Community> communityList, List<TravelInfo> travelInfoList,
			List<TravelReview> travelReviewList) {
		super();
		this.communityList = communityList;
		this.travelInfoList = travelInfoList;
		this.travelReviewList = travelReviewList;
	}

	public List<Community> getCommunityList() {
		return communityList;
	}

	public void setCommunityList(List<Community> communityList) {
		this.communityList = communityList;
	}

	public List<TravelInfo> getTravelInfoList() {
		return travelInfoList;
	}

	public void setTravelInfoList(List<TravelInfo> travelInfoList) {
		this.travelInfoList = travelInfoList;
	}

	public List<TravelReview> getTravelReviewList() {
		return travelReviewList;
	}

	public void setTravelReviewList(List<TravelReview> travelReviewList) {
		this.travelReviewList = travelReviewList;
	}

	@Override
	public String toString() {
		return "MyPostsDTO [communityList=" + communityList + ", travelInfoList=" + travelInfoList
				+ ", travelReviewList=" + travelReviewList + "]";
	}
	
	
}
