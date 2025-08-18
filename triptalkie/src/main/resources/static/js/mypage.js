/**
 *  mypage 관련 javaScript
<a th:href="@{/customerservice/update-myinfo}" 
   class="btn btn-block btn-complete btn-primary">
   내 정보 수정
</a>
: 이렇게도 처리 가능

 */

// ===== 내 정보 수정 클릭 -> 내 정보 수정 화면 =====
const updateMyInformationBtn = document.querySelector('#btn-update-myinfo');
if (updateMyInformationBtn) {
	updateMyInformationBtn.addEventListener('click', () => {
		location.href = '/customerservice/update-myinfo';
	});
}