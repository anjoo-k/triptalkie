package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class Qna {
	private long idx;
	private String title;
	private long view;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String memberId;
	
	public Qna() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Qna(long idx, String title, long view, String content, LocalDateTime createdAt, LocalDateTime updatedAt,
			String memberId) {
		super();
		this.idx = idx;
		this.title = title;
		this.view = view;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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

	public long getView() {
		return view;
	}

	public void setView(long view) {
		this.view = view;
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

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Qna [idx=" + idx + ", title=" + title + ", view=" + view + ", content=" + content + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", memberId=" + memberId + "]";
	}
	
}
