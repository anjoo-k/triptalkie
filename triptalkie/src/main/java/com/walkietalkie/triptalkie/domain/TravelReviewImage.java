package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class TravelReviewImage {
	private Long idx;
	private String uuid;	// 파일 고유 식별자 (UUID v4 기준 36자)
	private String originalName;	// 업로드 당시 원본 파일명
	private String savedName;		// 서버에 저장된 실제 파일명
	private String filePath;		// 실제 서버 저장 경로
	private Long fileSize;			// 파일 크기 (byte)
	private Long travelReviewIdx;	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public TravelReviewImage() {
		super();
	}

	public TravelReviewImage(Long idx, String uuid, String originalName, String savedName, String filePath,
			Long fileSize, Long travelReviewIdx, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.idx = idx;
		this.uuid = uuid;
		this.originalName = originalName;
		this.savedName = savedName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.travelReviewIdx = travelReviewIdx;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
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

	public Long getTravelReviewIdx() {
		return travelReviewIdx;
	}

	public void setTravelReviewIdx(Long travelReviewIdx) {
		this.travelReviewIdx = travelReviewIdx;
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

	@Override
	public String toString() {
		return "TravelReviewImage [idx=" + idx + ", uuid=" + uuid + ", originalName=" + originalName + ", savedName="
				+ savedName + ", filePath=" + filePath + ", fileSize=" + fileSize + ", travelReviewIdx="
				+ travelReviewIdx + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
}
