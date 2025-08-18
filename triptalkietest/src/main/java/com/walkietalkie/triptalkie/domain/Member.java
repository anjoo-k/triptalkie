package com.walkietalkie.triptalkie.domain;

import java.time.LocalDateTime;

public class Member {
  private String id;
  private String nickname;
  private String password;
  private String name;
  private LocalDateTime birth;
  private String phonenumber;
  private String email;
  private String address;
  private String travel_concept;
  private double credit;
  
  // 생성자
  public Member() {
    super();
    // TODO Auto-generated constructor stub
  }
  public Member(String id, String nickname, String password, String name, LocalDateTime birth, String phonenumber,
      String email, String address, String travel_concept, double credit) {
    super();
    this.id = id;
    this.nickname = nickname;
    this.password = password;
    this.name = name;
    this.birth = birth;
    this.phonenumber = phonenumber;
    this.email = email;
    this.address = address;
    this.travel_concept = travel_concept;
    this.credit = credit;
  }
  
  // getter setter 메서드
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getNickname() {
    return nickname;
  }
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public LocalDateTime getBirth() {
    return birth;
  }
  public void setBirth(LocalDateTime birth) {
    this.birth = birth;
  }
  public String getPhonenumber() {
    return phonenumber;
  }
  public void setPhonenumber(String phonenumber) {
    this.phonenumber = phonenumber;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getTravel_concept() {
    return travel_concept;
  }
  public void setTravel_concept(String travel_concept) {
    this.travel_concept = travel_concept;
  }
  public double getCredit() {
    return credit;
  }
  public void setCredit(double credit) {
    this.credit = credit;
  }
  // toString 메서드
  @Override
  public String toString() {
    return "Member [id=" + id + ", nickname=" + nickname + ", password=" + password + ", name=" + name + ", birth="
        + birth + ", phonenumber=" + phonenumber + ", email=" + email + ", address=" + address + ", travel_concept="
        + travel_concept + ", credit=" + credit + "]";
  }
  
  
}
