// 페이지 버튼 생성 함수
function renderPagination(currentPage, totalPages) {
  const pagesContainer = document.getElementById('pages');
  pagesContainer.innerHTML = '';

  const startPage = Math.max(1, currentPage - 2);
  const endPage = Math.min(totalPages, startPage + 4);

  for (let i = startPage; i <= endPage; i++) {
    const pageButton = document.createElement('button');
    pageButton.textContent = i;
    if (i === currentPage) {
      pageButton.classList.add('selected'); // 현재 페이지 버튼에 selected 클래스 추가
    }
    pageButton.addEventListener('click', () => goToPage(i));
    pagesContainer.appendChild(pageButton);
  }
}

// 페이지 이동 함수
function goToPage(page) {
  window.location.href = `/manager/viewBbsAll?reqPage=${page}&reqCnt=10`; // 링크 수정
}

// 이전 페이지 이동 버튼 이벤트 처리
document.getElementById('prevPage').addEventListener('click', () => {
  const currentPageButton = document.getElementById('pages').querySelector('button.selected');
  const currentPage = currentPageButton ? parseInt(currentPageButton.textContent) : 1;
  if (currentPage > 1) {
    goToPage(currentPage - 1);
  }
});

// 다음 페이지 이동 버튼 이벤트 처리
document.getElementById('nextPage').addEventListener('click', () => {
  const currentPageButton = document.getElementById('pages').querySelector('button.selected');
  const currentPage = currentPageButton ? parseInt(currentPageButton.textContent) : 1;
  const totalPages = parseInt(/*[[${totalCnt}]]*/ 0 / 10) + 1; // 서버에서 전달된 변수 사용
  if (currentPage < totalPages) {
    goToPage(currentPage + 1);
  }
});

// 처음 페이지 이동 버튼 이벤트 처리
document.getElementById('firstPage').addEventListener('click', () => {
  goToPage(1);
});

// 마지막 페이지 이동 버튼 이벤트 처리
document.getElementById('lastPage').addEventListener('click', () => {
  const totalPages = parseInt(/*[[${totalCnt}]]*/ 0 / 10) + 1; // 서버에서 전달된 변수 사용
  goToPage(totalPages);
});

// 초기 페이지 로드 시 호출
renderPagination(/*[[${cp}]]*/ null, /*[[${cpgs}]]*/ null); // 서버에서 전달된 변수 사용
