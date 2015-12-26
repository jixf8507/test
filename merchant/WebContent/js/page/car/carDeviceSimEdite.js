jQuery(document).ready(function() {

	jQuery('#facilitator').chosen();

	carDeviceCombox.requestData();
	
	// /// FORM VALIDATION /////
	jQuery("#form1").validate({
		rules : {
			'flowOfMonth' : "required",
			'simCard' : {
				"required" : true,
				"phone" : true
			}
		}
	});
	
	jQuery("#simCard").change(function(){
		checkSimCard();
	});

});

// 检查车载设备卡号是否存在
var checkSimCard = function(){
	jQuery.ajax({
		cache : true,
		type : "POST",
		url : contextPath + '/car/device/checkSimCard.htm?',
		data : "id="+jQuery('#id').val()+"&simCard="+jQuery('#simCard').val(), 
		async : false,
		dataType : 'json',
		error : function(request) {
			Dialog.alert("错误：操作失败");
		},
		success : function(data) {
			if (data.success) {
				
				
			}else{
				Dialog.alert("提示：该车载设备卡号已存在，请重新输入");
				jQuery('#simCard').val("");
			}
		}
	});
	
};

//车载设备选择框
var carDeviceCombox = new Combox({
	id : 'deviceId',
	url : contextPath + '/car/device/ajaxCarDevice.htm?t='
			+ new Date().getTime(),
	cText : 'deviceNO', 
	cValue : 'id',
	emptyText : '请选择车载设备',
	change : function() {
		
	},
	beforeload : function() {
		this.emptyValue = jQuery("#deviceId").val();
	}
});

/**
 * 提交form表单
 */
var submit = function(callBackEdite) {
	if (jQuery("#facilitator").val() == '') {
		Dialog.alert("提示：请选择运营商");
		return false;
	}
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/car/device/saveEdite.htm?',
			data : jQuery('#form1').serialize(), 
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功",function(){
						callBackEdite();
					});
					
				}else{
					Dialog.alert("提示：操作失败");
				}
			}
		});
	}
};