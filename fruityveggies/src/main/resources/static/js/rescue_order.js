/**
 * 
 */

function handleBoxClick(type) {
	
	
	
	
	
		const singleBoxB = document.querySelector('#singleBox');
		const familyBoxB = document.querySelector('#familyBox');
        const singleBox = document.getElementById('exampleRadios1');
        const familyBox = document.getElementById('exampleRadios2');
        const singleText = document.querySelector('.single-text');
        const familyText = document.querySelector('.family-text');
        

        if (type === 'single') {
			
            singleBox.checked = true;
            familyBox.checked = false;
            singleBox.parentElement.style.display = 'block'; // Show the element
        	familyBox.parentElement.style.display = 'none';
        	singleText.style.display = 'block';
        	familyText.style.display = 'none';
        	singleBoxB.style.border = 'solid';
			familyBoxB.style.border = 'none';
			
        } else if (type === 'family') {
			
            singleBox.checked = false;
            familyBox.checked = true;
            singleBox.parentElement.style.display = 'none'; // Hide the element
       		familyBox.parentElement.style.display = 'block'; // Show the element
       		singleText.style.display = 'none';
        	familyText.style.display = 'block';
        	singleBoxB.style.border = 'none';
			familyBoxB.style.border = 'solid';
        }
    }
    
    const orderRequest = document.getElementById("orderRequest");
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
    
document.addEventListener('DOMContentLoaded', () =>{
	

	        
	
	const back1 = document.querySelector('#back1');
	const back2 = document.querySelector('#back2');
	const back3 = document.querySelector('#back3');
	const back4 = document.querySelector('#back4');
	const button1 = document.querySelector('#buyBtn1');
	const button2 = document.querySelector('#buyBtn2');
	const button3 = document.querySelector('#buyBtn3');
	const card_body = document.querySelector('#card1');
	const card_body2 = document.querySelector('#card2');
	const card_body3 = document.querySelector('#card3');
	const card_body4 = document.querySelector('#card4');
	
	button1.addEventListener('click', (e)=>{
		
		e.preventDefault();
		
		card_body.style.display = 'none';
		card_body2.style.display = 'block';
		card_body3.style.display = 'none';
		card_body4.style.display = 'none';	
	});
	
	button2.addEventListener('click', (e)=>{
		
		e.preventDefault();
		
		card_body.style.display = 'none';
		card_body2.style.display = 'none';
		card_body3.style.display = 'block';
		card_body4.style.display = 'none';	
	});
	
	button3.addEventListener('click', (e)=>{
		
		e.preventDefault();
		
		card_body.style.display = 'none';
		card_body2.style.display = 'none';
		card_body3.style.display = 'none';
		card_body4.style.display = 'block';	
	});
	
	back1.addEventListener('click', (e)=>{
		
		window.location.href = "/main/regulardelivery";	
	});
	
	back2.addEventListener('click', (e)=>{
		
		e.preventDefault();
		
		card_body.style.display = 'block';
		card_body2.style.display = 'none';
		card_body3.style.display = 'none';
		card_body4.style.display = 'none';	
	});
	
	back3.addEventListener('click', (e)=>{
		
		e.preventDefault();
		
		card_body.style.display = 'none';
		card_body2.style.display = 'block';
		card_body3.style.display = 'none';
		card_body4.style.display = 'none';		
	});
	
	back4.addEventListener('click', (e)=>{
		
		e.preventDefault();
		
		card_body.style.display = 'none';
		card_body2.style.display = 'none';
		card_body3.style.display = 'block';
		card_body4.style.display = 'none';		
	});
	
	
	// "제외할 품목이 없어요" 체크박스 요소를 가져옵니다.
	var excludeCheckbox = document.getElementById("inlineCheckbox25");

	// 모든 체크박스 요소를 가져옵니다.
	var checkboxes = document.querySelectorAll(".form-check-input");

	// "제외할 품목이 없어요" 체크박스의 변경 이벤트 리스너를 등록합니다.
	excludeCheckbox.addEventListener("change", function() {
 	// "제외할 품목이 없어요" 체크박스가 선택되었다면
  	if (excludeCheckbox.checked) {
    // 모든 체크박스들을 반복하면서 "제외할 품목이 없어요" 체크박스가 아닌 체크박스들을 해제하고 비활성화합니다.
    for (var i = 0; i < checkboxes.length; i++) {
      if (checkboxes[i] !== excludeCheckbox) {
        checkboxes[i].checked = false;
        checkboxes[i].disabled = true;
      		}
    	}
  	} else {
    // "제외할 품목이 없어요" 체크박스가 선택되지 않았다면 모든 체크박스를 활성화 상태로 만듭니다.
    for (var i = 0; i < checkboxes.length; i++) {
      checkboxes[i].disabled = false;
    	}
 	 }
	});
	
	 const maxSelection = 3; // Maximum number of checkboxes to be selected

        // Function to update the selection count for a category
        function updateSelectionCount(category) {
            const checkboxes = document.querySelectorAll(`[name="${category}"]:checked`);
            const remainingCheckboxes = document.querySelectorAll(`[name="${category}"]:not(:checked)`);

            if (checkboxes.length >= maxSelection) {
                remainingCheckboxes.forEach((checkbox) => {
                    checkbox.disabled = true;
                });
            } else {
                remainingCheckboxes.forEach((checkbox) => {
                    checkbox.disabled = false;
                });
            }
        }

        // Add event listeners to the checkboxes
        const allCheckboxes = document.querySelectorAll('input[type="checkbox"]');
        allCheckboxes.forEach((checkbox) => {
            checkbox.addEventListener('change', () => {
                const category = checkbox.getAttribute('name');
                updateSelectionCount(category);
            });
        });
	
});
    
