/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
/*스크롤 컨트롤*/    
    const lnb = document.querySelector("#lnb");
    const lnbOffsetTop = lnb.offsetTop;
    
    const nevi = document.querySelector("div.text-end");

    window.addEventListener('scroll', function() {
    const scrollTop = window.scrollY || document.documentElement.scrollTop;

    if (lnbOffsetTop <= scrollTop) {
        // 스크롤 함
      lnb.classList.add('fixed');
      document.getElementById('text-end1').style.display = 'block';
      document.getElementById('text-end2').style.display = 'block';
      
      
    } else {
        // 스크롤 안함
      lnb.classList.remove('fixed');
      document.getElementById('text-end1').style.display = 'none';
      document.getElementById('text-end2').style.display = 'none';
    }
  });
/*스크롤 컨트롤*/    
    
    const loginBtn = document.querySelector('button#loginBtn1');
    loginBtn.addEventListener('click', function(){
        console.log('login');
        location.href="/login";
    });
    
    
    const signBtn = document.querySelector('button#signBtn1');
    signBtn.addEventListener('click', function(){
        console.log('signup');
    });
    const loginBtn1 = document.querySelector('button#loginBtn1');
    loginBtn1.addEventListener('click', function(){
        console.log('login1');
        location.href="/login";
    });
    
    
    const signBtn1 = document.querySelector('button#signBtn1');
    signBtn1.addEventListener('click', function(){
        console.log('signup1');
    });
});
















const cartmain_btn = document.querySelector('#cartmain_btn');
    cartmain_btn.addEventListener('click', () =>{
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
    
/*    for(let a of selectedOptionsName){
        console.log('selectedOptionsName : '+a);
    }
    for(let a of selectedOptionsCnt){
        console.log('selectedOptionscccc : '+a);
    }
    for(let a of selectedOptionsPrice){
        console.log('selectedOptionsPrice : '+a);
    }
*/
        
// 데이터 바인딩
    const data = {
            userId: userId,
    };
    // Ajax 요청을 보낼 url
    const reqUrl = '/freshmarket/maincart';
        
        
    
//장바구니 페이지 갈건지 체크 끝
            const confirmed_f = window.confirm("장바구니 창으로 이동 하시겠습니까?");
            if (confirmed_f) {
                // Ajax POST 요청을 보냄
                axios.post(reqUrl,data)
            .then(function(response){//성공 응답일 때 실행할 콜백 등록
                console.log(response);
                
                window.location.href = "/freshmarket/cart?id="+userId;
                
            })
            .catch((error)=>{//실패일 때 실행할 콜백 등록
                console.log(error);
            });
                
            } else {
                return;
            }
        
// 내가 선택한 옵션 선택 ㅌㅌ












    }); 
// 장바구니 버튼 끝










/*$(function() {
        var lnb = $("#lnb").offset().top;
        $(window).scroll(function() {
          var window = $(this).scrollTop();

          if(lnb <= window) {
            $("#lnb").addClass("fixed");
          } else {
            $("#lnb").removeClass("fixed");
          }
        })
      });*/