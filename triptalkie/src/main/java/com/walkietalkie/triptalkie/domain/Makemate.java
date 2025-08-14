package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class Makemate {
  private long idx;
  private String title;
  private String content;
  private String state;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;
  private LocalDateTime startdate;
  private LocalDateTime enddate;
  private String city_id;
  private String member_id;
  // 생성자
  public Makemate() {
    super();
    // TODO Auto-generated constructor stub
  }
  public Makemate(long idx, String title, String content, String state, LocalDateTime created_at,
      LocalDateTime updated_at, LocalDateTime startdate, LocalDateTime enddate, String city_id, String member_id) {
    super();
    this.idx = idx;
    this.title = title;
    this.content = content;
    this.state = state;
    this.created_at = created_at;
    this.updated_at = updated_at;
    this.startdate = startdate;
    this.enddate = enddate;
    this.city_id = city_id;
    this.member_id = member_id;
  }
  // getter setter 메서드
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
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
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
  public String getCity_id() {
    return city_id;
  }
  public void setCity_id(String city_id) {
    this.city_id = city_id;
  }
  public String getMember_id() {
    return member_id;
  }
  public void setMember_id(String member_id) {
    this.member_id = member_id;
  }
  // toString 메서드
  @Override
  public String toString() {
    return "Makemate [idx=" + idx + ", title=" + title + ", content=" + content + ", state=" + state + ", created_at="
        + created_at + ", updated_at=" + updated_at + ", startdate=" + startdate + ", enddate=" + enddate + ", city_id="
        + city_id + ", member_id=" + member_id + "]";
  }
  
}
