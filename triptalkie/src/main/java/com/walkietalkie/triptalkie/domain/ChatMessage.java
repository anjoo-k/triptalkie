package com.walkietalkie.triptalkie.domain;

public class ChatMessage {
	private long idx;
	private String content;
	private String readcheck;
	private String createdAt;
	private long chatroomIdx;
	private String memberId;
	
	public ChatMessage() {
	}
	public ChatMessage(String memberId, String content, Long chatroomIdx) {
		this.memberId = memberId;
		this.content = content;
		this.chatroomIdx = chatroomIdx;
	}

	public long getIdx() {
		return idx;
	}
	public void setIdx(long idx) {
		this.idx = idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReadcheck() {
		return readcheck;
	}
	public void setReadcheck(String readcheck) {
		this.readcheck = readcheck;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public long getChatroomIdx() {
		return chatroomIdx;
	}
	public void setChatroomIdx(long chatroomIdx) {
		this.chatroomIdx = chatroomIdx;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "ChatMessage [idx=" + idx + ", content=" + content
				+ ", readcheck=" + readcheck + ", createdAt=" + createdAt
				+ ", chatroomIdx=" + chatroomIdx + ", memberId=" + memberId + "]";
	}

}