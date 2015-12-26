jQuery(document).ready(function() {

	prepOrdersTable.reloadData();

	jQuery('#prepQueryBtn').click(function() {
		prepOrdersTable.bStateSave=false; 
		prepOrdersTable.reloadData();
	});

});

// 已预约订单列表
var prepOrdersTable = new PageDataTables(
		{
			tableId : 'prepOrdersTable',
			ajaxUrl : contextPath + '/order/order/ajaxData.htm?t='
				+ new Date().getTime(),
			aoColumns : [ // 设定各列宽度
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return '<input style="width: 20px;" type="radio" name="orderId" id="'
									+ id
									+ '" value="'
									+ obj.aData['id']
									+ '_'
									+ obj.aData['carNumber']
									+ '_'
									+ obj.aData['schemingGetTime']
									+ '_'
									+ obj.aData['schemingReturnTime']
									+ '_'
									+ obj.aData['name']
									+ '_'
									+ obj.aData['phone'] + '" >';
						},
						"sClass" : "center"
					}, {
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return ' ZCDD_' + id;
						},
						"sClass" : "center"
					}, {
						"mDataProp" : "carNumber",
						"sClass" : "center"
					},{
						"mDataProp" : "vin",
						"sClass" : "center"
					}, {
						"mDataProp" : "name",
						"sClass" : "center"
					}, {
						"mDataProp" : "phone",
						"sClass" : "center"
					}, {
						"mDataProp" : "realityGetTime",
						"sClass" : "center"
					}, {
						"mDataProp" : "gsiteName",
						"sClass" : "center"
					}, {
						"mDataProp" : "realityReturnTime",
						"sClass" : "center"
					}, {
						"mDataProp" : "rsiteName",
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"carNumber" : jQuery('#prepCarNumber').val() == '' ? ''
							: '%' + jQuery('#prepCarNumber').val() + '%',
							"vin" : jQuery('#vin').val() == '' ? ''
									: '%' + jQuery('#vin').val() + '%',
					"peccancyTime" : jQuery('#peccancyTime').val()
				};
			}
		});

/**
 * 提交form表单
 */
var submit = function(callBack) {
	var orderId = '';
	jQuery("input[name=orderId]").each(function() {
		if (this.checked) {
			orderId = jQuery(this).val();
		}
	});
	callBack(orderId);
};
