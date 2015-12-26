jQuery(document).ready(function() {

	jQuery('select.uniformselect').uniform();

	jQuery('#carNumber').change(function() {
		checkCarNumber(this);
	});

	jQuery('#vin').change(function() {
		checkCarVin(this);
	});

	jQuery("#form1").validate({
		rules : {
			'carNumber' : "required",
			'carType' : "required",
			'vin' : {
				"required" : true,
				"maxlength" : 17,
				"minlength" : 17
			},
			'cardNO' : "required",
			'phone' : "required"
		}
	});

	jQuery("#form1").submit(function() {
		if (!jQuery("#form1").valid()) {
			return false;
		}
		if (jQuery("#carType").val() == '') {
			Dialog.alert("提示：请选择车辆类型");
			return;
		}
	});

});

// 检查车辆号码是否存在
var checkCarNumber = function(thiz) {
	var carNumber = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/car/car/checkCarNumber.htm?",
		data : "carNumber=" + carNumber.val(),
		async : true,
		success : function(data) {
			var map = eval('(' + data + ')');
			if (map.success) {

			} else {
				Dialog.alert("提示：该车牌号码已经存在");
				carNumber.attr("value", "");
			}
		},
		error : function() {
			Dialog.alert("提示：验证失败");
		}
	});
};

// 检查车辆号码是否存在
var checkCarVin = function(thiz) {
	var vin = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/car/car/checkCarVin.htm?",
		data : "vin=" + vin.val(),
		async : true,
		success : function(data) {
			var map = eval('(' + data + ')');
			if (map.success) {

			} else {
				Dialog.alert("提示：该车牌识别号已经存在");
				vin.attr("value", "");
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
	diag.Title = "手机号码选择";
	diag.URL = contextPath + "/page/customer/customerWin.jsp?type=customer";
	diag.MessageTitle = '选择手机号码';
	diag.Message = "";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
		diag.close();
	};// 点击确定后调用的方法
	diag.show();
};

// 保存方法
var callBack = function(obj) {
	var arr = obj.split('_');
	jQuery("#customerId").attr("value", arr[1]);
	jQuery("#cardNO").attr("value", arr[3]);
	jQuery("#name").attr("value", arr[4]);
	jQuery("#phone").attr("value", arr[5]);
	jQuery("#idCard").attr("value", arr[6]);
};

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/customer/cars/saveAdd2.htm?',
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


