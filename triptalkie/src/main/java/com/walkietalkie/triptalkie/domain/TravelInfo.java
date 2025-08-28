package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class TravelInfo {
	private Long idx;
	private String title;
	private Long view;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String infotype;
	private LocalDateTime infodate;
	private String cityId;
	private String content;
	private String memberId;
	private String memberNickname;
	// db에 없는 변수, nickName 사용을 위해서 사용
	private String countryId;
	// 나라 선택 변수를 받기 위해서 사용
	private Member member;
	// Member 객체 생성
	private City city;
	// 계층형 매핑을 위한 city
	private Country country;

	// form에서 년-월만 받을 임시 필드
	private String tempMonth;

	// 생성자
	public TravelInfo() {
		super();
	}

	// getter / setter
	public String getTempMonth() {
		return tempMonth;
	}

	public void setTempMonth(String tempMonth) {
		this.tempMonth = tempMonth;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
    public String getCityName() {
        return city != null ? city.getName() : "";
    }
    
    public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getCountryName() {
    	return country != null ? country.getName() : "";
    }

	@Override
	public String toString() {
		return "TravelInfo [idx=" + idx + ", title=" + title + ", view=" + view
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", infotype=" + infotype + ", infodate=" + infodate
				+ ", cityId=" + cityId + ", content=" + content + ", memberId="
				+ memberId + ", memberNickname=" + memberNickname
				+ ", countryId=" + countryId + ", member=" + member + ", city="
				+ city + ", country=" + country + ", tempMonth=" + tempMonth
				+ "]";
	}

	
	

}