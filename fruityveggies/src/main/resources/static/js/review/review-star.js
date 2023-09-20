/**
 *  review-star.js
 */

document.addEventListener('DOMContentLoaded', () => {

	let star_score = 0;

	function Rating() { };
	Rating.prototype.rate = 0;
	Rating.prototype.setRate = function(newrate) {


		//별점 마킹 - 클릭한 별 이하 모든 별 체크 처리
		this.rate = newrate;
		let items = document.querySelectorAll('.rate_radio');
		items.forEach(function(item, idx) {
			if (idx < newrate) {
				item.checked = true;
				star_score = item.value;
			} else {
				item.checked = false;
			}
		});

		console.log(star_score);
	};
	let rating = new Rating();//별점 인스턴스 생성

	document.querySelector('.rating').addEventListener('click', function(e) {
		let elem = e.target;
		if (elem.classList.contains('rate_radio')) {
			rating.setRate(parseInt(elem.value));
			/*star_score = elem.value; // 별점 값을 star_score에 할당*/
		}
	});

	const reviewForm = document.querySelector('#reviewForm');

	// 삭제 버튼을 찾아서 이벤트 리스너를 등록.
	const btnCancel = document.querySelector('#btnCancel');
	btnCancel.addEventListener('click', () => {
		const check = confirm('취소하시면 메인페이지로 넘어갑니다. 취소하시겠습니까?');
		if (check) {
			location.href = '/main/mission';
		}
	});

	// 저장 버튼을 찾아서 이벤트 리스너를 등록.
	const btnSave = document.querySelector('#btnSave');
	btnSave.addEventListener('click', () => {
		/*const star_score = document.querySelector('input[name="rating"]:checked').value;*/
		const content = document.querySelector('textarea#content').value;
		console.log('star_score = ' + star_score);
		console.log('content = ' + content);
		console.log('reviewForm = ' + reviewForm);
		if (content === '') {
			alert('별점과 내용을 반드시 입력하세요.');
			return;
		}
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
   const itemId = document.querySelector('#itemId');
   itemId.value = idValue;
// 쿼리스트링 id값 알아내기 끝
		reviewForm.append('star_score', star_score); //star_score.title-> star_score = item.value로 받으니까 
		const check = confirm('변경 내용을 저장할까요?');
		if (check) {
			reviewForm.action = '/recipe/review';
			reviewForm.method = 'post';
			reviewForm.submit();
		}


	});
	
	//임시저장

		const formData = new FormData(); // 폼 데이터 객체 생성

		// 별점 데이터 추가
		const starScore = document.querySelector('input[name="rating"]:checked').value;
		formData.append('star_score', starScore);
		console.log('star_score', starScore);

		//내용 데이터 추가
		const content = document.querySelector('textarea#content').value;
		formData.append('content', content);
		console.log('Content', content);



	});

	//별점 데이터 value
	function getCheckboxValue() {
		const query = 'input[name = "rating"]:checked';
		const selectedEls = document.querySelectorAll(query);
		console.log(selectedEls);
		let result = '';
		selectedEls.forEach((el) => {
			result += el.value + '';
		});

		document.getElementById('result').innerText = result;
		console.log(result);

	};


//상품평 작성 글자수 초과 체크 이벤트 리스너
//document.querySelector('.ReviewEditor textarea').addEventListener('keydown', function() {
	//리뷰 1000자 초과 안되게 자동 자름
	//let review = document.querySelector('.ReviewEditor textarea');
	//let lengthCheckEx = /^.{1000,}$/;
	//if (lengthCheckEx.test(review.value)) {
		//1000자 초과 컷
		//review.value = review.value.substr(0, 1000);
	//}
//});

/*//저장 전송전 필드 체크 이벤트 리스너
	document.querySelector('#btnSave').addEventListener('click', function(e){
		//별점 선택 안했으면 메시지 표시
		if(rating.rate == 0){
			rating.showMessage('rate');
			return false;
		}
 });*/





