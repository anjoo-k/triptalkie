package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class Community {
  private long idx;
  private String title;
  LocalDateTime created_at;
  LocalDateTime updated_at;
  String content;
  String member_id;
  // 생성자
  public Community() {
    super();
    // TODO Auto-generated constructor stub
  }
  public Community(long idx, String title, LocalDateTime created_at, LocalDateTime updated_at, String content,
      String member_id) {
    super();
    this.idx = idx;
    this.title = title;
    this.created_at = created_at;
    this.updated_at = updated_at;
    this.content = content;
    this.member_id = member_id;
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
  public LocalDateTime getCreated_at() {
    return created_at;
  }
  public void setCreated_at(LocalDateTime created_at) {
    this.created_at = created_at;
  }
  public LocalDateTime getUpdated_at() {
    return updated_at;
  }
  public void setUpdated_at(LocalDateTime updated_at) {
    this.updated_at = updated_at;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getMember_id() {
    return member_id;
  }
  public void setMember_id(String member_id) {
    this.member_id = member_id;
  }
  // toString
  @Override
  public String toString() {
    return "Community [idx=" + idx + ", title=" + title + ", created_at=" + created_at + ", updated_at=" + updated_at
        + ", content=" + content + ", member_id=" + member_id + "]";
  }
}