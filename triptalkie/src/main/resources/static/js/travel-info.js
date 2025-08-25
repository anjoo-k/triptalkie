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

/**
 * 여행 정보 수정 페이지 나라별 도시 표시 기능을 위한 js
 */
document.addEventListener("DOMContentLoaded", function () {
    const countrySelect = document.getElementById("country");
    const citySelect = document.getElementById("city");
    const selectedCityId = citySelect.getAttribute("data-selected-city");

    function loadCities(countryId) {
        fetch(`/api/cities?countryId=${countryId}`)
            .then(res => res.json())
            .then(cities => {
                citySelect.innerHTML = '<option value="">-- 도시 선택 --</option>';
                cities.forEach(city => {
                    const opt = document.createElement("option");
                    opt.value = city.id;
                    opt.textContent = city.name;
                    if (city.id === selectedCityId) {
                        opt.selected = true;
                    }
                    citySelect.appendChild(opt);
                });
            });
    }

    countrySelect.addEventListener("change", function () {
        const countryId = this.value;
        if (countryId) loadCities(countryId);
        else citySelect.innerHTML = '<option value="">-- 도시 선택 --</option>';
    });

    // 초기 로딩 시 기존 나라가 선택되어 있으면 도시도 불러오기
    if (countrySelect.value) {
        loadCities(countrySelect.value);
    }
});


