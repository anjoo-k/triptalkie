package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class Travelreview {
	private long idx;
	private String title;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private boolean mateuse;
	private String concepttype;
	private String matetype;
	private String content;
	private String cityId;
	private String memberId;

	// 생성자
	public Travelreview() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Travelreview(long idx, String title, LocalDateTime createdAt, LocalDateTime updatedAt, boolean mateuse,
			String concepttype, String matetype, String content, String cityId, String memberId) {
		super();
		this.idx = idx;
		this.title = title;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.mateuse = mateuse;
		this.concepttype = concepttype;
		this.matetype = matetype;
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

	public boolean isMateuse() {
		return mateuse;
	}

	public void setMateuse(boolean mateuse) {
		this.mateuse = mateuse;
	}

	public String getConcepttype() {
		return concepttype;
	}

	public void setConcepttype(String concepttype) {
		this.concepttype = concepttype;
	}

	public String getMatetype() {
		return matetype;
	}

	public void setMatetype(String matetype) {
		this.matetype = matetype;
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
		return "Travelreview [idx=" + idx + ", title=" + title + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", mateuse=" + mateuse + ", concepttype=" + concepttype + ", matetype=" + matetype + ", content="
				+ content + ", cityId=" + cityId + ", memberId=" + memberId + "]";
	}

}
