var Ajax = {};
var Func = {};

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
					successFunction(data);
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

Func.get = function(obj, key) {
	return obj == null ? undefined : obj[key];
}

Func.isCheck = function(val) {
	return val == null || typeof val == 'undefined' || val == '' ? true : false;
}
