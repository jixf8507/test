jQuery(document).ready(function() {

	jQuery('input:file,input:checkbox').uniform();

//	curSiteCombox.requestData();
//	carTypeCombox.requestData();
	statusCombox.requestData();

	jQuery('#carNumber').change(function() {
		checkCarNumber(this);
	});

	jQuery('#vin').change(function() {
		checkCarVin(this);
	});

	jQuery('#bigImgFile').change(function() {
		imgUpload(this);
	});

	jQuery("#form1").validate({
		rules : {
			'carNumber' : "required",
			'carTypeId' : "required"
		}
	});

	jQuery("#form1").submit(function() {
		if (!jQuery("#form1").valid()) {
			return false;
		}
		if (jQuery("#carStatus").val() == '') {
			Dialog.alert("提示：请选择车辆状态");
			return false;
		}
		if (jQuery("#describe").val() == '') {
			Dialog.alert("提示：请填写原因");
			return false;
		}
	});
});
//// 选择框
//var curSiteCombox = new Combox({
//	id : 'curSiteId',
//	url : contextPath + '/merchant/site/ajaxSites.htm?t='
//			+ new Date().getTime(),
//	cText : 'siteName',
//	cValue : 'id',
//	emptyText : '请选择',
//	beforeload : function() {
//		this.emptyValue = jQuery("#hid_curSiteId").val();
//	}
//});
//// 车辆型号选择框
//var carTypeCombox = new Combox(
//		{
//			id : 'carTypeId',
//			url : contextPath + '/car/type/ajaxTypes.htm?t='
//					+ new Date().getTime(),
//			cText : 'typeName',
//			cValue : 'id',
//			emptyText : '请选择车辆型号',
//			beforeload : function() {
//				this.emptyValue = jQuery("#hid_carTypeId").val();
//			},
//			change : function() {
//				jQuery("#colorDiv")
//						.html(
//								'<select name="color" id="carColor"><option value="">请选择</option></select>');
//				colorCombox.requestData();
//			}
//		});
// 状态选择框
var statusCombox = new Combox({
	id : 'carStatus',
	url : contextPath + '/car/type/ajaxTypeStatus.htm?t='
			+ new Date().getTime(),
	cText : 'value',
	cValue : 'value',
	emptyText : '请选择车辆状态',
	beforeload : function() {
		this.paraData = {
			carTypeId : jQuery("#carTypeId").val() == '' ? jQuery(
					"#hid_carTypeId").val() : jQuery("#carTypeId").val()
		};
		this.emptyValue = jQuery("#hid_carStatus").val();
	}
});

//// 检查车辆号码是否存在
//var checkCarNumber = function(thiz) {
//	var carNumber = jQuery(thiz);
//	jQuery.ajax({
//		type : "POST",
//		url : contextPath + "/car/car/checkCarNumber.htm?",
//		data : {
//			'carNumber' : carNumber.val(),
//			'id' : jQuery("#id").val()
//		},
//		async : true,
//		success : function(data) {
//			var map = eval('(' + data + ')');
//			if (map.success) {
//
//			} else {
//				Dialog.alert("提示：该车牌号码已经存在");
//				carNumber.attr("value", "");
//			}
//		},
//		error : function() {
//			Dialog.alert("提示：验证失败");
//		}
//	});
//};

////检查车辆号码是否存在
//var checkCarVin = function(thiz) {
//	var vin = jQuery(thiz);
//	jQuery.ajax({
//		type : "POST",
//		url : contextPath + "/car/car/checkCarVin.htm?",
//		data : "vin=" + vin.val(),
//		async : true,
//		success : function(data) {
//			var map = eval('(' + data + ')');
//			if (map.success) {
//
//			} else {
//				Dialog.alert("提示：该车牌识别号已经存在");
//				vin.attr("value", "");
//			}
//		},
//		error : function() {
//			Dialog.alert("提示：验证失败");
//		}
//	});
//};
//
//// 上传车辆图片
//var imgUpload = function(thiz) {
//	var filepath = jQuery(thiz).val();
//	var extStart = filepath.lastIndexOf(".");
//	var ext = filepath.substring(extStart, filepath.length).toUpperCase();
//	if (filepath != "") {
//		if (ext != ".PNG" && ext != ".JPG") {
//			Dialog.alert("提示：只能上传png,jpg图片");
//			jQuery(thiz).outerHTML = jQuery(thiz).outerHTML;
//			return;
//		}
//		jQuery.ajaxFileUpload({
//			url : contextPath + '/system/upload/imageUpload.htm?',
//			secureuri : false,
//			fileElementId : 'bigImgFile',
//			dataType : 'json',
//			beforeSend : function() {
//
//			},
//			success : function(data, status) {
//				jQuery("#bigImg").attr("src", contextPath + data.data);
//				jQuery("#bigImgHidden").attr("value", data.data);
//			},
//			error : function() {
//
//			}
//		});
//	} else {
//		Dialog.alert("提示：请选择图片");
//	}
//};

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#carStatus").val() == '') {
		Dialog.alert("提示：请选择车辆状态");
		return false;
	}
	if (jQuery("#describe").val() == '') {
		Dialog.alert("提示：请填写原因");
		return false;
	}
	if (jQuery("#form1").valid()) {
		jQuery.ajax({ 
			cache : true,
			type : "POST",
			url : contextPath + '/car/car/saveStatusDescribe.htm?',
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
