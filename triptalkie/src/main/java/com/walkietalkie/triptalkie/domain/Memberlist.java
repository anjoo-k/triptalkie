package com.walkietalkie.triptalkie.domain;

public class Memberlist {

	private long makemateIdx;
	private String memberId;

	public Memberlist() {
		super();
	}

	public Memberlist(long makemateIdx, String memberId) {
		super();
		this.makemateIdx = makemateIdx;
		this.memberId = memberId;
	}

	public long getMakemateIdx() {
		return makemateIdx;
	}

	public void setMakemateIdx(long makemateIdx) {
		this.makemateIdx = makemateIdx;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Memberlist [makemateIdx=" + makemateIdx + ", memberId=" + memberId + "]";
	}

}
