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
		data : {
			'vin' : vin.val(),
			'id' : jQuery('#id').val()
		},
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
	diag.Title = "租赁卡号选择";
	diag.URL = contextPath + "/page/car/carWin.jsp?";
	diag.MessageTitle = '选择租赁卡号';
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

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/car/battery/saveEdite.htm?',
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