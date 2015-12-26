jQuery(document).ready(function() {

	jQuery("#priceTypeTableName,#priceTypeTableName1").chosen();

	returnSitesCombox.requestData();
	returnOrdersTable.reloadData();

	// 普通客户
	jQuery('#priceTypeTableName').change(function() {
		returnOrdersTable.bStateSave=false; 
		returnOrdersTable.reloadData();
	});
	jQuery('#returnQueryBtn,#normalDiv').click(function() {
		returnOrdersTable.bStateSave=false; 
		returnOrdersTable.reloadData();
	});
	// 企业会员
	jQuery('#queryBtn1,#unitDiv').click(function() {
		sitesCombox.requestData();
		getUnitOrdersTable.bStateSave=false; 
		getUnitOrdersTable.reloadData();
	});
	jQuery('#priceTypeTableName1').change(function() {
		getUnitOrdersTable.bStateSave=false; 
		getUnitOrdersTable.reloadData();
	});
	// 车辆调度
	jQuery('#queryBtn3,#controlDiv').click(function() {
		controlSitesCombox.requestData();
		getControlOrdersTable.bStateSave=false; 
		getControlOrdersTable.reloadData();
	});
	jQuery('#excelBtn3').click(function() {
		getControlOrdersTable.exportExcel();
	});
	// 公务用车
	jQuery('#queryBtn4,#officialDiv').click(function() {
		officialSitesCombox.requestData();
		getOfficialOrdersTable.bStateSave=false; 
		getOfficialOrdersTable.reloadData();
	});
	jQuery('#excelBtn4').click(function() {
		getOfficialOrdersTable.exportExcel();
	});
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

var sitesCombox = new Combox({
	id : 'relityGetSiteId1',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		getUnitOrdersTable.bStateSave=false; 
		getUnitOrdersTable.reloadData();
	}
});

var controlSitesCombox = new Combox({
	id : 'relityReturnSiteId3',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		getControlOrdersTable.bStateSave=false; 
		getControlOrdersTable.reloadData();
	}
});

var officialSitesCombox = new Combox({
	id : 'relityReturnSiteId4',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		getOfficialOrdersTable.bStateSave=false; 
		getOfficialOrdersTable.reloadData();
	}
});

// 已还车订单列表
var returnOrdersTable = new PageDataTables(
		{
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
					},
					{
						"mDataProp" : "carNumber",
						"sClass" : "center"
					},
					{
						"mDataProp" : "typeName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "type",
						"sClass" : "center"
					},
					{
						"mDataProp" : "name",
						"sClass" : "center"
					},
					{
						"mDataProp" : "phone",
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
						"mDataProp" : "orderStatus",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var carNumber = obj.aData['carNumber'];
							return '<a href="javaScript:openApplyWin('
									+ id
									+ ',\''
									+ carNumber
									+ '\',1)">收费</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:openDetailWin('
									+ id + ')">详情</a>';
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"carNumber" : jQuery('#returnCarNumber').val() == '' ? ''
							: '%' + jQuery('#returnCarNumber').val() + '%',
					"name" : jQuery('#returnUserName').val() == '' ? '' : '%'
							+ jQuery('#returnUserName').val() + '%',
					"phone" : jQuery('#returnUserPhone').val() == '' ? '' : '%'
							+ jQuery('#returnUserPhone').val() + '%',
					"relityReturnSiteId" : jQuery('#returnSiteId').val(),
					"orderStatus" : '已还车',
					"returnConfirm" : '已关门',
					"priceTypeTableName" : jQuery('#priceTypeTableName').val() == '' ? ''
							: jQuery('#priceTypeTableName').val()
				};
			}
		});

// 单位会员待取车审核订单列表
var getUnitOrdersTable = new PageDataTables(
		{
			tableId : 'getUnitOrdersTable',
			ajaxUrl : contextPath + "/order/unitOrder/ajaxData.htm?t="
					+ new Date().getTime(),
			aoColumns : [
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return ' DWHY_' + id;
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "carNumber",
						"sClass" : "center"
					},
					{
						"mDataProp" : "typeName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "type",
						"sClass" : "center"
					},
					{
						"mDataProp" : "enterpriseName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "contactPhone",
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
						"mDataProp" : "orderStatus",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var carNumber = obj.aData['carNumber'];
							return '<a href="javaScript:openApplyWin('
									+ id
									+ ',\''
									+ carNumber
									+ '\',2)">收费</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:openDetailWin('
									+ id + ')">详情</a>';
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"carNumber" : jQuery('#carNumber1').val() == '' ? '' : '%'
							+ jQuery('#carNumber1').val() + '%',
					"enterpriseName" : jQuery('#enterpriseName').val() == '' ? ''
							: '%' + jQuery('#enterpriseName').val() + '%',
					"contactPhone" : jQuery('#contactPhone').val() == '' ? ''
							: '%' + jQuery('#contactPhone').val() + '%',
					"relityGetSiteId" : jQuery('#relityGetSiteId1').val(),
					"orderStatus" : '已还车',
					"returnConfirm" : '已关门',
					"priceTypeTableName" : jQuery('#priceTypeTableName1').val() == '' ? ''
							: jQuery('#priceTypeTableName1').val()
				};
			}
		});

