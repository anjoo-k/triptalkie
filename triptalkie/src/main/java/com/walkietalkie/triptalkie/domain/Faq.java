package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class Faq {
	private Long idx;
	private String title;
	private Long view;
	private String content;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String memberId;
	
	public Faq() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Faq(Long idx, String title, Long view, String content, LocalDateTime createdAt, LocalDateTime updatedAt,
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

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getView() {
		return view;
	}

	public void setView(Long view) {
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
		return "Faq [idx=" + idx + ", title=" + title + ", view=" + view + ", content=" + content + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", memberId=" + memberId + "]";
	}
	
	
}
