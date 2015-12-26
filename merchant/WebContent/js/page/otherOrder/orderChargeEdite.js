jQuery(document).ready(function() {
	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'useCost' : "required",
			'otherCost' : {
				"required" : true,
				"number" : true
			},
			'totalCost' : {
				"required" : true,
				"number" : true
			},
			'ticket' : {
				"required" : true,
				"maxlength" : 100
			}
		}
	});
});

var submit = function(callBack) {
//	var payment = jQuery("input[name='payment']:checked").val();
//	var totalCost = jQuery("#totalCost").val();
//	if (payment == '余额付费' && !checkBalance()) {
//		Dialog.alert("提示：余额不足，请先充值！");
//		return;
//	}
	if (jQuery("#form1").valid()) {
//		Dialog.confirm('提示：确定' + payment + ' [ ' + totalCost + ' ] 元?',
//				function() {
					jQuery.ajax({
						cache : true,
						type : "POST",
						url : contextPath + '/order/charge/saveOtherEdite.htm?',
						data : jQuery('#form1').serialize(),
						async : false,
						dataType : 'json',
						error : function(request) {
							Dialog.alert("错误：操作失败");
						},
						success : function(data) {
							if (data.success) {
								Dialog.alert("提示：操作成功", function() {
									callBack();
								});
							} else {
								Dialog.alert("提示：" + data['info'], function() {
									callBack();
								});
							}
						}
					});
//				});
	}
};

/*
 * var cashPaySubmit = function(callBack) { if (jQuery("#form1").valid()) {
 * jQuery.ajax({ cache : true, type : "POST", url : contextPath +
 * '/order/charge/saveCashPay.htm?', data : jQuery('#form1').serialize(), async :
 * false, dataType : 'json', error : function(request) {
 * Dialog.alert("错误：支付失败"); }, success : function(data) { if (data.success) {
 * Dialog.alert("提示：支付成功",function(){ callBack(); }); } else {
 * Dialog.alert("提示：" + data['info'],function(){ callBack(); }); } } }); } else {
 * Dialog.alert("提示：内容填写不完整"); } };
 */

/**
 * 判断余额是否足够
 */
var checkBalance = function() {
	var balance = jQuery("#balance").val();
	var totalCost = jQuery("#totalCost").val();
	if (Number(balance) < Number(totalCost)) {
		return false;
	}
	return true;
};

// 保存方法
var callBackSave = function(obj) {
	var arr = obj.split('_');
	if (arr == '' || arr.length == 0) {
		jQuery("#couponId").attr("value", '');
		// jQuery("#couponNo").attr("value", arr[1]);
		jQuery("#couponNo").attr("value", '');
		jQuery("#couponBalance").attr("value", '');
	} else {
		jQuery("#couponId").attr("value", arr[0]);
		// jQuery("#couponNo").attr("value", arr[1]);
		jQuery("#couponNo").attr("value", 'ykzc-' + arr[0]);
		jQuery("#couponBalance").attr("value", arr[2]);
	}
	couponCost();
};