package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class MakemateImage {

	private Long idx;
	private String uuid;
	private String originalName;
	private String savedName;
	private String filePath;
	private Long fileSize;
	private Long makemateIdx;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public MakemateImage() {
		super();
	}
	public MakemateImage(Long idx, String uuid, String originalName, String savedName, String filePath, Long fileSize,
			Long makemateIdx, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.idx = idx;
		this.uuid = uuid;
		this.originalName = originalName;
		this.savedName = savedName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.makemateIdx = makemateIdx;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	public MakemateImage(String uuid, String originalName, String savedName, String filePath, Long fileSize,
			Long makemateIdx) {
		super();
		this.uuid = uuid;
		this.originalName = originalName;
		this.savedName = savedName;
		this.filePath = filePath;
		this.fileSize = fileSize;
		this.makemateIdx = makemateIdx;
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
	public Long getMakemateIdx() {
		return makemateIdx;
	}
	public void setMakemateIdx(Long makemateIdx) {
		this.makemateIdx = makemateIdx;
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
		return "MakemateImage [idx=" + idx + ", uuid=" + uuid + ", originalName=" + originalName + ", savedName="
				+ savedName + ", filePath=" + filePath + ", fileSize=" + fileSize + ", makemateIdx=" + makemateIdx
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

	

}
