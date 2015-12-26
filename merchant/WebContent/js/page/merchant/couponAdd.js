jQuery(document).ready(function() {
	jQuery('#toDateStr').datetimepicker();



	
	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'couponName' : "required",
			'number' : "required",
			'balance' : "required",
			'toDateStr' :  "required"
		}
	});
});



//将字符串转换为时间
var changeDate= function (str){
	str = str.replace(/-/g,"/");
	var date = new Date(str );
	return date;
}






/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#form1").valid()) {
		//如果选择的过期时间大于当前时间则不允许提交表单
		var toDateStr=jQuery('#toDateStr').val();
		var toDate=changeDate(toDateStr);
		if(toDate <= new Date()){
			Dialog.alert("提示：到期时间不能小于创建时间");
			return false;
		}
		
			jQuery.ajax({
				cache : true,
				type : "POST",
				url : contextPath + '/merchant/coupon/saveAdd.htm?t='
								  + new Date().getTime(),
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
	};
};