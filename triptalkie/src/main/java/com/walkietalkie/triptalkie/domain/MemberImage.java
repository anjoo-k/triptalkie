package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class MemberImage {

	private Long idx;
	private String uuid;
	// 원본 파일명이 중복됬을 때를 위한 파일 고유 식별자
	private String originalName;
	// 업로드 시점의 원본 파일명
	private String savedName;
	// 서버에 저장된 파일명
	private String filePath;
	// 서버 저장 경로
	private Long fileSize;
	// 파일 크기
	private String memberId;
	// 회원 ID (FK)
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	// 생성자
	public MemberImage() {
		super();
	}

	// Getter / Setter 메서드
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

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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
		return "MemberImage [idx=" + idx + ", uuid=" + uuid + ", originalName=" + originalName + ", savedName="
				+ savedName + ", filePath=" + filePath + ", fileSize=" + fileSize + ", memberId=" + memberId
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
