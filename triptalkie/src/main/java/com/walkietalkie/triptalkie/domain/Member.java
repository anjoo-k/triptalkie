package com.walkietalkie.triptalkie.domain;

import java.time.LocalDate;

public class Member {
	private String id;
	private String nickname;
	private String password;
	private String name;
	private LocalDate birth;
	private String gender;
	private String phonenumber;
	private String email;
	private String address;
	private String travelConcept;
	private double credit;
	private int active;

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getTravelConcept() {
		return travelConcept;
	}

	public void setTravelConcept(String travelConcept) {
		this.travelConcept = travelConcept;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public boolean isActive() {
		return active == 1;
	}

	public void setActive(int active) {
        this.active = active;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", nickname=" + nickname + ", password=" + password + ", name=" + name + ", birth="
				+ birth + ", gender=" + gender + ", phonenumber=" + phonenumber + ", email=" + email + ", address="
				+ address + ", travelConcept=" + travelConcept + ", credit=" + credit + ", active=" + active + "]";
	}

}
