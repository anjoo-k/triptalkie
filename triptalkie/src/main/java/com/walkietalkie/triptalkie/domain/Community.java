package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class Community {
  private long idx;
  private String title;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  String content;
  String memberId;
  // 생성자
  public Community() {
    super();
    // TODO Auto-generated constructor stub
  }
  public Community(long idx, String title, LocalDateTime createdAt, LocalDateTime updatedAt, String content,
      String memberId) {
    super();
    this.idx = idx;
    this.title = title;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.content = content;
    this.memberId = memberId;
  }
  // getter, setter
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
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }
  public void setCreated_at(LocalDateTime createdAt) {
    this.createdAt = createdAt; 	
  }
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }
  public void setUpdated_at(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
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
  public void setMember_id(String memberId) {
    this.memberId = memberId;
  }
  // toString
  @Override
  public String toString() {
    return "Community [idx=" + idx + ", title=" + title + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
        + ", content=" + content + ", memberId=" + memberId + "]";
  }

}