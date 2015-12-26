jQuery(document).ready(function() {

	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'typeName' : "required",
			'monthFee' : {
				"required" : true,
				"number" : true
			},
			'dayFee' : {
				"required" : true,
				"number" : true
			},
			'hourFee' : {
				"required" : true,
				"number" : true
			}
		}
	});

	
	jQuery('#typeName').change(function() {
		checkTypeName(this);
	});
	
});

//检查套餐名称是否存在
var checkTypeName = function(thiz) {
	var typeName = jQuery(thiz);
	jQuery.ajax({
		type : "post",
		url: contextPath + '/price/priceType/long/checkTypeNameOnly.htm?',
		data : "typeName=" + typeName.val(),
		async : true,
		success : function(data) {
			var map = eval('(' + data + ')');
			if (map.success) {

			} else {
				Dialog.alert("提示：该长租收费套餐名称已存在！请重新输入.");
				typeName.attr("value", "");
			}
		},
		error : function() {
			Dialog.alert("提示：验证失败");
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
			url : contextPath + '/price/priceType/long/saveAdd2.htm?',
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
