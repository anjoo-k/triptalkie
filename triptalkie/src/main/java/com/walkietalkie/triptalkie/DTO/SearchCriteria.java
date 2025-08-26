package com.walkietalkie.triptalkie.DTO;

public class SearchCriteria {
	private String keyword;
	private String countryId;
	private String cityId;
	private String conceptType;
	
	public SearchCriteria() {
		super();
	}
	
	public SearchCriteria(String countryId, String cityId, String conceptType) {
		super();
		this.countryId = countryId;
		this.cityId = cityId;
		this.conceptType = conceptType;
	}

	public SearchCriteria(String keyword, String countryId, String cityId, String conceptType) {
		super();
		this.keyword = keyword;
		this.countryId = countryId;
		this.cityId = cityId;
		this.conceptType = conceptType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getConceptType() {
		return conceptType;
	}

	public void setConceptType(String conceptType) {
		this.conceptType = conceptType;
	}

	@Override
	public String toString() {
		return "SearchCriteria [keyword=" + keyword + ", countryId=" + countryId + ", cityId=" + cityId
				+ ", conceptType=" + conceptType + "]";
	}

}
