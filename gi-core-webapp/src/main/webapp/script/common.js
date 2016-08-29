$.ajaxSetup({
	beforeSend : function(xhr){
		if (sessionId) {
			xhr.setRequestHeader("x-auth-token", sessionId);
		}
	}
}); 
/**
 * 将序列化参数字符串转为json格式
 */
$.fn.serializeObject = function() {
	var obj = {};
	var count = 0;
	$.each(this.serializeArray(), function(i, o) {
		var n = o.name, v = o.value;
		count++;
		obj[n] = obj[n] === undefined ? v : $.isArray(obj[n]) ? obj[n].concat(v) : [ obj[n], v ];
	});
	obj.nameCounts = count + "";// 表单name个数
	return JSON.stringify(obj);
};

$.postJSON = function(url,data,success){
	$.ajax({
		  type: "POST",
		  url: url,
		  data: data,
		  success: success,
		  dataType: 'json',
		  contentType : "application/json; charset=UTF-8",
		});
};

