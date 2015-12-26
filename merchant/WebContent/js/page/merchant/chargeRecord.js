jQuery(document).ready(function() {
	jQuery("#type,#type1,#status,#status1").chosen();

	sitesCombox.requestData();

	jQuery("#beginTime,#endTime,#beginTime1,#endTime1").datepicker();
	normalCustomersTable.reloadData();

	// 普通客户
	jQuery('#queryBtn,#normalDiv').click(function() {
		normalCustomersTable.bStateSave = false; 
		normalCustomersTable.reloadData();
	});
	jQuery('#type,#status').change(function() {
		normalCustomersTable.bStateSave = false; 
		normalCustomersTable.reloadData();
	});
	jQuery('#excelBtn').click(function() {
		normalCustomersTable.exportExcel();
	});

	// 单位会员
	jQuery('#queryBtn1,#unitDiv').click(function() {
		unitCustomersTable.bStateSave = false; 
		unitCustomersTable.reloadData();
		unitSitesCombox.requestData();
	});
	jQuery('#type1,#status1').change(function() {
		unitCustomersTable.bStateSave = false; 
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
		normalCustomersTable.bStateSave = false; 
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
		unitCustomersTable.bStateSave = false; 
		unitCustomersTable.reloadData();
	}
});

var normalCustomersTable = new PageDataTables({
	tableId : 'normalCustomersTable',
	ajaxUrl : contextPath + "/merchant/chargeRecord/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/merchant/chargeRecord/exportToExcel.htm?",
	aoColumns : [
			{
				"mDataProp" : "idCard",
				"sClass" : "center"
			},
			{
				"mDataProp" : "phone",
				"sClass" : "center"
			},
			{
				"mDataProp" : "name",
				"sClass" : "center"
			},
			{
				"mDataProp" : "addBalance",
				"sClass" : "center"
			},
			{
				"mDataProp" : "type",
				"sClass" : "center"
			},
			{
				"mDataProp" : "createdTime",
				"sClass" : "center"
			},
			{
				"mDataProp" : "transactUser",
				"sClass" : "center"
			},
			{
				"mDataProp" : "siteName",
				"sClass" : "center"
			},
			{
				"mDataProp" : "describe",
				"sClass" : "center"
			},
			{
				"mDataProp" : "ticket",
				"sClass" : "center"
			},
			{
				"mDataProp" : "checkUser",
				"sClass" : "center"
			},
			{
				"mDataProp" : "checkDescribe",
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var name = obj.aData['name'];
					var status = obj.aData['status'];
					if (status == '已入账') {
						return '已核对入账';
					} else {
						return '<a href="javaScript:openCheckWin(' + id + ',\''
								+ name + '\')">核对入账</a>';
					}
				},
				"sClass" : "center"
			} ],
	beforeload : function() {
		this.paraData = {
			"idCard" : jQuery('#idCard').val() == '' ? '' : '%'
					+ jQuery('#idCard').val() + '%',
			"phone" : jQuery('#phone').val() == '' ? '' : '%'
					+ jQuery('#phone').val() + '%',
			"name" : jQuery('#name').val() == '' ? '' : '%'
					+ jQuery('#name').val() + '%',
			"transactUser" : jQuery('#transactUser').val() == '' ? '' : '%'
					+ jQuery('#transactUser').val() + '%',
			"checkUser" : jQuery('#checkUser').val() == '' ? '' : '%'
					+ jQuery('#checkUser').val() + '%',
			"type" : jQuery('#type').val(),
			"siteId" : jQuery('#siteId').val(),
			"status" : jQuery('#status').val(),
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59'
		};
	}
});

var unitCustomersTable = new PageDataTables({
	tableId : 'unitCustomersTable',
	ajaxUrl : contextPath + "/customer/unit/ajaxTradeData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/customer/unit/exportTradeToExcel.htm?",
	aoColumns : [
			{
				"mDataProp" : "enterpriseName",
				"sClass" : "center"
			},
			{
				"mDataProp" : "contactPerson",
				"sClass" : "center"
			},
			{
				"mDataProp" : "contactPhone",
				"sClass" : "center"
			},
			{
				"mDataProp" : "addBalance",
				"sClass" : "center"
			},
			{
				"mDataProp" : "type",
				"sClass" : "center"
			},
			{
				"mDataProp" : "createdTime",
				"sClass" : "center"
			},
			{
				"mDataProp" : "transactUser",
				"sClass" : "center"
			},
			{
				"mDataProp" : "siteName",
				"sClass" : "center"
			},
			{
				"mDataProp" : "describe",
				"sClass" : "center"
			},
			{
				"mDataProp" : "ticket",
				"sClass" : "center"
			},
			{
				"mDataProp" : "checkUser",
				"sClass" : "center"
			},
			{
				"mDataProp" : "checkDescribe",
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var name = obj.aData['enterpriseName'];
					var status = obj.aData['status'];
					if (status == '已入账') {
						return '已核对入账';
					} else {
						return '<a href="javaScript:openCheckWin(' + id + ',\''
								+ name + '\')">核对入账</a>';
					}
				},
				"sClass" : "center"
			} ],
	beforeload : function() {
		this.paraData = {
			"contactPerson" : jQuery('#contactPerson').val() == '' ? '' : '%'
					+ jQuery('#contactPerson').val() + '%',
			"enterpriseName" : jQuery('#enterpriseName').val() == '' ? '' : '%'
					+ jQuery('#enterpriseName').val() + '%',
			"contactPhone" : jQuery('#contactPhone').val() == '' ? '' : '%'
					+ jQuery('#contactPhone').val() + '%',
			"transactUser" : jQuery('#transactUser1').val() == '' ? '' : '%'
					+ jQuery('#transactUser1').val() + '%',
			"checkUser" : jQuery('#checkUser1').val() == '' ? '' : '%'
					+ jQuery('#checkUser1').val() + '%',
			"chargeCondition" : "yes",
			"type" : jQuery('#type1').val(),
			"siteId" : jQuery('#siteId1').val(),
			"status" : jQuery('#status1').val(),
			"beginTime" : jQuery('#beginTime1').val(),
			"endTime" : jQuery('#endTime1').val() == '' ? '' : jQuery(
					'#endTime1').val()
					+ ' 23:59:59'
		};
	}
});

// 打开核对收费页面
var openCheckWin = function(id, name) {
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 450;
	diag.Title = "核对收费";
	diag.URL = contextPath + "/customer/accountRecord/checkFee.htm?id=" + id;
	diag.MessageTitle = name;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};
