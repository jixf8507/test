jQuery(document).ready(function() {
	sitesCombox.requestData();

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

var submit = function(callBack) {
	var siteId = jQuery('#siteId').val();
	if(siteId == ""){
		Dialog.alert("请选择退款租赁点");
		return false;
	}
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/customer/account/saveRefund.htm?',
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
