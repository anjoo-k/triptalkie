/**
 * 	customerservice 관련 javaScript
 */

const signUpBtn = document.querySelector('.btn-signup');
if(signUpBtn){
    signUpBtn.addEventListener('click', () => {
        location.href = '/signup';
    });
}

// ===== 좌측 list 링크 =====
const costomerserviceListItems = document.querySelectorAll('.list-group-item');
function notice(){
	costomerserviceListItems.forEach
}


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
