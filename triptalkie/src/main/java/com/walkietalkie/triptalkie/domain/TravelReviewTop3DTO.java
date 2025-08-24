package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class TravelReviewTop3DTO {
	private long idx;
	private String title;
	private String thumbnail;
	private String countryName;
	private String cityName;
	private int view;
	private LocalDateTime createdAt;
	
	public TravelReviewTop3DTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TravelReviewTop3DTO(long idx, String title, String thumbnail, String countryName, String cityName, int view,
			LocalDateTime createdAt) {
		super();
		this.idx = idx;
		this.title = title;
		this.thumbnail = thumbnail;
		this.countryName = countryName;
		this.cityName = cityName;
		this.view = view;
		this.createdAt = createdAt;
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

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	@Override
	public String toString() {
		return "TravelReviewTop3DTO [idx=" + idx + ", title=" + title + ", thumbnail=" + thumbnail + ", countryName="
				+ countryName + ", cityName=" + cityName + ", view=" + view + ", createdAt=" + createdAt + "]";
	}

	
}
