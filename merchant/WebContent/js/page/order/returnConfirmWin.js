jQuery(document).ready(function() {

	jQuery("#priceTypeTableName,#priceTypeTableName1").chosen();

	sitesCombox.requestData();

	getOrdersTable.reloadData();

	// 普通客户
	jQuery('#queryBtn,#normalDiv').click(function() {
		getOrdersTable.reloadData();
	});
	jQuery('#priceTypeTableName').change(function() {
		getOrdersTable.reloadData();
	});
	// 车辆调度
	jQuery('#queryBtn3,#controlDiv').click(function() {
		controlSitesCombox.requestData();
		getControlOrdersTable.reloadData();
	});
	jQuery('#excelBtn3').click(function() {
		getControlOrdersTable.exportExcel();
	});
	// 公务用车
	jQuery('#queryBtn4,#officialDiv').click(function() {
		officialSitesCombox.requestData();
		getOfficialOrdersTable.reloadData();
	});
	jQuery('#excelBtn4').click(function() {
		getOfficialOrdersTable.exportExcel();
	});
});

var sitesCombox = new Combox({
	id : 'relityReturnSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		jQuery('#siteId').val(jQuery('#relityReturnSiteId').val());
		getOrdersTable.reloadData();
	},
	beforeload : function() {
		this.emptyValue = jQuery("#siteId").val();
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
		jQuery('#siteId').val(jQuery('#relityReturnSiteId3').val());
		getControlOrdersTable.reloadData();
	},
	beforeload : function() {
		this.emptyValue = jQuery("#siteId").val();
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
		jQuery('#siteId').val(jQuery('#relityReturnSiteId4').val());
		getOfficialOrdersTable.reloadData();
	},
	beforeload : function() {
		this.emptyValue = jQuery("#siteId").val();
	}
});

var getOrdersTable = new PageDataTables(
		{
			tableId : 'getOrdersTable',
			iDisplayLength : 5,
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
						"mDataProp" : "realityReturnTime",
						"sClass" : "center"
					},
					{
						"mDataProp" : "rsiteName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "returnCarConfirm",
						"sClass" : "center"
					},
					{
						"mDataProp" : "menForReturn",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var deviceNO = obj.aData['deviceNO'];
							if (deviceNO == 'null' || deviceNO == '') {
								return '未安装设备';
							}
							return deviceNO;
						},
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var carNumber = obj.aData['carNumber'];
							var currentCarStatus = obj.aData['currentCarStatus'];
							var returnCarConfirm = obj.aData['returnCarConfirm'];
							var deviceNO = obj.aData['deviceNO'];
							if (returnCarConfirm == '已审查' && deviceNO != 'null'
									&& deviceNO != '') {
								return '<a href="javaScript:contorlCarClose('
										+ obj.aData['id']
										+ ',\''
										+ deviceNO
										+ '\',1)">还车</a>&nbsp;&nbsp;|'
										+ '<div class="menu" style="margin-right:5px; position:relative;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:cancel('
										+ id
										+ ',\''
										+ carNumber
										+ '\',\''
										+ currentCarStatus
										+ '\')">取消还车</a></li><li><a href="javaScript:openDetailWin('
										+ obj.aData['id']
										+ ')">查看详情</a></li></ul><li></ul></div>';
							} else {
								return '<a href="javaScript:openApplyWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',\''
										+ currentCarStatus
										+ '\',1)">审查</a>&nbsp;&nbsp;|'
										+ '<div class="menu"  style="margin-right:5px; position:relative;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:cancel('
										+ id
										+ ',\''
										+ carNumber
										+ '\',\''
										+ currentCarStatus
										+ '\')">取消还车</a></li><li><a href="javaScript:openDetailWin('
										+ obj.aData['id']
										+ ')">查看详情</a></li></ul><li></ul></div>';
							}
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
							+ jQuery('#carNumber').val() + '%',
					"relityReturnSiteId" : jQuery('#siteId').val(),
					"orderStatus" : '已还车',
					"returnCarConfirm" : '等待审查',
					"priceTypeTableName" : jQuery('#priceTypeTableName').val() == '' ? ''
							: jQuery('#priceTypeTableName').val()
				};
			}
		});


var aoColumns = [
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
			"mDataProp" : "realityReturnTime",
			"sClass" : "center"
		},
		{
			"mDataProp" : "rsiteName",
			"sClass" : "center"
		},
		{
			"mDataProp" : "orderDescribe",
			"sClass" : "center"
		},
		{
			"mDataProp" : "returnCarConfirm",
			"sClass" : "center"
		},
		{
			"mDataProp" : "menForReturn",
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
				var returnCarConfirm = obj.aData['returnCarConfirm'];
				var deviceNO = obj.aData['deviceNO'];
				if (returnCarConfirm == '已审查' && deviceNO != 'null'
						&& deviceNO != '') {
					return '<a href="javaScript:contorlOtherCarClose('
							+ obj.aData['id']
							+ ',\''
							+ deviceNO
							+ '\',3)">还车</a>&nbsp;&nbsp;|'
							+ '<div class="menu" style="margin-right:5px; position:relative;"><ul><li><a class="drop" href="javaScript:openMore('
							+ id
							+ ');">更多</a><ul id="ulList"><li><a href="javaScript:cancel('
							+ id
							+ ',\''
							+ carNumber
							+ '\',\''
							+ currentCarStatus
							+ '\')">取消还车</a></li><li><a href="javaScript:openOtherDetailWin('
							+ obj.aData['id']
							+ ')">查看详情</a></li></ul><li></ul></div>';
				} else {
					return '<a href="javaScript:openOtherApplyWin('
							+ id
							+ ',\''
							+ carNumber
							+ '\',\''
							+ currentCarStatus
							+ '\',3)">审查</a>&nbsp;&nbsp;|'
							+ '<div class="menu"  style="margin-right:5px; position:relative;"><ul><li><a class="drop" href="javaScript:openMore('
							+ id
							+ ');">更多</a><ul id="ulList"><li><a href="javaScript:cancel('
							+ id
							+ ',\''
							+ carNumber
							+ '\',\''
							+ currentCarStatus
							+ '\')">取消还车</a></li><li><a href="javaScript:openOtherDetailWin('
							+ obj.aData['id']
							+ ')">查看详情</a></li></ul><li></ul></div>';
				}
			},
			"sClass" : "center"
		} ];

