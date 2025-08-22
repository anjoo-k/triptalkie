package com.walkietalkie.triptalkie.domain;

public class Memberlist {

	private int makemate_idx;
	private String member_id;

	public Memberlist() {
		super();
	}

	public Memberlist(int makemate_idx, String member_id) {
		super();
		this.makemate_idx = makemate_idx;
		this.member_id = member_id;
	}

	public int getMakemate_idx() {
		return makemate_idx;
	}

	public void setMakemate_idx(int makemate_idx) {
		this.makemate_idx = makemate_idx;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	@Override
	public String toString() {
		return "Memberlist [makemate_idx=" + makemate_idx + ", member_id=" + member_id + "]";
	}

}
