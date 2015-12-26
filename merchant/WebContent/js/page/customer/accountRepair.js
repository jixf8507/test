jQuery(document).ready(function() {
	sitesCombox.requestData();

	jQuery("#form1").validate({
		rules : {
			'cardNO' : "required",
			'bankCardNO' : "required",
			'bankType' : "required"
		}
	});

	jQuery('#cardNO').change(function() {
		checkCardNO(this);
	});

});

// 选择框
var sitesCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择',
	change : function() {

	}
});

/**
 * 检测租赁卡号是否
 */
var checkCardNO = function(thiz) {
	var cardNO = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/customer/customer/checkCardNO.htm?",
		data : "cardNO=" + cardNO.val(),
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：该卡号已经存在");
				cardNO.attr("value", "");
			}
		},
		error : function() {
			Dialog.error("提示：验证失败");
		}
	});
};

var submit = function(callBack) {
	if (jQuery("#siteId").val() == '') {
		Dialog.alert("提示：请选择站点");
		return false;
	}
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/customer/account/saveRepair.htm?',
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
