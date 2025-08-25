package com.walkietalkie.triptalkie.DTO;

import java.time.LocalDateTime;

/**
 * ReviewList 출력할 때 필요한 데이터만 모아놓은 DTO
 */
public class TravelInfoListDTO {
	// 접근제어자 private 변수 선언
    private long idx;
    private String title;
    private int view;
    private LocalDateTime createdAt;
    private String infotype;
    private LocalDateTime infodate;
    private String cityName;       
    // city 테이블의 name을 받을 필드
    private String memberNickname;
    // member 테이블의 nickname을 받을 필드
    
    // 생성자
	public TravelInfoListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// getter & setter
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
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getInfotype() {
		return infotype;
	}
	public void setInfotype(String infotype) {
		this.infotype = infotype;
	}
	public LocalDateTime getInfodate() {
		return infodate;
	}
	public void setInfodate(LocalDateTime infodate) {
		this.infodate = infodate;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	
	// toString 메서드
	@Override
	public String toString() {
		return "TravelReviewListDTO [idx=" + idx + ", title=" + title + ", view=" + view + ", createdAt=" + createdAt
				+ ", infotype=" + infotype + ", infodate=" + infodate + ", cityName=" + cityName + ", memberNickname="
				+ memberNickname + "]";
	}
    
}
