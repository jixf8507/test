jQuery(document).ready(function() {
	jQuery("#form1").validate({
		rules : {
			'ticket' : {
				"required" : true,
				"maxlength" : 100
			}
//			'checkDescribe' : "required"
		}
	});
});

var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/customer/accountRecord/saveCheckFee.htm?',
			data : jQuery('#form1').serialize(),
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功",function(){
						callBack();
					});
				}else{
					Dialog.alert("提示：操作失败");
				}
			}
		});
	}
};
