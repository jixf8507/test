jQuery(document).ready(function() {
	jQuery('input:file').uniform();

	carDeviceTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		carDeviceTable.bStateSave=false; 
		carDeviceTable.reloadData();
	});

	// 点击导出
	jQuery('#excelBtn').click(function() {
		carDeviceTable.exportExcel();
	});

});

// 车载设备列表
var carDeviceTable = new PageDataTables(
		{
			tableId : 'carDeviceTable',
			ajaxUrl : contextPath + "/car/device/ajaxData.htm?t="
					+ new Date().getTime(), 
			exportUrl : contextPath + "/car/device/exportToExcel.htm?", 
			aoColumns : [ 
					{
						"mDataProp" : "deviceNO",
						"sClass" : "center"
					},
					{
						"mDataProp" : "status",
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
							}else if (carTableName == 'customer_cars') {
								return '客户车辆';
							}
							return '';
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "customerName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "phone",
						"sClass" : "center"
					},
					{
						"mDataProp" : "simCard",
						"sClass" : "center"
					},
					{
						"mDataProp" : "lastOnlineTime",
						"sClass" : "center"
					}, 
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];  
							var deviceNO = obj.aData['deviceNO']; 
							var simId = obj.aData['simId'];
							
							return '<span class="menu" style="margin-right:10px;"><ul><li><a class="drop" style="width:40px;" href="javaScript:openMore('
							+ id
							+ ');">管理卡</a><ul id="ulList"><li><a href="javaScript:openSelectSimWin('
							+ id + ',\'' + deviceNO
							+ '\')">绑定卡</a></li><li><a href="javaScript:openResetSimWin(' 
							+ id + ',\'' + deviceNO
							+ '\',\''+simId+'\')">移除卡</a></li></ul><li></ul></span><span class="menu" style="margin-right:20px;"><ul><li><a class="drop" style="width:40px;" href="javaScript:openMore('
							+ id
							+ ');">绑定车</a><ul id="ulList"><li><a href="javaScript:openSelectCarWin('
							+ id
							+ ',\'' 
							+ deviceNO
							+ '\')">租赁车辆</a></li><li><a href="javaScript:openSelectCustomerCarWin('
							+ id + ',\'' + deviceNO
							+ '\')">客户车辆</a></li></ul><li></ul></span>';
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"deviceNO" : jQuery('#deviceNO').val() == '' ? '' : '%'
							+ jQuery('#deviceNO').val() + '%',
					"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
							+ jQuery('#carNumber').val() + '%'
				};
			}
		});


//点击移除按钮
var openResetSimWin = function(id, deviceNO,simId) {
	if(simId == '' || simId == null){
		Dialog.alert("提示：该车载设备未绑定设备卡");
		return;
	}
	Dialog.confirm('警告：确认移除 [' + deviceNO + '] 设备卡吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/car/device/resetSim.htm?",
			data : "id=" + simId,
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

var deviceId = '';

// 打开选择安装车辆
var openSelectCarWin = function(id, deviceNO) {
	deviceId = id;
	var diag = new Dialog();
	diag.Width = 700;
	diag.Height = 500;
	diag.Title = "设备安装车辆选择";
	diag.URL = contextPath + "/page/car/selectCar.jsp?noCarDevice=yes";
	diag.MessageTitle = deviceNO;
	diag.Message = "车辆选择";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};// 点击确定后调用的方法
	diag.show();
};

// 打开选择安装车辆
var openSelectCustomerCarWin = function(id, deviceNO) {
	deviceId = id;
	var diag = new Dialog();
	diag.Width = 700;
	diag.Height = 500;
	diag.Title = "设备安装客户车辆选择";
	diag.URL = contextPath
			+ "/page/customer/selectCustomerCar.jsp?noCarDevice=yes";
	diag.MessageTitle = deviceNO;
	diag.Message = "客户车辆选择";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};// 点击确定后调用的方法
	diag.show();
};

// 打开选择设备卡
var openSelectSimWin = function(id, deviceNO) {
	deviceId = id;
	var diag = new Dialog();
	diag.Width = 700;
	diag.Height = 500;
	diag.Title = "设备安装设备卡选择";
	diag.URL = contextPath
	+ "/page/car/selectSim.jsp?noCarDevice=yes&deviceId="+id;
	diag.MessageTitle = deviceNO;
	diag.Message = "设备卡选择";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBackSave);
	};
	diag.show();
};

var callBackSave = function (){
	location.reload();
};

// 保存方法
var callBack = function(data) {
	jQuery.ajax({
		cache : true,
		type : "POST",
		url : contextPath + '/car/device/updateCar.htm?',
		data : {
			id : deviceId,
			carId : data['carId'],
			carTableName : data['carTableName']
		},
		async : false,
		dataType : 'json',
		error : function(request) {
			Dialog.alert("提示：操作失败");
		},
		success : function(data) {
			if (data.success) {
				Dialog.alert("提示：操作成功",function(){
					location.reload();
				});
				
			} else {
				Dialog.alert("提示：操作失败");
			}
		}
	});

};