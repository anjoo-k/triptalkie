/**
 *  mypage 관련 javaScript
 */

// ===== 내 정보 수정 버튼 클릭 -> 패스워드 확인 화면 =====
/**
    <a th:href="@{/customerservice/update-myinfo}" 
    class="btn btn-block btn-complete btn-primary">
    내 정보 수정
	 </a>
	 : 이렇게도 처리 가능
 */
const updateMyInformationBtn = document.querySelector('#btn-update-myinfo');
if (updateMyInformationBtn) {
	updateMyInformationBtn.addEventListener('click', () => {
		location.href = '/mypage/password-check';
	});
}

// ===== 비밀번호 확인 버튼  =====
//  -> 비밀번호 틀렸을 경우 -> 모달
//  -> 비밀번호 맞았을 경우 -> 내 정보 수정 화면

// URL 쿼리 파라미터에 error=1 이 있으면 모달 표시
document.addEventListener("DOMContentLoaded", function(){
	let error =  document.querySelector("main").dataset.error;
	if(error === 'true'){
		const modalElement = document.querySelector('#myModal-passwordCheck');
		if(modalElement && window.bootstrap){
			new bootstrap.Modal(modalElement).show();
		}else{
			alert('잘못입력하셨습니다.');
		}
	}
})
