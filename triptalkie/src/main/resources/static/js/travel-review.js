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
	const backBtn = document.querySelector(".back-to-list");
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

	// 댓글 기능
	const commentBtn = document.getElementById('comment-write-btn');
	if (commentBtn) {
		commentBtn.addEventListener("click", async () => {
			// 세션 유무 체크
			//let loginMemberId = /*[['${memberId}']]*/ null;
			alert('로그인 한 사람 : ' + loginMemberId);
			if (loginMemberId == null) {
				$('#login-required-modal').modal('show');
				return;
			}

			const travelReviewId = commentBtn.getAttribute('data-idx');
			const loginMember = document.getElementById('memberId').value;
			const commentContents = document.getElementById('comment-content').value;


			if (!commentContents.trim()) {
				alert("댓글 내용을 입력하세요.");
				return;
			}

			try {
				const response = await fetch(`/travelReviewComment/register`, {
					method: 'POST',
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify({
						travelReviewIdx: travelReviewId,
						memberId: loginMember,
						content: commentContents
					})
				});

				const result = await response.json();

				if (response.ok) {
					showComment(result.comment);
					document.getElementById('comment-content').value = '';	// 입력창을 비워줌
				} else {
					console.error('댓글 작성 실패', result.message);
				}

			} catch (e) {
				console.error(e);
				console.error('댓글 등록 중 오류 발생', e);
			}
		});
	}

	// 댓글 처리
	function showComment(comment) {
		const commentList = document.getElementById('comment-list');
		if (!commentList) return;

		// createdAt 포맷 YYYY-MM-DD
		let dateText = '';
		if (comment.createdAt) {
			const d = new Date(comment.createdAt);
			const year = d.getFullYear();
			const month = String(d.getMonth() + 1).padStart(2, '0');
			const day = String(d.getDate()).padStart(2, '0');
			dateText = `${year}-${month}-${day}`;
		}

		const div = document.createElement('div');
		div.className = 'row mb-3';
		div.innerHTML = `
			        <div class="col-sm-1">
			            <img src="/images/original-profile.png" class="rounded-circle" style="width:40px;height:40px;">
			        </div>
			        <div class="col-sm-11">
			            <div class="row mb-1">
			                <div class="col-sm-9 d-flex align-items-center">
			                    <span class="nickname subheading">${comment.nickName}</span>
			                </div>
			                <div class="col-sm-3 text-end">
			                    <a href="#" class="text-muted me-1 update-reply btn btn-edit" data-id="${comment.idx}" data-member-id="${comment.memberId}">수정</a> 
			                    <a href="#" class="text-muted delete-reply btn btn-delete" data-id="${comment.idx}" data-member-id="${comment.memberId}">삭제</a>
			                </div>
			            </div>
			            <div class="row mb-1">
			                <div class="col-sm-12 text-end">
			                    <span class="text-muted comment-date">${dateText}</span>
			                </div>
			            </div>
			            <div class="row">
			                <div class="col-sm-12 comment-padding">
			                    <span class="reply-content content-text">${comment.content}</span>
			                </div>
			            </div>
			        </div>
			    `;
		commentList.prepend(div);
	}

	// 수정 
	let idxToUpdateReply = null;
	document.querySelectorAll('.update-reply').forEach(updateReplyBtn => {
		updateReplyBtn.addEventListener("click", async function() {
			alert('댓글 수정 버튼 클릭!');
			idxToUpdateReply = this.getAttribute('data-id');

			try {
				const response = await fetch(`/travelReviewComment/findByIdx?idx=${idxToUpdateReply}`, { method: 'GET' });
				const data = await response.json();
				if (data.success) {
					updateComment(this, data.comment, idxToUpdateReply);	// 조회한 데이터 값을 파라미터 값으로 넣어준다.
				}
			} catch (e) {
				console.error(e);
				alert('댓글 수정 중 오류 발생');
			}
		});
	});

	// 수정 버튼 생성
	function updateComment(updateReplyBtn, comment, idxToUpdateReply) {
		const commentRow = updateReplyBtn.closest('.row.mb-1');
		const commentDiv = commentRow.parentElement.querySelector('.reply-content-box');
		if (!commentDiv) return;

		if (commentDiv.querySelector('.edit-comment')) return; // 이미 편집 중이면 종료

		const replyContent = commentDiv.querySelector('.reply-content');
		if (!replyContent) return;

		replyContent.style.display = 'none';
		// 수정/삭제 버튼 숨기기
		const btnGroup = commentRow.querySelector('.col-sm-3');
		if (btnGroup) btnGroup.style.display = 'none';

		const input = document.createElement('input');
		input.type = 'text';
		input.value = comment.content;
		input.id = "edit-comment";
		input.classList.add('edit-comment');

		const saveBtn = document.createElement('button');
		saveBtn.textContent = '저장';
		saveBtn.classList.add('save-btn');

		const cancelBtn = document.createElement('button');
		cancelBtn.textContent = '취소';
		cancelBtn.classList.add('cancel-btn');

		commentDiv.appendChild(input);
		commentDiv.appendChild(saveBtn);
		commentDiv.appendChild(cancelBtn);
		
		// 저장 이벤트
		saveBtn.addEventListener('click', async () => {
			alert('저장 버튼 클릭!');
			const newComment = input.value.trim();
			if (!newComment) return alert('댓글 내용을 입력하세요.');

			alert('idxToUpdateReply : ' + idxToUpdateReply);
			alert('newComment : ' + newComment);

			try {
				const response = await fetch(`/travelReviewComment/update?idx=${idxToUpdateReply}`, {
					method: 'POST',
					headers: { 'Content-Type': 'application/json' },
					body: JSON.stringify({ idx: idxToUpdateReply, content: newComment })
				});
				const result = await response.json();
				if (response.ok) {
					replyContent.textContent = result.comment.content;
					cleanup();
				} else {
					alert(result.message);
				}
			} catch (e) {
				console.error(e);
				alert('댓글 수정 중 오류 발생');
			}
		});

		// 취소 이벤트
		cancelBtn.addEventListener('click', cleanup);

		function cleanup() {
			replyContent.style.display = '';
			input.remove();
			saveBtn.remove();
			cancelBtn.remove();
			if (btnGroup) btnGroup.style.display = '';
		}

	}

	// 삭제
	const commentDeleteModal = document.getElementById('commentDeleteModal');
	const confirmCommentDeleteBtn = document.getElementById('confirm-comment-delete');

	let idxToDeleteReply = null;

	// 댓글 삭제 버튼 클릭
	document.querySelectorAll('.delete-reply').forEach(btn => {
		btn.addEventListener('click', function() {
			idxToDeleteReply = this.getAttribute('data-id'); // 클릭한 댓글 ID
			if (idxToDeleteReply) {
				// Bootstrap 4 모달 열기
				$(commentDeleteModal).modal('show');
			}
		});
	});

	// 모달에서 '예' 버튼 클릭
	if (confirmCommentDeleteBtn) {
		confirmCommentDeleteBtn.addEventListener('click', async function() {
			if (!idxToDeleteReply) return;

			try {
				const response = await fetch(`/travelReviewComment/delete?idx=${idxToDeleteReply}`, {
					method: 'POST',
				});
				const data = await response.json();

				if (data.success) {
					alert(data.message);
					location.reload(); // 삭제 후 새로고침
				} else {
					alert(data.message);
				}
			} catch (e) {
				console.error(e);
				alert('댓글 삭제 중 오류 발생');
			}
		});
	}
});