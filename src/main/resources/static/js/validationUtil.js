const numberPattern = /[0-9]/g;
const charPattern = /[a-z]/ig;
const pwdPattern = /[`~!@@#$%^&*|₩₩₩'₩\";:₩/?]/gi;
const space = /\s/;
const retMsg = '8~16자리, 하나이상의 문자, 하나의 숫자 및 하나의 특수 문자를 사용해주세요.';

const emailPattern = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
const retEmailMsg = '정확한 이메일을 입력해주세요.';

// 8~16자리, 하나이상의 문자, 하나의 숫자 및 하나의 특수 문자 정규식
function isPasswordPattern(pwd) {
	return passwordPattern(pwd);
}

function isPasswordPatternCheck(pwd, check) {
	const retMsg = passwordPattern(check);
	if (pwd != '' && retMsg == '') {
		return pwd != check ? '패스워드가 일치하지 않습니다.' : '';
	}
	return retMsg;
}

function passwordPattern(pwd) {
	if (pwd === '') {
		return '';
	}
	if (pwd.length < 8 || pwd.length > 16) {
		return retMsg;
	}
	else if (pwd.search(space) != -1) {
		return retMsg;
	}
	else if(pwd.search(numberPattern) < 0 || pwd.search(charPattern) < 0 || pwd.search(pwdPattern) < 0 ){
		return retMsg;
	}
	return '';
}

function isEmailPattern(email) {
	if (email === '') {
		return '';
	}
	if (email.search(emailPattern) < 0) {
		return retEmailMsg;
	}
	return '';
}
