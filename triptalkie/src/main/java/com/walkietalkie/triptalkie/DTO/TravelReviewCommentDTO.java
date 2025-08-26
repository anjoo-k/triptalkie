package com.walkietalkie.triptalkie.DTO;

import java.time.LocalDateTime;

public class TravelReviewCommentDTO {
	private Integer idx;	// 댓글 번호
	private Long travelReviewIdx;	// 게시글 번호
	private String content;			// 내용
	private String memberId;		// 작성자
	private String nickName;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	

	public TravelReviewCommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TravelReviewCommentDTO(Integer idx, Long travelReviewIdx, String content, String memberId, String nickName,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.idx = idx;
		this.travelReviewIdx = travelReviewIdx;
		this.content = content;
		this.memberId = memberId;
		this.nickName = nickName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	public Integer getIdx() {
		return idx;
	}


	public void setIdx(Integer idx) {
		this.idx = idx;
	}


	public Long getTravelReviewIdx() {
		return travelReviewIdx;
	}


	public void setTravelReviewIdx(Long travelReviewIdx) {
		this.travelReviewIdx = travelReviewIdx;
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


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
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


	@Override
	public String toString() {
		return "TravelReviewCommentDTO [idx=" + idx + ", travelReviewIdx=" + travelReviewIdx + ", content=" + content
				+ ", memberId=" + memberId + ", nickName=" + nickName + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}

	
}
