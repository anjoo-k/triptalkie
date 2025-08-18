package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class Travelreview {
  private long idx;
  private String title;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;
  private boolean mateuse;
  private String concepttype;
  private String matetype;
  private String content;
  private String city_id;
  private String member_id;
  // 생성자
  public Travelreview() {
    super();
    // TODO Auto-generated constructor stub
  }
  public Travelreview(long idx, String title, LocalDateTime created_at, LocalDateTime updated_at, boolean mateuse,
      String concepttype, String matetype, String content, String city_id, String member_id) {
    super();
    this.idx = idx;
    this.title = title;
    this.created_at = created_at;
    this.updated_at = updated_at;
    this.mateuse = mateuse;
    this.concepttype = concepttype;
    this.matetype = matetype;
    this.content = content;
    this.city_id = city_id;
    this.member_id = member_id;
  }
  //getter setter 메서드
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
  public boolean isMateuse() {
    return mateuse;
  }
  public void setMateuse(boolean mateuse) {
    this.mateuse = mateuse;
  }
  public String getConcepttype() {
    return concepttype;
  }
  public void setConcepttype(String concepttype) {
    this.concepttype = concepttype;
  }
  public String getMatetype() {
    return matetype;
  }
  public void setMatetype(String matetype) {
    this.matetype = matetype;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
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
    return "Travelreview [idx=" + idx + ", title=" + title + ", created_at=" + created_at + ", updated_at=" + updated_at
        + ", mateuse=" + mateuse + ", concepttype=" + concepttype + ", matetype=" + matetype + ", content=" + content
        + ", city_id=" + city_id + ", member_id=" + member_id + "]";
  }
  
}
