/**
 * country 변화 감지해서 city 데이터 가져옴
 */
document.getElementById("country").addEventListener("change", function() {
  const countryId = this.value;
  const citySelect = document.getElementById("city");

  // 기존 옵션 초기화
  citySelect.innerHTML = '<option value="">-- 도시 선택 --</option>';

  if (countryId) {
	fetch(`/cities/findCitiesByCountry?countryId=${countryId}`)
      .then(res => res.json())
      .then(data => {
        data.forEach(city => {
          const option = document.createElement("option");
          option.value = city.id;
          option.textContent = city.name;
          citySelect.appendChild(option);
        });
      });
  }
});