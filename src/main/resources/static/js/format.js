/**
 * input number에 숫자 길이 설정
 * @param event
 * @returns {boolean}
 */
function maxLengthCheck(object){
	if (object.value.length > object.maxLength){
		object.value = object.value.slice(0, object.maxLength);
	}
}
