
let navbartog = document.getElementById("navbartog");
let dropdownmenu = document.getElementById("dropdown-menu");

navbartog.addEventListener("click", function () {
  dropdownmenu.classList.toggle("show"); // Toggle dropdown menu open or close
  navbartog.classList.toggle("active"); // Toggle active class for styling
});





//상단바 3개 Buttons
let btns = appbar.getElementsByClassName("nav-item");
for (let i = 0; i < btns.length; i++) {
  btns[i].addEventListener("click", function () {
    let current = document.getElementsByClassName("active");
    current[0].className = current[0].className.replace(" active", "");
    this.className += " active";
  });
}



// // 세부내용 페이지[이거는 연결해야함.]
//  // 뒤로 가기 버튼을 클릭했을 때 브라우저의 뒤로 가기 기능을 실행하는 함수
//  function goBack() {
//   window.history.back();
// }

// // 뒤로 가기 버튼 요소를 가져와 클릭 이벤트를 추가
// const backButton = document.getElementById('backButton');
// backButton.addEventListener('click', goBack);


