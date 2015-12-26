jQuery(document).ready(function() {

	jQuery(".chzn-select").chosen();
	jQuery("#beginTime,#endTime,#beginTime1,#endTime1").datepicker();
	normalCustomersTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn,#normalDiv').click(function() {
		normalCustomersTable.bStateSave=false;//点击查询的时候清除cookies里面的分页缓存。
		normalCustomersTable.reloadData();
	});
	// 点击查询按钮事件
	jQuery('#type').change(function() {
		normalCustomersTable.bStateSave=false;//清除cookies里面的分页缓存。
		normalCustomersTable.reloadData();
	});
	// 点击导出
	jQuery('#excelBtn').click(function() {
		normalCustomersTable.exportExcel();
	});
	
	// 点击查询按钮事件
	jQuery('#queryBtn1,#unitDiv').click(function() {
		unitCustomersTable.bStateSave=false;//清除cookies里面的分页缓存。
		unitCustomersTable.reloadData();
	});
	// 点击查询按钮事件
	jQuery('#type1').change(function() {
		unitCustomersTable.bStateSave=false;//清除cookies里面的分页缓存。
		unitCustomersTable.reloadData();
	});
	// 点击导出
	jQuery('#excelBtn1').click(function() {
		unitCustomersTable.exportExcel();
	});

});

var normalCustomersTable = new PageDataTables({
	tableId : 'normalCustomersTable',
	ajaxUrl : contextPath + "/customer/accountRecord/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/customer/accountRecord/exportToExcel.htm?",
	aoColumns : [ {
		"mDataProp" : "idCard",
		"sClass" : "center"
	}, {
		"mDataProp" : "phone",
		"sClass" : "center"
	}, {
		"mDataProp" : "name",
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
		"mDataProp" : "createdTime",
		"sClass" : "center"
	}, {
		"mDataProp" : "ticket",
		"sClass" : "center"
	}, {
		"mDataProp" : "transactUser",
		"sClass" : "center"
	}, {
		"mDataProp" : "describe",
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
			"type" : jQuery('#type').val(),
			"beginTime" : jQuery('#beginTime').val() == '' ? '' : jQuery(
					'#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + " 23:59:59"
		};
	}
});

var unitCustomersTable = new PageDataTables({
	tableId : 'unitCustomersTable',
	ajaxUrl : contextPath + "/customer/unit/ajaxTradeData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/customer/unit/exportTradeToExcel.htm?",
	aoColumns : [ {
		"mDataProp" : "enterpriseName",
		"sClass" : "center"
	}, {
		"mDataProp" : "contactPerson",
		"sClass" : "center"
	}, {
		"mDataProp" : "contactPhone",
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
		"mDataProp" : "createdTime",
		"sClass" : "center"
	},{
		"mDataProp" : "ticket",
		"sClass" : "center"
	}, {
		"mDataProp" : "transactUser",
		"sClass" : "center"
	}, {
		"mDataProp" : "describe",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"enterpriseName" : jQuery('#enterpriseName').val() == '' ? '' : '%'
					+ jQuery('#enterpriseName').val() + "%",
			"contactPerson" : jQuery('#contactPerson').val() == '' ? '' : '%'
					+ jQuery('#contactPerson').val() + "%",
			"contactPhone" : jQuery('#contactPhone').val() == '' ? '' : '%'
					+ jQuery('#contactPhone').val() + "%",
			"type" : jQuery('#type1').val(),
			"beginTime" : jQuery('#beginTime1').val() == '' ? '' : jQuery(
					'#beginTime1').val(),
			"endTime" : jQuery('#endTime1').val() == '' ? '' : jQuery(
					'#endTime1').val()
					+ " 23:59:59"
		};
	}
});
