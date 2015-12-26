jQuery(document).ready(function() {

	jQuery("#priceTypeTableName,#priceTypeTableName1").chosen();

	getSitesCombox.requestData();

	getOrdersTable.reloadData();
	// 普通客户
	jQuery('#priceTypeTableName').change(function() {
		getOrdersTable.bStateSave=false; 
		getOrdersTable.reloadData();
	});
	jQuery('#getQueryBtn,#normalDiv').click(function() {
		getOrdersTable.bStateSave=false; 
		getOrdersTable.reloadData();
	});
	// 企业会员
	jQuery('#queryBtn1,#unitDiv').click(function() {
		sitesCombox.requestData();
		getUnitOrdersTable.bStateSave=false; 
		getUnitOrdersTable.reloadData();
	});
	jQuery('#excelBtn1').click(function() {
		getUnitOrdersTable.exportExcel();
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
	id : 'relityGetSiteId3',
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
	id : 'relityGetSiteId4',
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

// 已取车订单列表
var getOrdersTable = new PageDataTables(
		{
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
						"mDataProp" : "orderStatus",
						"sClass" : "center"
					},
					{
						"mDataProp" : "menForGet",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var deviceNO = obj.aData['deviceNO'];
							if (deviceNO == 'null' || deviceNO == '') {
								return '未安装设备';
							} else {
								return deviceNO;
							}
						},
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var carNumber = obj.aData['carNumber'];
							return '<a href="javaScript:openGetWin('
									+ id
									+ ',\''
									+ carNumber
									+ '\')">申请还车</a>&nbsp;&nbsp;|<br/><a href="javaScript:openDetailWin('
									+ id + ')">查看详情</a>';
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"carNumber" : jQuery('#getCarNumber').val() == '' ? ''
							: '%' + jQuery('#getCarNumber').val() + '%',
					"name" : jQuery('#getUserName').val() == '' ? '' : '%'
							+ jQuery('#getUserName').val() + '%',
					"phone" : jQuery('#getUserPhone').val() == '' ? '' : '%'
							+ jQuery('#getUserPhone').val() + '%',
					"relityGetSiteId" : jQuery('#getSiteId').val(),
					"orderStatus" : '已取车',
					"priceTypeTableName" : jQuery('#priceTypeTableName').val() == '' ? ''
							: jQuery('#priceTypeTableName').val()
				// "getConfirm" : '客户同意'
				};
			}
		});

// 单位会员待取车审核订单列表
var getUnitOrdersTable = new PageDataTables(
		{
			tableId : 'getUnitOrdersTable',
			ajaxUrl : contextPath + "/order/unitOrder/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/order/unitOrder/exportUseToExcel.htm?",
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
						"mDataProp" : "orderStatus",
						"sClass" : "center"
					},
					{
						"mDataProp" : "menForGet",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var deviceNO = obj.aData['deviceNO'];
							if (deviceNO == 'null' || deviceNO == '') {
								return '未安装设备';
							} else {
								return deviceNO;
							}
						},
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var carNumber = obj.aData['carNumber'];
							return '<a href="javaScript:openGetWin('
									+ id
									+ ',\''
									+ carNumber
									+ '\')">申请还车</a>&nbsp;&nbsp;|<br/><a href="javaScript:openDetailWin('
									+ id + ')">查看详情</a>';
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
					"orderStatus" : '已取车',
					"priceTypeTableName" : jQuery('#priceTypeTableName1').val() == '' ? ''
							: jQuery('#priceTypeTableName1').val()
				};
			}
		});

var aoColums = [
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
			"mDataProp" : "orderDescribe",
			"sClass" : "center"
		},
		{
			"mDataProp" : "orderStatus",
			"sClass" : "center"
		},
		{
			"mDataProp" : "menForGet",
			"sClass" : "center"
		},
		{
			"fnRender" : function(obj) {
				var deviceNO = obj.aData['deviceNO'];
				if (deviceNO == 'null' || deviceNO == '') {
					return '未安装设备';
				} else {
					return deviceNO;
				}
			},
		},
		{
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				var carNumber = obj.aData['carNumber'];
				return '<a href="javaScript:openOtherGetWin('
						+ id
						+ ',\''
						+ carNumber
						+ '\')">申请还车</a>&nbsp;&nbsp;|<br/><a href="javaScript:openOtherDetailWin('
						+ obj.aData['id'] + ')">查看详情</a>';
			},
			"sClass" : "center"
		} ];

