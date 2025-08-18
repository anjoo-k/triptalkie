package com.walkietalkie.triptalkie.domain;

public class country {
  private String id;
  private String name;
  private String land_id;
  // 생성자
  public country() {
    super();
    // TODO Auto-generated constructor stub
  }
  public country(String id, String name, String land_id) {
    super();
    this.id = id;
    this.name = name;
    this.land_id = land_id;
  }
  // getter setter
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getLand_id() {
    return land_id;
  }
  public void setLand_id(String land_id) {
    this.land_id = land_id;
  }
  // toString
  @Override
  public String toString() {
    return "country [id=" + id + ", name=" + name + ", land_id=" + land_id + "]";
  }
  
}
