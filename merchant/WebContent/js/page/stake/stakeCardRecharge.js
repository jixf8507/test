jQuery(document).ready(function() {

	jQuery('#comPort,#baudRate').chosen();

	sitesCombox.requestData();

	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'cardID' : {
				"required" : true,
				"maxlength" : 8,
				"minlength" : 8
			},
			'addBalance' : {
				"required" : true,
				"number" : true
			},
			'name' : "required",
		// 'ticket' : "required",
		}
	});

	jQuery('#nzkh').live('change',function() {
		getCustomer();
	});
	

});

// 选择框
var sitesCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点'
});

var getCustomer = function() {
	jQuery.ajax({
		cache : true,
		type : "POST",
		url : contextPath + '/stake/card/getCardByCardId.htm?',
		data : "cardID=" + jQuery('#nzkh').val(),
		async : false,
		dataType : 'json',
		error : function(request) {
			Dialog.alert("提示：获取客户信息失败");
		},
		success : function(data) {
			if (data.success) {
				var info = data.info.split(",");
				jQuery('#customerId').val(info[0]);
				jQuery('#name').val(info[1]);
				jQuery('#phone').val(info[2]);
				jQuery('#accountId').val(info[3]);
				jQuery('#oldBalance').val(info[4]);

			} else {
				Dialog.alert("提示： " + data.info);
				jQuery('#customerId').val("");
				jQuery('#name').val("");
				jQuery('#phone').val("");
				jQuery('#accountId').val("");
				jQuery('#oldBalance').val("");
			}
		}
	});
};

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		if (jQuery('#siteId').val() == '') {
			Dialog.alert("提示：请选择租赁点");
			return false;
		}
		Dialog.confirm('提示：确认充值 ' + jQuery('#addBalance').val() + ' 元?',
				function() {
					jQuery.ajax({
						cache : true,
						type : "POST",
						url : contextPath + '/customer/account/saveCardBalanceCharge.htm?',
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
								Dialog.alert("提示：操作失败 ");
							}
						}
					});
				});
	}
};
