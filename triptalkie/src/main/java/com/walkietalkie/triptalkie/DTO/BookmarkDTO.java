package com.walkietalkie.triptalkie.DTO;

import java.time.LocalDateTime;

public class BookmarkDTO {

    // bookmark
    private Long bookmarkIdx;

    // makemate
    private Long makemateIdx;
    private String title;
    private String content;
    private LocalDateTime startdate;  // DATETIME → LocalDateTime 추천
    private LocalDateTime enddate;
    private String conceptType;
    private String matetype;
    private String ageGroup;
    private String budget;
    private String state;

    // member
    private String memberId;
    private String memberNickname;
    private String memberGender;

    // city
    private String cityId;
    private String cityName;

    // country
    private String countryId;
    private String countryName;

    // land
    private String landId;
    private String landName;
	public Long getBookmarkIdx() {
		return bookmarkIdx;
	}
	public void setBookmarkIdx(Long bookmarkIdx) {
		this.bookmarkIdx = bookmarkIdx;
	}
	public Long getMakemateIdx() {
		return makemateIdx;
	}
	public void setMakemateIdx(Long makemateIdx) {
		this.makemateIdx = makemateIdx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getStartdate() {
		return startdate;
	}
	public void setStartdate(LocalDateTime startdate) {
		this.startdate = startdate;
	}
	public LocalDateTime getEnddate() {
		return enddate;
	}
	public void setEnddate(LocalDateTime enddate) {
		this.enddate = enddate;
	}
	public String getConceptType() {
		return conceptType;
	}
	public void setConceptType(String conceptType) {
		this.conceptType = conceptType;
	}
	public String getMatetype() {
		return matetype;
	}
	public void setMatetype(String matetype) {
		this.matetype = matetype;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getLandId() {
		return landId;
	}
	public void setLandId(String landId) {
		this.landId = landId;
	}
	public String getLandName() {
		return landName;
	}
	public void setLandName(String landName) {
		this.landName = landName;
	}
	@Override
	public String toString() {
		return "BookmarkDTO [bookmarkIdx=" + bookmarkIdx + ", makemateIdx=" + makemateIdx + ", title=" + title
				+ ", content=" + content + ", startdate=" + startdate + ", enddate=" + enddate + ", conceptType="
				+ conceptType + ", matetype=" + matetype + ", ageGroup=" + ageGroup + ", budget=" + budget + ", state="
				+ state + ", memberId=" + memberId + ", memberNickname=" + memberNickname + ", memberGender="
				+ memberGender + ", cityId=" + cityId + ", cityName=" + cityName + ", countryId=" + countryId
				+ ", countryName=" + countryName + ", landId=" + landId + ", landName=" + landName + "]";
	}
	
	
    
}
