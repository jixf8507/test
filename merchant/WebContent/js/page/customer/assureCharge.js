jQuery(document).ready(function() {

	sitesCombox.requestData();

	jQuery("#form1").validate({
		rules : {
			'phone' : "required",
			'addBalance' : {
				"required" : true,
				"number" : true
			},
			'ticket' : {
				"required" : true,
				"maxlength" : 100
			}
		}
	});

	jQuery("#form1").submit(function() {
		if (!jQuery("#form1").valid()) {
			return false;
		}
		// var describe = jQuery("input[name='describe']:checked").val();
		if (jQuery("#siteId").val() == '') {
			Dialog.alert("提示：请选择站点名称");
			return false;
		}
	});

});

// 选择框
var sitesCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择站点'
});

// 获取订单列表
var getCustomer = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "手机号码选择";
	diag.URL = contextPath + "/page/customer/customerWin.jsp?type=all";
	diag.MessageTitle = '选择手机号码';
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
	jQuery("#customerId").attr("value", arr[1]);
	jQuery("#accountId").attr("value", arr[2]);
	jQuery("#cardNO").attr("value", arr[3]);
	jQuery("#name").attr("value", arr[4]);
	jQuery("#phone").attr("value", arr[5]);
	jQuery("#idCard").attr("value", arr[6]);
	jQuery("#balance").attr("value", arr[7]);
};
