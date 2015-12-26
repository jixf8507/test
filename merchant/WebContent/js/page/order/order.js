jQuery(document).ready(function() {

	prepSitesCombox.requestData();
	getSitesCombox.requestData();
	returnSitesCombox.requestData();
	cancelSitesCombox.requestData();

	prepOrdersTable.reloadData();

	jQuery('#prepQueryBtn').click(function() {
		prepOrdersTable.bStateSave=false; 
		prepOrdersTable.reloadData();
	});

	jQuery('#getQueryBtn,#getitleId').click(function() {
		getOrdersTable.bStateSave=false; 
		getOrdersTable.reloadData();
	});

	jQuery('#returnQueryBtn,#returntitleId').click(function() {
		returnOrdersTable.bStateSave=false; 
		returnOrdersTable.reloadData();
	});

	jQuery('#cancelQueryBtn,#canceltitleId').click(function() {
		cancelOrdersTable.bStateSave=false; 
		cancelOrdersTable.reloadData();
	});

});

// 预约取车租赁点选择框
var prepSitesCombox = new Combox({
	id : 'prepSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
	+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		prepOrdersTable.bStateSave=false; 
		prepOrdersTable.reloadData();
	}
});

// 实际取车租赁点选择框
var getSitesCombox = new Combox({
	id : 'getSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
	+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		getOrdersTable.bStateSave=false; 
		getOrdersTable.reloadData();
	}
});

// 实际还车租赁点选择框
var returnSitesCombox = new Combox({
	id : 'returnSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
	+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		returnOrdersTable.bStateSave=false; 
		returnOrdersTable.reloadData();
	}
});

// 取消预约取车租赁点选择框
var cancelSitesCombox = new Combox({
	id : 'cancelSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
	+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		cancelOrdersTable.bStateSave=false; 
		cancelOrdersTable.reloadData();
	}
});

// 已预约订单列表
var prepOrdersTable = new PageDataTables({
	tableId : 'prepOrdersTable',
	ajaxUrl : contextPath + "/order/order/ajaxData.htm?t="
			+ new Date().getTime(), 
	aoColumns : [  
	{
		"fnRender" : function(obj) {
			var id = obj.aData['id'];
			return ' ZCDD_' + id;
		},
		"sClass" : "center"
	}, {
		"mDataProp" : "carNumber",
		"sClass" : "center"
	}, {
		"mDataProp" : "typeName",
		"sClass" : "center"
	}, {
		"mDataProp" : "name",
		"sClass" : "center"
	}, {
		"mDataProp" : "phone",
		"sClass" : "center"
	}, {
		"mDataProp" : "schemingGetTime",
		"sClass" : "center"
	}, {
		"mDataProp" : "gsiteName",
		"sClass" : "center"
	}],
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#prepCarNumber').val() == '' ? '' : '%'
					+ jQuery('#prepCarNumber').val() + '%',
			"name" : jQuery('#prepUserName').val() == '' ? '' : '%'
					+ jQuery('#prepUserName').val() + '%',
			"phone" : jQuery('#prepUserPhone').val() == '' ? '' : '%'
					+ jQuery('#prepUserPhone').val() + '%',
			"relityGetSiteId" : jQuery('#prepSiteId').val(),
			"orderStatus" : '已预约'
		};
	}
});

// 已取车订单列表
var getOrdersTable = new PageDataTables({
	tableId : 'getOrdersTable',
	ajaxUrl : contextPath + "/order/order/ajaxData.htm?t="
			+ new Date().getTime(), 
	aoColumns : [  
	{
		"fnRender" : function(obj) {
			var id = obj.aData['id'];
			return ' ZCDD_' + id;
		},
		"sClass" : "center"
	}, {
		"mDataProp" : "carNumber",
		"sClass" : "center"
	}, {
		"mDataProp" : "typeName",
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
		"mDataProp" : "orderStatus",
		"sClass" : "center"
	}, {
		"mDataProp" : "subOrderStatus",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#getCarNumber').val() == '' ? '' : '%'
					+ jQuery('#getCarNumber').val() + '%',
			"name" : jQuery('#getUserName').val() == '' ? '' : '%'
					+ jQuery('#getUserName').val() + '%',
			"phone" : jQuery('#getUserPhone').val() == '' ? '' : '%'
					+ jQuery('#getUserPhone').val() + '%',
			"relityGetSiteId" : jQuery('#getSiteId').val(),
			"orderStatus" : '已取车'
		};
	}
});

// 已还车订单列表
var returnOrdersTable = new PageDataTables({
	tableId : 'returnOrdersTable',
	ajaxUrl : contextPath + "/order/order/ajaxData.htm?t="
			+ new Date().getTime(), 
	aoColumns : [  
	{
		"fnRender" : function(obj) {
			var id = obj.aData['id'];
			return ' ZCDD_' + id;
		},
		"sClass" : "center"
	}, {
		"mDataProp" : "carNumber",
		"sClass" : "center"
	}, {
		"mDataProp" : "typeName",
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
	}, {
		"mDataProp" : "orderStatus",
		"sClass" : "center"
	}, {
		"mDataProp" : "subOrderStatus",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#returnCarNumber').val() == '' ? '' : '%'
					+ jQuery('#returnCarNumber').val() + '%',
			"name" : jQuery('#returnUserName').val() == '' ? '' : '%'
					+ jQuery('#returnUserName').val() + '%',
			"phone" : jQuery('#returnUserPhone').val() == '' ? '' : '%'
					+ jQuery('#returnUserPhone').val() + '%',
			"relityReturnSiteId" : jQuery('#returnSiteId').val(),
			"orderStatus" : '已还车'
		};
	}
});

// 已取车车辆列表
var cancelOrdersTable = new PageDataTables({
	tableId : 'cancelOrdersTable',
	ajaxUrl : contextPath + "/order/order/ajaxData.htm?t="
			+ new Date().getTime(), 
	aoColumns : [  
	{
		"fnRender" : function(obj) {
			var id = obj.aData['id'];
			return ' ZCDD_' + id;
		},
		"sClass" : "center"
	}, {
		"mDataProp" : "carNumber",
		"sClass" : "center"
	}, {
		"mDataProp" : "typeName",
		"sClass" : "center"
	}, {
		"mDataProp" : "name",
		"sClass" : "center"
	}, {
		"mDataProp" : "phone",
		"sClass" : "center"
	}, {
		"mDataProp" : "schemingGetTime",
		"sClass" : "center"
	}, {
		"mDataProp" : "schemingReturnTime",
		"sClass" : "center"
	}, {
		"mDataProp" : "orderStatus",
		"sClass" : "center"
	}, {
		"mDataProp" : "subOrderStatus",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#cancelCarNumber').val() == '' ? '' : '%'
					+ jQuery('#cancelCarNumber').val() + '%',
			"name" : jQuery('#cancelUserName').val() == '' ? '' : '%'
					+ jQuery('#cancelUserName').val() + '%',
			"phone" : jQuery('#cancelUserPhone').val() == '' ? '' : '%'
					+ jQuery('#cancelUserPhone').val() + '%',
			"relityGetSiteId" : jQuery('#cancelSiteId').val(),
			"orderStatus" : '已取消'
		};
	}
});