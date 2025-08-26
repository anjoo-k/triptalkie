package com.walkietalkie.triptalkie.domain;

import java.util.List;

public class Memberlist {

	private long makemateIdx;
	private String memberId;
	private List<MemberImage> memberImage;

	public Memberlist() {
		super();
	}

	public Memberlist(long makemateIdx, String memberId, List<MemberImage> memberImage) {
		super();
		this.makemateIdx = makemateIdx;
		this.memberId = memberId;
		this.memberImage = memberImage;
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

	public List<MemberImage> getMemberImage() {
		return memberImage;
	}

	public void setMemberImage(List<MemberImage> memberImage) {
		this.memberImage = memberImage;
	}

	@Override
	public String toString() {
		return "Memberlist [makemateIdx=" + makemateIdx + ", memberId=" + memberId + ", memberImage=" + memberImage
				+ "]";
	}

}
