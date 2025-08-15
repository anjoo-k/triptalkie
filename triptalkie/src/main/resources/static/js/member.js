/**
 * 	member 관련 javaScript
 */


/* 중복 확인을 위한 js */
const duplicateCheck = document.querySelectorAll('.btn-duplicate');
duplicateCheck.forEach(duplicateCheckBtn => {
	duplicateCheckBtn.addEventListener('click', function() {
		const targetId = duplicateCheckBtn.dataset.target; // 클릭한 버튼의 data-target 읽기
		const input = document.getElementById(targetId); // 연결된 input 찾기
        const labelName = duplicateCheckBtn.dataset.label;  // 한글 이름
		console.log(`${labelName} 값: ${input.value}`);
		
		if(!input.value.trim()) {
			alert(`${labelName} 을(를) 입력하세요`)
			return;
		}
	});
});

/* 비밀번호 확인 */
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirmPassword');
const confirmMessage = document.getElementById('confirmMessage');

function showSuccess(message) {
	confirmMessage.innerHTML = message;
}

function confirmPasswordCheck() {
    if (confirmPassword.value === "") {
        confirmMessage.textContent = "";
        return;
    }

    if (password.value === confirmPassword.value) {
        showSuccess('<p style="margin-top:5px; color:green;">비밀번호가 일치합니다.</p>');
    } else {
		showSuccess('<p style="margin-top:5px; color:red;">비밀번호가 일치하지 않습니다.</p>');
    }
}

// 입력 이벤트 등록
password.addEventListener('input', confirmPasswordCheck);
confirmPassword.addEventListener('input', confirmPasswordCheck);

/* 완료 버튼 클릭 후 데이터 저장 */
