jQuery(document).ready(function() {

	jQuery("#beginTime,#endTime").datepicker();

	costsTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		costsTable.bStateSave = false; 
		costsTable.reloadData();
	});

	// 点击查询按钮事件
	jQuery('#excelBtn').click(function() {
		costsTable.exportExcel();
	});

});

var costsTable = new PageDataTables({
	tableId : 'costsTable',
	ajaxUrl : contextPath + "/statistics/car/ajaxData.htm?t="
			+ new Date().getTime(), 
	exportUrl : contextPath + "/statistics/car/exportToExcel.htm?", 
	aoColumns : [ 
	{
		"mDataProp" : "carNumber",
		"sClass" : "center"
	}, {
		"mDataProp" : "typeName",
		"sClass" : "center"
	}, {
		"mDataProp" : "manufacturerName",
		"sClass" : "center"
	}, {
		"mDataProp" : "cs",
		"sClass" : "center"
	}, {
		"mDataProp" : "ksm",
		"sClass" : "center"
	}, {
		"mDataProp" : "cost",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
					+ jQuery('#carNumber').val() + '%',
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59'
		};
	}
});
