jQuery(document).ready(function() {
	
	jQuery('.chzn-select').chosen();

	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'cardID' : {
				"required" : true,
				"maxlength" : 8,
				"minlength" : 8
			},
			'cardNumber' : {
				"required" : true,
				"maxlength" : 20,
			},
			'phone' : "required",
		}
	});

	jQuery('#nzkh').change(function() {
		checkCardID(this);
	});

	jQuery('#cardNumber').change(function() {
		checkCardNumber(this);
	});
});

// 获取内置卡号列表
var getCardID = function() {
	if (window.ActiveXObject) {
		var diag = new Dialog();
		diag.Width = 800;
		diag.Height = 500;
		diag.Title = "内置卡号选择";
		diag.URL = contextPath + "/page/stake/readCard.jsp?";
		diag.MessageTitle = '选择内置卡号';
		diag.Message = "";
		diag.OKEvent = function() {
			diag.innerFrame.contentWindow.submit(callBackGetCardID);
			diag.close();
		};
		diag.show();
	} else {
		Dialog.alert("提示：请使用IE浏览器访问");
	}
};

// 获取内置卡号返回方法
var callBackGetCardID = function(obj) {
	jQuery('#cardID').val(obj);

};

// 检查内置卡号是否重复
var checkCardID = function(thiz) {
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/stake/card/checkCardID.htm?",
		data : "cardID=" + jQuery('#nzkh').val() + "&id="
				+ jQuery('#id').val(),
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：内置卡号已重复,请重新填写！");
				jQuery('#nzkh').val("");
			}
		},
		error : function() {
			Dialog.alert("提示：验证失败");
		}
	});
};

// 检查外置卡号是否重复
var checkCardNumber = function(thiz) {
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/stake/card/checkCardNumber.htm?",
		data : "cardNumber=" + jQuery('#cardNumber').val() + "&id="
				+ jQuery('#id').val(),
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：外置卡号已重复,请重新填写！");
				jQuery('#cardNumber').val("");
			}
		},
		error : function() {
			Dialog.alert("提示：验证失败");
		}
	});
};

// 获取订单列表
var getCustomer = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "身份证号码选择";
	diag.URL = contextPath + "/page/customer/customerWin.jsp?";
	diag.MessageTitle = '选择身份证号码';
	diag.Message = "";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBackGetCustomer);
		diag.close();
	};
	diag.show();
};

// 获取客户信息返回方法
var callBackGetCustomer = function(obj) {
	var arr = obj.split('_');
	jQuery("#customerId").attr("value", arr[1]);
	jQuery("#name").attr("value", arr[4]);
	jQuery("#phone").attr("value", arr[5]);
	// jQuery("#cardNumber").attr("value", arr[2]);
};

// 打开选择安装车辆
var openSelectCarWin = function() {
	var diag = new Dialog();
	diag.Width = 700;
	diag.Height = 500;
	diag.Title = "设备安装车辆选择";
	diag.URL = contextPath + "/page/car/selectCar.jsp?";
	diag.MessageTitle = '';
	diag.Message = "车辆选择";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
		diag.close();
	};// 点击确定后调用的方法
	diag.show();
};

// 打开选择安装车辆
var openSelectCustomerCarWin = function() {
	var customerId = jQuery("#customerId").val();
	if (customerId == '') {
		Dialog.alert("提示：请先选择客户");
		return null;
	}
	var diag = new Dialog();
	diag.Width = 700;
	diag.Height = 500;
	diag.Title = "设备安装客户车辆选择";
	diag.URL = contextPath + "/page/customer/selectCustomerCar.jsp?customerId="
			+ customerId;
	diag.MessageTitle = '客户：' + jQuery("#name").val();
	diag.Message = "客户车辆选择";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
		diag.close();
	};// 点击确定后调用的方法
	diag.show();
};

// 保存方法
var callBack = function(data) {
	jQuery("#carId").val(data['carNumber']);
	jQuery("#carTableName").val(data['carTableName']);
};

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery('#phone').val() == '') {
		Dialog.alert("提示：请选择客户手机号码");
		return false;
	}
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/stake/card/saveEdite.htm?',
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

				}else{
					Dialog.alert("提示：操作失败  "+ data.info);
				}
			}
		});
	}
};
