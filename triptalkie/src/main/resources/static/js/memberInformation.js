/**
 *  makemate 상세글 프로필 연결
 */

document.addEventListener("DOMContentLoaded", () => {
	const profile = document.querySelector("#profile");
	profile.addEventListener("click", () => {
		document.querySelector("#profileForm").submit();
	});
});