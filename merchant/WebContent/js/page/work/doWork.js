jQuery(document).ready(function() {

	jQuery('#workStatus').chosen();
	jQuery("#updated").datetimepicker();

	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'updated' : "required",
			'transactDes' : "required"
		}
	});

});

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		if (jQuery('#workStatus').val() == '') {
			Dialog.alert("提示：请选择工单状态");
			return;
		}
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/work/order/saveEdite.htm?',
			data : jQuery('#form1').serialize(),
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