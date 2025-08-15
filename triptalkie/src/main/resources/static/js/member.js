/**
 * 	member 관련 javaScript
 */

const duplicateCheck = document.querySelectorAll('.duplicateCheckBtn');

/* 중복 확인을 위한 js */
duplicateCheck.forEach(duplicateCheckBtn => {
	duplicateCheckBtn.addEventListener('click', function() {
		const targetId = duplicateCheckBtn.dataset.target; // 클릭한 버튼의 data-target 읽기
		const input = document.getElementById(targetId); // 연결된 input 찾기
		console.log(`${targetId} 값:`, input.value);
		
		if(!input) {
			alert(`${targetId} 를 입력하세요`)
			return;
		}
	});
});

/* 비밀번호 확인 */
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirmPassword');
const message = document.getElementById('passwordMessage');

function confirmPasswordCheck() {
    if (confirmPassword.value === "") {
        message.textContent = "";
        return;
    }

    if (password.value === confirmPassword.value) {
        message.style.color = "green";
        message.textContent = "비밀번호가 일치합니다.";
    } else {
        message.style.color = "red";
        message.textContent = "비밀번호가 일치하지 않습니다.";
    }
}

// 입력 이벤트 등록
password.addEventListener('input', confirmPasswordCheck);
confirmPassword.addEventListener('input', confirmPasswordCheck);