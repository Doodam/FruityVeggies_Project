/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
    
    const userIdElement = document.querySelector('#userId');
    
    const regul_btn = document.querySelector('#regul_btn');
    
    regul_btn.addEventListener('click', (e) =>{
        e.preventDefault();
        location.href="/rescueorder/rescue_order?id="+userIdElement.innerHTML;   
    });
    
    
    /*console.log('asdf');
   function addDiv() {
       console.log('addDiv');
            // 새로운 <div> 요소 생성
            var newDiv = document.createElement("div");
            newDiv.innerHTML = "새로운 내용입니다.";

            // 생성한 <div> 요소를 기존의 <div> 영역에 추가
            var container = document.getElementById("container");
            container.appendChild(newDiv);
        }

        // 버튼 클릭 이벤트와 함수를 연결
        var addButton = document.getElementById("addButton");
        addButton.addEventListener("click", addDiv);*/
    
});

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