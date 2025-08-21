document.addEventListener('DOMContentLoaded', () => {
    const btn = document.getElementById("bookmarkBtn");

    if (btn) {
        btn.addEventListener("click", function () {
            const memberId = this.getAttribute("data-member-id");
            const makemateIdx = this.getAttribute("data-makemate-idx");

            fetch("/bookmark/toggle", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: `memberId=${memberId}&makemateIdx=${makemateIdx}`
            })
            .then(response => response.text())
            .then(result => {
                if (result === "true") {
                    // 북마크 추가됨 → 아이콘 변경
                    btn.src = "/images/bookmark-on.png";
                } else {
                    // 북마크 해제됨 → 원래 아이콘으로
                    btn.src = "/images/bookmark-off.png";
                }
            })
            .catch(error => {
                console.error("북마크 토글 실패:", error);
            });
        });
    }
});
