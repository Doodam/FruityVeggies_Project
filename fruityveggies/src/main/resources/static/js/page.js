function showContent(sectionId) {
    var sections = document.querySelectorAll('.section-content'); // 모든 섹션 요소 선택

    // 모든 섹션 요소의 클래스 제거
    sections.forEach(function(section) {
        section.classList.remove('active');
    });

    // 선택한 섹션 요소의 클래스 추가
    var selectedSection = document.getElementById(sectionId);
    if (selectedSection) {
        selectedSection.classList.add('active');
      
      // 부드러운 스크롤 애니메이션
      window.scrollTo({
        top: selectedSection.offsetTop,
        behavior: 'smooth'
      });
    }
}

document.addEventListener('DOMContentLoaded', function() {
	console.log('111');
	const order_btn = document.querySelector('#order_btn_1');
		order_btn.addEventListener('click', (e) =>{
        
        e.preventDefault();
        
        const userIdElement = document.querySelector('#userId');
		let userId='';
	
	    if (userIdElement === null) {
	        
	        const confirmed = window.confirm("로그인 창으로 이동 하시겠습니까?");
            if (confirmed) {
                window.location.href = "/login";
            } else {
                window.location.href = "/rescueorder/rescue_order?resId="+userId;
            }
	        
	        console.log('Element not found.');
	        return;
	        
	    } else {
			userId = userIdElement.innerHTML;
	        window.location.href = "/rescueorder/rescue_order?resId="+userId;
	    }
    });
    
    
	
    var sectionLinks = document.querySelectorAll('.section-link');

    sectionLinks.forEach(function(link) {
        link.addEventListener('click', function(event) {
            event.preventDefault(); // 기본 동작 중지
            var sectionId = link.getAttribute('data-section-id');
            showContent(sectionId);
        });
        
        
     });
});
