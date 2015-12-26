jQuery(document).ready(function() {
	normalCustomersTable.reloadData();

	// 点击查询按钮事件
	jQuery('#normalQueryBtn').click(function() {
		normalCustomersTable.reloadData();
	});

});

var column = [ // 设定各列宽度
		{
			"fnRender" : function(obj) {
				var noCarDevice = jQuery('#noCarDevice').val();
				var id = obj.aData['id'];
				var carNumber = obj.aData['carNumber'];
				var deviceNO = obj.aData['deviceNO'];
				if (noCarDevice == 'yes' && deviceNO != '') {
					return '';
				}
				return '<input style="width: 20px;" type="radio" name="id" id="'
						+ id + '" value="' + id + '_' + carNumber + '" >';
			},
			"sClass" : "center"
		}, {
			"mDataProp" : "carNumber",
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
			"mDataProp" : "deviceNO",
			"sClass" : "center"
		} ];

var normalCustomersTable = new PageDataTables({
	tableId : 'normalCustomersTable',
	ajaxUrl : contextPath + "/customer/cars/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/customer/cars/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : column,
	beforeload : function() {
		this.paraData = {
			"phone" : jQuery('#normalPhone').val() == '' ? '' : '%'
					+ jQuery('#normalPhone').val() + "%",
			"name" : jQuery('#normalName').val() == '' ? '' : '%'
					+ jQuery('#normalName').val() + "%",
			"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
					+ jQuery('#carNumber').val() + "%",
			"customerId" : jQuery('#customerId').val()
		};
	}
});

var submit = function(callBack) {
	var selId = '';
	jQuery("input[name=id]").each(function() {
		if (this.checked) {
			selId = jQuery(this).val();
		}
	});
	var arr = selId.split('_');
	var data = {
		carId : arr[0],
		carNumber : arr[1],
		carTableName : 'customer_cars'
	};
	callBack(data);
};