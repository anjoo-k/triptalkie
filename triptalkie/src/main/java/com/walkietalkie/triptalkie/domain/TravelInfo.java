package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class TravelInfo {
	private long idx;
	private String title;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String infotype;
	private LocalDateTime infodate;
	private String content;
	private String memberId;

	// 생성자
	public TravelInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TravelInfo(long idx, String title, LocalDateTime createdAt, LocalDateTime updatedAt, String infotype,
			LocalDateTime infodate, String content, String memberId) {
		super();
		this.idx = idx;
		this.title = title;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.infotype = infotype;
		this.infodate = infodate;
		this.content = content;
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

	public String getInfotype() {
		return infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}

	public LocalDateTime getInfodate() {
		return infodate;
	}

	public void setInfodate(LocalDateTime infodate) {
		this.infodate = infodate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Travelinfo [idx=" + idx + ", title=" + title + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", infotype=" + infotype + ", infodate=" + infodate + ", content=" + content + ", memberId="
				+ memberId + "]";
	}

}
