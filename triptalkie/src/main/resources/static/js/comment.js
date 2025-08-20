/**
 * comment를 위한 JavaScript
 */

const commentBtn = document.querySelector('#commentBtn');
const textarea = document.querySelector('#comment');
const commentList = document.querySelector('#comment-list'); 

async function loadComments(){
	/*const boardId -> 각자 게시판 id 담음,
	 hidden input 등으로 전달*/
	try{
		const response = await fetch("/comment");
		const result = await response.json();
		
		if(result.sucess){
			result.data.farEach(comment => {
				const div = documnet.creatElement("div");
				div.innerHTML = `<b>${comment.memberId}</b> ${c.content} <br><small>${c.createdAt}</small>`;
				commentList.appendChild(div);
			});
		}
	}catch(error){
		console.error(error);
	}
}


  

document.addEventListener("DOMContentLoaded", function () {
 
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
