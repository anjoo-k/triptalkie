document.addEventListener('DOMContentLoaded', () => {
    const btn = document.getElementById("bookmarkBtn");

    if (btn) {
        btn.addEventListener("click", function () {
            const memberId = this.getAttribute("data-member-id");
            const makemateIdx = this.getAttribute("data-makemate-idx");

            fetch("/bookmark/toggle", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: `memberId=${memberId}&makemateIdx=${makemateIdx}`
            })
            .then(response => response.text())
            .then(result => {
                if (result === "true") {
                    // 북마크 추가됨 → 아이콘 변경
                    btn.src = "/images/bookmark-on.png";
                } else {
                    // 북마크 해제됨 → 원래 아이콘으로
                    btn.src = "/images/bookmark-off.png";
                }
            })
            .catch(error => {
                console.error("북마크 토글 실패:", error);
            });
        });
    }
});

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
