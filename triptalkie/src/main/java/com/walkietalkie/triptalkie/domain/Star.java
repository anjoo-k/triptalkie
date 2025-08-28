package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class Star {
	private Long idx;
	private String raterId;
	private String ratedId;
	private double rating;
	private LocalDateTime createdAt;
	private Long makemateIdx;	
	private boolean israted;
	
	public Star() {}

	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public String getRaterId() {
		return raterId;
	}

	public void setRaterId(String raterId) {
		this.raterId = raterId;
	}

	public String getRatedId() {
		return ratedId;
	}

	public void setRatedId(String ratedId) {
		this.ratedId = ratedId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Long getMakemateIdx() {
		return makemateIdx;
	}

	public void setMakemateIdx(Long makemateIdx) {
		this.makemateIdx = makemateIdx;
	}

	public boolean isIsrated() {
		return israted;
	}

	public void setIsrated(boolean israted) {
		this.israted = israted;
	}

	@Override
	public String toString() {
		return "Star [idx=" + idx + ", raterId=" + raterId + ", ratedId=" + ratedId + ", rating=" + rating
				+ ", createdAt=" + createdAt + ", makemateIdx=" + makemateIdx + ", israted=" + israted + "]";
	};
	
	
	
}
