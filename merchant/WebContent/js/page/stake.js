$(document).ready(function() {

	$('#btn').click(function() {
		submit();
	});

	document.onkeydown = function(e) {
		var ev = document.all ? window.event : e;
		if (ev.keyCode == 13) {
			submit();
		}
	};

});

var submit = function() {

	if ($('#username').val() == '') {
		alert("请输入用户名！");
		return false;
	}

	if ($('#password').val() == '') {
		alert("请输入用户密码！");
		return false;
	}
	$.ajax({
		type : "POST",
		url : "system/stakeLogin/validata.htm?",
		data : {
			'username' : $('#username').val(),
			'password' : $('#password').val()
		},
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				// $('#passwordLable').html("正在进入系统。。。");
				location.href = data.info;
			} else {
				alert("用户名或密码不正确！");
			}
		},
		error : function() {
			alert("请求异常");
		}
	});
};
