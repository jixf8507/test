jQuery(document).ready(function() {

	sitesCombox.requestData();
	sitesTable.reloadData();
	
	jQuery('#status').chosen();

	jQuery('#queryBtn').click(function() {
		sitesTable.bStateSave = false;
		sitesTable.reloadData();
	});
	
	jQuery('#status').change(function() {
		sitesTable.bStateSave = false;
		sitesTable.reloadData();
	});

	// 点击导出
	jQuery('#excelBtn').click(function() {
		sitesTable.exportExcel();
	});
});

// 选择框
var sitesCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		sitesTable.bStateSave = false;
		sitesTable.reloadData();
	}
});

var sitesTable = new PageDataTables({
	tableId : 'sitesTable',
	ajaxUrl : contextPath + "/parking/space/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/parking/space/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : [ // 设定各列宽度
	{
		"mDataProp" : "siteName",
		"sClass" : "center"
	}, {
		"mDataProp" : "spaceNO",
		"sClass" : "center"
	}, {
		"mDataProp" : "area",
		"sClass" : "center"
	}, {
		"mDataProp" : "factoryNo",
		"sClass" : "center"
	}, {
		"mDataProp" : "deviceNo",
		"sClass" : "center"
	}, {
		"mDataProp" : "nameplate",
		"sClass" : "center"
	}, {
		"mDataProp" : "position",
		"sClass" : "center"
	}, {
		"mDataProp" : "status",
		"sClass" : "center"
	}],
	beforeload : function() {
		this.paraData = {
			"siteId" : jQuery('#siteId').val(),
			"status" : jQuery('#status').val(),
			"spaceNO" : jQuery('#spaceNO').val() == '' ? '' : '%'
					+ jQuery('#spaceNO').val() + '%',
			"factoryNoLike" : jQuery('#factoryNo').val() == '' ? '' : '%'
					+ jQuery('#factoryNo').val() + '%'
		};
	}
});
