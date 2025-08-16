package com.walkietalkie.triptalkie.domain;

public class Land {
  private String id;
  private String name;
  // 생성자
  public Land() {
    super();
    // TODO Auto-generated constructor stub
  }
  public Land(String id, String name) {
    super();
    this.id = id;
    this.name = name;
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
  // toString
  @Override
  public String toString() {
    return "Land [id=" + id + ", name=" + name + "]";
  }
  
  

}
