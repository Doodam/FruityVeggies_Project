/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {
    
    
    
    const orderRequest = document.getElementById("orderrequest");
    const inputContainer = document.getElementById("inputContainer");
    const customInput = document.getElementById("customInput");


    orderRequest.addEventListener('change', () => {
        if (orderRequest.value === "직접 입력") {
            inputContainer.style.display = "block";
            customInput.setAttribute("required", "true");
        } else {
            inputContainer.style.display = "none";
            customInput.removeAttribute("required");
        }
    });
    
    
    // 배송비 총 금액
    // 배송비가 2000원인 경우
    const deliveryCost = 2000;

    // 배송비 총합 계산
    let totalDeliveryCost = 0;
    document.querySelectorAll('[id^="deliveryCost_"]').forEach(function(item) {
        totalDeliveryCost += parseInt(item.innerText);
    });
    document.getElementById("totalDeliveryCost").innerText = totalDeliveryCost;
    
    
    
    // 총 상품 금액
    let totalPrice = 0;
     document.querySelectorAll('[id^="opTotal_"]').forEach(function(item) { // 소계 값을 가지고 있는 요소들을 선택합니다.
    totalPrice += parseInt(item.innerText); // 해당 요소의 텍스트 값을 가져와 정수형으로 변환한 뒤 총 가격에 더합니다.
  });
  document.getElementById("totalPrice").innerText = totalPrice; // 총 가격을 보여주는 요소에 값을 할당합니다.

    // 배송비 + 결제 총 금액 (결제하기 위한 금액임)
    let totalPayment = totalDeliveryCost + totalPrice;
     document.getElementById("totalPayment").innerText = totalPayment;
    
    
   // 결제

    const buyBtn = document.querySelector('button#buyBtn')
    const radioInputs = document.querySelectorAll('input[type="radio"][name="payment"]');

   
     buyBtn.addEventListener('click', () => {
         
         let selectedPayment = null;
         let tossPayments = TossPayments("test_ck_4vZnjEJeQVx9dyz6KMOrPmOoBN0k");

      /*   let orderName = document.querySelector("td[th\\:text='${cart.name}']").textContent;*/
         let amount = parseFloat(totalPayment);  
         console.log('떠라', amount);  
    
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
                     "amount": amount,
                     "orderId": "sample-" + orderId,
                     "orderName": '생블루베리 중과 1kg...',
                     "successUrl": successUrl,
                     "failUrl": failUrl,
                     "cardCompany": null,
                     "cardInstallmentPlan": null,
                     "maxCardInstallmentPlan": null,
                     "useCardPoint": false,
                     "customerName": "이재은",
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

                     "amount": amount,
                     "orderId": "sample-" + orderId,
                     "orderName": "생블루베리 중과 1kg",
                     "successUrl": successUrl,
                     "failUrl": failUrl,
                     "customerName": "이재은",
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

                     "amount": amount,
                     "orderId": "sample-" + orderId,
                     "orderName": "생블루베리 중과 1kg",
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
