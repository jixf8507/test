jQuery(document).ready(function() {

	jQuery("#deviceTypeName,#manufacturer").chosen();

	sitesCombox.requestData();
	areaCombox.requestData();
	chargePortCombox.requestData();

	jQuery('#factoryNo').change(function() {
		var factoryNo = jQuery('#factoryNo');
		checkFatoryNo(factoryNo);
	});

	// jQuery('#nameplate').change(function() {
	// var nameplate = jQuery('#nameplate');
	// checkNameplate(nameplate);
	// });

	jQuery('#deviceNo').change(function() {
		var deviceNo = jQuery('#deviceNo');
		checkDeviceNo(deviceNo);
	});

	jQuery("#deviceTypeName").change(function() {
		if (jQuery('#deviceTypeName').val() == '旗翔交流') {
			jQuery('#deviceKind').val('交');
		} else {
			jQuery('#deviceKind').val('直');
		}
	});

	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'factoryNo' : "required",
			'siteCode' : "required",
			'areaCode' : "required",
			'chargePort' : "required",
			'deviceNo' : "required",
			'point' : "required",
			// 'nameplate' : "required",
			'manufacturer' : "required",
			'fixedVoltage' : {
				"required" : true,
				"number" : true
			},
			'devicePower' : {
				"required" : true,
				"number" : true
			}
		}
	});

	jQuery('#bigImgFile').change(function() {
		imgUpload(this);
	});

});

// 选择框
var sitesCombox = new Combox(
		{
			id : 'siteId',
			url : contextPath + '/merchant/site/ajaxSites.htm?t='
					+ new Date().getTime(),
			cText : 'siteName',
			cValue : 'id',
			emptyText : '请选择',
			beforeload : function() {
				this.emptyValue = jQuery('#hidSiteId').val();
			},
			change : function() {
				jQuery("#areaDiv")
						.html(
								'<select name="areaCode" id="areaCode"><option value="">请选择</option></select>');
				areaCombox.requestData();
			}
		});

var areaCombox = new Combox({
	id : 'areaCode',
	url : contextPath + '/stake/device/ajaxArea.htm?t=' + new Date().getTime(),
	cText : 'area_name',
	cValue : 'area_code',
	emptyText : '请选择分区',
	beforeload : function() {
		this.paraData = {
			siteCode : jQuery('#siteId').val() == '' ? jQuery('#hidSiteId')
					.val() : jQuery('#siteId').val()
		};
		this.emptyValue = jQuery('#hidAreaCode').val();
	},
	change : function() {

	}
});

var chargePortCombox = new Combox({
	id : 'chargePort',
	url : contextPath + '/stake/device/portTypes.htm?paraData='
			+ encodeURI(encodeURI(JSON.stringify({
				"parkType" : "1口1车"
			}))) + '&t=' + new Date().getTime(),
	cText : 'chargePort',
	cValue : 'chargePort',
	emptyText : '请选择充电端口类型',
	beforeload : function() {
		this.emptyValue = jQuery('#hidChargePort').val();
	},
	change : function() {

	}
});

// 检查出厂编号是否重复
var checkFatoryNo = function(factoryNo) {
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/stake/device/checkFactoryNo.htm?",
		data : "factoryNo=" + factoryNo.val() + "&id=" + jQuery('#id').val(),
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：出厂编号已重复,请重新填写！");
				factoryNo.attr("value", "");
			}
		},
		error : function() {
			Dialog.alert("提示：验证失败");
		}
	});
};
// 检查铭牌是否重复
var checkNameplate = function(nameplate) {
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/stake/device/checkNameplate.htm?",
		data : "nameplate=" + nameplate.val() + "&id=" + jQuery('#id').val(),
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：铭牌已重复,请重新填写！");
				nameplate.attr("value", "");
			}
		},
		error : function() {
			Dialog.alert("提示：验证失败");
		}
	});
};

// 检查设备名称是否重复
var checkDeviceNo = function(deviceNo) {
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/stake/device/checkDeviceNo.htm?",
		data : "deviceNo=" + deviceNo.val() + "&id=" + jQuery('#id').val(),
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：设备名称已重复,请重新填写！");
				deviceNo.attr("value", "");
			}
		},
		error : function() {
			Dialog.alert("提示：验证失败");
		}
	});
};

// 上传图片
var imgUpload = function(thiz) {
	var filepath = jQuery(thiz).val();
	var extStart = filepath.lastIndexOf(".");
	var ext = filepath.substring(extStart, filepath.length).toUpperCase();
	if (filepath != "") {
		if (ext != ".PNG" && ext != ".JPG") {
			Dialog.alert("提示：只能上传png,jpg图片");
			jQuery(thiz).outerHTML = jQuery(thiz).outerHTML;
			return;
		}
		jQuery.ajaxFileUpload({
			url : contextPath + '/system/upload/imageUpload.htm?',
			secureuri : false,
			fileElementId : 'bigImgFile',
			dataType : 'json',
			beforeSend : function() {

			},
			success : function(data, status) {
				jQuery("#bigImg").attr("src", contextPath + data.data);
				jQuery("#bigImgHidden").attr("value", data.data);
			},
			error : function() {

			}
		});
	} else {
		Dialog.alert("提示：请选择图片");
	}
};

var showMap = function() {
	var lng = jQuery("#lng").val();
	var lat = jQuery("#lat").val();
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "坐标位置选择";
	diag.MessageTitle = "点击选择坐标";
	diag.Message = "";
	diag.URL = contextPath + "/merchant/map/page.htm?lng=" + lng + "&lat="
			+ lat;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.clickOK(callBack);
		diag.close();
	};
	diag.show();
};

// 保存方法
var callBack = function(lat, lng) {
	jQuery("#lng").attr("value", lng);
	jQuery("#lat").attr("value", lat);
	jQuery("#point").attr("value", lng + ',' + lat);
};

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#siteId").val() == '') {
		Dialog.alert("提示：请选择充电站名称");
		return false;
	}
	if (jQuery("#areaCode").val() == '') {
		Dialog.alert("提示：请选择充电区域");
		return false;
	}
	if (jQuery("#chargePort").val() == '') {
		Dialog.alert("提示：请选择充电端口类型");
		return false;
	}
	if (jQuery("#deviceTypeName").val() == '') {
		Dialog.alert("提示：请选择设备类型");
		return false;
	}
	if (jQuery("#manufacturer").val() == '') {
		Dialog.alert("提示：请选择生产厂商");
		return false;
	}
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/stake/device/saveEdite.htm?',
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

function imgError(thiz) {
	thiz.src = contextPath + '/img/car.png';
}