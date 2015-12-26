jQuery(document).ready(function() {
	
	jQuery('#created').datetimepicker();
	jQuery('#form1').validate({
		rules : {
			workName : "required",
			workDes : "required",
			transactUser:"required"

		},
		messages : {
			workName : "请填写工单名称",
			workDes : "请填写工单描述描述",
			transactUser:"请填写发布人"

		}
	});

});

/**
 * 提交form表单
 */
var submit1 = function(callBack) {
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/work/order/updateWorkOrder.htm?',
			data : jQuery('#form1').serialize(),// 你的formid
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功", function() {
						callBack();
					});

				}
			}
		});
	}
};