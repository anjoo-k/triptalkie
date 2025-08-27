package com.walkietalkie.triptalkie.DTO;

import java.time.LocalDateTime;

public class TravelInfoTop3DTO {
	private long idx;
	private String title;
	private String thumbnail;
	private int view;
	private LocalDateTime createdAt;
	
	public TravelInfoTop3DTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TravelInfoTop3DTO(long idx, String title, String thumbnail, int view, LocalDateTime createdAt) {
		super();
		this.idx = idx;
		this.title = title;
		this.thumbnail = thumbnail;
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
		return "TravelInfoTop3DTO [idx=" + idx + ", title=" + title + ", thumbnail=" + thumbnail + ", view=" + view
				+ ", createdAt=" + createdAt + "]";
	}

	
}
