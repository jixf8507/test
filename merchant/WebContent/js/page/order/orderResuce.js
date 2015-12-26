jQuery(document).ready(function() {

	newResucesTable.reloadData();
	jQuery('#newQueryBtn').click(function() {
		newResucesTable.bStateSave=false; 
		newResucesTable.reloadData();
	});

	jQuery('#hisQueryBtn,#hisDiv').click(function() {
		historyResucesTable.bStateSave=false; 
		historyResucesTable.reloadData();
	});
});

var newResucesTable = new PageDataTables({
	tableId : 'newResucesTable',
	ajaxUrl : contextPath + "/order/resuce/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	aoColumns : [ // 设定各列宽度
	{
		"mDataProp" : "phone",
		"sClass" : "center"
	}, {
		"mDataProp" : "name",
		"sClass" : "center"
	}, {
		"mDataProp" : "info",
		"sClass" : "center"
	}, {
		"mDataProp" : "address",
		"sClass" : "center"
	}, {
		"mDataProp" : "carNumber",
		"sClass" : "center"
	}, {
		"mDataProp" : "createdTime",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#newCarNumber').val() == '' ? '' : '%'
					+ jQuery('#newCarNumber').val() + '%',
			"customerName" : jQuery('#newUserName').val() == '' ? '' : '%'
					+ jQuery('#newUserName').val() + '%',
			"phone" : jQuery('#newUserPhone').val() == '' ? '' : '%'
					+ jQuery('#newUserPhone').val() + '%',
			"payStatus" : '未支付'
		};
	}
});

var historyResucesTable = new PageDataTables({
	tableId : 'historyResucesTable',
	ajaxUrl : contextPath + "/order/resuce/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	aoColumns : [ // 设定各列宽度
	{
		"mDataProp" : "phone",
		"sClass" : "center"
	}, {
		"mDataProp" : "name",
		"sClass" : "center"
	}, {
		"mDataProp" : "info",
		"sClass" : "center"
	}, {
		"mDataProp" : "address",
		"sClass" : "center"
	}, {
		"mDataProp" : "carNumber",
		"sClass" : "center"
	}, {
		"mDataProp" : "checkUser",
		"sClass" : "center"
	}, {
		"mDataProp" : "payCost",
		"sClass" : "center"
	}, {
		"mDataProp" : "payStatus",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#hisCarNumber').val() == '' ? '' : '%'
					+ jQuery('#hisCarNumber').val() + '%',
			"customerName" : jQuery('#hisUserName').val() == '' ? '' : '%'
					+ jQuery('#hisUserName').val() + '%',
			"phone" : jQuery('#hisUserPhone').val() == '' ? '' : '%'
					+ jQuery('#hisUserPhone').val() + '%',
			"relityGetSiteId" : jQuery('#hisCurSiteId').val(),
			"payStatus" : '已支付'
		};
	}
});
