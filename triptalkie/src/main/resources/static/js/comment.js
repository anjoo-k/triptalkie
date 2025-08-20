/**
 * comment를 위한 JavaScript
 */

console.log("✅ comment.js loaded");
async function loadComments() {
	const commentList = document.querySelector('#comment-list');
	/*const boardId -> 각자 게시판 id 담음,
	 hidden input 등으로 전달*/
	try {
		const response = await fetch("/comment/list");
		const result = await response.json();

		console.log(result);
		console.log(result.data);

		if (result.success) {
			commentList.innerHTML = "";
			result.data.forEach(comment => {
				const div = document.createElement("div");
				div.innerHTML = `<b>${comment.memberId}</b> ${comment.content} <br><small>${comment.createdAt}</small>`;
				commentList.appendChild(div);
			});
		}
	} catch (error) {
		console.error(error);
	}
}


document.addEventListener("DOMContentLoaded", function() {
	const commentBtn = document.querySelector('#commentBtn');
	const textarea = document.querySelector('#comment');
	const commentList = document.querySelector('#comment-list');

	loadComments();

	commentBtn.addEventListener("click", async () => {
		const text = textarea.value.trim();
		if (!text) {
			alert("댓글을 입력하세요");
			return;
		}

		try {
			const response = await fetch("/comment", {
				method: "POST",
				headers: { "Content-Type": "application/json" },
				body: JSON.stringify({
					content: text,
					memberId: "user01"
				})
			});

			const result = await response.json();
			if (result.success) {
				// 댓글 DOM 추가
				const newCommentArea = document.createElement("div");
				newCommentArea.classList.add("comment-item", "border", "p-2", "mb-2");
				newCommentArea.innerText = result.data.comment;
				commentList.prepend(newCommentArea);

				textarea.value = "";
			}
		} catch (error) {
			console.log(error);
			alert("댓글 저장 실패!");
		}
	});
});
