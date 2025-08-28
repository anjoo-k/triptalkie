package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class Makemate {
	private long idx;
	private String title;
	private String content;
	private long view;
	private String mateType;
	private String conceptType;
	private int maxPeople;
	private String ageGroup;
	private String budget;
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

	public Makemate(long idx, String title, String content, long view, String mateType, String conceptType,
			int maxPeople, String ageGroup, String budget, String state, LocalDateTime createdAt, LocalDateTime updatedAt,
			LocalDateTime startdate, LocalDateTime enddate, String cityId, String memberId) {
		super();
		this.idx = idx;
		this.title = title;
		this.content = content;
		this.view = view;
		this.mateType = mateType;
		this.conceptType = conceptType;
		this.maxPeople = maxPeople;
		this.ageGroup = ageGroup;
		this.budget = budget;
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

	public long getView() {
		return view;
	}

	public void setView(long view) {
		this.view = view;
	}

	public String getMateType() {
		return mateType;
	}

	public void setMateType(String mateType) {
		this.mateType = mateType;
	}

	public String getConceptType() {
		return conceptType;
	}

	public void setConceptType(String conceptType) {
		this.conceptType = conceptType;
	}

	public int getMaxPeople() {
		return maxPeople;
	}

	public void setMaxPeople(int maxPeople) {
		this.maxPeople = maxPeople;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
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
		return "Makemate [idx=" + idx + ", title=" + title + ", content=" + content + ", view=" + view + ", mateType="
				+ mateType + ", conceptType=" + conceptType + ", maxPeople=" + maxPeople + ", ageGroup=" + ageGroup
				+ ", budget=" + budget + ", state=" + state + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", startdate=" + startdate + ", enddate=" + enddate + ", cityId=" + cityId + ", memberId=" + memberId
				+ "]";
	}

}
