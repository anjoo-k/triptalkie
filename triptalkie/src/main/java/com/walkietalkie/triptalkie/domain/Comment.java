package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class Comment {
	private Integer idx;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String memberId;

	public Comment() {
		super();
	}

	public Comment(String content, LocalDateTime createdAt, LocalDateTime updatedAt, String memberId) {
		super();
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.memberId = memberId;
	}

	public Comment(Integer idx, String content, LocalDateTime createdAt, LocalDateTime updatedAt, String memberId) {
		super();
		this.idx = idx;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.memberId = memberId;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public void setUpdatedAt(LocalDateTime updateddAt) {
		this.updatedAt = updateddAt;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Comment [idx=" + idx + ", content=" + content + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", memberId=" + memberId + "]";
	}

}