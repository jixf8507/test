jQuery(document).ready(function() {

	carDeviceSimTable.reloadData();

	jQuery('#queryBtn,#normalDiv').click(function() {
		carDeviceSimTable.bStateSave=false; 
		carDeviceSimTable.reloadData();
	});

	jQuery('#excelBtn').click(function() {
		carDeviceSimTable.exportExcel();
	});

	jQuery('#queryBtn1,#delDiv').click(function() {
		carDeviceSimDelTable.bStateSave=false; 
		carDeviceSimDelTable.reloadData();
	});

	jQuery('#excelBtn1').click(function() {
		carDeviceSimDelTable.exportExcel();
	});

	jQuery('#addBtn').live('click',function() {
		openAddWin();
	});

});

var carDeviceSimTable = new PageDataTables(
		{
			tableId : 'carDeviceSimTable',
			ajaxUrl : contextPath + "/car/device/ajaxSimData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/car/device/exportSimToExcel.htm?",
			aoColumns : [
					{
						"mDataProp" : "simCard",
						"sClass" : "center"
					},
					{
						"mDataProp" : "facilitator",
						"sClass" : "center"
					},
					{
						"mDataProp" : "flowOfMonth",
						"sClass" : "center"
					},
					{
						"mDataProp" : "deviceNO",
						"sClass" : "center"
					},
					{
						"mDataProp" : "carNumber",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var carTableName = obj.aData['carTableName'];
							if (carTableName == 'car') {
								return '商家车辆';
							} else if (carTableName == 'customer_cars') {
								return '客户车辆';
							}
							return '';
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "status",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var simCard = obj.aData['simCard'];
							var deviceNO = obj.aData['deviceNO'];
							return '<a href="javaScript:openSelectCarWin('
									+ id
									+ ',\''
									+ simCard
									+ '\')">选择</a>&nbsp;&nbsp;|<span class="menu" style="margin-right: 10px;"><ul><li><a class="drop" href="javaScript:openMore('
									+ id
									+ ');">更多</a><ul id="ulList"><li><a href="javaScript:openEditeWin('
									+ id
									+ ',\''
									+ simCard
									+ '\')">修改</a></li><li><a href="javaScript:openResetWin('
									+ id
									+ ',\''
									+ deviceNO
									+ '\',\''
									+ simCard
									+ '\')">移除</a></li><li><a href="javaScript:openDelWin('
									+ id + ',\'' + simCard
									+ '\')">废弃</a></li></ul><li></ul></span>';
						},
						"sClass" : "center"
					} ],
					addButton : [
					             {"tableId":"carDeviceSimTable","outId":"addBtn","value":"新增"},
					            ],
			beforeload : function() {
				this.paraData = {
					"simCard" : jQuery('#simCard').val() == '' ? '' : '%'
							+ jQuery('#simCard').val() + '%',
					"deviceNO" : jQuery('#deviceNO').val() == '' ? '' : '%'
							+ jQuery('#deviceNO').val() + '%',
					"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
							+ jQuery('#carNumber').val() + '%',
					"status" : "正常"

				};
			}
		});

var carDeviceSimDelTable = new PageDataTables({
	tableId : 'carDeviceSimDelTable',
	ajaxUrl : contextPath + "/car/device/ajaxSimData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/car/device/exportSimToExcel.htm?",
	aoColumns : [ {
		"mDataProp" : "simCard",
		"sClass" : "center"
	}, {
		"mDataProp" : "facilitator",
		"sClass" : "center"
	}, {
		"mDataProp" : "flowOfMonth",
		"sClass" : "center"
	}, {
		"mDataProp" : "status",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"simCard" : jQuery('#simCard1').val() == '' ? '' : '%'
					+ jQuery('#simCard1').val() + '%',
			"status" : "删除"

		};
	}
});

var simId = '';

// 打开选择安装车辆
var openSelectCarWin = function(id, simCard) {
	simId = id;
	var diag = new Dialog();
	diag.Width = 700;
	diag.Height = 500;
	diag.Title = "安装设备选择";
	diag.URL = contextPath + "/page/car/selectCarDevice.jsp?noSim=yes&simId="
			+ id;
	diag.MessageTitle = simCard;
	diag.Message = "设备选择";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 点击删除按钮
var openDelWin = function(id, simCard) {
	Dialog.confirm('警告：确认删除设备卡 [' + simCard + '] 吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/car/device/deleteSim.htm?",
			data : "id=" + id,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：删除成功", function() {
						location.reload(true);
					});

				} else {
					Dialog.alert("提示：删除失败");
				}
			},
			error : function() {
				Dialog.alert("错误：删除失败");
			}
		});
	});
};

// 点击移除按钮
var openResetWin = function(id, deviceNO, simCard) {
	if (deviceNO == '' || deviceNO == null) {
		Dialog.alert("提示：该设备卡未绑定车载设备");
		return;
	}
	Dialog.confirm('警告：确认移除 [' + simCard + '] 车载设备吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/car/device/resetSim.htm?",
			data : "id=" + id,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：移除成功", function() {
						location.reload(true);
					});

				} else {
					Dialog.alert("提示：移除失败");
				}
			},
			error : function() {
				Dialog.alert("错误：移除失败");
			}
		});
	});
};

var openAddWin = function() {
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 400;
	diag.Title = "新增车载设备卡";
	diag.URL = contextPath + "/car/device/add.htm?";
	diag.MessageTitle = "新增车载设备卡";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

var openEditeWin = function(id, simCard) {
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 400;
	diag.Title = "修改车载设备卡";
	diag.URL = contextPath + "/car/device/edite.htm?id=" + id;
	diag.MessageTitle = simCard;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

var callBackEdite = function() {
	location.reload();
};

// 保存方法
var callBack = function() {
	location.reload();
};