jQuery(document).ready(function() {

	jQuery('input:file,input:checkbox').uniform();

	curSiteCombox.requestData();
	carTypeCombox.requestData();
	colorCombox.requestData();

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
			'carTypeId' : "required",
			'color' : "required",
			'describe' : "required",
			'vin' : {
				"required" : true,				
				"maxlength" : 17,
				"minlength" : 17
			},
			'chargeDuration' : {
				"required" : true,
				"number" : true
			},
			'curSiteId' : "required"
		}
	});

	jQuery("#form1").submit(function() {
		if (!jQuery("#form1").valid()) {
			return false;
		}
		if (jQuery("#carTypeId").val() == '') {
			Dialog.alert("提示：请选择车辆型号");
			return false;
		}
		if (jQuery("#carColor").val() == '') {
			Dialog.alert("提示：请选择车辆颜色");
			return false;
		}
		if (jQuery("#curSiteId").val() == '') {
			Dialog.alert("提示：请选择当前站点");
			return false;
		}
	});
});
// 选择框
var curSiteCombox = new Combox({
	id : 'curSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择'
});
// 车辆型号选择框
var carTypeCombox = new Combox(
		{
			id : 'carTypeId',
			url : contextPath + '/car/type/ajaxTypes.htm?t='
					+ new Date().getTime(),
			cText : 'typeName',
			cValue : 'id',
			emptyText : '请选择车辆型号',
			change : function() {
				jQuery("#colorDiv")
						.html(
								'<select name="color" id="carColor"><option value="">请选择</option></select>');
				colorCombox.requestData();
			}
		});
// 颜色选择框
var colorCombox = new Combox({
	id : 'carColor',
	url : contextPath + '/car/type/ajaxTypeColors.htm?t='
			+ new Date().getTime(),
	cText : 'value',
	cValue : 'value',
	emptyText : '请选择车辆颜色',
	beforeload : function() {
		this.paraData = {
			carTypeId : jQuery("#carTypeId").val()
		};
	},
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

// 上传车辆图片
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
