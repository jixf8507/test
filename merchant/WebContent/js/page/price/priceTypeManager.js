jQuery(document).ready(function() {

	priceTypesTable.reloadData();

	// 点击查询按钮事件
	jQuery('#preQueryBtn').click(function() {
		priceTypesTable.bStateSave=false; 
		priceTypesTable.reloadData();
	});

	// 点击导出
	jQuery('#preExcelBtn').click(function() {
		priceTypesTable.exportExcel();
	});

	// 点击查询按钮事件
	jQuery('#overQueryBtn,#overDiv').click(function() {
		overPriceTable.bStateSave=false; 
		overPriceTable.reloadData();
	});

	// 点击导出
	jQuery('#overExcelBtn').click(function() {
		overPriceTable.exportExcel();
	});
 
	jQuery('#addBtn').live('click',function() {
		addPrice();
	});
});

// 正在使用的收费套餐
var priceTypesTable = new PageDataTables(
		{
			tableId : 'priceTypesTable',
			ajaxUrl : contextPath + "/price/priceType/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/price/priceType/exportToExcel.htm?",
			aoColumns : [{
				"mDataProp" : "typeName",
				"sClass" : "center"
			},
			{
				"mDataProp" : "oneHoursCost",
				"sClass" : "center"
			},
			{
				"mDataProp" : "nightCost",
				"sClass" : "center"
			},
			{
				"mDataProp" : "maxCostForDay",
				"sClass" : "center"
			},
			{
				"mDataProp" : "startTimeForDay",
				"sClass" : "center"
			},
			{
				"mDataProp" : "endTimeForDay",
				"sClass" : "center"
			},
			{
				"mDataProp" : "maxKmsForDay",
				"sClass" : "center"
			},
			{
				"mDataProp" : "perKmsPrice",
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var typeName = obj.aData['typeName'];
					return '<a href="javaScript:openInfoWin('
							+ id
							+ ',\''
							+ typeName
							+ '\');">选择车辆</a> &nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:deleteFun('
							+ id + ',\'' + typeName + '\')" id="' + id
							+ '" >删除</a>';
				},
				"sClass" : "center"
			} ],	
			addButton : [
			             {"tableId":"priceTypesTable","outId":"addBtn","value":"新增"},
			            ],
			beforeload : function() {
				this.paraData = {
					"typeName" : jQuery('#preTypeName').val() == '' ? '' : '%'
							+ jQuery('#preTypeName').val() + '%',
					"flag" : '正常'
				};
			}
		});
// 已结束的租赁收费套餐
var overPriceTable = new PageDataTables({
	tableId : 'overPriceTable',
	ajaxUrl : contextPath + "/price/priceType/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/price/priceType/exportToExcel.htm?",
	aoColumns : [{
		"mDataProp" : "typeName",
		"sClass" : "center"
	},
	{
		"mDataProp" : "oneHoursCost",
		"sClass" : "center"
	},
	{
		"mDataProp" : "nightCost",
		"sClass" : "center"
	},
	{
		"mDataProp" : "maxCostForDay",
		"sClass" : "center"
	},
	{
		"mDataProp" : "startTimeForDay",
		"sClass" : "center"
	},
	{
		"mDataProp" : "endTimeForDay",
		"sClass" : "center"
	},
	{
		"mDataProp" : "maxKmsForDay",
		"sClass" : "center"
	},
	{
		"mDataProp" : "perKmsPrice",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"typeName" : jQuery('#overTypeName').val() == '' ? '' : '%'
					+ jQuery('#overTypeName').val() + '%',
			"flag" : '删除'
		};
	}
});

// 保存方法
var callBack = function() {
	location.reload();
};

/**
 * 打开车辆选择对话框
 */
var openInfoWin = function(id, typeName) {
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 500;
	diag.Title = "应用车辆选择";
	diag.URL = contextPath + "/page/price/selectCar.jsp?id=" + id
			+ "&tableName=wh_hours_price_type";
	diag.MessageTitle = typeName;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 删除套餐
var deleteFun = function(id, typeName) {
	Dialog.confirm('警告：确认删除租赁套餐  [ ' + typeName + ' ] ?', function() {
		jQuery.ajax({
			type : 'post',
			url : contextPath + "/price/priceType/delete.htm?",
			data : "id=" + id,
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：删除失败！请重试.");
				} else {
					Dialog.alert("提示：删除成功.", function() {
						location.reload(true);
					});
				}
			},
			error : function() {
				Dialog.alert("错误：删除失败！请重试.");
			}
		});
	});
};

//点击弹出新增租赁套餐按钮。
var addPrice = function(){
	var diag =new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title="新增租赁套餐";
	diag.URL=contextPath + "/price/priceType/add.htm";
	diag.MessageTitle="新增租赁套餐";
	diag.OKEvent=function(){
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
	
};
