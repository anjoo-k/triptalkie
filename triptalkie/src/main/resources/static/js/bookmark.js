document.addEventListener('DOMContentLoaded', () => {
    
    const btns = document.querySelectorAll(".bookmarkBtn");
    console.log("📌 북마크 버튼 개수:", btns.length);

    btns.forEach(btn => {
        btn.addEventListener("click", function () {
            console.log("🖱️ 북마크 클릭됨!", this);

            const memberId = this.getAttribute("data-member-id");
            const makemateIdx = this.getAttribute("data-makemate-idx");
            console.log("👉 memberId:", memberId, "👉 makemateIdx:", makemateIdx);

            fetch("/bookmark/toggle", {
                method: "POST",
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded"
                },
                body: `memberId=${memberId}&makemateIdx=${makemateIdx}`
            })
            .then(response => response.json())
            .then(isBookmarked => {
                console.log("📥 서버 응답:", isBookmarked);
                if (isBookmarked) {
                    this.src = "/images/bookmark-on.png";
                } else {
                    this.src = "/images/bookmark-off.png";
                }
            })
            .catch(error => {
                console.error("❌ 북마크 토글 실패:", error);
            });
        });
    });
});