package com.walkietalkie.triptalkie.DTO;

public class PageDTO {
    private int currentPage;   // 현재 페이지
    private int totalPage;     // 전체 페이지 수
    private int startPage;     // 시작 페이지 번호
    private int endPage;       // 끝 페이지 번호
    private boolean prev;      // 이전 버튼 활성화 여부
    private boolean next;      // 다음 버튼 활성화 여부

    // 생성자
    public PageDTO(int currentPage, int totalCount, int size) {
        this.currentPage = currentPage;

        // 전체 페이지 수
        this.totalPage = (int) Math.ceil((double) totalCount / size);

        // 페이지 블럭 범위 (예: 1~5, 6~10)
        int pageBlock = 5; // 한번에 보여줄 페이지 번호 개수
        this.startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;
        this.endPage = Math.min(startPage + pageBlock - 1, totalPage);

        // 이전/다음 버튼 여부
        this.prev = startPage > 1;
        this.next = endPage < totalPage;
    }

    // getter
    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public boolean isPrev() {
        return prev;
    }

    public boolean isNext() {
        return next;
    }
}
