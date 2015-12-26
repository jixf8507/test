jQuery(document).ready(function() {

	jQuery('#position,#position1').chosen();

	jQuery("#form1").validate({
		rules : {
			'factoryNo' : "required",
		// 'area' : "required",
		// 'address' : "required",
		}
	});
	
	if(jQuery('#hidPosition').val()==''){
		jQuery('#leftRight').hide();
		jQuery('#left').show();
	}
	
});

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		var paraData = {
			"factoryNo" : jQuery('#factoryNo').val(),
			"position" : jQuery('#position').val() == '' ? jQuery('#position1')
					.val() : jQuery('#position').val(),
			"id" : jQuery('#id').val()
		};
		if (jQuery('#position').val() == "" && jQuery('#position1').val() == "") {
			Dialog.alert("提示：请选择所在充电桩的位置");
			return;
		}
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/parking/space/saveEdite.htm?paraData='
					+ encodeURI(encodeURI(JSON.stringify(paraData))),
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

				} else {
					Dialog.alert("提示：" + data.info);
				}
			}
		});
	}
};

// 获取充电桩列表
var getFactory = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "充电桩选择";
	diag.URL = contextPath + "/page/stake/deviceWin.jsp";
	diag.MessageTitle = '选择充电桩';
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
		diag.close();
	};
	diag.show();
};

// 保存方法
var callBack = function(obj) {
	var arr = obj.split('_');
	jQuery("#factoryNo").attr("value", arr[1]);
	jQuery("#deviceNo").attr("value", arr[2]);
	jQuery("#nameplate").attr("value", arr[3]);
	jQuery("#siteName").attr("value", arr[4]);
	jQuery("#deviceTypeNo").attr("value", arr[5]);
	jQuery("#parkingId").attr("value", arr[6]);
	jQuery("#lat").attr("value", arr[7]);
	jQuery("#lng").attr("value", arr[8]);

	if (arr[5] == 0) {
		jQuery("#position").attr("value",'');
		jQuery('#leftRight').hide();
		jQuery('#left').show();
	} else {
		jQuery("#position1").attr("value",'');
		jQuery('#left').hide();
		jQuery('#leftRight').show();
	}
};