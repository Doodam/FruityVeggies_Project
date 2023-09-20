/**
 * 
 */
document.addEventListener('DOMContentLoaded', () =>{
	
	const back = document.querySelector('#back');
	
	back.addEventListener('click', (e)=>{
		
		window.location.href = "/rescueorder/rescue_order";	
	});
	
	
	
	// 결제
	 const buyBtn = document.querySelector('button#payment-button')
	 const radioInputs = document.querySelectorAll('input[type="radio"][name="payment"]');

   
     buyBtn.addEventListener('click', () => {
         
         let selectedPayment = null;
         let tossPayments = TossPayments("test_ck_4vZnjEJeQVx9dyz6KMOrPmOoBN0k");

         /*let amount = parseFloat(totalPrice);*/  
         console.log('떠라');  
    
         function pay(method, requestJson) {
             console.log(requestJson);
             tossPayments.requestPayment(method, requestJson)
                 .catch(function(error) {
                     if (error.code === "USER_CANCEL") {
                         alert('유저가 취소했습니다.');
                     } else {
                         alert(error.message);
                     }

                 });
         }
        
         let path = "/freshmarket";
         let successUrl = window.location.origin + path + "/success";
         let failUrl = window.location.origin + path + "/fail";
         let callbackUrl = window.location.origin + path + "/va_callback";
         let orderId = new Date().getTime();


         // 선택된 라디오버튼 값을 확인
         for (const radioInput of radioInputs) {
             if (radioInput.checked) {
                 selectedPayment = radioInput.value;
                 break;
             }
         }
       
            
         if (selectedPayment === 'card') {
             let jsons = {
                 "card": {
                     "amount": 25000,
                     "orderId": "sample-" + orderId,
                     "orderName": '패밀리 박스',
                     "successUrl": successUrl,
                     "failUrl": failUrl,
                     "cardCompany": null,
                     "cardInstallmentPlan": null,
                     "maxCardInstallmentPlan": null,
                     "useCardPoint": false,
                     "customerName": "서정아",
                     "customerEmail": null,
                     "customerMobilePhone": null,
                     "taxFreeAmount": null,
                     "useInternationalCardOnly": false,
                     "flowMode": "DEFAULT",
                     "discountCode": null,
                     "appScheme": null
                 }
             }
              pay('카드', jsons.card);
            
         } else if (selectedPayment === 'transfer') {
             let jsons = {
                 "transfer": { //계좌이체 결제창

                     "amount": 25000,
                     "orderId": "sample-" + orderId,
                     "orderName": "패밀리 박스",
                     "successUrl": successUrl,
                     "failUrl": failUrl,
                     "customerName": "서정아",
                     "customerEmail": null,
                     "customerMobilePhone": null,
                     "taxFreeAmount": null,
                     "cashReceipt": {
                         "type": "소득공제"
                     },
                     "useEscrow": false
                 }
             }
             pay('계좌이체', jsons.transfer);
         } else if (selectedPayment === 'phone') {

             let jsons = {
                 "phone": { // 휴대폰 결제창

                     "amount": 25000,
                     "orderId": "sample-" + orderId,
                     "orderName": "패밀리 박스",
                     "successUrl": successUrl,
                     "failUrl": failUrl,
                     "mobileCarrier": null

                 }
             }
             pay('휴대폰', jsons.phone);
         } else {
             // 선택된 결제 방법이 없는 경우에 대한 처리
             alert('결제 방법을 선택해주세요.');
         }
         
           
            
        });
	
	
});