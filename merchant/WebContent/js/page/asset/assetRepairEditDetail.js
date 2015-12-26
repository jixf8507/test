jQuery(document).ready(function() {

			jQuery("#form1").validate({
				rules : {
					'repairReason' : "required",
					'repairStatus' : "required",
					'fee' : {
						"required" : true,
						"number" : true
					},
					'accessories' : "required",
				}
			});

});

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/asset/repair/saveEditDetail.htm?',
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

