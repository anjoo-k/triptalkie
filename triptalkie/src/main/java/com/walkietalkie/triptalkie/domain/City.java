package com.walkietalkie.triptalkie.domain;

public class City {
	private String id;
	private String name;
	private String countryId;
	private Country country;

	// 생성자
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public City(String id, String name, String countryId) {
		super();
		this.id = id;
		this.name = name;
		this.countryId = countryId;
	}

	public City(String id, String name, String countryId, Country country) {
		super();
		this.id = id;
		this.name = name;
		this.countryId = countryId;
		this.country = country;
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

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
		this.countryId = (country != null) ? country.getId() : null;
	}

	// toString
	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", countryId=" + countryId + ", country=" + country + "]";
	}
}
