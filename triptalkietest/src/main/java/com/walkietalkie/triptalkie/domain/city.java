package com.walkietalkie.triptalkie.domain;

public class city {
  private String id;
  private String name;
  private String country_id;
  // 생성자
  public city() {
    super();
    // TODO Auto-generated constructor stub
  }
  public city(String id, String name, String country_id) {
    super();
    this.id = id;
    this.name = name;
    this.country_id = country_id;
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
  public String getCountry_id() {
    return country_id;
  }
  public void setCountry_id(String country_id) {
    this.country_id = country_id;
  }
  // toString
  @Override
  public String toString() {
    return "city [id=" + id + ", name=" + name + ", country_id=" + country_id + "]";
  }
  
}
