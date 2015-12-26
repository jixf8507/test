jQuery(document).ready(function() {

	jQuery("#realityReturnTime").datetimepicker();

	sitesCombox.requestData();

	jQuery("#form1").validate({
		rules : {
			'relityReturnSiteId' : "required",
			'reaReturnTime' : "required"
		}

	});

});

// 预约取车租赁点选择框
var sitesCombox = new Combox({
	id : 'relityReturnSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
	+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	beforeload : function() {
		this.emptyValue = jQuery("pid").val();
	}	
});

var submit = function(callBack) {
	if(jQuery('#relityReturnSiteId').val() == ''){
		Dialog.alert("提示：请选择还车租赁点");
		return false;
	}
	
	var realityGetTime = jQuery('#realityGetTime').val();
	var realityReturnTime = jQuery('#realityReturnTime').val();
	if(realityGetTime > realityReturnTime+":00.0"){
		Dialog.alert("提示：还车时间不能早于取车时间");
		return false;
	}
	
	/*realityReturnTime = (realityReturnTime+":00").replace(/-/g,"/");
	var returnTimeDate = new Date(realityReturnTime);
	
	if(new Date().getTime() - returnTimeDate.getTime() > 30*60*1000){
		Dialog.alert("提示：还车时间不能在当前时间30分钟之前");
		return false;
	}*/
	
	if (jQuery("#form1").valid() && jQuery("#relityReturnSiteId").val() != ''
		 &&  jQuery("#realityReturnTime").val() != '') {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/order/returnApply/saveReturnCar.htm?',
			data : jQuery('#form1').serialize(), 
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：申请还车失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：申请还车成功",function(){
						callBack();
					});
				}else{
					Dialog.alert("提示："+data.info);
				}
			}
		});
	}
};
