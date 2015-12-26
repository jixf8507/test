jQuery(document).ready(function() {
	
	
});

var submit = function(callBack) {
//	if (jQuery("#form1").valid()) { 
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/customer/unit/saveRefund.htm?',
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
//	}
};
