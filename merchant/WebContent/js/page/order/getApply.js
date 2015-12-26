jQuery(document).ready(function() {

	sitesCombox.requestData();

	waitSitesCombox.requestData();

	prepOrdersTable.reloadData();

	jQuery('#prepQueryBtn').click(function() {
		prepOrdersTable.bStateSave = false;
		prepOrdersTable.reloadData();
	});

	// 点击查询按钮事件
	jQuery('#waitQueryBtn,#waitDiv').click(function() {
		waitCarsTable.bStateSave = false;
		waitCarsTable.reloadData();
	});

});

// 预约取车选择框
var sitesCombox = new Combox({
	id : 'prepCurSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		prepOrdersTable.bStateSave = false;
		prepOrdersTable.reloadData();
	}
});

var waitSitesCombox = new Combox({
	id : 'waitCurSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		waitCarsTable.bStateSave = false;
		waitCarsTable.reloadData();
	}
});

// 预约取车列表
var prepOrdersTable = new PageDataTables(
		{
			tableId : 'prepOrdersTable',
			ajaxUrl : contextPath + "/order/order/ajaxData.htm?t="
					+ new Date().getTime(),
			aoColumns : [
					{

						"fnRender" : function(obj) {
							return '<img src="'
									+ contextPath
									+ obj.aData['littleIcon']
									+ '" onError="imgError(this)" alt="汽车图片" width="50px;" height="50px;" />';
						},
						"sClass" : "center"
					},
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
						"mDataProp" : "name",
						"sClass" : "center"
					},
					{
						"mDataProp" : "phone",
						"sClass" : "center"
					},
					{
						"mDataProp" : "schemingGetTime",
						"sClass" : "center"
					},
					{
						"mDataProp" : "gsiteName",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var deviceNO = obj.aData['deviceNO'];
							if (deviceNO == 'null' || deviceNO == '') {
								return obj.aData['ckms'];
							} else {
								return obj.aData['curKms'];
							}
						},
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var deviceNO = obj.aData['deviceNO'];
							if (deviceNO == 'null' || deviceNO == '') {
								return obj.aData['csurplusKms'];
							} else {
								return obj.aData['dsurplusKms'];
							}
						},
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
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var deviceStatus = obj.aData['deviceStatus'];
							if (deviceStatus == '故障') {
								return '<span style="color: red">'
										+ deviceStatus + '</span>';
							} else if (deviceStatus == '正常') {
								return deviceStatus;
							} else {
								return '';
							}
						},
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var cid = obj.aData['carId'];
							var orderId = obj.aData['id'];
							var carNumber = obj.aData['carNumber'];
							var deviceStatus = obj.aData['deviceStatus'];
							var deviceNO = obj.aData['deviceNO'];
							if (deviceStatus == '故障') {
								return '';
							} else {
								return '<a href="javaScript:getCar('
										+ cid
										+ ',\''
										+ carNumber
										+ '\','
										+ orderId
										+ ',\''
										+ deviceNO
										+ '\',\'wh_hours_price_type\')">取车</a></a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:deleteFun('
										+ orderId + ',1)" >取消</a>';
							}
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"name" : jQuery('#prepName').val() == '' ? '' : '%'
							+ jQuery('#prepName').val() + '%',
					"carNumber" : jQuery('#prepCarNumber').val() == '' ? ''
							: '%' + jQuery('#prepCarNumber').val() + '%',
					"phone" : jQuery('#prepUserPhone').val() == '' ? '' : '%'
							+ jQuery('#prepUserPhone').val() + '%',
					"relityGetSiteId" : jQuery('#prepCurSiteId').val(),
					"orderStatus" : '已预约'
				};
			}
		});

