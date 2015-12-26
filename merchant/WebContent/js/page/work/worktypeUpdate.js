jQuery(document).ready(function() {
	jQuery('#form1').validate({
		rules : {
			typeName : "required",
			workDes : "required"

		},
		messages : {
			typeName : "请填写类型名称",
			workDes : "请填写类型描述"
		}
	});

});

/**
 * 提交form表单
 */
var submit1 = function(callBack1) {
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/work/manage/updateWorkType.htm?',
			data : jQuery('#form1').serialize(),// 你的formid
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
				
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功", function() {
						callBack1();
					});

				}
			}
		});
	}
};