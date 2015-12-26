jQuery(document).ready(function() {

	jQuery("#realityGetTime,#schemingReturnTime").datetimepicker();

	prepCurSitesCombox.requestData();

	jQuery("#form1").validate({
		rules : {
			'priceTypeName' : "required",
			'customerName' : "required",
			'reaGetTime' : "required",
			'scheReturnTime' : "required",
			'phone' : "required",
			'verCode' : "required"
		}

	});
});

// 预约取车选择框
var prepCurSitesCombox = new Combox({
	id : 'shortPriceTypeId',
	url : contextPath + '/price/typeRelation/ajaxCarTypes.htm?t='
			+ new Date().getTime(),
	cText : 'typeName',
	cValue : 'id',
	emptyText : '请选择租赁收费套餐',
	beforeload : function() {
		this.emptyValue = jQuery("pid").val();
		this.paraData = {
			"tableName" : jQuery('#priceTypeTableName').val(),
			"carId" : jQuery('#carId').val(),
			"type" : "innerSelect"
		};
	},
	change : function(v, obj) {
		jQuery("#priceTypeName").val(obj.typeName);
		jQuery("#priceTypeId").val(obj.id);
	}
});

// 获取订单列表
var getCustomer = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "客户选择";
	diag.URL = contextPath
			+ "/page/customer/customerWin.jsp?noUse=yes&tableName="
			+ jQuery('#priceTypeTableName').val();
	diag.MessageTitle = '选择客户';
	diag.Message = "";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
		diag.close();
	};
	diag.show();
};

// 保存方法
var callBack = function(obj) {
	if (obj.indexOf("_") == -1) {
		Dialog.alert("提示：" + obj);
		jQuery("#customerId").attr("value", '');
		jQuery("#cardNO").attr("value", '');
		jQuery("#customerName").attr("value", '');
		jQuery("#phone").attr("value", '');
		jQuery("#idCard").attr("value", '');
		jQuery("#moneyOfassure").attr("value", '');
		jQuery("#balance").attr("value", '');
	} else {
		var arr = obj.split('_');
		jQuery("#customerId").attr("value", arr[1]);
		jQuery("#cardNO").attr("value", arr[3]);
		jQuery("#customerName").attr("value", arr[4]);
		jQuery("#phone").attr("value", arr[5]);
		jQuery("#idCard").attr("value", arr[6]);
		jQuery("#moneyOfassure").attr("value", arr[7]);
		jQuery("#balance").attr("value", arr[8]);
	}
};

var submit = function(callBack) {
	if (jQuery("#shortPriceTypeId").val() == '') {
		Dialog.alert("提示：请选租赁套餐");
		return false;
	}
	var realityGetTime = jQuery('#realityGetTime').val();
	var schemingReturnTime = jQuery('#schemingReturnTime').val();

	/*
	 * var getTime = (realityGetTime+":00").replace(/-/g,"/"); var getTimeDate =
	 * new Date(getTime); if(new Date().getTime() < getTimeDate.getTime()){
	 * Dialog.alert("提示：取车时间不能大于当前时间"); return false; }
	 */
	if (schemingReturnTime < realityGetTime) {
		Dialog.alert("提示：最迟还车时间不能早于取车时间");
		return false;
	}
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/order/getApply/saveGetCar.htm?',
			data : jQuery('#form1').serialize(),
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：取车失败");
			},
			success : function(data) {
				if (data.success) {
					// Dialog.alert("提示：" + data.info);
					Dialog.alert("提示：取车成功", function() {
						callBack();
					});
				} else {
					Dialog.alert("提示：" + data.info);
				}
			}
		});
	}
};
