$(document).ready(
		function() {

			var w = $(window).width();
			var h = $(window).height();

			var wa = (w - 399) / 2;
			var ha = (h - 550) / 2;

			$(".login").css("margin-left", wa);
			$(".login").css("margin-top", ha);

			$(".login_form ul li:first").css("margin-top", "0px");
			$(".login_in span:first").css("margin-left", "80px");

			$("#login_enter").mouseout(
					function() {
						$("#login_enter").css("background",
								"url(img/e1.png) no-repeat");
					});
			$("#login_enter").mouseenter(
					function() {
						$("#login_enter").css("background",
								"url(img/e2.png) no-repeat");
					});

			$("#login_enter").mousedown(
					function() {
						$("#login_enter").css("background",
								"url(img/e2.png) no-repeat");
					});

			$("#login_enter").mouseup(
					function() {
						$("#login_enter").css("background",
								"url(img/e1.png) no-repeat");
					});

			$("#myform").mouseenter(function() {
				$("#myform").css("background", "url(img/q2.png) no-repeat");
			});
			
			$("#myform").mouseout(function() {
				$("#myform").css("background", "url(img/q1.png) no-repeat");
			});

			$("#myform").mousedown(function() {
				$("#myform").css("background", "url(img/q2.png) no-repeat");
			});

			$("#myform").mouseup(function() {
				$("#myform").css("background", "url(img/q1.png) no-repeat");
			});

			$("#myform").click(
					function() {
						$(".login_form").find(":input").not(
								":button,:submit,:reset,:hidden").val("")
								.removeAttr("checked").removeAttr("selected");

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
		return;
	}

	if ($('#password').val() == '') {
		alert("请输入用户密码！");
		return;
	}
	$.ajax({
		type : "POST",
		url : "../system/stakeLogin/validata.htm?",
		data : {
			'username' : $('#username').val(),
			'password' : $('#password').val()
		},
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				// $('#passwordLable').html("正在进入系统。。。");
				location.href = '../' + data.info;
			} else {
				alert("用户名或密码不正确！");
			}
		},
		error : function() {
			alert("请求异常");
		}
	});
};