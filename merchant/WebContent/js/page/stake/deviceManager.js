jQuery(document).ready(function() {

	sitesCombox.requestData();
	sitesTable.reloadData();

	jQuery('#status,#status1,#isupdateprice,#isupdateprice1').chosen();

	jQuery('#status,#isupdateprice').change(function() {
		sitesTable.bStateSave = false;
		sitesTable.reloadData();
	});

	jQuery('#queryBtn,#otherDiv').click(function() {
		sitesTable.bStateSave = false;
		sitesTable.reloadData();
	});

	// 点击导出
	jQuery('#excelBtn').click(function() {
		sitesTable.exportExcel();
	});

	jQuery('#queryBtn1,#normalDiv').click(function() {
		sitesCombox1.requestData();
		normalTable.bStateSave = false;
		normalTable.reloadData();
	});

	jQuery('#status1,#isupdateprice1').change(function() {
		normalTable.bStateSave = false;
		normalTable.reloadData();
	});

	// 点击导出
	jQuery('#excelBtn1').click(function() {
		normalTable.exportExcel();
	});

	jQuery('#addBtn').live('click', function() {
		opendeviceadd();
	});
});

// 选择框
var sitesCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择充电站',
	change : function() {
		sitesTable.reloadData();
	}
});

var sitesCombox1 = new Combox({
	id : 'siteId1',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择充电站',
	change : function() {
		normalTable.reloadData();
	}
});

var sitesTable = new PageDataTables(
		{
			tableId : 'sitesTable',
			ajaxUrl : contextPath + "/stake/device/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/stake/device/exportToExcel.htm?",
			aoColumns : [
					{
						"fnRender" : function(obj) {
							return '<img src="'
									+ contextPath
									+ obj.aData['imgUrl']
									+ '" onError="imgError(this)" alt="汽车图片" width="50px;" height="50px;" />';
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "factoryNo",
						"sClass" : "center"
					},
					{
						"mDataProp" : "deviceNo",
						"sClass" : "center"
					},
					{
						"mDataProp" : "nameplate",
						"sClass" : "center"
					},
					{
						"mDataProp" : "siteName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "area_name",
						"sClass" : "center"
					},
					{
						"mDataProp" : "name",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var status = obj.aData['isupdateprice'];
							if (status == '1') {
								return '已下发';
							} else {
								return '未下发';
							}
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "deviceTypeName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "manufacturer",
						"sClass" : "center"
					},
					{
						"mDataProp" : "devicePower",
						"sClass" : "center"
					},
					{
						"mDataProp" : "fixedVoltage",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var status = obj.aData['status'];
							if (status == '废弃') {
								return '不联网';
							} else {
								return status;
							}
						},
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var factoryNo = obj.aData['factoryNo'];
							return '<a href="javaScript:openEditeWin('
									+ id
									+ ',\''
									+ factoryNo
									+ '\')">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;'
									+ '<div class="menu" style="margin-right: 0;"><ul><li><a class="drop" href="javaScript:openMore('
									+ id
									+ ');">更多</a><ul id="ulList"><li><a href="javaScript:deleteFun('
									+ id
									+ ',\''
									+ factoryNo
									+ '\')"  >删除</a></li><li><a href="javaScript:openPriceWin('
									+ id + ',\'' + factoryNo
									+ '\');">选择</a></li></ul></li></ul></div>';
						},
						"sClass" : "center"
					} ],
			addButton : [ {
				"outId" : "addBtn",
				"value" : "新增"
			}, ],
			beforeload : function() {
				this.paraData = {
					"isupdateprice" : jQuery('#isupdateprice').val(),
					"status" : jQuery('#status').val(),
					"siteId" : jQuery('#siteId').val(),
					"factoryNo" : jQuery('#factoryNo').val() == '' ? '' : '%'
							+ jQuery('#factoryNo').val() + '%',
					"flag" : '正常',
					"deviceType" : 1
				};
			}
		});

