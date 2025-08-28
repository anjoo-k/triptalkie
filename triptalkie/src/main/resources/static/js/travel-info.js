document.getElementById("country").addEventListener("change", function() {
  const countryId = this.value;
  const citySelect = document.getElementById("city");
  citySelect.innerHTML = '<option value="">-- 도시 선택 --</option>'; // 초기화

  if (!countryId) return;

  fetch(`/cities/findCitiesByCountry?countryId=${countryId}`)
    .then(response => response.json())
    .then(data => {
      const selectedCityId = citySelect.dataset.selectedCity;
      data.forEach(city => {
        const option = document.createElement("option");
        option.value = city.id;
        option.text = city.name;
        if (city.id === selectedCityId) option.selected = true;
        citySelect.appendChild(option);
      });
    })
    .catch(error => console.error("도시 조회 실패:", error));
});