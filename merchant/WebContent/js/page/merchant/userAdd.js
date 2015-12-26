jQuery(document).ready(function() {
	jQuery("#rights").chosen();
	sitesCombox.requestData();

	jQuery('#email').change(function() {
		checkEmail(this);
	});

	jQuery("#form1").validate({
		rules : {
			'email' : {
				"required" : true,
				"email" : true
			},
			'mobilePhone' : {
				"required" : true,
				"phone" : true
			},
			'userName' : "required",
			'siteId' : "required",
			'rights' : "required"
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
	emptyText : '请选择租赁点',
	beforeload : function() {
		this.emptyValue = jQuery("#siteIdHid").val();
	},
});

// 检测登录邮箱是否已经存在
var checkEmail = function(thiz) {
	var email = jQuery(thiz);
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/merchant/user/checkEmail.htm?",
		data : {
			email : email.val(),
			id : jQuery("#id").val()
		},
		async : true,
		dataType : 'json',
		success : function(data) {
			if (data.success) {

			} else {
				Dialog.alert("提示：登录邮箱已经存在！");
				email.attr("value", "");
			}
		},
		error : function() {
			Dialog.alert("提示：验证失败");
		}
	});
};

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		if (jQuery("#siteId").val() == '') {
			Dialog.alert("提示：请选择租赁点");
			return false;
		}
		if (jQuery("#rights").val() == '') {
			Dialog.alert("提示：请选择角色");
			return false;
		}
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/merchant/user/saveAdd2.htm?',
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