jQuery(document).ready(function() {

	// 验证脚本
	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'areaCode' : "required",
			'areaName' : "required",
			'siteCode' : "required"
		}
	});

	sitesCombox.requestData();
	areaCodeCombox.requestData();
});

// 选择框
var sitesCombox = new Combox({
	id : 'siteCode',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择站点',
	change : function() {
		checkCode();
	}
});

// 选择框
var areaCodeCombox = new Combox({
	id : 'areaCode',
	url : contextPath + '/stake/area/ajaxAreaCode.htm?t='
			+ new Date().getTime(),
	cText : 'code',
	cValue : 'code',
	emptyText : '请选择',
	change : function() {
		jQuery('#areaCodeId').val(jQuery('#areaCode').val());
		checkCode();
	}
});

var submit = function(callBack) {
	if (jQuery("#siteCode").val() == '') {
		Dialog.alert("提示：请选择充电站名称");
		return false;
	}
	if (jQuery("#areaCodeId").val() == '') {
		Dialog.alert("提示：请选择充电区域");
		return false;
	}
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/stake/area/saveAdd.htm?',
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

// 检查区域编号是否重复
var checkCode = function() {
	if (jQuery("#siteCode").val() == '' || jQuery("#areaCodeId").val() == '') {
		return false;
	}
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/stake/area/checkCode.htm?",
		data : {
			'siteCode' : jQuery("#siteCode").val(),
			'areaCode' : jQuery("#areaCode").val()
		},
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：区域编号已被使用,请重新选择！");
				// jQuery("#areaCode
				// option[value='']").attr("selected","selected");
				jQuery('#areaCodeId').val("");
			}
		},
		error : function() {
			Dialog.alert("提示：验证失败");
		}
	});
};
