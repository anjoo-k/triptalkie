package com.walkietalkie.triptalkie.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TravelReviewCommentDTO {
	private Integer idx;
	private Long travelReviewIdx;
	private Long parentId;
	private String content;
	private String memberId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private List<TravelReviewCommentDTO> replies = new ArrayList<>();

	public TravelReviewCommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TravelReviewCommentDTO(Integer idx, Long travelReviewIdx, Long parentId, String content, String memberId,
			LocalDateTime createdAt, LocalDateTime updatedAt, List<TravelReviewCommentDTO> replies) {
		super();
		this.idx = idx;
		this.travelReviewIdx = travelReviewIdx;
		this.parentId = parentId;
		this.content = content;
		this.memberId = memberId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.replies = replies;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public List<TravelReviewCommentDTO> getReplies() {
		return replies;
	}

	public void setReplies(List<TravelReviewCommentDTO> replies) {
		this.replies = replies;
	}

	@Override
	public String toString() {
		return "TravelReivewCommentDTO [idx=" + idx + ", travelReviewIdx=" + travelReviewIdx + ", parentId=" + parentId
				+ ", content=" + content + ", memberId=" + memberId + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", replies=" + replies + "]";
	}

}