var normalTable = new PageDataTables(
		{
			tableId : 'normalTable',
			ajaxUrl : contextPath + "/stake/device/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/stake/device/exportToExcel.htm?",
			aoColumns : [
					{
						"fnRender" : function(obj) {
							return '<img src="'
									+ contextPath
									+ obj.aData['imgUrl']
									+ '" onError="imgError(this)" alt="汽车图片" width="50px;" height="50px;" />';
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "factoryNo",
						"sClass" : "center"
					},
					{
						"mDataProp" : "deviceNo",
						"sClass" : "center"
					},
					{
						"mDataProp" : "nameplate",
						"sClass" : "center"
					},
					{
						"mDataProp" : "siteName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "area_name",
						"sClass" : "center"
					},
					{
						"mDataProp" : "name",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var status = obj.aData['isupdateprice'];
							if (status == '1') {
								return '已下发';
							} else {
								return '未下发';
							}
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "deviceTypeName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "manufacturer",
						"sClass" : "center"
					},
					{
						"mDataProp" : "devicePower",
						"sClass" : "center"
					},
					{
						"mDataProp" : "fixedVoltage",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var status = obj.aData['status'];
							if (status == '废弃') {
								return '不联网';
							} else {
								return status;
							}
						},
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var factoryNo = obj.aData['factoryNo'];
							var deviceTypeNo = obj.aData['deviceTypeNo'];// "0"子桩子；"2"总桩子；"1"其他
							if (deviceTypeNo == 0) {
								return '<a href="javaScript:openChildEditeWin('
										+ id
										+ ',\''
										+ factoryNo
										+ '\')">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;'
										+ '<div class="menu" style="margin-right: 0;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:deleteFun('
										+ id
										+ ',\''
										+ factoryNo
										+ '\')"  >删除</a></li></ul></li></ul></div>';
							} else {
								return '<a href="javaScript:openParentEditeWin('
										+ id
										+ ',\''
										+ factoryNo
										+ '\')">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;'
										+ '<div class="menu" style="margin-right: 0;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:deleteFun('
										+ id
										+ ',\''
										+ factoryNo
										+ '\')"  >删除</a></li><li><a href="javaScript:openPriceWin('
										+ id
										+ ',\''
										+ factoryNo
										+ '\');">选择</a></li></ul></li></ul></div>';
							}
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"isupdateprice" : jQuery('#isupdateprice1').val(),
					"status" : jQuery('#status1').val(),
					"siteId" : jQuery('#siteId1').val(),
					"factoryNo" : jQuery('#factoryNo1').val() == '' ? '' : '%'
							+ jQuery('#factoryNo1').val() + '%',
					"flag" : '正常',
					"deviceType" : 30
				};
			}
		});

// 打开租赁套餐选择页面
var openPriceWin = function(id, factoryNo) {
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 300;
	diag.Title = "充电桩充电收费套餐选择";
	diag.URL = contextPath + "/page/stake/selectPrice.jsp?id=" + id;
	diag.MessageTitle = factoryNo;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 打开新增窗口
var opendeviceadd = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "新增充电设备";
	diag.URL = contextPath + "/stake/device/add.htm?";
	diag.MessageTitle = "新增充电设备";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);

	};
	diag.show();
};

// 打开充电设备编辑页面
var openEditeWin = function(id, factoryNo) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "充电设备编辑";
	diag.URL = contextPath + "/stake/device/edite.htm?id=" + id;
	diag.MessageTitle = factoryNo;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 打开充电设备编辑页面(1托30子桩子)
var openChildEditeWin = function(id, factoryNo) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "充电设备编辑";
	diag.URL = contextPath + "/stake/device/childEdite.htm?id=" + id;
	diag.MessageTitle = factoryNo;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 打开充电设备编辑页面(1托30主桩子)
var openParentEditeWin = function(id, factoryNo) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "充电设备编辑";
	diag.URL = contextPath + "/stake/device/parentEdite.htm?id=" + id;
	diag.MessageTitle = factoryNo;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};

// 删除套餐
var deleteFun = function(id, factoryNo) {
	Dialog.confirm('警告：确认删除该充电设备 [' + factoryNo + '] 吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/stake/device/delete.htm?",
			data : "id=" + id,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：删除成功", function() {
						location.reload(true);
					});

				} else {
					Dialog.alert("提示：操作失败");
				}
			},
			error : function() {
				Dialog.alert("提示：操作失败");
			}
		});
	});
};

function imgError(thiz) {
	thiz.src = contextPath + '/img/car.png';
}