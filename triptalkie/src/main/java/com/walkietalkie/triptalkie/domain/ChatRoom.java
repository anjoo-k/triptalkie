package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class ChatRoom {
	// 인스턴스 변수
	private Long idx;
	private String member1Id;
	private String member2Id;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Long makemateIdx;
	// 생성자
	public ChatRoom() {
		super();
	}
	// getter, setter 메서드
	public Long getIdx() {
		return idx;
	}
	public void setIdx(Long idx) {
		this.idx = idx;
	}
	public String getMember1Id() {
		return member1Id;
	}
	public void setMember1Id(String member1Id) {
		this.member1Id = member1Id;
	}
	public String getMember2Id() {
		return member2Id;
	}
	public void setMember2Id(String member2Id) {
		this.member2Id = member2Id;
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
	public Long getMakemateIdx() {
		return makemateIdx;
	}
	public void setMakemateIdx(Long makemateIdx) {
		this.makemateIdx = makemateIdx;
	}
	// toString 메서드
	@Override
	public String toString() {
		return "ChatRoom [idx=" + idx + ", member1Id=" + member1Id
				+ ", member2Id=" + member2Id + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", makemateIdx=" + makemateIdx
				+ "]";
	}

}