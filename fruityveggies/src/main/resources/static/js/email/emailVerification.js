document.addEventListener('DOMContentLoaded', () => {
    const requestVerificationBtn = document.querySelector('button#requestVerificationBtn');
    const verificationInputGroup = document.querySelector('.verification-input-group');
    const emailConfirmBtn = document.querySelector('button#emailConfirmBtn');
    const passwordInput = document.querySelector('input#password');
    const confirmPasswordInput = document.querySelector('input#confirmPassword');
    const signupBtn = document.querySelector('#signupBtn');
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
                checkFormValidity();
                timerDisplay.style.display = 'none'; // 타이머 숨기기
            } else {
                toggleButtonState(false);
                emailConfirmed = false;
                checkFormValidity();
            }
        } catch (error) {
            console.error(error);
            alert('인증번호 확인에 실패하였습니다.');
        }
    });

    passwordInput.addEventListener('input', checkFormValidity);
    confirmPasswordInput.addEventListener('input', checkFormValidity);
	
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

    function enableSignupButton() {
        signupBtn.disabled = false;
    }

    function disableSignupButton() {
        signupBtn.disabled = true;
    }

    function checkFormValidity() {
        const passwordValue = passwordInput.value;
        const confirmPasswordValue = confirmPasswordInput.value;

        const passwordsMatch = passwordValue === confirmPasswordValue;
        const passwordIsValid = validatePassword(passwordValue);

        if (emailConfirmed && passwordsMatch && passwordIsValid) {
            enableSignupButton();
            formIsValid = true;
        } else {
            disableSignupButton();
            formIsValid = false;
        }
    }

    function validatePassword(password) {
        const charTypeCount = [
            /[A-Z]/,
            /[a-z]/,
            /\d/,
            /[!@#$%^&*()_+{}\[\]:;<>,.?~\-]/
        ].reduce((count, regex) => count + regex.test(password), 0);

        return password.length >= 6 && charTypeCount >= 2;
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
});