// 人工取车，空闲的车辆表格
var waitCarsTable = new PageDataTables(
		{
			tableId : 'waitCarsTable',
			ajaxUrl : contextPath + "/car/car/ajaxCarData.htm?t="
					+ new Date().getTime(),
			aoColumns : [
					{
						"fnRender" : function(obj) {
							return '<img src="'
									+ contextPath
									+ obj.aData['littleIcon']
									+ '" onError="imgError(this)" alt="汽车图片" width="50px;" height="50px;" />';
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "carNumber",
						"sClass" : "center"
					},
					{
						"mDataProp" : "manufacturerName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "typeName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "siteName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "status",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var deviceNO = obj.aData['deviceNO'];
							if (deviceNO == 'null' || deviceNO == '') {
								return obj.aData['kms'];
							} else {
								return obj.aData['curKms'];
							}
						},
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var deviceNO = obj.aData['deviceNO'];
							if (deviceNO == 'null' || deviceNO == '') {
								return obj.aData['csurplusKms'];
							} else {
								return obj.aData['dsurplusKms'];
							}
						},
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
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var deviceStatus = obj.aData['deviceStatus'];
							if (deviceStatus == '故障') {
								return '<span style="color: red">'
										+ deviceStatus + '</span>';
							} else if (deviceStatus == '正常') {
								return deviceStatus;
							} else {
								return '';
							}
						},
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var carNumber = obj.aData['carNumber'];
							var deviceStatus = obj.aData['deviceStatus'];
							if (deviceStatus == '故障') {
								return '';
							} else {
								return '<a href="javaScript:openGetWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',\'\',\'wh_hours_price_type\')">客户租车</a>&nbsp;&nbsp;|<span class="menu" style="margin-right: 3px;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:openGetWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',\'\',\'month_lease_price_type\')" >企业租车</a></li><li><a href="javaScript:openOtherGetWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',\'3\')" >车辆调度</a></li><li><a href="javaScript:openOtherGetWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',\'4\')" >公务用车</a></li></ul><li></ul></span>';
							}
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"carNumber" : jQuery('#waitCarNumber').val() == '' ? ''
							: '%' + jQuery('#waitCarNumber').val() + '%',
					"curSiteId" : jQuery('#waitCurSiteId').val(),
					"status" : '空闲',
					"flag" : '正常'
				};
			}
		});

var imgError = function(thiz) {
	thiz.src = contextPath + '/img/car.png';
};

// 预约取车发送开门命令
var getCar = function(cId, carNumber, orderId, deviceNO, tableName) {
	if (deviceNO == '' || deviceNO == null) {
		openGetWin(cId, carNumber, orderId, tableName);
		return;
	}
	Dialog.confirm('提示：确认取车?', function() {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + "/order/getApply/getBookCar.htm?",
			data : "orderId=" + orderId + "&carId=" + cId,
			async : false,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：取车成功", function() {
						location.reload(true);
					});
				} else {
					Dialog.alert("提示：" + data.info);
				}
			},
			error : function() {
				Dialog.alert("提示：取车失败");
			}
		});
	});
};

// 打开分时租赁套餐选择页面
var openGetWin = function(cId, carNumber, orderId, tableName) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "填写取车信息";
	diag.URL = contextPath + "/order/getApply/getCarEdite.htm?cId=" + cId
			+ "&orderId=" + orderId + "&tableName=" + tableName;

	diag.MessageTitle = carNumber;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 打开车辆调度,公务用车选择页面
var openOtherGetWin = function(cId, carNumber, orderType) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "填写取车信息";
	diag.URL = contextPath + "/order/other/getApply/getCarEdite.htm?cId=" + cId
			+ "&orderType=" + orderType;

	diag.MessageTitle = carNumber;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};

// 点击取消租车按钮
var deleteFun = function(id, orderType) {
	Dialog.confirm('提示：确认取消该次租赁吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/order/order/cancel.htm?",
			data : "id=" + id + "&orderType=" + orderType,
			async : true,
			success : function(data) {
				var map = eval('(' + data + ')');
				if (map.success) {
					Dialog.alert("提示：取消租车订单成功", function() {
						location.reload(true);
					});
				} else {
					Dialog.alert("提示：取消租车订单失败");
				}
			},
			error : function() {
				Dialog.alert("提示：取消租车订单失败");
			}
		});
	});
};
