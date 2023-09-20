document.addEventListener('DOMContentLoaded', () => {
	const requestVerificationBtn = document.querySelector('button#requestVerificationBtn');
	const verificationInputGroup = document.querySelector('.verification-input-group');
	const emailConfirmBtn = document.querySelector('button#emailConfirmBtn');
	const emailInput = document.querySelector('input#email');
	const timerDisplay = document.querySelector('#timerDisplay');

	let emailConfirmed = false;
	let formIsValid = false;
	let timerInterval;

	requestVerificationBtn.addEventListener('click', async () => {
		const email = emailInput.value;

		if (email === '') {
			alert('이메일을 입력하세요.');
			return;
		}

		const data = { email };
		const reqUrl = '/email/verify';

		try {
			const response = await axios.post(reqUrl, data);
			console.log('response:', response.data);
			toggleRequestButtonState(true);

			verificationInputGroup.style.display = 'block';
			startTimer(100);
		} catch (error) {
			console.error(error);
			alert('인증번호 요청에 실패하였습니다.');
		}
	});

	emailConfirmBtn.addEventListener('click', async () => {
		const verificationCode = document.querySelector('input#verificationCode').value;

		if (verificationCode === '') {
			alert('인증번호를 입력하세요.');
			return;
		}

		const data = { verificationCode };
		const reqUrl = '/email/confirm';

		try {
			const response = await axios.post(reqUrl, data);
			console.log('response2:', response.data);

			if (response.data.confirmMessage === 'success') {
				toggleButtonState(true);
				emailConfirmed = true;
				timerDisplay.style.display = 'none'; // 타이머 숨기기
				activateFindIdButton(); // 아이디 찾기 버튼 활성화
			} else {
				toggleButtonState(false);
				emailConfirmed = false;
			}
		} catch (error) {
			console.error(error);
			alert('인증번호 확인에 실패하였습니다.');
		}
	});

	function toggleRequestButtonState(isSuccess) {
		const requestVerificationBtn = document.querySelector('button#requestVerificationBtn');

		if (isSuccess) {
			requestVerificationBtn.style.backgroundColor = 'green';
			requestVerificationBtn.innerHTML = '요청성공';
			requestVerificationBtn.disabled = true;
		} else {
			requestVerificationBtn.disabled = false;
		}
	}

	function toggleButtonState(isSuccess) {
		const emailConfirmBtn = document.querySelector('button#emailConfirmBtn');

		if (isSuccess) {
			emailConfirmBtn.style.backgroundColor = 'green';
			emailConfirmBtn.innerHTML = '인증 완료';
		} else {
			emailConfirmBtn.style.backgroundColor = 'red';
			emailConfirmBtn.innerHTML = '인증 실패';
		}
	}

	function startTimer(duration) {
		let timer = duration, minutes, seconds;

		clearInterval(timerInterval);
		timerInterval = setInterval(function() {
			minutes = parseInt(timer / 60, 10);
			seconds = parseInt(timer % 60, 10);

			minutes = minutes < 10 ? "0" + minutes : minutes;
			seconds = seconds < 10 ? "0" + seconds : seconds;

			timerDisplay.textContent = `${minutes}:${seconds}`;

			if (--timer < 0) {
				clearInterval(timerInterval);
				timerDisplay.textContent = '';
				toggleRequestButtonState(false);
				resetRequestButton();
			}
		}, 1000);
	}

	function resetRequestButton() {
		const requestVerificationBtn = document.querySelector('button#requestVerificationBtn');
		requestVerificationBtn.style.backgroundColor = '';
		requestVerificationBtn.innerHTML = '인증번호 요청';
		requestVerificationBtn.disabled = false;
	}

	function activateFindIdButton() {
		const findIdButton = document.createElement('button');
		findIdButton.textContent = '아이디 찾기';
		findIdButton.className = 'btn btn-primary';
		findIdButton.style.display = 'block';
		findIdButton.style.margin = '0 auto';
		findIdButton.disabled = false;

		findIdButton.addEventListener('click', async () => {
			const email = emailInput.value;
			const data = new URLSearchParams();
			data.append('email', email);

			const reqUrl = '/findid/findbyemail';
			try {
				const findIdResponse = await axios.post(reqUrl, data);
				const foundUsername = findIdResponse.data;

				// 아이디를 찾았을 때 모달 창에 결과 표시
				const idConfirmModalContent = document.getElementById('idConfirmModalContent');
				idConfirmModalContent.innerHTML = `회원님의 아이디는: <strong>${foundUsername}</strong> 입니다.`;

				// 모달 창 열기
				const idFoundModal = document.getElementById('idFoundModal');
				idFoundModal.classList.add('show');
				idFoundModal.style.display = 'block';
				document.body.classList.add('modal-open');


			} catch (error) {
				console.error(error);
				alert('아이디를 찾을 수 없습니다.');
			}
		});

		// 로그인 버튼 클릭 이벤트 처리
		const loginButton = document.getElementById('loginButton');
		loginButton.addEventListener('click', (e) => {
			// 원하는 주소로 리다이렉트
			e.preventDefault();
			window.location.href = '/login';
		});

		// 모달 닫기
		function closeModal() {
			const idFoundModal = document.getElementById('idFoundModal');
			idFoundModal.classList.remove('show');
			idFoundModal.style.display = 'none';
			document.body.classList.remove('modal-open');
		}

		// 모달 닫기 버튼 클릭 이벤트 처리
		const closeModalButton = document.querySelector('.modal .close');
		if (closeModalButton) {
			closeModalButton.addEventListener('click', closeModal);
		}

		// 모달 외부 클릭으로 닫기
		const modalBackdrop = document.querySelector('.modal-backdrop');
		if (modalBackdrop) {
			modalBackdrop.addEventListener('click', closeModal);
		}



		const container = document.querySelector('.card-body');
		container.appendChild(findIdButton);
	}


});


