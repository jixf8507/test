jQuery(document).ready(function() {

	jQuery("#sex").chosen();

	jQuery('#phone').change(function() {
		checkPhone(this);
	});

	jQuery('#cardNO').change(function() {
		checkCardNO(this);
	});
	
	jQuery('#idCard').change(function() {
		checkIdCard(this);
	});

	jQuery('#idCardImgFile').change(function() {
		uploadIdCardImg(this);
	});

	jQuery("#form1").validate({
		rules : {
			'phone' : {
				"required" : true,
				"phone" : true
			},
			'name' : "required",
			'verCode' : "required",
			'idCard' : {
				"required" : true,
				"idcard" : true
			},
			'sex' : "required",
			'address' : "required",
			'accountPO.cardNO' : "required",
			'accountPO.moneyOfassure' : {
				"required" : true,
				"number" : true
			}
		}
	});

});

// 检查手机号码是否已存在
var checkPhone = function(thiz) {
	var phone = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/customer/customer/checkPhone.htm?",
		data : {
			phone : phone.val(),
			id : jQuery("#id").val()
		},
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				
			} else {
				Dialog.alert("提示：该手机号码已经存在");
				phone.attr("value", "");
			}
		},
		error : function() {
			Dialog.error("提示：验证失败");
		}
	});
};

// 检测租赁卡号是否已存在
var checkCardNO = function(thiz) {
	var cardNO = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/customer/customer/checkCardNO.htm?",
		data : {
			cardNO : cardNO.val(),
			id : jQuery("#accountId").val()
		},
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：该租赁卡号已经存在");
				cardNO.attr("value", "");
			}
		},
		error : function() {
			Dialog.error("提示：验证失败");
		}
	});
};

// 检测身份证是否已存在
var checkIdCard = function(thiz) {
	var idCard = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/customer/customer/checkIdCard.htm?",
		data : {
			idCard : idCard.val(),
			id : jQuery("#id").val()
		},
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {
				
			} else {
				Dialog.alert("提示：该身份证号码已经存在");
				idCard.attr("value", "");
			}
		},
		error : function() {
			Dialog.error("提示：验证失败");
		}
	});
};

// 上传用户身份证照片
var uploadIdCardImg = function(thiz) {
	var filepath = jQuery(thiz).val();
	var extStart = filepath.lastIndexOf(".");
	var ext = filepath.substring(extStart, filepath.length).toUpperCase();
	if (filepath != "") {
		if (ext != ".PNG" && ext != ".JPG") {
			Dialog.alert("提示：只能上传png,jpg图片");
			jQuery(thiz).outerHTML = jQuery(thiz).outerHTML;
			return;
		}
		jQuery.ajaxFileUpload({
			url : contextPath + '/system/upload/imageUpload.htm?',
			secureuri : false,
			fileElementId : 'idCardImgFile',
			dataType : 'json',
			async : true,
			beforeSend : function() {

			},
			success : function(data, status) {
				jQuery("#idCardImg").attr("src", contextPath + data.data);
				jQuery("#idCardImgHidden").attr("value", data.data);
				jQuery("#driveCardImgHidden").attr("value", data.data);
			},
			error : function() {
				alert(e);
			}
		});
	} else {
		Dialog.alert("提示：请选择图片");
	}
};

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/customer/customer/saveEdite.htm?',
			data : jQuery('#form1').serialize(), 
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功",function(){
						callBack();
					});
					
				}
			}
		});
	}
};

var imgError = function(thiz) {
	thiz.src = contextPath + '/img/car.png';
};