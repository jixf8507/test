var flag = false;
jQuery(document).ready(function() {

	siteCombox.requestData();

	jQuery('#cardNO').change(function() {
		checkCardNO(this);
	});

	jQuery("#form1").validate({
		rules : {
			'cardNO' : "required",
			'verCode' : "required",
			'bankCardNO' : "required",
			'bankType' : "required"
		},
		ignore : "hidden"
	});

	flag = true;
});

// 选择框
var siteCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择',
	beforeload : function() {
		this.emptyValue = jQuery("#hid_curSiteId").val();
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
				Dialog.alert("提示：该租赁卡号已经存在");
				cardNO.attr("value", "");
			}
		},
		error : function() {
			Dialog.error("提示：验证失败");
		}
	});
};

function imgError(thiz) {
	thiz.src = contextPath + '/img/car.png';
}

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if(jQuery('#siteId').val()==''){
		Dialog.alert("提示：请选择开户站点");
		return false;
	}
	if (flag && jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/customer/potential/saveCheck.htm?',
			data : jQuery('#form1').serialize(), 
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