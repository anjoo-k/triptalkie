/**
 * 	member 관련 javaScript
 * 	회원가입, 로그인
 */

// ===== 회원가입 페이지: 중복 확인 버튼 =====
const duplicateCheckBtns = document.querySelectorAll('.btn-duplicate');
duplicateCheckBtns.forEach(btn => {
    btn.addEventListener('click', () => {
        const targetId = btn.dataset.target;
        const input = document.getElementById(targetId);
        const labelName = btn.dataset.label;

        if (!input || !input.value.trim()) {
            alert(`${labelName} 을(를) 입력하세요`);
            return;
        }
        console.log(`${labelName} 값: ${input.value}`);
    });
});

// ===== 비밀번호 확인 =====
const password = document.getElementById('password');
const confirmPassword = document.getElementById('confirmPassword');
const confirmMessage = document.getElementById('confirmMessage');

function showMessage(message) {
    if(confirmMessage) confirmMessage.innerHTML = message;
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

if(password && confirmPassword){
    password.addEventListener('input', confirmPasswordCheck);
    confirmPassword.addEventListener('input', confirmPasswordCheck);
}

// ===== 회원 가입 버튼 이벤트 =====
const signUpBtn = document.querySelector('.btn-signup');
if(signUpBtn){
    signUpBtn.addEventListener('click', () => {
        location.href = '/signup';
    });
}

const findIdOrPwdBtn = document.querySelector('.btn-findIdOrPwd');
if(findIdOrPwdBtn){
    findIdOrPwdBtn.addEventListener('click', () => {
        alert('아이디/비밀번호 찾기 클릭!');
    });
}

const loginBtn = document.querySelector('.btn-login');
if(loginBtn){
    loginBtn.addEventListener('click', () => {
        alert('로그인 버튼 클릭!');
    });
}
