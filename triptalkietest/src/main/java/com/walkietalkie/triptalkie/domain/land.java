package com.walkietalkie.triptalkie.domain;

public class land {
  private String id;
  private String name;
  // 생성자
  public land() {
    super();
    // TODO Auto-generated constructor stub
  }
  public land(String id, String name) {
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
    return "land [id=" + id + ", name=" + name + "]";
  }
  
  

}