// 车辆调度待取车审核订单列表
var getControlOrdersTable = new PageDataTables(
		{
			tableId : 'getControlOrdersTable',
			ajaxUrl : contextPath + "/order/otherOrder/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/order/otherOrder/exportToExcel.htm?",
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
			    			"mDataProp" : "realityGetTime",
			    			"sClass" : "center"
			    		},
			    		{
			    			"mDataProp" : "gsiteName",
			    			"sClass" : "center"
			    		},
			    		{
			    			"mDataProp" : "orderDescribe",
			    			"sClass" : "center"
			    		},
			    		{
			    			"mDataProp" : "orderStatus",
			    			"sClass" : "center"
			    		},
			    		{
			    			"mDataProp" : "menForGet",
			    			"sClass" : "center"
			    		},
			    		{
			    			"fnRender" : function(obj) {
			    				var deviceNO = obj.aData['deviceNO'];
			    				if (deviceNO == 'null' || deviceNO == '') {
			    					return '未安装设备';
			    				} else {
			    					return deviceNO;
			    				}
			    			},
			    		},
			    		{
			    			"fnRender" : function(obj) {
			    				var id = obj.aData['id'];
			    				var carNumber = obj.aData['carNumber'];
			    				return '<a href="javaScript:openOtherGetWin('
			    						+ id
			    						+ ',\''
			    						+ carNumber
			    						+ '\')">申请还车</a>&nbsp;&nbsp;|<br/><a href="javaScript:openOtherDetailWin('
			    						+ obj.aData['id'] + ')">查看详情</a>';
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
					"relityGetSiteId" : jQuery('#relityGetSiteId3').val(),
					"orderStatus" : '已取车',
					"priceTypeTableName" : jQuery('#priceTypeTableName1').val() == '' ? ''
							: jQuery('#priceTypeTableName1').val(),
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
			exportUrl : contextPath + "/order/otherOrder/exportToExcel.htm?",
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
			    			"mDataProp" : "orderDescribe",
			    			"sClass" : "center"
			    		},
			    		{
			    			"mDataProp" : "orderStatus",
			    			"sClass" : "center"
			    		},
			    		{
			    			"mDataProp" : "menForGet",
			    			"sClass" : "center"
			    		},
			    		{
			    			"fnRender" : function(obj) {
			    				var deviceNO = obj.aData['deviceNO'];
			    				if (deviceNO == 'null' || deviceNO == '') {
			    					return '未安装设备';
			    				} else {
			    					return deviceNO;
			    				}
			    			},
			    		},
			    		{
			    			"fnRender" : function(obj) {
			    				var id = obj.aData['id'];
			    				var carNumber = obj.aData['carNumber'];
			    				return '<a href="javaScript:openOtherGetWin('
			    						+ id
			    						+ ',\''
			    						+ carNumber
			    						+ '\')">申请还车</a>&nbsp;&nbsp;|<br/><a href="javaScript:openOtherDetailWin('
			    						+ obj.aData['id'] + ')">查看详情</a>';
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
					"orderStatus" : '已取车',
					"priceTypeTableName" : jQuery('#priceTypeTableName1').val() == '' ? ''
							: jQuery('#priceTypeTableName1').val(),
					"orderType" : 4
				};
			}
		});

var imgError = function(thiz) {
	thiz.src = contextPath + '/img/car.png';
};

// 打开租赁套餐选择页面
var openGetWin = function(id, carNumber) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "填写还车信息";
	diag.URL = contextPath + "/order/returnApply/returnCarEdite.htm?orderId="
			+ id;
	diag.MessageTitle = carNumber;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 打开租赁套餐选择页面
var openOtherGetWin = function(id, carNumber) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "填写还车信息";
	diag.URL = contextPath + "/order/returnApply/returnOtherCarEdite.htm?orderId="
	+ id;
	diag.MessageTitle = carNumber;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
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

//打开租赁详情页面
var openOtherDetailWin = function(id,orderType) {
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
