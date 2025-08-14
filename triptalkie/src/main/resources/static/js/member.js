/**
 * 	member 관련 javaScript
 */

const duplicateCheckBtn = document.querySelectorAll('.duplicateCheckBtn');

duplicateCheckBtn.forEach(duplicateCheck => {
	duplicateCheck.addEventListener('click', function(){
		console.log('버튼이 클릭되었습니다!', this);
	});
});