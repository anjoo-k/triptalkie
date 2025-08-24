document.addEventListener('DOMContentLoaded', () => {
	// 조회수 증가
	document.querySelectorAll(".title").forEach(countView => {
		countView.addEventListener("click", async (e) => {
			e.preventDefault();
			const idx = countView.dataset.id;
			console.log("idx : " + idx);
			try {
				const response = await fetch(`/travel-review/increment-view/${idx}`, {
					method: "POST"
				});

				if (response.ok) {
					window.location.href = `/travel-review/detail-review/${idx}`;
				} else {
					console.log("조회수 증가 실패");
				}

			} catch (error) {
				console.error(error);
			}
		});
	});

	// 글쓰기 버튼
	const writeBtn = document.querySelector(".btn-write");
	if (writeBtn) {
		writeBtn.addEventListener("click", async () => {
			const response = await fetch("");
			const isLoggedIn = await response.json();

			if (!isLoggedIn) {
				alert("로그인 후 이용이 가능합니다.");
				location.href = "/member/login";
			} else {
				location.href = "/travel-review/registerReviewPage";
			}
		});
	}

	// 파일 첨부 시 사진 한 장 미리 보여주기
	const photoInput = document.getElementById("photo");
	const preview = document.getElementById("preview");

	if (photoInput) {

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

	}

	// 뒤로 가기 버튼
	const backBtn = document.querySelector(".btn-back");
	if (backBtn) {
		backBtn.addEventListener("click", function() {
			location.href = "/travel-review/findTravelreviewAllList";
		});
	}

	// ---- 수정 페이지 이동 ----
	const editBtn = document.getElementById('btn-edit');
	if (editBtn) {
		editBtn.addEventListener('click', () => {
			const idx = editBtn.getAttribute('data-idx');
			if (idx) {
				window.location.href = `/travel-review/edit-review/${idx}`;
			}
		});
	}

	// ---- 삭제 버튼 ----
	const deleteBtn = document.getElementById('btn-delete');
	const confirmDeleteBtn = document.querySelector('.btn-confirm-delete');
	let idxToDelete = null;

	if (deleteBtn) {
		deleteBtn.addEventListener('click', () => {
			idxToDelete = deleteBtn.getAttribute('data-idx');
			if (idxToDelete) {
				$('#myModal').modal('show'); // Bootstrap 모달 열기
			}
		});
	}

	if (confirmDeleteBtn) {
		confirmDeleteBtn.addEventListener('click', async () => {
			let idxInput = document.querySelector('#idx');
			if (!idxInput) return;
			let idx = idxInput.value;
			try {
				const response = await fetch(`/travel-review/deleteTravelreviewByIdx?idx=${idx}`, {
					method: 'POST',
				});
				const data = await response.json();
				
				if (data.success) {
					alert(data.message);
					window.location.href = '/travel-review/findTravelreviewAllList';
				} else {
					alert(data.message);
				}
			} catch (e) {
				console.error(e);
				alert('삭제 중 오류 발생');
			}
		});
	}

	// 검색 기능
	const countrySelect = document.getElementById('countrySelect');
	if (countrySelect) {
		countrySelect.addEventListener('change', function() {
			const countryId = this.value;
			console.log("selected country countryId : " + countryId);
			const citySelect = document.getElementById('citySelect');

			fetch(`/cities/findCitiesByCountry?countryId=${countryId}`)
				.then(res => {
					if (!res.ok) {
						throw new Error("서버 오류: " + res.status);
					}
					return res.json();
				})
				.then(cities => {
					citySelect.innerHTML = '<option value="">도시 선택</option>';
					cities.forEach(city => {
						const option = document.createElement('option');

						// 서버 JSON 필드명이 cityId인지 확인!
						option.value = city.cityId || city.id || '';
						option.textContent = city.name;

						citySelect.appendChild(option);
					});
				})
				.catch(err => {
					console.error("도시 목록 불러오기 실패:", err);
					citySelect.innerHTML = '<option value="">도시를 불러올 수 없습니다</option>';
				});
		});
	}
});