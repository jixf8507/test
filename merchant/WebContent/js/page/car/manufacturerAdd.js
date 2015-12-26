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
		data : "manufacturerName=" + manufacturerName.val(),
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
