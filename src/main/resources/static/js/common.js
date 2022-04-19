var Ajax = {};
var Func = {};

/**
 * 기본 Ajax 템플릿
 * 리턴 공통 처리 되어있음
 * @param url
 * @param data
 * @param successFunction
 * @param option
 */
Ajax.execute = function(url, data, successFunction, option) {
	option = option == null ? "GET" : option;
	$.ajax({
		type : option,
		async : true,
		url : url,
		data : JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success : function(data) {
			if (Func.get(data, "result")) {
				if (Func.get(data, "result").error) {
					alert(Func.get(data, "result").message)
				}
				else {
					successFunction(Func.get(data, "result"));
				}
			}
			else if (Func.get(data, "error")) {
				alert(Func.get(data, "error").message);
			}
			else {
				alert(ERROR_MSG);
			}
		},
		error : function(data) {
			alert(ERROR_MSG)
			return false;
		}
	});
}

/**
 * 빈값 여부 확인
 * form태그의 모든 태그를 반복문돌며 시작하며 빈값 확인 시 얼럿창 호출
 * not-required-value 클래스 포함 시 무시
 * data-empty-label 값으로 alert창 호출
 * @param form
 * @returns {boolean}
 */
Func.validationCheck = function(form) {
	for (const formElement of form[0]) {
		if (!$(formElement).hasClass('not-required-value')) {
			// 필수값이 아닐때만 값 확인
			if ($(formElement).val() == '') {
				alert($(formElement).data('emptyLabel') + ' 입력해주세요.')
				return false;
			}
		}
	}
	return true;
}

/**
 * 입력값이없으면 alert처리함
 * msg 입력 시 입력값이 없으면 msg 출력
 * @param value
 */
Func.isNotEmptyAlert = function(value, msg) {
	if (!Func.isCheck(value)) {
		if (typeof value == 'boolean') {
			if (value) {
				arguments.length >= 2 ? alert(msg) : alert(value);
			}
		}
		else if (typeof value == 'string') {
			arguments.length >= 2 ? alert(msg) : alert(value);
		}
		return true;
	}
	return false;
}

/**
 * AJAX 데이터 전송전 form태그를 입력받아 배열로 만들어서 리턴
 * @returns {{}}
 */
$.fn.getObject = function() {
	let retObject = {};
	for (const formElement of this[0]) {
		retObject[$(formElement).attr('id')] = $(formElement).val();
	}
	return retObject;
}

/**
 * 해당 값 가져오기 - null 에러가 발생되지않게 함수를 사용함
 * @param obj
 * @param key
 * @returns {undefined|*}
 */
Func.get = function(obj, key) {
	return obj == null ? undefined : obj[key];
}

/**
 * 해당값 유무 확인
 * @param val
 * @returns {boolean}
 */
Func.isCheck = function(val) {
	return val == null || typeof val == 'undefined' || val == '' ? true : false;
}

/**
 * Checkbox 설정
 * @param obj
 * @returns {*|jQuery}
 */
Func.isCheckBox = function(obj) {
	return $(obj).is(':checked');
}
Func.setCheckBox = function(obj, is) {
	$(obj).prop('checked', is);
}

/**
 * 로컬 스토리지 설정
 */
Func.setLocalStorage = function(key, val) {
	localStorage.setItem(key, val);
}
Func.delLocalStorage = function(key) {
	localStorage.removeItem(key);
}
Func.getLocalStorage = function(key) {
	return localStorage.getItem(key);
}

/**
 * 엔터키 여부
 * @param e
 * @returns {boolean}
 */
Func.pressEnterKey = function(e) {
	if (e.keyCode == 13) {
		return true;
	}
	return false;
}

/**
 * CapsLock on/off 여부
 * @param e
 */
Func.isCapsLock = function(e) {
	if (e.getModifierState("CapsLock")) {
		return 'Caps Lock이 켜져 있습니다.';
	}
	return '';
}
