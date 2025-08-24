package com.walkietalkie.triptalkie.domain;

public class ChatMessage {
	private long idx;
	private String content;
	private String readcheck;
	private String createdAt;
	private long chatroomId;
	private String memberId;
	public ChatMessage() {
		super();
		// TODO Auto-generated constructor stub
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
	public long getChatroomId() {
		return chatroomId;
	}
	public void setChatroomId(long chatroomId) {
		this.chatroomId = chatroomId;
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
				+ ", chatroomId=" + chatroomId + ", memberId=" + memberId + "]";
	}

}