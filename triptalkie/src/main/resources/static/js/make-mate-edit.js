/* 여행 일수 계산 */
document.addEventListener("DOMContentLoaded", () => {
	const startdate = document.querySelectorAll(".startdate");
	const enddate = document.querySelectorAll(".enddate");
	const days = document.querySelectorAll(".days");
	
	startdate.forEach((startEl, index) => {
		const startStr = startEl.innerText.trim();
		const endStr = enddate[index].innerText.trim();
		
		const [yearStart, monthStart, dayStart] = startStr.split("-").map(Number);
		const [yearEnd, monthEnd, dayEnd] = endStr.split("-").map(Number);
		
		const startDate = new Date(yearStart, monthStart - 1, dayStart);
		const endDate = new Date(yearEnd, monthEnd - 1, dayEnd);
		
		// (1000 * 60 * 60 * 24) : 자바스크립트에서 Date 객체끼리 뺄셈을 하면 → 두 날짜의 밀리초 차이(ms) 를 반환
		// +1 : 시작일과 종료일을 모두 포함해서 계산
		const diffDate = (endDate - startDate) / (1000 * 60 * 60 * 24) + 1;
		days[index].innerText = `(${diffDate}일)`
	})
})


