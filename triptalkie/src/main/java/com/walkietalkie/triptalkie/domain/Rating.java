package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class Rating {
	private String title;
	private String memberId;
	private LocalDateTime enddate;
	private String imagePath;
	public Rating(String title, String memberId, LocalDateTime enddate, String imagePath) {
		this.title = title;
		this.memberId = memberId;
		this.enddate = enddate;
		this.imagePath = imagePath; 
	}
	
	
	public Rating(String title, String memberId, LocalDateTime enddate) {
		this.title = title;
		this.memberId = memberId;
		this.enddate = enddate;
	}
	
	public Rating() {
    }


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public LocalDateTime getEnddate() {
		return enddate;
	}
	public void setEnddate(LocalDateTime enddate) {
		this.enddate = enddate;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	@Override
	public String toString() {
		return "Rating [title=" + title + ", memberId=" + memberId + ", enddate=" + enddate + ", imagePath="
				+ imagePath + "]";
	}
	
	
	
}

	