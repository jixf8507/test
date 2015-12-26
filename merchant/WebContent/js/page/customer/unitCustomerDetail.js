jQuery(document).ready(function() {

	jQuery(".chzn-select").chosen();

	jQuery("#beginTime,#endTime").datepicker();

	jQuery('#queryBtn,#tradeDiv').click(function() {
		tradeTable.bStateSave = false;
		tradeTable.reloadData();
	});
	jQuery('#type').change(function() {
		tradeTable.bStateSave = false;
		tradeTable.reloadData();
	});
	jQuery('#excelBtn').click(function() {
		tradeTable.exportExcel();
	});

	jQuery('#customerQueryBtn,#customerDiv').click(function() {
		customerTable.bStateSave = false;
		customerTable.reloadData();
	});
	jQuery('#customerExcelBtn').click(function() {
		customerTable.exportExcel();
	});

});

var tradeTable = new PageDataTables({
	tableId : 'tradeTable',
	iDisplayLength : 6,
	ajaxUrl : contextPath + "/customer/unit/ajaxTradeData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/customer/unit/exportTradeToExcel.htm?",
	aoColumns : [ {
		"mDataProp" : "createdTime",
		"sClass" : "center"
	}, {
		"mDataProp" : "addBalance",
		"sClass" : "center"
	}, {
		"mDataProp" : "newBalance",
		"sClass" : "center"
	}, {
		"mDataProp" : "type",
		"sClass" : "center"
	}, {
		"mDataProp" : "describe",
		"sClass" : "center"
	}, {
		"mDataProp" : "transactUser",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"type" : jQuery('#type').val(),
			"customerId" : jQuery('#customerId').val(),
			"beginTime" : jQuery('#beginTime').val() == '' ? '' : jQuery(
					'#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + " 23:59:59"
		};
	}
});

var customerTable = new PageDataTables({
	tableId : 'customerTable',
	iDisplayLength : 6,
	bSort : false,
	ajaxUrl : contextPath + "/customer/unit/ajaxCustomerData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/customer/unit/exportCustomerToExcel.htm?",
	aoColumns : [
			{
				"mDataProp" : "phone",
				"sClass" : "center"
			},
			{
				"mDataProp" : "name",
				"sClass" : "center"
			},
			{
				"mDataProp" : "idCard",
				"sClass" : "center"
			},
			{
				"mDataProp" : "moneyOfassure",
				"sClass" : "center"
			},
			{
				"mDataProp" : "balance",
				"sClass" : "center"
			},
			{
				"mDataProp" : "status",
				"sClass" : "center"
			}],
	beforeload : function() {
		this.paraData = {
			"enterpriseId" : jQuery('#customerId').val(),
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
