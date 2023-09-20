/**
 * 
 */
document.addEventListener('DOMContentLoaded', () => {

 // ------ 클라이언트 키로 객체 초기화 ------
    const clientKey = 'test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq';
    const tossPaymentsCardBtn = document.querySelector('#tossPayments_Card');
    const tossPayments_BankTransferBtn = document.querySelector('#tossPayments_BankTransfer');
    const tossPayments_VirtualAccountBtn = document.querySelector('#tossPayments_VirtualAccount');

tossPaymentsCardBtn.addEventListener('click', () => {
    
    const tossPayments_Card = TossPayments(clientKey);
    // ------ 결제창 띄우기 ------
    tossPayments_Card.requestPayment('카드', { // 결제수단 파라미터 (카드, 계좌이체, 가상계좌, 휴대폰 등)
  
      amount: 19000, // 결제 금액
      orderId: 'Io63ef89pAyGLgQOk0lEx', // 주문 ID(주문 ID는 상점에서 직접 만들어주세요.)
      orderName: '토스 티셔츠', // 주문명
      customerName: '김토스', // 구매자 이름
      successUrl: window.location.origin + "/freshmarket/success", // 결제 성공 시 이동할 페이지(이 주소는 예시입니다. 상점에서 직접 만들어주세요.)
      failUrl: window.location.origin + "/freshmarket/fail", // 결제 실패 시 이동할 페이지(이 주소는 예시입니다. 상점에서 직접 만들어주세요.)
    })
    .catch(function (error) {
      if (error.code === 'USER_CANCEL') {
        // 결제 고객이 결제창을 닫았을 때 에러 처리
      } else if (error.code === 'INVALID_CARD_COMPANY') {
        // 유효하지 않은 카드 코드에 대한 에러 처리
      }
    });
    });
    
    
    
    
    tossPayments_BankTransferBtn.addEventListener('click', () => {
        const tossPayments_BankTransfer = TossPayments(clientKey);
    // ------ 결제창 띄우기 ------
    tossPayments_BankTransfer.requestPayment('계좌이체', { // 결제수단 파라미터 (카드, 계좌이체, 가상계좌, 휴대폰 등)
  
      amount: 100, // 결제 금액
      orderId: 'Io63ef89pAyGLgQOk0lEx', // 주문 ID(주문 ID는 상점에서 직접 만들어주세요.)
      orderName: '테스트 결제', // 주문명
      customerName: '김토스', // 구매자 이름
      successUrl: 'https://docs.tosspayments.com/guides/payment/test-success', // 결제 성공 시 이동할 페이지(이 주소는 예시입니다. 상점에서 직접 만들어주세요.)
      failUrl: 'https://docs.tosspayments.com/guides/payment/test-fail', // 결제 실패 시 이동할 페이지(이 주소는 예시입니다. 상점에서 직접 만들어주세요.)
    })
    .catch(function (error) {
      if (error.code === 'USER_CANCEL') {
        // 결제 고객이 결제창을 닫았을 때 에러 처리
      } else if (error.code === 'INVALID_CARD_COMPANY') {
        // 유효하지 않은 카드 코드에 대한 에러 처리
      }
    });
    });
    
    
    
    
     
      tossPayments_VirtualAccountBtn.addEventListener('click', () => {
        const tossPayments_VirtualAccount = TossPayments(clientKey);
    // ------ 결제창 띄우기 ------
    tossPayments_VirtualAccount.requestPayment('가상계좌', { // 결제수단 파라미터 (카드, 계좌이체, 가상계좌, 휴대폰 등)
  
      amount: 100, // 결제 금액
      orderId: 'Io63ef89pAyGLgQOk0lEx', // 주문 ID(주문 ID는 상점에서 직접 만들어주세요.)
      orderName: '테스트 결제', // 주문명
      customerName: '김토스', // 구매자 이름
      successUrl: 'https://docs.tosspayments.com/guides/payment/test-success', // 결제 성공 시 이동할 페이지(이 주소는 예시입니다. 상점에서 직접 만들어주세요.)
      failUrl: 'https://docs.tosspayments.com/guides/payment/test-fail', // 결제 실패 시 이동할 페이지(이 주소는 예시입니다. 상점에서 직접 만들어주세요.)
    })
    .catch(function (error) {
      if (error.code === 'USER_CANCEL') {
        // 결제 고객이 결제창을 닫았을 때 에러 처리
      } else if (error.code === 'INVALID_CARD_COMPANY') {
        // 유효하지 않은 카드 코드에 대한 에러 처리
      }
    });
    });
    
    
    
});