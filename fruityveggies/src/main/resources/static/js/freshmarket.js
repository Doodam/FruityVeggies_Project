/**
 * 
 */
 document.addEventListener('DOMContentLoaded', ()=> {
	
	// 폼 전체 
    const formSelect = document.querySelector('#form-select');
    
    // 옵션 디테일 영역
    const opDetailArea = document.querySelector('#opDetailArea');
    
    // 옵션 선택 항목 저장 객체
    const selectedOptionsSet = new Set();
    
    // 최종 상품 금액을 출력할 영역
    const totalPrice = document.querySelector('.result_price');
    let totalAmount = 0;// 최종 값
    
	let value;
    
	

// 셀렉트 박스 이벤트 리스너 시작    
    // 섹렉트 박스 옵션 변경 시 처리
    formSelect.addEventListener('change', (e) =>{
		
		console.log('start!!');
    const selectedOption_value = e.target.value;
    value = selectedOption_value;
    const selectedOption_text = e.target[e.target.selectedIndex].textContent;
    
    console.log('selectedOption_value : '+selectedOption_value);// 30000
    console.log('selectedOption_text : '+selectedOption_text);//유기농 송이바나나 3.5kg 30000원
    
    // 옵션 디테일 영역에 들어감 text 
    const opDetailText = selectedOption_text;
    console.log('opDetailText : '+opDetailText)

    if (! (selectedOptionsSet.has(opDetailText) || selectedOption_text =='옵션을 선택해주세요')) {// 없는 옵션을 선택하면 아래실행()
    
        selectedOptionsSet.add(selectedOption_value);
    	for(let a of selectedOptionsSet){
				   console.log("selectedOptionsSet before : "+a);
			   }

        totalAmount += Number(selectedOption_value);
        console.log('selec totalAmount : '+totalAmount);
        totalPrice.innerHTML = totalAmount;

        const div = document.createElement("div");
		div.id = `div_${selectedOption_value}`;
                    div.innerHTML = `<p id="name_${selectedOption_value}">${opDetailText}</p>
                                <button type="button" class="minus btn" id="minus_${selectedOption_value}" value="${selectedOption_value}">-</button>
                                <input type="number" class="numBox" id="numBox_${selectedOption_value}" min="1" max="10" value="1"  readonly="readonly" />
                                <button type="button" class="plus btn" id="plus_${selectedOption_value}" value="${selectedOption_value}">+</button>
                                <label id="numbers_${selectedOption_value}"></label>
                                <button type="button" class="cancel btn" id="cancel_${selectedOption_value}">취소</button>
                                `;
                                
        opDetailArea.appendChild(div);

        const plus = document.querySelector(`#plus_${selectedOption_value}`);
        console.log('plus111111111111111 : '+plus.value);
        const minus = document.querySelector(`#minus_${selectedOption_value}`);
        const numBox = document.querySelector(`#numBox_${selectedOption_value}`);
        const cancel = document.querySelector(`#cancel_${selectedOption_value}`);
        const targetDiv = document.querySelector(`#div_${selectedOption_value}`);
        
        // 한 옵션 합산 가격
        const price_label = document.querySelector(`#numbers_${selectedOption_value}`);
        price_label.innerHTML = minus.value;
        console.log('price_label.value = 1111111111111111',+price_label.innerHTML);
        
       //console.log('price_label 값 : '+Number(price_label.textContent.replace(/\D/g, ''))); 
		
		console.log('totalAmount : '+totalAmount);
/*플러스 버튼 시작*/        
        plus.addEventListener('click', () =>{
			console.log('numBox.value : '+numBox.value);
            const num = numBox.value;
                const plusNum = Number(num) + 1;
                console.log('number'+plusNum);
          
               if(plusNum >= 10) {
                        numBox.value = 10;
                        price_label.innerHTML = selectedOption_value*10;
               } else {
                        numBox.value = plusNum;
                        price_label.innerHTML = selectedOption_value*plusNum;
               }
               
               
               // 1번라벨, 2번라벨, 3번 라벨
               // 1번 +, - 를 누르면 3000+
               // 2번 +, - 를 누르면 6000+
               // 3번 +, - 를 누르면 9000+
               
               console.log(totalAmount);
               console.log('testtesttesttesttesttest2 : '+Number(price_label.textContent.replace(/\D/g, '')));
               //numbers_못난이파프리카_1kg
               /*const oa1 = 'numbers_못난이파프리카_'+e.target[formSelect.options.length].textContent;*/
               console.log('formSelect.options.length:'+formSelect.options.length);
                  
               console.log('plus.value : '+plus.value);           
               totalAmount += Number(plus.value);
               console.log('plus area : '+ (totalAmount+plus.value));
               totalPrice.innerHTML = totalAmount;
               
             });        
/*플러스 버튼 끝*/  

/*마이너스 버튼 시작*/
           minus.addEventListener('click', () =>{
              var num = numBox.value;
              var minusNum = Number(num) - 1;
   
              if(minusNum <= 1) {
                    numBox.value = 1;
                    price_label.innerHTML = selectedOption_value*1; // Update label 
               } else {
                   numBox.value = minusNum;
                    price_label.innerHTML = selectedOption_value*minusNum; // Update label       
              }
              
               totalAmount -= Number(minus.value)*numBox.value;
               console.log('minus area : '+ totalAmount);
               totalPrice.innerHTML = totalAmount;
          });
/*마이너스 버튼 끝*/

/*캔슬 버튼 시작*/
			cancel.addEventListener('click', (e) =>{
				console.log('123');
				targetDiv.remove();
				
				// 토탈 값 변경
			   totalAmount -= Number(minus.value)*(Number(numBox.value));
               console.log('minus area : '+ totalAmount);
               totalPrice.innerHTML = totalAmount;
               
               
               console.log('삭제할 값 : '+minus.value);
               // selectedOptionsSet 요소 값 제거
               selectedOptionsSet.delete(minus.value);
               
               for(let a of selectedOptionsSet){
				   console.log("selectedOptionsSet after: "+a);
			   }
               
				
			});
/*캔슬 버튼 끝*/
   
    }                      
                                
    });
// 셀렉트 박스 이벤트 리스너 끝    

// 주문하기 버튼 시작    	 
	const order_btn = document.querySelector('#order_btn');
	order_btn.addEventListener('click', () =>{

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

// 옵션 선택했는지 확인 시작
		if(selectedOptionsSet.size === 0){
			alert('옵션을 하나 이상 선택해 주세요!');
			return;
		}
// 옵션 선택했는지 확인 끝	

// 내가 선택한 옵션 선택 ㄱㄱ
    // 옵션 이름 저장
    const selectedOptionsName = [];//selectedOption_text
    // 옵션 갯수 저장
    const selectedOptionsCnt = [];//numBox.value
    // 옵션의 최종 가격 저장
    const selectedOptionsPrice = [];//price_label.innerHTML
    
    const opDetailDivs = document.querySelectorAll(".opDetailArea > div");
		
		opDetailDivs.forEach(function (div) {
        const value = div.id.split("_")[1];
        const name = document.getElementById(`name_${value}`).textContent;
        const cnt = document.getElementById(`numBox_${value}`).value;
        const price = document.getElementById(`numbers_${value}`).textContent;

        selectedOptionsName.push(name);
        selectedOptionsCnt.push(cnt);
        selectedOptionsPrice.push(price);
    });	
    
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
// 쿼리스트링 id값 알아내기 시작
	// 주어진 URL
	const url = window.location.href;
	
	// URL 객체 생성
	const urlObject = new URL(url);
	
	// URLSearchParams 객체 생성
	const searchParams = new URLSearchParams(urlObject.search);
	
	// "id" 쿼리스트링 값 가져오기
	const idValue = searchParams.get("id");
	
	console.log(idValue); // "1" 출력
// 쿼리스트링 id값 알아내기 끝
        
// 데이터 바인딩
	const data = {
            names: selectedOptionsName,
            counts: selectedOptionsCnt,
            prices: selectedOptionsPrice,
            id: idValue,
            userId: userId
	};
	// Ajax 요청을 보낼 url
	const reqUrl = '/freshmarket/freshmarket'; 
	    
    // Ajax POST 요청을 보냄
		axios.post(reqUrl,data)
			.then(function(response){//성공 응답일 때 실행할 콜백 등록
				console.log(response);
				
				location.href="/freshmarket/freshmarketorder/"+userId;
			})
			.catch((error)=>{//실패일 때 실행할 콜백 등록
				console.log(error);
			});
// 내가 선택한 옵션 선택 ㅌㅌ
    
		
	}); 
// 주문하기 버튼 끝

// 장바구니 버튼 시작

const cart_btn = document.querySelector('#cart_btn');
	cart_btn.addEventListener('click', () =>{
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

// 옵션 선택했는지 확인 시작
		if(selectedOptionsSet.size === 0){
			alert('옵션을 하나 이상 선택해 주세요!');
			return;
		}
// 옵션 선택했는지 확인 끝



// 내가 선택한 옵션 선택 ㄱㄱ
    // 옵션 이름 저장
    const selectedOptionsName = [];//selectedOption_text
    // 옵션 갯수 저장
    const selectedOptionsCnt = [];//numBox.value
    // 옵션의 최종 가격 저장
    const selectedOptionsPrice = [];//price_label.innerHTML
    
    const opDetailDivs = document.querySelectorAll(".opDetailArea > div");
		
		opDetailDivs.forEach(function (div) {
        const value = div.id.split("_")[1];
        const name = document.getElementById(`name_${value}`).textContent;
        const cnt = document.getElementById(`numBox_${value}`).value;
        const price = document.getElementById(`numbers_${value}`).textContent;

        selectedOptionsName.push(name);
        selectedOptionsCnt.push(cnt);
        selectedOptionsPrice.push(price);
    });	
    
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
// 쿼리스트링 id값 알아내기 시작
	// 주어진 URL
	const url = window.location.href;
	
	// URL 객체 생성
	const urlObject = new URL(url);
	
	// URLSearchParams 객체 생성
	const searchParams = new URLSearchParams(urlObject.search);
	
	// "id" 쿼리스트링 값 가져오기
	const idValue = searchParams.get("id");
	
	console.log(idValue); // "1" 출력
// 쿼리스트링 id값 알아내기 끝
        
	// 이미지 태그를 id로 선택합니다.
	var imageTag = document.querySelector('#mainImg');

	// 이미지 태그의 src 속성을 변수에 저장합니다.
	const img = imageTag ? imageTag.src : '';

	// qew 변수에 이미지 URL이 저장되어 있습니다.
	console.log(img); // 이미지 URL 출력        
        
        
// 데이터 바인딩
	const data = {
            names: selectedOptionsName,
            counts: selectedOptionsCnt,
            prices: selectedOptionsPrice,
            id: idValue,
            userId: userId,
            mainImg:img
	};
	// Ajax 요청을 보낼 url
	const reqUrl = '/freshmarket/cart';
	    
	    
    
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
    	 

 });