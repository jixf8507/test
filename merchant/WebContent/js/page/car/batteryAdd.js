jQuery(document).ready(function() {
	jQuery('input:file,input:checkbox').uniform();
	jQuery('#vin').change(function() {
		checkVin(this);
	});

	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'vin' : "required",
			'carId' : "required",
			'carNumber' : "required",
			'batteryType' : "required",
			'maxVoltag' : {
				"required" : true,
				"number" : true
			},
			'maxCurrent' : {
				"required" : true,
				"number" : true
			},
			'maxTemp' : {
				"required" : true,
				"number" : true
			},
			'minVoltage' : {
				"required" : true,
				"number" : true
			},
			'minCurrent' : {
				"required" : true,
				"number" : true
			},
			'minTemp' : {
				"required" : true,
				"number" : true
			}
		}
	});

});

//
var checkVin = function(thiz) {
	var vin = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/car/battery/checkVin.htm?",
		data : "vin=" + vin.val(),
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("该VIN码已经存在！");
				vin.attr("value", "");
			}
		},
		error : function() {
			Dialog.alert("验证失败");
		}
	});
};

// 获取订单列表
var getCar = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "车辆选择";
	diag.URL = contextPath + "/page/car/carWin.jsp?";
	diag.MessageTitle = '选择车辆';
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
	jQuery("#carId").attr("value", arr[0]);
	jQuery("#carNumber").attr("value", arr[1]);
};