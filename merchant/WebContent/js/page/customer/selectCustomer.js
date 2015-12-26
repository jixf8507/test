jQuery(document).ready(function() {

	customerTable.reloadData();
	var selId = '';
	addCustomerId(selId);
	
	jQuery('#queryBtn').click(function(){
//		customerTable.bStateSave=false; 
		customerTable.reloadData();
	});

	jQuery(".checkall").click(function() {
		var thiz = this;
		jQuery("input[name=userId]").each(function() {
			jQuery(this).attr('checked', thiz.checked);
		});
	});
	
	jQuery(".userId,.checkall").live('click',function(){
		addCustomerId(selId);
	});
	
});


var addCustomerId = function(selId){
	var selId = jQuery('#customerId').val();
	jQuery("input[name=userId]checkbox:checked").each(function() {
//		if (jQuery(this).is(':checked')) {
			selId += jQuery(this).val() + ",";
			
//		}
	});
	
	
//	var html = '<input type="text" name="customerId" value="'+selId+'" />';
//	jQuery('#addInput').append(html);		
};

var customerTable = new PageDataTables(
		{
			tableId : 'customerTable',
			iDisplayLength : 6,
			bSort : false,
			ajaxUrl : contextPath + "/customer/customer/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/customer/customer/exportToExcel.htm?",
			aoColumns : [
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var enterpriseName = obj.aData['enterpriseName'];
							 if (enterpriseName.length > 0) {
								return '';
							} else {
								return '<input style="width: 20px;" type="checkbox" name="userId" class="userId" id="'
										+ id
										+ '" title="'
										+ id
										+ '"value="'
										+ id + '" >';
							}

						},
						"sWidth" : "4%",
						"sClass" : "center"
					}, {
						"mDataProp" : "phone",
						"sClass" : "center"
					}, {
						"mDataProp" : "name",
						"sClass" : "center"
					}, {
						"mDataProp" : "idCard",
						"sClass" : "center"
					}, {
						"mDataProp" : "moneyOfassure",
						"sClass" : "center"
					}, {
						"mDataProp" : "balance",
						"sClass" : "center"
					}, {
						"mDataProp" : "status",
						"sClass" : "center"
					}, {
						"mDataProp" : "enterpriseName",
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"idCard" : jQuery('#idCard').val() == '' ? '' : '%'
							+ jQuery('#idCard').val() + "%",
					"phone" : jQuery('#phone').val() == '' ? '' : '%'
							+ jQuery('#phone').val() + "%",
					"name" : jQuery('#name').val() == '' ? '' : '%'
							+ jQuery('#name').val() + "%",
					"notStatus" : '注销'
				};
			}
		});

/**
 * 提交form表单
 */
var submit = function(callBack) {
	var selId = '';
	jQuery("input[name=userId]").each(function() {
		if (jQuery(this).is(':checked')) {
			selId += jQuery(this).val() + ",";
		}
	});
	if(selId==''){
		Dialog.alert("提示：未选择客户");
		return false;
	}
	Dialog.confirm('提示：确认添加当前页已选择客户?', function() {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/customer/unit/saveCustomerAdd.htm?',
			data : "customerIds=" + selId + "&id=" + jQuery('#id').val(),
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功", function() {
						callBack();
					});
				}
			}
		});
	});
};
