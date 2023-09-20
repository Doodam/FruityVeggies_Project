document.addEventListener('DOMContentLoaded', () => {
	var priceData = {};
	
	let totalAcount = 0;
	let cc = 0;
	
	const bill_btn = document.querySelector('#bill_btn');
	
	bill_btn.addEventListener('click', () =>{
		// Ajax 요청을 보낼 url
		const reqUrl = '/cart/bill';
		    
		// 모든 체크된 체크박스 요소를 선택합니다.
	    const checkedCheckboxes = document.querySelectorAll('input[name="cartCheckbox"]:checked');
	    
	    // 선택된 체크박스의 값을 배열에 저장합니다.
	    const selectedIds = Array.from(checkedCheckboxes).map(checkbox => checkbox.value);
		
		console.log('selectedIds : ',selectedIds);
		
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
		
		const data = {
	        selectedIds: selectedIds
	    };	    	
		    
	    	// Ajax POST 요청을 보냄
		axios.post(reqUrl,data)
			.then(function(response){//성공 응답일 때 실행할 콜백 등록
				console.log(response);
				
				location.href="/freshmarket/freshmarketorder/1/"+idValue;
			})
			.catch((error)=>{//실패일 때 실행할 콜백 등록
				console.log(error);
			});
	});
	
	
	
	
	
	const chk_del = document.querySelector('#chk_del');
	
	chk_del.addEventListener('click', () =>{
		// Ajax 요청을 보낼 url
		const reqUrl = '/cart/del';
		    
		// 모든 체크된 체크박스 요소를 선택합니다.
	    const checkedCheckboxes = document.querySelectorAll('input[name="cartCheckbox"]:checked');
	    
	    // 선택된 체크박스의 값을 배열에 저장합니다.
	    const selectedIds = Array.from(checkedCheckboxes).map(checkbox => checkbox.value);
		
		console.log('selectedIds : ',selectedIds);
		
		const data = {
	        selectedIds: selectedIds
	    };	    	
		    
	    	// Ajax POST 요청을 보냄
		axios.post(reqUrl,data)
			.then(function(response){//성공 응답일 때 실행할 콜백 등록
				console.log(response);
				location.reload();
			})
			.catch((error)=>{//실패일 때 실행할 콜백 등록
				console.log(error);
			});
	});
	
	
	const totalPrice = document.querySelector('#total_price');
	
	for (var i = 0; i < listsData.length; i++) {
	    var price = listsData[i].price;
	    console.log(price);
	
	    // 객체 또는 맵에 가격을 키로 사용하여 데이터를 추가합니다.
	    priceData[price] = listsData[i];
	
	    // 각 가격에 해당하는 수량 필드를 선택하고 이벤트 리스너를 추가합니다.
	    const quantityInputs = document.querySelectorAll(`[name="cntNum_${price}"]`);
	    
	    const opTotal = document.querySelectorAll(`[name="opTotal_${price}"]`);
	    
	    for(let a of opTotal){
			totalAcount += Number(a.innerHTML);
		}
	    console.log('1111111111 : ',totalAcount);
	    totalPrice.innerHTML = totalAcount;
	    console.log('quantityInputs : ',quantityInputs.innerHtml);
	    quantityInputs.forEach(input => {
	        input.addEventListener('change', event => {
				
				console.log('quantityInputs : ',quantityInputs );
				// name 속성 값을 가져옵니다.
	        const nameAttribute = input.getAttribute('name');
	        // '_' 문자를 기준으로 분할하여 숫자 값을 추출합니다.
	        const splitParts = nameAttribute.split('_');
	        const numberPart = splitParts[1]; // 'cntNum_16900'에서 16900 추출
	        
	        const value = parseInt(numberPart); // 문자열을 숫자로 변환합니다.
	        
	        console.log('value : ', value);
	        
			const opTotal = document.querySelector(`#opTotal_${value}`);
			console.log('input.value : ', Number(input.value));
			
			opTotal.innerHTML = Number(input.value) * value ;
			console.log('opTotal.innerHTML : ',opTotal.innerHTML);
			
			
			totalAcount += Number(opTotal.innerHTML);	
			console.log('totalAcount : ',totalAcount);
			totalPrice.innerHTML = totalAcount;
	        /*const newQuantity = parseInt(input.value);
	        const newTotalPrice = price * newQuantity;
	
	        // 해당 총 가격을 보여주는 <td> 요소를 업데이트합니다.
	        const totalTd = document.querySelector(`#opTotal_${price}`);
	        totalTd.textContent = newTotalPrice;
	
	        const reserveAmount = price * 0.05;
	
	        // 해당 예약 금액을 보여주는 <td> 요소를 업데이트합니다.
	        const reservesTd = document.querySelector(`#reserves_${price}`);
	        reservesTd.textContent = reserveAmount;*/
	        });
	    });
	}
});