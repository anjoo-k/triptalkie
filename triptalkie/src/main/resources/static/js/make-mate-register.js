/* 여행종료일 선택: 시작일보다 앞의 날짜 선택 불가 */
const startDate = document.getElementById("startdate");
const endDate = document.getElementById("enddate");

document.addEventListener("DOMContentLoaded", () => {
	const today = new Date().toISOString().split("T")[0];
	startDate.min = today;
	endDate.min = today;
});

startDate.addEventListener("change", event => {
	endDate.min = event.target.value;
});

document.querySelector("form").addEventListener("submit", event => {
	if (endDate.value && (startDate.value > endDate.value)) {
		alert("종료일은 시작일 이후여야 합니다.")
		event.preventDefault();
	}
})

/* land 선택 -> country 필터, country 선택 -> city 필터 */
const landSelect = document.getElementById("landSelect");
const countrySelect = document.getElementById("countrySelect");
const citySelect = document.getElementById("citySelect");

document.addEventListener("DOMContentLoaded", () => {
	[...citySelect.options].forEach(opt => {
		if (opt.value) opt.hidden = true;
	});

	[...countrySelect.options].forEach(opt => {
		if (opt.value) opt.hidden = true;
	});
});

landSelect.addEventListener("change", event => {
	const selectLandId = event.target.value;
	[...countrySelect.options].forEach(option => {
		if (!option.value) return;
		option.hidden = (option.dataset.landId !== selectLandId);
	});
	countrySelect.value = "";
	citySelect.value = "";
	[...citySelect.options].forEach(opt => { if (opt.value) opt.hidden = true; });
});

countrySelect.addEventListener("change", event => {
	const selectCountryId = event.target.value;
	[...citySelect.options].forEach(opt => {
		if (!opt.value) return;
		opt.hidden = (opt.dataset.countryId !== selectCountryId);
	});
	citySelect.value = "";
});


