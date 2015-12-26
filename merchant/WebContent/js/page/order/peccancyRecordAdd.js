jQuery(document).ready(function() {
	jQuery("#peccancyTime").datetimepicker();
	jQuery("#payStatus").chosen();

	jQuery("#form1").validate({
		rules : {
			'peccancyTime' : "required",
			'carNumber' : "required",
			'address' : "required",
			'info' : "required",
			'payCost' : {
				"required" : true,
				"number" : true
			}
		}
	});
});

// 获取订单列表
var getOrder = function() {
	var peccancyTime = jQuery('#peccancyTime').val();
	if (peccancyTime == '') {
		Dialog.alert("提示：请选择违章时间");
		return;
	}
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "违章租赁信息选择";
	diag.URL = contextPath + "/page/order/orderWin.jsp?peccancyTime="
			+ peccancyTime;
	diag.MessageTitle = '违章时间：' + peccancyTime;
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
	jQuery('#carNumber').attr("value", arr[1]);
	jQuery('#orderId').attr("value", arr[0]);
	jQuery('#orderNO').attr("value", 'ZCDD_' + arr[0]);
	jQuery('#getTime').attr("value", arr[2]);
	jQuery('#returnTime').attr("value", arr[3]);
	jQuery('#customerName').attr("value", arr[4]);
	jQuery('#phone').attr("value", arr[5]);
};


/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/order/peccancyRecord/saveAdd2.htm?',
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

