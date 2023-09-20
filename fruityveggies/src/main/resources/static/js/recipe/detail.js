/**
 * detail 페이지
 */

document.addEventListener('DOMContentLoaded', () => {
    const uploadBtn = document.querySelector('.uploadBtn');

    // 버튼에 클릭 이벤트 리스너를 추가합니다.
    uploadBtn.addEventListener('click', () => {
        // 버튼이 클릭되었을 때 "upload.html" 페이지로 리다이렉션합니다.
        window.location.href = 'upload';
    });
    
    //좋아요
    const likeButton = document.querySelector('.like-button');
    const likeImg = document.querySelector('.like-img');

    likeButton.addEventListener('click', () => {
    likeImg.classList.toggle('liked');
    });
});