//车辆调度待取车审核订单列表
var getControlOrdersTable = new PageDataTables(
		{
			tableId : 'getControlOrdersTable',
			iDisplayLength : 5,
			ajaxUrl : contextPath + "/order/otherOrder/ajaxData.htm?t="
					+ new Date().getTime(),
			aoColumns : aoColumns,
			beforeload : function() {
				this.paraData = {
					"carNumber" : jQuery('#carNumber3').val() == '' ? '' : '%'
							+ jQuery('#carNumber3').val() + '%',
					"relityReturnSiteId" : jQuery('#siteId').val(),
					"orderStatus" : '已还车',
					"returnCarConfirm" : '等待审查',
					"orderType" : 3
				};
			}
		});

//公务用车待取车审核订单列表
var getOfficialOrdersTable = new PageDataTables(
		{
			tableId : 'getOfficialOrdersTable',
			iDisplayLength : 5,
			ajaxUrl : contextPath + "/order/otherOrder/ajaxData.htm?t="
					+ new Date().getTime(),
			aoColumns : aoColumns,
			beforeload : function() {
				this.paraData = {
						"carNumber" : jQuery('#carNumber4').val() == '' ? '' : '%'
							+ jQuery('#carNumber4').val() + '%',
					"relityReturnSiteId" : jQuery('#siteId').val(),
					"orderStatus" : '已还车',
					"returnCarConfirm" : '等待审查',
					"orderType" : 4
				};
			}
		});

// 发送关门命令
var contorlCarClose = function(id, deviceNO,orderType) {
	Dialog.confirm('确认发送还车命令吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/monitor/main/ajaxSendCarCmd.htm?",
			data : "Order=9&DeviceNO=" + deviceNO + "&BookID=" + id+"&orderType="+orderType,
			async : true,
			success : function(data) {
				var map = eval('(' + data + ')');

				if (map.success) {
					Dialog.alert("提示：还车成功", function() {
						callBack();
					});

				} else {
					Dialog.alert("提示：" + map.info);
				}
			},
			error : function() {
				Dialog.alert("错误：还车失败");
			}
		});
	});
};

// 发送关门命令
var contorlOtherCarClose = function(id, deviceNO,orderType) {
	Dialog.confirm('确认发送还车命令吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/monitor/main/ajaxSendOtherCarCmd.htm?",
			data : "Order=9&DeviceNO=" + deviceNO + "&BookID=" + id+"&orderType="+orderType,
			async : true,
			success : function(data) {
				var map = eval('(' + data + ')');
				
				if (map.success) {
					Dialog.alert("提示：还车成功", function() {
						callBack();
					});
					
				} else {
					Dialog.alert("提示：" + map.info);
				}
			},
			error : function() {
				Dialog.alert("错误：还车失败");
			}
		});
	});
};


// 打开还车审核页面
var openApplyWin = function(id, carNumber, currentCarStatus,orderType) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 650;
	diag.Title = "还车审查";
	diag.URL = contextPath + "/order/returnConfirm/edite.htm?id=" + id
			+ "&currentCarStatus=" + currentCarStatus+"&orderType="+orderType;
	diag.MessageTitle = carNumber;
	diag.OKEvent = function() {
		Dialog.confirm('确定收费正确，确定后不可以再修改', function() {
		diag.innerFrame.contentWindow.submit(callBack);
		diag.close();
		});
	};
	diag.show();
};

// 打开还车审核页面
var openOtherApplyWin = function(id, carNumber, currentCarStatus,orderType) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 650;
	diag.Title = "还车审查";
	diag.URL = contextPath + "/order/returnConfirm/editeOther.htm?id=" + id
	+ "&currentCarStatus=" + currentCarStatus+"&orderType="+orderType;
	diag.MessageTitle = carNumber;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
		diag.close();
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

// 打开租赁详情页面
var openOtherDetailWin = function(id) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 650;
	diag.Title = "查看订单详情";
	diag.URL = contextPath + "/order/otherOrder/detail.htm?id=" + id;
	diag.MessageTitle = 'ZCDD_' + id;
	diag.show();
};

// 取消还车
var cancel = function(id, carNumber, currentCarStatus) {
	Dialog.confirm('确认取消还车?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/order/returnApply/cancel.htm?",
			data : "id=" + id,
			async : true,
			success : function(data) {
				var map = eval('(' + data + ')');
				if (map.success) {
					Dialog.alert("提示：取消还车成功", function() {
						callBack();
					});
				} else {
					Dialog.alert("提示：取消还车失败");
				}
			},
			error : function() {
				Dialog.alert("错误：取消还车失败");
			}
		});
	});
};

// 保存方法
var callBack = function() {
	location.reload();
};
