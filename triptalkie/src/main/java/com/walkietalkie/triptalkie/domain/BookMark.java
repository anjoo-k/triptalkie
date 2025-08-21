package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class BookMark {
	private long idx;
	private String memberId;
	private long makemate_idx;
	LocalDateTime createdAt;
	
	public BookMark(long idx, String memberId, long makemate_idx, LocalDateTime createdAt) {
		super();
		this.idx = idx;
		this.memberId = memberId;
		this.makemate_idx = makemate_idx;
		this.createdAt = createdAt;
	}
	
	public long getIdx() {
		return idx;
	}
	public void setIdx(long idx) {
		this.idx = idx;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public long getMakemate_idx() {
		return makemate_idx;
	}
	public void setMakemate_idx(long makemate_idx) {
		this.makemate_idx = makemate_idx;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "BookMark [idx=" + idx + ", memberId=" + memberId + ", makemate_idx=" + makemate_idx + ", createdAt="
				+ createdAt + "]";
	}
	
	
}

	