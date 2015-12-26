jQuery(document).ready(function() {

	provinceCombox.requestData();
	cityCombox.requestData();

	jQuery('#imgUrlFile').change(function() {
		imgupload(this);
	});

	jQuery('#siteName').change(function() {
		checkSiteName(this);
	});

	var type = jQuery('#type').val();

	jQuery("input[name=types]").each(function() {
		var value = jQuery(this).val();
		if ((type & value) == value) {
			this.checked = true;
		}
	});

	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'siteName' : "required",
			'address' : "required",
			'principal' : "required",
			'phone' : "required",
			'xy' : "required",
			'lat' : "required"
		}
	});
});

// 选择框
var provinceCombox = new Combox(
		{
			id : 'province',
			url : contextPath + '/merchant/site/ajaxProvinces.htm?t='
					+ new Date().getTime(),
			cText : 'proName',
			cValue : 'proName',
			emptyText : '请选择省',
			beforeload : function() {
				this.emptyValue = jQuery("#hid_province").val();
			},
			change : function() {
				jQuery("#cityDiv")
						.html(
								'<select name="city" id="city" style="width: 200px;"><option value="">请选择</option></select>');
				cityCombox.requestData();
			}
		});

// 选择框
var cityCombox = new Combox({
	id : 'city',
	url : contextPath + '/merchant/site/ajaxCitys.htm?t='
			+ new Date().getTime(),
	cText : 'cityName',
	cValue : 'cityName',
	emptyText : '请选择市',
	beforeload : function() {
		this.emptyValue = jQuery("#hid_city").val();
		this.paraData = {
			'pName' : jQuery('#province').val() == '' ? jQuery("#hid_province")
					.val() : jQuery('#province').val()
		};

	},
	change : function() {

	}
});

var imgupload = function(thiz) {
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
			fileElementId : 'imgUrlFile',
			dataType : 'json',
			beforeSend : function() {

			},
			success : function(data, status) {
				jQuery("#imgUrl").attr("src", contextPath + data.data);
				jQuery("#imgUrlHidden").attr("value", data.data);
			},
			error : function() {
				Dialog.alert(e);
			}
		});
	} else {
		Dialog.alert("提示：请选择图片");
	}
};

var checkSiteName = function(thiz) {
	var siteName = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/merchant/site/checkSiteName.htm?",
		data : {
			siteName : siteName.val(),
			id : jQuery('#id').val()
		},
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：该名称已经存在");
				siteName.attr("value", "");
			}
		},
		error : function() {
			Dialog.alert("验证失败");
		}
	});
};

var imgError = function(thiz) {
	thiz.src = contextPath + '/img/car.png';
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
	};// 点击确定后调用的方法
	diag.show();
};

// 保存方法
var callBack = function(lat, lng) {
	jQuery("#lng").attr("value", lng);
	jQuery("#lat").attr("value", lat);
	jQuery("#xy").attr("value", lng + ',' + lat);
};

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/merchant/site/saveEdite.htm?',
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

				}
			}
		});
	}
};