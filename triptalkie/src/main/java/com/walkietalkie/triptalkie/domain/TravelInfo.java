package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class TravelInfo {
	private long idx;
	private String title;
	private long view;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String infotype;
	private LocalDateTime infodate;
	private String cityId;
	private String content;
	private String memberId;
	private String memberNickname;
	// db에 없는 변수, nickName 사용을 위해서 사용

	private Member member;
	// Member 객체 생성

	// form에서 년-월만 받을 임시 필드
	private String tempMonth;

	// getter / setter
	public String getTempMonth() {
		return tempMonth;
	}

	public void setTempMonth(String tempMonth) {
		this.tempMonth = tempMonth;
	}

	// 생성자
	public TravelInfo() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getInfotype() {
		return infotype;
	}

	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public LocalDateTime getInfodate() {
		return infodate;
	}

	public void setInfodate(LocalDateTime infodate) {
		this.infodate = infodate;
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

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "TravelInfo [idx=" + idx + ", title=" + title + ", view=" + view + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", infotype=" + infotype + ", infodate=" + infodate + ", cityId="
				+ cityId + ", content=" + content + ", memberId=" + memberId + ", memberNickname=" + memberNickname
				+ ", member=" + member + ", tempMonth=" + tempMonth + "]";
	}

	

}
