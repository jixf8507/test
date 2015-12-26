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
		$('#usernameLable').html("请输入用户名！");
		return false;
	} else {
		$('#usernameLable').html("");
	}

	if ($('#password').val() == '') {
		$('#passwordLable').html("请输入用户密码！");
		return false;
	} else {
		$('#passwordLable').html("");
	}

	$.ajax({
		type : "POST",
		url : "system/login/validata.htm?",
		data : {
			'username' : $('#username').val(),
			'password' : $('#password').val()
		},
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				$('#passwordLable').html("正在进入系统。。。");
				location.href = data.info;
			} else {
				$('#passwordLable').html("用户名或密码不正确！");
			}
		},
		error : function() {
			alert("请求异常");
		}
	});
};


$(document).ready(function(){
	 var w=$(window).width();
	 var h =$(window).height();
	 var wc =(w-328)/2;
	 var hc =(h-250)/2;
	 
	
	 
	 $("#wx_show,#wx_show2").click( function () {
		 $(".mengb,body").css("width",w);
		 $(".mengb,body").css("height",h);
		 $(".mengb").show(); 
		 $(".wx_show_img").show(); 
		 $("body").css("overflow","hidden"); 
		 $(".wx_show_img").css("top",hc);
		 $(".wx_show_img").css("left",wc);

		 
	 });
	 $("#closa").click( function () {
		 $(".mengb").hide(); 
		 $(".wx_show_img").hide(); 
		 $("body").css("width","auto");
		 $("body").css("height","auto");
		 $("body").css("overflow","auto"); 
		 
	 });
});

