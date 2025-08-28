document.addEventListener('DOMContentLoaded', function () {
  console.log("✅ rating.js 실행됨!");

  $('#ratingModal').on('show.bs.modal', function (event) {
    const button = $(event.relatedTarget);
    const ratedId = button.data('ratedid');
    const makemateIdx = button.data('makemateidx');
    
    console.log('ratedId:', ratedId);
    console.log('makemateIdx from JS:', makemateIdx);
    
    $('#ratedId').val(ratedId);
    $('#makemateIdx').val(makemateIdx);
  });
});
