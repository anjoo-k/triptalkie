document.addEventListener('DOMContentLoaded', () => {
    // ---- 수정 페이지 이동 ----
    const editBtn = document.getElementById('btn-edit');
    if (editBtn) {
        editBtn.addEventListener('click', () => {
            const idx = editBtn.getAttribute('data-idx');
            if (idx) {
                // ✅ Controller 매핑과 맞춤
                window.location.href = `/community/update/${idx}`;
            }
        });
    }

	// ---- 삭제 버튼 ----
	const deleteBtn = document.getElementById('btn-delete');
	if (deleteBtn) {
	    deleteBtn.addEventListener('click', async () => {
	        const idx = deleteBtn.getAttribute('data-idx');
	        if (idx && confirm("정말 삭제하시겠습니까?")) {
	            try {
	                const response = await fetch(`/community/delete/${idx}`, {
	                    method: 'POST'
	                });
	                if (response.ok) {
	                    window.location.href = '/community/list';
	                } else {
	                    alert('삭제 실패');
	                }
	            } catch (err) {
	                console.error(err);
	                alert('삭제 중 오류 발생');
	            }
	        }
	    });
	}

});
