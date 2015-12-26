jQuery(document).ready(function() {

	sitesCombox.requestData();

	jQuery("#beginTime,#endTime,#beginTime1,#endTime1").datepicker();
	normalCustomersTable.reloadData();

	// 车辆调度
	jQuery('#queryBtn,#normalDiv').click(function() {
		normalCustomersTable.bStateSave=false;
		normalCustomersTable.reloadData();
	});
	jQuery('#excelBtn').click(function() {
		normalCustomersTable.exportExcel();
	});

	// 公务用车
	jQuery('#queryBtn1,#unitDiv').click(function() {
		unitSitesCombox.requestData();
		unitCustomersTable.bStateSave=false;
		unitCustomersTable.reloadData();
	});
	jQuery('#excelBtn1').click(function() {
		unitCustomersTable.exportExcel();
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
		normalCustomersTable.bStateSave=false;
		normalCustomersTable.reloadData();
	}
});

var unitSitesCombox = new Combox({
	id : 'siteId1',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		unitCustomersTable.bStateSave=false;
		unitCustomersTable.reloadData();
	}
});

var aoColumns = [
		{
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				return ' CLDD_' + id;
			},
			"sClass" : "center"
		},
		{
			"mDataProp" : "carNumber",
			"sClass" : "center"
		},
		{
			"mDataProp" : "userName",
			"sClass" : "center"
		},
		{
			"mDataProp" : "mobilePhone",
			"sClass" : "center"
		},
		{
			"mDataProp" : "realityGetTime",
			"sClass" : "center"
		},
		{
			"mDataProp" : "gsiteName",
			"sClass" : "center"
		},
		{
			"mDataProp" : "menForGet",
			"sClass" : "center"
		},
		{
			"mDataProp" : "realityReturnTime",
			"sClass" : "center"
		},
		{
			"mDataProp" : "rsiteName",
			"sClass" : "center"
		},
		{
			"mDataProp" : "menForReturn",
			"sClass" : "center"
		},
		{
			"mDataProp" : "kms",
			"sClass" : "center"
		},
		{
			"fnRender" : function(obj) {
				return '<a href="javaScript:openDetailWin(' + obj.aData['id']
						+ ')">查看详情</a>';
			},
			"sClass" : "center"
		} ];

var normalCustomersTable = new PageDataTables({
	tableId : 'normalCustomersTable',
	ajaxUrl : contextPath + "/order/otherOrder/ajaxTableData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/order/otherOrder/exportTotleToExcel.htm?",
	aoColumns : aoColumns,
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
					+ jQuery('#carNumber').val() + '%',
			"userName" : jQuery('#userName').val() == '' ? '' : '%'
					+ jQuery('#userName').val() + '%',
			"mobilePhone" : jQuery('#mobilePhone').val() == '' ? '' : '%'
					+ jQuery('#mobilePhone').val() + '%',
			"menForGet" : jQuery('#menForGet').val() == '' ? '' : '%'
					+ jQuery('#menForGet').val() + '%',
			"menForReturn" : jQuery('#menForReturn').val() == '' ? '' : '%'
					+ jQuery('#menForReturn').val() + '%',
			"siteId" : jQuery('#siteId').val(),
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59',
			"orderStatus" : '已付费',
			"orderType" : 3,
			"orderId" : jQuery('#orderId').val() == '' ? '' : Number(jQuery(
					'#orderId').val())
		};
	}
});

var unitCustomersTable = new PageDataTables({
	tableId : 'unitCustomersTable',
	ajaxUrl : contextPath + "/order/otherOrder/ajaxTableData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/order/otherOrder/exportTotleToExcel.htm?",
	aoColumns : [
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					return ' GWYC_' + id;
				},
				"sClass" : "center"
			},
			{
				"mDataProp" : "carNumber",
				"sClass" : "center"
			},
			{
				"mDataProp" : "userName",
				"sClass" : "center"
			},
			{
				"mDataProp" : "mobilePhone",
				"sClass" : "center"
			},
			{
				"mDataProp" : "realityGetTime",
				"sClass" : "center"
			},
			{
				"mDataProp" : "gsiteName",
				"sClass" : "center"
			},
			{
				"mDataProp" : "menForGet",
				"sClass" : "center"
			},
			{
				"mDataProp" : "realityReturnTime",
				"sClass" : "center"
			},
			{
				"mDataProp" : "rsiteName",
				"sClass" : "center"
			},
			{
				"mDataProp" : "menForReturn",
				"sClass" : "center"
			},
			{
				"mDataProp" : "kms",
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					return '<a href="javaScript:openDetailWin('
							+ obj.aData['id'] + ')">查看详情</a>';
				},
				"sClass" : "center"
			} ],
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#carNumber1').val() == '' ? '' : '%'
					+ jQuery('#carNumber1').val() + '%',
			"userName" : jQuery('#userName1').val() == '' ? '' : '%'
					+ jQuery('#userName1').val() + '%',
			"mobilePhone" : jQuery('#mobilePhone1').val() == '' ? '' : '%'
					+ jQuery('#mobilePhone1').val() + '%',
			"menForGet" : jQuery('#menForGet1').val() == '' ? '' : '%'
					+ jQuery('#menForGet1').val() + '%',
			"menForReturn" : jQuery('#menForReturn1').val() == '' ? '' : '%'
					+ jQuery('#menForReturn1').val() + '%',
			"siteId" : jQuery('#siteId1').val(),
			"beginTime" : jQuery('#beginTime1').val(),
			"endTime" : jQuery('#endTime1').val() == '' ? '' : jQuery(
					'#endTime1').val()
					+ ' 23:59:59',
			"orderStatus" : '已付费',
			"orderType" : 4,
			"orderId" : jQuery('#orderId1').val() == '' ? '' : Number(jQuery(
					'#orderId1').val())
		};
	}
});

// 打开租赁详情页面
var openDetailWin = function(id) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 650;
	diag.Title = "查看订单详情";
	diag.URL = contextPath + "/order/otherOrder/detail.htm?id=" + id;
	diag.MessageTitle = 'ZCDD_' + id;
	diag.show();
};
