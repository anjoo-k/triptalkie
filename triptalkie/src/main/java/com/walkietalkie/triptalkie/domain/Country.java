package com.walkietalkie.triptalkie.domain;

public class Country {
	private String id;
	private String name;
	private String landId;
	private Land land;

	// 생성자
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Country(String id, String name, String landId) {
		super();
		this.id = id;
		this.name = name;
		this.landId = landId;
	}

	public Country(String id, String name, String landId, Land land) {
		super();
		this.id = id;
		this.name = name;
		this.landId = landId;
		this.land = land;
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

	public String getLandId() {
		return landId;
	}

	public void setLandId(String landId) {
		this.landId = landId;
	}

	public Land getLand() {
		return land;
	}

	public void setLand(Land land) {
		this.land = land;
		this.landId = (land != null) ? land.getId() : null;
	}

	// toString
	@Override
	public String toString() {
		return "Country [id=" + id + ", name=" + name + ", landId=" + landId + ", land=" + land + "]";
	}

}
