jQuery(document).ready(function() {

	batteryTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		batteryTable.bStateSave=false; 
		batteryTable.reloadData();
	});

	// 点击导出
	jQuery('#excelBtn').click(function() {
		batteryTable.exportExcel();
	});

});

var batteryTable = new PageDataTables(
		{
			tableId : 'batteryTable',
			ajaxUrl : contextPath + "/car/battery/ajaxData.htm?t="
					+ new Date().getTime(),// 这个是请求的地址
			exportUrl : contextPath + "/car/battery/exportToExcel.htm?",// 这个是请求的地址
			aoColumns : [ // 设定各列宽度
					{
						"mDataProp" : "vin",
						"sClass" : "center"
					},
					{
						"mDataProp" : "carNumber",
						"sClass" : "center"
					},
					{
						"mDataProp" : "batteryType",
						"sClass" : "center"
					},
					{
						"mDataProp" : "maxVoltag",
						"sClass" : "center"
					},
					{
						"mDataProp" : "maxCurrent",
						"sClass" : "center"
					},
					{
						"mDataProp" : "maxTemp",
						"sClass" : "center"
					},
					{
						"mDataProp" : "minVoltage",
						"sClass" : "center"
					},
					{
						"mDataProp" : "minCurrent",
						"sClass" : "center"
					},
					{
						"mDataProp" : "minTemp",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var vin = obj.aData['vin'];
							return '<a href="javaScript:openEditeWin('
									+ id
									+ ',\''
									+ vin
									+ '\')">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:deleteFun('
									+ id + ')">删除</a>';
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"vin" : jQuery('#vin').val() == '' ? '' : '%'
							+ jQuery('#vin').val() + '%'
				};
			}
		});

// 打开租赁套餐选择页面
var openEditeWin = function(id, vin) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "电池信息编辑";
	diag.URL = contextPath + "/car/battery/edite.htm?id=" + id;
	diag.MessageTitle = vin;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};// 点击确定后调用的方法
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};

/**
 * 点击删除按钮触发事件
 */
var deleteFun = function(id) {
	Dialog.confirm('确认删除该电池信息吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/car/battery/delete.htm?",
			data : "id=" + id,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功！",function(){
						location.reload(true);
					});
					
				} else {
					Dialog.alert("提示：操作失败，请重新操作！");
				}
			},
			error : function() {
				Dialog.alert("提示：操作失败，请重新操作！");
			}
		});
	});
};
