/**
 * 
 */
 document.addEventListener('DOMContentLoaded', ()=> {
	 //로그인 했는지 확인 시작		
		const userIdElement = document.querySelector('#userId');
		let userId='';
	
	    if (userIdElement === null) {
	        
	        const confirmed = window.confirm("로그인 창으로 이동 하시겠습니까?");
            if (confirmed) {
                window.location.href = "/login";
            } else {
                return;
            }
	        
	        console.log('Element not found.');
	        return;
	        
	    } else {
			userId = userIdElement.innerHTML;
	        console.log(userId); // 출력: "exampleUser"
	    }
//로그인 했는지 확인 끝
 });