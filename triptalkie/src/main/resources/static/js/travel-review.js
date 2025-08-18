/**
 * 여행 후기 게시판 JavaScript
 */

document.querySelector(".btn-write").addEventListener("click", function() {
    location.href = "/write-review";
});

const table = document.getElementById('reviewTable').getElementsByTagName('tbody')[0];
const totalRows = 5; // 항상 보여주고 싶은 줄 수
const currentRows = table.rows.length;

for (let i = currentRows; i < totalRows; i++) {
	const newRow = table.insertRow();
	for (let j = 0; j < 5; j++) {  // 테이블 열 개수
		const newCell = newRow.insertCell();
		newCell.innerHTML = '&nbsp;'; // 공백으로 높이 유지
	}
}



/*
 * 	여행 후기 게시판 글 작성
 */

// 첨부파일
$(".custom-file-input").on("change", function() {
  var fileName = $(this).val().split("\\").pop();
  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});

// 목록
const backToList = document.querySelector('.btn-back');
if(backToList){
	backToList.addEventListener('click', () => {
		alert('목록 버튼 클릭!');
	});
	
}

// 완료
const completedWriting = document.querySelector('.btn-completed-writing');
if(completedWriting){
	completedWriting.addEventListener('click', () => {
		alert('완료 클릭!');
	});
	
}
