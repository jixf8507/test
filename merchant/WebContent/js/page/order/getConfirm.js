jQuery(document).ready(function() {

	jQuery("#priceTypeTableName,#priceTypeTableName1").chosen();

	prepCurSitesCombox.requestData();

	getOrdersTable.reloadData();
	// 普通客户
	jQuery('#queryBtn,#normalDiv').click(function() {
		getOrdersTable.bStateSave=false; 
		getOrdersTable.reloadData();
	});
	jQuery('#priceTypeTableName').change(function() {
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

var prepCurSitesCombox = new Combox({
	id : 'relityGetSiteId',
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

// 待取车审核订单列表
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
					// {
					// "fnRender" : function(obj) {
					// var deviceNO = obj.aData['deviceNO'];
					// if (deviceNO == 'null' || deviceNO == '') {
					// return '未安装设备';
					// }
					//
					// return '<a href="javaScript:contorlCar('
					// + obj.aData['id'] + ',\'' + deviceNO
					// + '\')">开门</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a
					// href="javaScript:contorlCarClose('
					// + obj.aData['id'] + ',\'' + deviceNO
					// + '\')">关门</a>';
					// },
					// "sClass" : "center"
					// },
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var carNumber = obj.aData['carNumber'];
							var currentCarStatus = obj.aData['currentCarStatus'];
							var deviceNO = obj.aData['deviceNO'];
							if (deviceNO == 'null' || deviceNO == '') {
								return '<a href="javaScript:openApplyWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',1)">审查</a>&nbsp;&nbsp;|&nbsp;&nbsp;'
										+ '<div class="menu" style="margin-right: 5px;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:deleteFun('
										+ id
										+ ',\''
										+ currentCarStatus
										+ '\',\'租车\',1)">取消租车</a></li><li><a href="javaScript:openDetailWin('
										+ id
										+ ',1)">查看详情</a></li></ul><li></ul></div>';
							} else {
								return '<a href="javaScript:openApplyWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',1)">审查</a>&nbsp;&nbsp;|&nbsp;&nbsp;<span class="menu" style="margin-right: 5px;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:openDetailWin('
										+ id
										+ ',1)">查看详情</a></li></ul><li></ul></span>';
							}

						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
							+ jQuery('#carNumber').val() + '%',
					"name" : jQuery('#name').val() == '' ? '' : '%'
							+ jQuery('#name').val() + '%',
					"phone" : jQuery('#phone').val() == '' ? '' : '%'
							+ jQuery('#phone').val() + '%',
					"relityGetSiteId" : jQuery('#relityGetSiteId').val(),
					"type" : 'getApply',
					"orderStatus" : '已取车',
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
			exportUrl : contextPath + "/order/unitOrder/exportUseToExcel.htm?",
			aoColumns : [
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return 'DWHY_' + id;
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
							var currentCarStatus = obj.aData['currentCarStatus'];
							var deviceNO = obj.aData['deviceNO'];
							if (deviceNO == 'null' || deviceNO == '') {
								return '<a href="javaScript:openApplyWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',2)">审查</a>&nbsp;&nbsp;|&nbsp;&nbsp;'
										+ '<div class="menu" style="margin-right: 0;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:deleteFun('
										+ id
										+ ',\''
										+ currentCarStatus
										+ '\',\'租车\',2)">取消租车</a></li><li><a href="javaScript:openDetailWin('
										+ id
										+ ',2)">查看详情</a></li></ul><li></ul></div>';
							} else {
								return '<a href="javaScript:openApplyWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',2)">审查</a>&nbsp;&nbsp;|&nbsp;&nbsp;<span class="menu" style="margin-right: 0;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:openDetailWin('
										+ id
										+ ',2)">查看详情</a></li></ul><li></ul></span>';
							}

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
					"type" : 'getApply',
					"orderStatus" : '已取车',
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
							var currentCarStatus = obj.aData['currentCarStatus'];
							var deviceNO = obj.aData['deviceNO'];
							if (deviceNO == 'null' || deviceNO == '') {
								return '<a href="javaScript:openApplyWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',3)">审查</a>&nbsp;&nbsp;|&nbsp;&nbsp;'
										+ '<div class="menu" style="margin-right: 15px;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:deleteFun('
										+ id
										+ ',\''
										+ currentCarStatus
										+ '\',\'车辆调度\',3)">取消调度</a></li><li><a href="javaScript:openOtherDetailWin('
										+ id
										+ ',3)">查看详情</a></li></ul><li></ul></div>';
							} else {
								return '<a href="javaScript:openApplyWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',3)">审查</a>&nbsp;&nbsp;|&nbsp;&nbsp;<span class="menu" style="margin-right: 15px;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:openOtherDetailWin('
										+ id
										+ ',3)">查看详情</a></li></ul><li></ul></span>';
							}

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
					"type" : 'getApply',
					"orderStatus" : '已取车',
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
							return 'GWYC_' + id;
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
							var currentCarStatus = obj.aData['currentCarStatus'];
							var deviceNO = obj.aData['deviceNO'];
							if (deviceNO == 'null' || deviceNO == '') {
								return '<a href="javaScript:openApplyWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',4)">审查</a>&nbsp;&nbsp;|&nbsp;&nbsp;'
										+ '<div class="menu" style="margin-right: 15px;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:deleteFun('
										+ id
										+ ',\''
										+ currentCarStatus
										+ '\',\'公务用车\',3)">取消用车</a></li><li><a href="javaScript:openOtherDetailWin('
										+ id
										+ ',4)">查看详情</a></li></ul><li></ul></div>';
							} else {
								return '<a href="javaScript:openApplyWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',4)">审查</a>&nbsp;&nbsp;|&nbsp;&nbsp;<span class="menu" style="margin-right: 15px;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:openOtherDetailWin('
										+ id
										+ ',4)">查看详情</a></li></ul><li></ul></span>';
							}

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
					"type" : 'getApply',
					"orderType" : 4
				};
			}
		});

