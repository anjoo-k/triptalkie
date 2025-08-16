package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class Makemate {
	private long idx;
	private String title;
	private String content;
	private String state;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime startdate;
	private LocalDateTime enddate;
	private String cityId;
	private String memberId;

	// 생성자
	public Makemate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Makemate(long idx, String title, String content, String state, LocalDateTime createdAt,
			LocalDateTime updatedAt, LocalDateTime startdate, LocalDateTime enddate, String cityId, String memberId) {
		super();
		this.idx = idx;
		this.title = title;
		this.content = content;
		this.state = state;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.startdate = startdate;
		this.enddate = enddate;
		this.cityId = cityId;
		this.memberId = memberId;
	}

	public long getIdx() {
		return idx;
	}

	public void setIdx(long idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDateTime startdate) {
		this.startdate = startdate;
	}

	public LocalDateTime getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDateTime enddate) {
		this.enddate = enddate;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Makemate [idx=" + idx + ", title=" + title + ", content=" + content + ", state=" + state
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", startdate=" + startdate + ", enddate="
				+ enddate + ", cityId=" + cityId + ", memberId=" + memberId + "]";
	}

}
