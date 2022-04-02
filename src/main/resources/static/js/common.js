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
				successFunction(data);
			}
			else {
				alert(ERROR_MSG);
			}
		},
		error : function() {
			alert(ERROR_MSG)
			return false;
		}
	});
}

Func.get = function(obj, key) {
	return obj == null ? undefined : obj[key];
}