// 车辆调度待取车审核订单列表
var getControlOrdersTable = new PageDataTables(
		{
			tableId : 'getControlOrdersTable',
			ajaxUrl : contextPath + "/order/otherOrder/ajaxData.htm?t="
					+ new Date().getTime(),
			aoColumns : [
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
						"mDataProp" : "orderDescribe",
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
						"mDataProp" : "orderStatus",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var carNumber = obj.aData['carNumber'];
							return '<a href="javaScript:openApplyWin('
									+ id
									+ ',\''
									+ carNumber
									+ '\',3)">收费</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:openOtherDetailWin('
									+ id + ')">详情</a>';
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"carNumber" : jQuery('#carNumber3').val() == '' ? '' : '%'
							+ jQuery('#carNumber3').val() + '%',
					"userName" : jQuery('#userName').val() == '' ? '' : '%'
							+ jQuery('#userName').val() + '%',
					"mobilePhone" : jQuery('#mobilePhone').val() == '' ? ''
							: '%' + jQuery('#mobilePhone').val() + '%',
					"relityGetSiteId" : jQuery('#relityReturnSiteId3').val(),
					"orderStatus" : '已还车',
					"returnConfirm" : '已关门',
					"orderType" : 3
				};
			}
		});

// 公务用车待取车审核订单列表
var getOfficialOrdersTable = new PageDataTables(
		{
			tableId : 'getOfficialOrdersTable',
			ajaxUrl : contextPath + "/order/otherOrder/ajaxData.htm?t="
					+ new Date().getTime(),
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
						"mDataProp" : "orderDescribe",
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
						"mDataProp" : "orderStatus",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var carNumber = obj.aData['carNumber'];
							return '<a href="javaScript:openApplyWin('
									+ id
									+ ',\''
									+ carNumber
									+ '\',4)">收费</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:openOtherDetailWin('
									+ id + ')">详情</a>';
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"carNumber" : jQuery('#carNumber4').val() == '' ? '' : '%'
							+ jQuery('#carNumber4').val() + '%',
					"userName" : jQuery('#userName1').val() == '' ? '' : '%'
							+ jQuery('#userName1').val() + '%',
					"mobilePhone" : jQuery('#mobilePhone1').val() == '' ? ''
							: '%' + jQuery('#mobilePhone1').val() + '%',
					"relityGetSiteId" : jQuery('#relityGetSiteId4').val(),
					"orderStatus" : '已还车',
					"returnConfirm" : '已关门',
					"orderType" : 4
				};
			}
		});

// 打开租赁套餐选择页面
var openApplyWin = function(id, carNumber, orderType) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "租赁收费";
	diag.URL = contextPath + "/order/charge/edite.htm?id=" + id + "&orderType="
			+ orderType;
	diag.MessageTitle = carNumber;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	}, function() {

	};
	diag.show();
	// diag.addButton("okButton", "现金支付", function() {
	// Dialog.confirm('提示：确定使用现金支付?', function() {
	// diag.innerFrame.contentWindow.cashPaySubmit(callBack);
	// }, function() {
	// });
	// });
	// diag.okButton.value = "余额支付";
};

// 打开租赁详情页面
var openDetailWin = function(id) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 650;
	diag.Title = "查看订单详情";
	diag.URL = contextPath + "/order/order/detail.htm?id=" + id;
	diag.MessageTitle = 'ZCDD_' + id;
	diag.show();
};

// 打开公务用车、车辆调度详情页面
var openOtherDetailWin = function(id) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 650;
	diag.Title = "查看订单详情";
	diag.URL = contextPath + "/order/otherOrder/detail.htm?id=" + id;
	diag.MessageTitle = 'ZCDD_' + id;
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};
