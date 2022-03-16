var Ajax = {};

Ajax.execute = function(url, data, successFunction, option) {
	option = option == null ? "POST" : "GET";
	$.ajax({
		type : option,
		async : true,
		url : url,
		data : JSON.stringify(data),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success : function(data) {
			successFunction(data);
		},
		error : function() {
			alert("데이터 처리중 에러가 발생하였습니다.");
			return false;
		}
	});
}
