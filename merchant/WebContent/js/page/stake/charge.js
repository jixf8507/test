jQuery(document).ready(function() {

//	jQuery("#form1").validate({
//		rules : {
//			'areaCode' : "required",
//			'areaName' : "required",
//			'siteCode' : "required"
//		}
//	});

});

var submit = function(callBack) {
	if (jQuery('#Kind').val() != '刷卡充电') {
		Dialog.alert("提示：暂只支持刷卡充电方式的结算");
		return;
	}
	if (jQuery('#cost').val()>jQuery('#balance').val()) {
		Dialog.alert("提示：余额不足，请先充值");
		return;
	}
	Dialog.confirm("警告：确认结算该充电订单？", function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/stake/rechargeCost/chargeOrder.htm?",
			data : "id=" + jQuery('#id').val(),
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功！", function() {
						callBack();
					});
				} else {
					Dialog.alert("提示：" + data.info);
				}
			},
			error : function() {
				Dialog.alert("提示：操作失败！");
			}
		});
	});

};
