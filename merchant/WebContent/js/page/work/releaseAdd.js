jQuery(document).ready(function() {

	jQuery('#urgency').chosen();

	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'workName' : "required",
			'workDes' : "required",
			'typeName' : "required"
		}
	});
});

var getWorkType = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "工单类型列表";
	diag.URL = contextPath + '/page/work/selectWorkType.jsp?',
			diag.MessageTitle = "选择工单类型";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
		diag.close();
	};
	diag.show();
};

// 将数据回写
var callBack = function(obj) {
	var arr = obj.split('_');
	jQuery("#userId").attr("value", arr[0]);
	jQuery("#typeName").attr("value", arr[1]);
	jQuery("#typeDes").attr("value", arr[2]);
	jQuery("#userName").attr("value", arr[3]);
	jQuery("#mobilePhone").attr("value", arr[4]);
};

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		if (jQuery('#urgency').val() == '') {
			Dialog.alert("提示：请选择紧急程度");
			return;
		}
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/work/order/saveAdd.htm?',
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