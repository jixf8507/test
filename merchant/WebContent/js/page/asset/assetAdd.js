jQuery(document).ready(function() {

	jQuery("#increasingMode,#unit").chosen();
	jQuery("#purchaseDate").datepicker();
	
	manufacturerCombox.requestData();
	categoryCombox.requestData();

	jQuery("#form2").validate({
		rules : {
			'assetsName' : "required",
			'model' : "required",
			'purchaseDate' : "required",
			'fee' : {"required" : true,"number" : true}
			}
	});

});

var categoryCombox = new Combox({
	id : 'categoryId',
	url : contextPath + '/asset/category/ajaxCategoryData.htm?t='
	+ new Date().getTime(),
	cText : 'name',
	cValue : 'id',
	emptyText : '请选择资产类型',
	change : function() {
		 
	}
});
 
var manufacturerCombox = new Combox({
	id : 'manufacturId',
	url : contextPath + '/car/manufacturer/ajaxManufacturer.htm?t='
			+ new Date().getTime(),
	cText : 'manufacturerName',
	cValue : 'id',
	emptyText : '请选择供应商',
	change : function() {

	}
});

/**
 * 提交form表单
 */
var submit = function(callBackAdd) {
	if (jQuery("#categoryId").val() == '') {
		Dialog.alert("提示：请选择资产类型");
		return false;
	}
	if (jQuery("#increasingMode").val() == '') {
		Dialog.alert("提示：请选择增加方式");
		return false;
	}
	if (jQuery("#unit").val() == '') {
		Dialog.alert("提示：请选择计量单位");
		return false;
	}
	if (jQuery("#manufacturId").val() == '') {
		Dialog.alert("提示：请选择供应商");
		return false;
	}
	if (jQuery("#form2").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/asset/manage/saveAdd.htm?',
			data : jQuery('#form2').serialize(), 
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功",function(){
						callBackAdd();
					});
					
				}else{
					Dialog.alert("提示：操作失败");
				}
			}
		});
	}
};