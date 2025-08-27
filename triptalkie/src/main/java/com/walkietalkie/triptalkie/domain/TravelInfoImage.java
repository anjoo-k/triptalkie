package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class TravelInfoImage {
    private Long idx;
    private String uuid;
    private String originalName;
    private String savedName;
    private String filePath;
    private Long fileSize;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long travelinfoIdx;
	public TravelInfoImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdx() {
		return idx;
	}
	public void setIdx(Long idx) {
		this.idx = idx;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getSavedName() {
		return savedName;
	}
	public void setSavedName(String savedName) {
		this.savedName = savedName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
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
	public Long getTravelinfoIdx() {
		return travelinfoIdx;
	}
	public void setTravelinfoIdx(Long travelinfoIdx) {
		this.travelinfoIdx = travelinfoIdx;
	}
	@Override
	public String toString() {
		return "TravelInfoImage [idx=" + idx + ", uuid=" + uuid + ", originalName=" + originalName + ", savedName="
				+ savedName + ", filePath=" + filePath + ", fileSize=" + fileSize + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", travelinfoIdx=" + travelinfoIdx + "]";
	}
    
}
