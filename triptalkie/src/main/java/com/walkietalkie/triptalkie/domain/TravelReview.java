package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class TravelReview {
	private long idx;
	private String title;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private int mateUse;
	private String conceptType;
	private String mateType;
	private String content;
	private String cityId;
	private String memberId;

	// 생성자
	public TravelReview() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TravelReview(long idx, String title, LocalDateTime createdAt, LocalDateTime updatedAt, int mateUse,
			String conceptType, String mateType, String content, String cityId, String memberId) {
		super();
		this.idx = idx;
		this.title = title;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.mateUse = mateUse;
		this.conceptType = conceptType;
		this.mateType = mateType;
		this.content = content;
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

	public int getMateUse() {
		return mateUse;
	}

	public void setMateUse(int mateUse) {
		this.mateUse = mateUse;
	}

	public String getConceptType() {
		return conceptType;
	}

	public void setConceptType(String conceptType) {
		this.conceptType = conceptType;
	}

	public String getMateType() {
		return mateType;
	}

	public void setMateType(String mateType) {
		this.mateType = mateType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
		return "TravelReview [idx=" + idx + ", title=" + title + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", mateUse=" + mateUse + ", conceptType=" + conceptType + ", mateType=" + mateType + ", content="
				+ content + ", cityId=" + cityId + ", memberId=" + memberId + "]";
	}

	
}
