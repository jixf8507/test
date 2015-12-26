jQuery(document).ready(function() {

	jQuery("#type").chosen();

	sitesCombox.requestData();

	jQuery("#beginTime,#endTime").datepicker();

	drawsTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		drawsTable.bStateSave = false; 
		drawsTable.reloadData();
	});

	// 点击查询按钮事件
	jQuery('#type').change(function() {
		drawsTable.bStateSave = false; 
		drawsTable.reloadData();
	});

	// 点击查询按钮事件
	jQuery('#excelBtn').click(function() {
		drawsTable.exportExcel();
	});
});

var sitesCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		drawsTable.bStateSave = false; 
		drawsTable.reloadData();
	}
}); 

var drawsTable = new PageDataTables({
	tableId : 'drawsTable',
	ajaxUrl : contextPath + "/merchant/accountRecord/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/merchant/accountRecord/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : [ // 设定各列宽度
	{

		"mDataProp" : "addBalance",
		"sClass" : "center"
	}, {
		"mDataProp" : "newBalance",
		"sClass" : "center"
	}, {
		"mDataProp" : "type",
		"sClass" : "center"
	}, {
		"mDataProp" : "siteName",
		"sClass" : "center"
	}, {
		"mDataProp" : "transactUser",
		"sClass" : "center"
	}, {
		"mDataProp" : "createdTime",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"siteId" : jQuery('#siteId').val(),
			"type" : jQuery('#type').val(),
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59'
		};
	}
});
