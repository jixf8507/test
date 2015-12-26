jQuery(document).ready(function() {

	jQuery("#form1").validate({
		rules : {
			'yPassword' : "required",
			'password' : "required",
			'conPassword' : "required"
		}
	});
});

var submit = function(callBack) {
	if (jQuery("#form1").valid() && validate()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/system/login/updatePwd.htm?',
			data : {
				'ypassword' : jQuery('#yPassword').val(),
				'password' : jQuery('#password').val()
			},// 你的formid
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功");
					callBack();
				}else{
					Dialog.alert(data.info);
				}
			}
		});
	}
};

var validate = function() {
	if (jQuery('#yPassword').val() == '') {
		Dialog.alert('原密码不能为空');
		return false;
	}
	if (jQuery('#password').val() == '') {
		Dialog.alert('新密码不能为空');
		return false;
	}
	if (jQuery('#conPassword').val() == '') {
		Dialog.alert('请填写确认密码');
		return false;
	}
	if (jQuery('#conPassword').val() != jQuery('#password').val()) {
		Dialog.alert('确认密码不一致');
		return false;
	}
	return true ;
};