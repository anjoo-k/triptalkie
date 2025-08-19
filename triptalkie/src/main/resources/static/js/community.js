document.addEventListener('DOMContentLoaded', () => {
	// 글쓰기 버튼
	const writeBtn = document.querySelector(".btn-write");
	if (writeBtn) {
		writeBtn.addEventListener("click", function() {
			location.href = "/write-review";
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
				window.location.href = `/community/edit-review/${idx}`;
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
					method: 'GET',
				});
				if (response.ok) {
					window.location.href = '/travel-review/findTravelreviewAllList';
				} else {
					alert('삭제 실패');
				}
			} catch (e) {
				console.error(e);
				alert('삭제 중 오류 발생');
			}
		});
	}
	
	// 검색 기능
	document.getElementById('countrySelect').addEventListener('change', function() {
	    const countryName = this.value;
	    const citySelect = document.getElementById('citySelect');

	    fetch(`/cities/citiesAllList?countryName=${countryName}`)
	        .then(res => res.json())
	        .then(cities => {
	            citySelect.innerHTML = '<option value="">도시 선택</option>';
	            cities.forEach(city => {
	                const option = document.createElement('option');
	                option.value = city.id;
	                option.text = city.name;
	                citySelect.appendChild(option);
	            });
	        });
	});
});