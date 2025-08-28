/**
 * 	member 관련 javaScript
 * 	회원가입, 로그인
 */

document.addEventListener('DOMContentLoaded', () => {
	// ===== signup.html =====
	async function checkDuplicate(targetId, labelName) {
		const input = document.getElementById(targetId);
		const resultDiv = document.getElementById(`${targetId}CheckMessage`);

		if (!input.value.trim()) {
			alert(`${labelName} 을(를) 입력하세요`);
			return;
		}

		let url = "";
		if (targetId === "id")
			url = `/member/checkMemberById?id=${input.value}`;
		else if (targetId === "phonenumber")
			url = `/member/checkMemberByPhonenumber?phonenumber=${input.value}`;
		else if (targetId === "email")
			url = `/member/checkMemberByEmail?email=${input.value}`;

		try {
			const response = await fetch(url);
			if (!response.ok) {
				throw new Error(`HTTP error! status: ${response.status}`);
			}

			const result = await response.text();
			if (result == 0) {
				resultDiv.innerHTML = `<p style="margin-top:5px; color:#9a9a9a; margin-bottom: 0">사용 가능한 ${labelName}입니다.</p>`;
			} else {
				resultDiv.innerHTML = `<p style="margin-top:5px; color:red; margin-bottom: 0">사용할 수 없는 ${labelName}입니다.</p>`;
			}

		} catch (error) {
			resultDiv.innerHTML = `<p style="margin-top:5px; color:red; margin-bottom: 0">서버 오류 발생</p>`;
			console.error(error);
		}
	}

	document.querySelectorAll('.btn-duplicate').forEach(btn => {
		btn.addEventListener('click', () => {
			checkDuplicate(btn.dataset.target, btn.dataset.label);
		});
	});

	const chkNickname = document.getElementById('nickname');
	if (chkNickname) {
		chkNickname.addEventListener("blur", async (event) => {
			let nickname = event.target.value;

			if (!nickname) {
				document.getElementById('nicknameCheckMessage').innerHTML = "";
				return;
			}

			try {
				const response = await fetch(`/member/checkMemberByNickname?nickname=${nickname}`);
				if (!response.ok) {
					throw new Error(`HTTP error status : ${response.status}`);
				}
				const checkMemberByNickname = await response.text();
				const resultDiv = document.getElementById('nicknameCheckMessage');
				if (checkMemberByNickname == 0) {
					resultDiv.innerHTML = `<p style="margin-top:5px; color:#9a9a9a; margin-bottom: 0">사용 가능한 닉네임입니다.</p>`;
				} else {
					resultDiv.innerHTML = `<p style="margin-top:5px; color:red; margin-bottom: 0">이미 사용중인 닉네임입니다.</p>`;
				}

			} catch (error) {
				console.error("Fetch Error : ", error);
				resultDiv.innerHTML = `문제 발생`;
			}

		});
	}

	// ===== 비밀번호 일치 확인 =====
	const password = document.getElementById('password');
	const confirmPassword = document.getElementById('confirmPassword');
	const confirmMessage = document.getElementById('confirmMessage');

	function showMessage(message) {
		if (confirmMessage) confirmMessage.innerHTML = message;
	}

	function confirmPasswordCheck() {
		if (!password || !confirmPassword || !confirmMessage) return;

		if (confirmPassword.value === "") {
			confirmMessage.textContent = "";
			return;
		}

		if (password.value === confirmPassword.value) {
			showMessage('<p style="margin-top:5px; color:green;">비밀번호가 일치합니다.</p>');
		} else {
			showMessage('<p style="margin-top:5px; color:red;">비밀번호가 일치하지 않습니다.</p>');
		}
	}

	if (password && confirmPassword) {
		password.addEventListener('input', confirmPasswordCheck);
		confirmPassword.addEventListener('input', confirmPasswordCheck);
	}

	// ===== 회원 가입 버튼 이벤트 =====
	const signUpBtn = document.querySelector('.btn-signup');
	if (signUpBtn) {
		signUpBtn.addEventListener('click', () => {
			location.href = '/signup';
		});
	}

	const findIdOrPwdBtn = document.querySelector('.btn-findIdOrPwd');
	if (findIdOrPwdBtn) {
		findIdOrPwdBtn.addEventListener('click', () => {
			alert('아이디/비밀번호 찾기 클릭!');
		});
	}

	const fileInput = document.getElementById('profileImage');
	const previewImage = document.querySelector('.original--profile');
	if (fileInput) {
		fileInput.addEventListener('change', function() {
			const file = this.files[0];
			if (file) {
				const reader = new FileReader();
				reader.onload = function(e) {
					previewImage.src = e.target.result;
				};
				reader.readAsDataURL(file);
			} else {
				previewImage.src = '/images/original-profile.png';
			}
		});
	}

	// ===== 아이디 찾기 버튼 이벤트 =====
	const findIdBtn = document.querySelector('.id-btn');
	if (findIdBtn) {
		findIdBtn.addEventListener("click", async (e) => {
			e.preventDefault();
			const email = document.getElementById('email').value;
			try {
				const response = await fetch(`/member/findIdByEmail?email=${email}`, {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json'
					},
					body: JSON.stringify({ email: email })
				});
				const data = await response.json();

				// Bootstrap 모달 가져오기
				const modal = $('#find-id-modal'); // jQuery 선택
				const modalBody = modal.find('.modal-body');
				const modalFooter = modal.find('.modal-footer');

				if (data.success) {
					modalBody.html(`<span>회원님의 아이디는 <strong>${data.memberId}</strong> 입니다.</span>
						<br><span>로그인 해주세요</span>`);
					modalFooter.html(`
				                   <a href="/member/loginPage" class="btn btn-primary btn--radius mx-2">로그인</a>
				                   <button type="button" class="btn btn-secondary btn--radius mx-2" data-dismiss="modal">닫기</button>
				               `);
				} else {
					modalBody.text(`해당 이메일로 가입된 아이디가 없습니다.`);
					modalFooter.html(`
				                   <a href="/member/registerPage" class="btn btn-primary btn--radius mx-2">회원가입</a>
				                   <button type="button" class="btn btn-secondary btn--radius mx-2" data-dismiss="modal">닫기</button>
				               `);
				}
				modal.modal('show');

			} catch (e) {
				console.error(e);
				alert('아이디 찾기 중 오류 발생');
			}
		});
	}

});

	document.addEventListener("DOMContentLoaded", function() {
	  let loginError = document.getElementById("loginErrorFlag").value === "true";
	  if (loginError) {
	    $('#loginFailedModal').modal('show');
	  }
	});
