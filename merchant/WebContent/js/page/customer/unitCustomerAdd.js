jQuery(document).ready(function() {

	jQuery('#contactPhone').change(function() {
		checkPhone(this);
	});

	jQuery('#enterpriseName').change(function() {
		checkName(this);
	});

	jQuery("#form1").validate({
		rules : {
			'contactPhone' : {
				"required" : true,
				"phone" : true
			},
			'contactPerson' : {
				"required" : true,
				"maxlength" : 20
			},
			'enterpriseName' : {
				"required" : true,
				"maxlength" : 50
			},
			'address' : {
				"required" : true,
				"maxlength" : 150
			},
			'bankType' : {
				"required" : true,
				"maxlength" : 20
			},
			'bankCardNO' : {
				"required" : true,
				"maxlength" : 20
			},
		}
	});

});

// 检查手机号码是否已存在
var checkPhone = function(thiz) {
	var phone = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/customer/unit/checkPhone.htm?",
		data : "phone=" + phone.val(),
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

// 检测企业名称是否已存在
var checkName = function(thiz) {
	var name = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/customer/unit/checkName.htm?",
		data : "name=" + name.val(),
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：该企业名称已经存在");
				name.attr("value", "");
			}
		},
		error : function() {
			Dialog.error("提示：验证失败");
		}
	});
};

/**
 * 提交form表单
 */
var submit = function(callBackAdd) {
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/customer/unit/saveAdd.htm?',
			data : jQuery('#form1').serialize(),
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功", function() {
						callBackAdd();
					});

				}
			}
		});
	}
};
