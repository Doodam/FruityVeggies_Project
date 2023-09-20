document.addEventListener('DOMContentLoaded', () => {
  const requestVerificationBtn = document.querySelector('button#requestVerificationBtn');
  const verificationInputGroup = document.querySelector('.verification-input-group');
  const emailConfirmBtn = document.querySelector('button#emailConfirmBtn');
  const emailInput = document.querySelector('input#email');
  const usernameInput = document.querySelector('input#username'); // 아이디 입력란
  const timerDisplay = document.querySelector('#timerDisplay');
	
	const newPasswordInput = document.querySelector('#newPassword');
	const confirmPasswordInput = document.querySelector('#confirmNewPassword');
	
	const confirmButton = document.getElementById('confirmButton');
	
	confirmButton.addEventListener('click', async () => {
		
		if( !(newPasswordInput.value == confirmPasswordInput.value) ) {
			alert('비밀번호가 일치하지 않습니다.');
			return;
		}
		
		const password = newPasswordInput.value;
		const email = emailInput.value;
		
		const data = new URLSearchParams();
		
			data.append('password', password);
			data.append('email', email);
			const reqUrl = '/email/chpw';

		try {
			const response = await axios.post(reqUrl, data);
			console.log('response:', response.data);
			
			window.location.href= "/login"
		} catch (error) {
			console.error(error);
			alert('인증번호 요청에 실패하였습니다.');
		}
		
		
		
	});
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
				timerDisplay.style.display = 'none';

				newPasswordGroup.style.display = 'block';
				confirmPasswordGroup.style.display = 'block';
				confirmButtonGroup.style.display = 'block'; // 확인 버튼 표시

				// emailLabel 숨김
				const emailLabel = document.getElementById('emailLabel');
				emailLabel.style.display = 'none';

				// 기존 이메일 입력 폼과 인증번호 입력 폼 숨김
				emailInput.style.display = 'none';
				document.querySelector('input#verificationCode').style.display = 'none';

				requestVerificationBtn.style.display = 'none';
				emailConfirmBtn.style.display = 'none';
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

			minutes = minutes < 10 ? '0' + minutes : minutes;
			seconds = seconds < 10 ? '0' + seconds : seconds;

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
});
