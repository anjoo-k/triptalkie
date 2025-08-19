/**
 * comment를 위한 JavaScript
 */

document.addEventListener("DOMContentLoaded", function () {
  const commentBtn = document.querySelector('#commentBtn');
  const textarea = document.querySelector('#comment');
  const commentList = document.querySelector('#comment-list'); 
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
        body: JSON.stringify({ content: text,
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
