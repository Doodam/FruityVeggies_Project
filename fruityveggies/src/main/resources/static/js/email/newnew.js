 document.addEventListener('DOMContentLoaded', ()=> {
    
    const formSelect = document.querySelector('#form-select');
    const action = document.querySelector('#action');
    const selectedOptionsSet = new Set();
    console.log('selectedOptionsSet : '+selectedOptionsSet);
   /* const price = document.querySelector('dd.price');//3000원
    const priceText = price.textContent;
    let numbersOnly = priceText.replace(/\D/g, '');*/
    
    const price1 = document.querySelector('.result_price');
    let totalAmount = 0;// 최종 값

            formSelect.addEventListener('change', (e) => {
                
                const selectedOption_c = e.target.options[e.target.selectedIndex];
            console.log('선택된 옵션:', selectedOption_c.value, selectedOption_c.text);
            console.log('0:', e.target.options[0].value);
            console.log('1:', e.target.options[1].value);
            console.log('2:', e.target.options[2].value);
            console.log('3:', e.target.options[3].value);
        
            console.log('0:', e.target.options[0].text);
            console.log('1:', e.target.options[1].text);
            console.log('2:', e.target.options[2].text);
            console.log('3:', e.target.options[3].text);
            
                const selectedOption = e.target.value;
                const selectedOption_text = e.target[e.target.selectedIndex].textContent;
                
                console.log('vdfvdvd:'+selectedOption)
                const optionText = `못난이파프리카_${selectedOption_text}`;//selectedOption = 1kg, 2kg, 5kg
                
                if (! (selectedOptionsSet.has(optionText) || selectedOption =='옵션을 선택해주세요')) {// 없는 옵션을 선택하면 아래실행()
                    selectedOptionsSet.add(optionText);
                 
                    const div = document.createElement("div");
                    div.innerHTML = `<p>${optionText}</p>
                                <button type="button" class="minus btn" id="minus_${optionText}" >-</button>
                                <input type="number" class="numBox" id="numBox_${optionText}" min="1" max="10" value="1"  kl="${optionText}" readonly="readonly" />
                                <button type="button" class="plus btn" id="plus_${optionText}">+</button>
                                <label id="numbers_${optionText}">${selectedOption}원</label>
                                <p></p>`;
                    action.appendChild(div);
                    
                    
                  const plus = document.querySelector(`#plus_${optionText}`);
               const minus = document.querySelector(`#minus_${optionText}`);// 갯수
                 const numBox = document.querySelector(`#numBox_${optionText}`);
                 
                 const price_label = document.querySelector(`#numbers_${optionText}`);// 한 옵션 합산 가격
           
                  console.log('testtesttesttesttesttest1 : '+Number(price_label.textContent.replace(/\D/g, '')));
            
           plus.addEventListener('click', () =>{
            const num = numBox.value;
                const plusNum = Number(num) + 1;
                console.log('number'+plusNum);
          
               if(plusNum >= 10) {
                        numBox.value = 10;
                        price_label.innerHTML = selectedOption*10;           
               } else {
                        numBox.value = plusNum;
                        price_label.innerHTML = selectedOption*plusNum;
               }
               
               totalAmount += Number(price_label.innerHTML.replace(/\D/g, ''));
               // 1번라벨, 2번라벨, 3번 라벨
               // 1번 +, - 를 누르면 3000+
               // 2번 +, - 를 누르면 6000+
               // 3번 +, - 를 누르면 9000+
               
               console.log(totalAmount);
               console.log('testtesttesttesttesttest2 : '+Number(price_label.textContent.replace(/\D/g, '')));
               totalAmount = Number(document.querySelector(`#numbers_${optionText}`).textContent.replace(/\D/g, ''));
               //numbers_못난이파프리카_1kg
               /*const oa1 = 'numbers_못난이파프리카_'+e.target[formSelect.options.length].textContent;*/
               console.log('formSelect.options.length:'+formSelect.options.length);
               
               
               
               console.log('asdfasdfasdfasdf:'+'numbers_못난이파프리카_'+e.target[formSelect.options.length-1].textContent);
               
               price1.innerHTML = totalAmount;
               
             });
  
           minus.addEventListener('click', () =>{
              var num = numBox.value;
              var minusNum = Number(num) - 1;
   
              if(minusNum <= 1) {
                    numBox.value = 1;
                    price_label.innerHTML = selectedOption*1; // Update label 
               } else {
                   numBox.value = minusNum;
                    price_label.innerHTML = selectedOption*minusNum; // Update label       
              }
              
            totalAmount -=Number(price_label.innerHTML.replace(/\D/g, '')); 
            
            console.log(totalAmount);
            console.log('testtesttesttesttesttest3 : '+Number(price_label.textContent.replace(/\D/g, '')));
            price1.innerHTML = totalAmount;
          });
          
          
          /*totalAmount += numbersOnly * 1;*/
            /*htmlStr = `<p class="result_price">${totalAmount}원</p>`;
            action1.innerHTML = htmlStr; */ 
               
                }
    });
    
          
 });