package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class Rating {
	private String title;
	private String memberId;
	private LocalDateTime enddate;
	private Long makemateIdx;
	private Boolean israted;
	public Rating(String title, String memberId, LocalDateTime enddate, Long makemateIdx, Boolean israted) {
		super();
		this.title = title;
		this.memberId = memberId;
		this.enddate = enddate;
		this.makemateIdx = makemateIdx;
		this.israted = israted;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public LocalDateTime getEnddate() {
		return enddate;
	}
	public void setEnddate(LocalDateTime enddate) {
		this.enddate = enddate;
	}
	public Long getMakemateIdx() {
		return makemateIdx;
	}
	public void setMakemateIdx(Long makemateIdx) {
		this.makemateIdx = makemateIdx;
	}
	public Boolean getIsrated() {
		return israted;
	}
	public void setIsrated(Boolean israted) {
		this.israted = israted;
	}
	@Override
	public String toString() {
		return "Rating [title=" + title + ", memberId=" + memberId + ", enddate=" + enddate + ", makemateIdx="
				+ makemateIdx + ", israted=" + israted + "]";
	}
	
}

	