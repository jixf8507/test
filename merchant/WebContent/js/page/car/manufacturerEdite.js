jQuery(document).ready(function() {

	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'manufacturerName' : "required",
			'fullName' : "required",
			'address' : "required",
			'linkman' : "required",
			'linkphone' : {
				"required" : true,
				"phone" : true
			},
			'email' : {
				"required" : true,
				"email" : true
			}
		}
	});

	jQuery('#manufacturerName').change(function() {
		checkName(this);
	});

});

var checkName = function(thiz) {
	var manufacturerName = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/car/manufacturer/checkName.htm?",
		data : {
			manufacturerName : manufacturerName.val(),
			id : jQuery("#id").val()
		},
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：厂商名称已经存在");
				manufacturerName.attr("value", "");
			}
		},
		error : function() {
			Dialog.alert("错误：验证失败");
		}
	});
};

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/car/manufacturer/saveEdite.htm?',
			data : jQuery('#form1').serialize(),// 你的formid
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
