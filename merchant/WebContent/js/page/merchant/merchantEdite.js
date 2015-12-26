jQuery(document).ready(function() {

//	jQuery('#idCard').change(function() {
//		checkIdcard(this);
//	});
	provinceCombox.requestData();
	cityCombox.requestData();
	jQuery("#form1").validate({
		rules : {
			'mobilePhone' : {
				"required" : true,
				"phone" : true
			},
			'merchantName' : "required",
			'corporation' : "required",
			'idCard' :  {
				"required" : true,
				"idcard" : true
			},
			'hotTel' : "required", 
			'alipayAccount' : "required",
			'alipayAccountName' : "required",
			'bankTypeName' : "required",
			'rentalMoney' :"required"
		}
	});

});

// 检测身份证是否已经存在
var checkIdcard = function(thiz) {
	var idCard = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/merchant/merchant/checkIdcard.htm?",
		data : {
			idCard : idCard.val(),
			id : jQuery("#id").val()
		},
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：身份证已经存在！");
				email.attr("value", "");
			}
		},
		error : function() {
			alert("验证失败");
		}
	});
};


//选择框
var provinceCombox = new Combox({
	id : 'province',
	url : contextPath + '/merchant/site/ajaxProvinces.htm?t='
			+ new Date().getTime(),
	cText : 'proName',
	cValue : 'proName',
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
	beforeload : function() {
		this.paraData = {
			'pName' : jQuery("#province").val()
		};
	},
	change : function() {

	}
});