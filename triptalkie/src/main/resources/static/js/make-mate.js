document.addEventListener('DOMContentLoaded', () => {
	/* 작성하기 */
	// 파일 첨부 시 사진 한 장 미리 보여주기
	const photoInput = document.getElementById("photo");
	const preview = document.getElementById("preview");

	photoInput.addEventListener("change", (e) => {
		const file = e.target.files[0];
		if (file) {
			const reader = new FileReader();
			reader.onload = function(e) {
				preview.src = e.target.result;
				preview.style.display = "inline-block"; // 이미지 보이게
			}
			reader.readAsDataURL(file);
		}
	});

	/* 하단 버튼 섹션 */
	// 목록 버튼
	const backBtn = document.querySelector(".btn-back");
	if (backBtn) {
		backBtn.addEventListener("click", function() {
			location.href = "/make-mate/list";
		});
	}

	// 완료 버튼
	const complete = document.querySelector(".btn-complete");
	if(complete) {
		complete.addEventListener("click", async () => {
			alert("완료 버튼 활성화!");
		});
	}
		
});