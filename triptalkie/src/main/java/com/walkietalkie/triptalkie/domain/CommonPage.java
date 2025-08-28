package com.walkietalkie.triptalkie.domain;

import java.util.List;

public class CommonPage<T> {
	private List<T> content;
	private int size;
	// 한 페이지에 보여줄 아이템 수
	private int currentPage;
	// 현재 페이지
	private int totalPage;
	// 총 페이지 수
	private int startPage;
	// 페이지 블록 시작
	private int endPage;
	// 페이지 플록 종료
	
	public CommonPage() {
		super();
	}

	public CommonPage(List<T> content, int size, int currentPage, int totalPage, int startPage, int endPage) {
		super();
		this.content = content;
		this.size = size;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

}
