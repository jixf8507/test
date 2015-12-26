jQuery(document).ready(function() {

	// 其他收费金额变化
	// jQuery('#otherCost').change(function() {
	// countCost();
	// });

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
			}
		}
	});

});

var couponCost = function() {
	// 现金优惠券金额变化（除维修费用外都减）
//	var maintenanceCost = Number(jQuery("#maintenanceCost").val());
	var totalCost = Number(jQuery("#hid_totalCost").val());
	var couponBalance = -Number(jQuery("#couponBalance").val());
//	totalCost = totalCost - maintenanceCost;
	if (totalCost > couponBalance) {
		jQuery("#totalCost").val(totalCost - couponBalance);
		jQuery("#couponCost").val(-couponBalance);
	} else {
		jQuery("#totalCost").val(0.00);
		jQuery("#couponCost").val(-totalCost);
	}
	// countCost();
};

var couponKmsCost = function() {
	jQuery("#hid_perKmsCost").attr("value", jQuery('#perKmsCost').val());
	// 里程优惠券金额变化（只减超出公里数收费）
	var totalCost = Number(jQuery("#hid_perKmsCost").val());
	var couponBalance = -Number(jQuery("#couponBalance").val());
	if (totalCost > couponBalance) {
		jQuery("#hid_perKmsCost").val(totalCost - couponBalance);
		jQuery("#couponCost").val(-couponBalance);
	} else {
		jQuery("#hid_perKmsCost").val(0.00);
		jQuery("#couponCost").val(-totalCost);
	}
	countCost();
};

var countCost = function() {
	var otherCost = jQuery("#otherCost").val();
	var useCost = jQuery("#useCost").val();
	var perKmsCost = jQuery("#hid_perKmsCost").val();
	var maintenanceCost = jQuery("#maintenanceCost").val();
	jQuery("#totalCost")
			.attr(
					"value",
					(Number(otherCost) + Number(useCost) + Number(perKmsCost) + Number(maintenanceCost)));
};

var submit = function(callBack) {
	var payment = jQuery("input[name='payment']:checked").val();
	var ticket = jQuery("#ticket").val();
	var totalCost = jQuery("#totalCost").val();
	if (payment == '余额付费' && !checkBalance()) {
		Dialog.alert("提示：余额不足，请先充值！");
		return;
	}
	if (payment != '余额付费' && ticket == '') {
		Dialog.alert("提示：请输入票据编号！");
		return;
	}
	if (jQuery("#form1").valid()) {
		Dialog.confirm('提示：确定' + payment + ' [ ' + totalCost + ' ] 元?',
				function() {
					jQuery.ajax({
						cache : true,
						type : "POST",
						url : contextPath + '/order/charge/saveEdite.htm?',
						data : jQuery('#form1').serialize(),
						async : false,
						dataType : 'json',
						error : function(request) {
							Dialog.alert("错误：支付失败");
						},
						success : function(data) {
							if (data.success) {
								Dialog.alert("提示：支付成功", function() {
									callBack();
								});
							} else {
								Dialog.alert("提示：" + data['info'], function() {
									callBack();
								});
							}
						}
					});
				});
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

// 获取优惠券列表
var getCoupons = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "租车优惠券选择";
	diag.URL = contextPath + "/page/merchant/couponWin.jsp";
	diag.MessageTitle = '选择租车优惠券';
	diag.Message = "";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBackSave);
		diag.close();
	};
	diag.show();
};

// 保存方法
var callBackSave = function(obj) {
	var arr = obj.split('_');
	if (jQuery('#orderType').val() == 2 && arr[3] == 2) {
		Dialog.alert("提示：长租暂只能使用现金优惠券");
		return;
	}
	if (arr == '' || arr.length == 1) {
		jQuery("#couponId").attr("value", '');
		// jQuery("#couponNo").attr("value", arr[1]);
		jQuery("#couponNo").attr("value", '');
		jQuery("#couponBalance").attr("value", '');
		jQuery("#couponType").attr("value", '');
		jQuery("#hid_perKmsCost").attr("value", jQuery('#perKmsCost').val());
	} else {
		jQuery("#couponId").attr("value", arr[0]);
		// jQuery("#couponNo").attr("value", arr[1]);
		jQuery("#couponNo").attr("value", 'ykzc-' + arr[0]);
		jQuery("#couponBalance").attr("value", '-' + arr[2]);
		jQuery("#couponType").attr("value", arr[3] == 1 ? "现金优惠券" : "里程优惠券");
	}
	// 现金优惠券
	if (arr[3] == 1 || arr.length == 1) {
		jQuery("#hid_perKmsCost").attr("value", jQuery('#perKmsCost').val());
		couponCost();
		// 里程优惠券
	} else {
		couponKmsCost();
	}
};