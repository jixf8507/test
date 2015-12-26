jQuery(document).ready(function() {

	jQuery("#realityGetTime").datetimepicker();

	jQuery("#form1").validate({
		rules : {
			'reaGetTime' : "required",
			'orderDescribe' : "required",
			'userName' : "required"
		}
	});
});

//获取员工列表
var getMerchantUser = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "使用人选择";
	diag.URL = contextPath + "/page/merchant/userWin.jsp";
	diag.MessageTitle = '选择使用人';
	diag.Message = "";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
		diag.close();
	}; 
	diag.show();
};

var callBack = function(obj) {
	var arr = obj.split('_');
	jQuery("#userId").attr("value", arr[0]);
	jQuery("#userName").attr("value", arr[1]);
	jQuery("#mobilePhone").attr("value", arr[2]);
};

var submit = function(callBack) {
	/*var realityGetTime = jQuery('#realityGetTime').val();
	var getTime = (realityGetTime+":00").replace(/-/g,"/");
	var getTimeDate = new Date(getTime);
	if(new Date().getTime() < getTimeDate.getTime()){
		Dialog.alert("提示：取车时间不能大于当前时间");
		return false;
	}*/
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/order/other/getApply/saveGetCar.htm?',
			data : jQuery('#form1').serialize(), 
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：取车失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：取车成功",function(){
						callBack();
					}); 
				} else {
					Dialog.alert("提示：" + data.info);
				}
			}
		});
	}
};