/*// 点击取车按钮
var contorlCar = function(id, deviceNO) {
	Dialog.confirm('确认发送开门命令吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/monitor/main/ajaxSendCarCmdOpen.htm?",
			data : "Order=6&DeviceNO=" + deviceNO + "&BookID=" + id+"&orderType="+orderType,
			async : true,
			success : function(data) {
				var map = eval('(' + data + ')');

				if (map.success) {
					Dialog.alert("提示：开门成功", function() {
						callBack();
					});
				} else {
					alert("提示：开门失败！" + map.info);
					location.reload(true);
				}
			},
			error : function() {
				alert("错误：开门失败");
			}
		});
	});
};
// 点击关门按钮
var contorlCarClose = function(id, deviceNO) {
	Dialog.confirm('确认发送关门命令吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/monitor/main/ajaxSendCarCmdOpen.htm?",
			data : "Order=7&DeviceNO=" + deviceNO + "&BookID=" + id+"&orderType="+orderType,
			async : true,
			success : function(data) {
				var map = eval('(' + data + ')');
				if (map.success) {
					Dialog.alert("提示：关门成功", function() {
						callBack();
					});
				} else {
					alert("提示：关门失败！" + map.info);
				}
			},
			error : function() {
				alert("错误：关门失败");
			}
		});
	});
};*/

// 打开租赁套餐选择页面
var openApplyWin = function(id, carNumber, orderType) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 650;
	diag.Title = "取车审查";
	diag.URL = contextPath + "/order/getConfirm/edite.htm?id=" + id
			+ "&orderType=" + orderType;
	diag.MessageTitle = carNumber;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 打开租赁详情页面
var openDetailWin = function(id,orderType) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 650;
	diag.Title = "查看订单详情";
	diag.URL = contextPath + "/order/order/detail.htm?id=" + id+"&orderType="+orderType;
	diag.MessageTitle = 'ZCDD_' + id;
	diag.show();
};

// 打开租赁详情页面
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

// 点击取消租车按钮
var deleteFun = function(id, currentCarStatus,type,orderType) {
	// if(currentCarStatus == '已取车'){
	// Dialog.alert("请先发送关门命令");
	// }else{

	Dialog.confirm('确认取消该次'+type+'吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/order/order/cancel.htm?",
			data : "id=" + id+"&orderType="+orderType,
			async : true,
			success : function(data) {
				var map = eval('(' + data + ')');
				if (map.success) {
					Dialog.alert("提示：取消"+type+"订单成功", function() {
						callBack();
					});
				} else {
					Dialog.alert("提示：取消"+type+"订单失败");
				}
			},
			error : function() {
				Dialog.alert("错误：取消"+type+"订单失败");
			}
		});
	});
	// }
};