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

});

var cloumn = [ 
		{
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
				return '<a href="javaScript:openInfoWin(' + obj.aData['id']
						+ ',\'' + obj.aData['typeName'] + '\');">查看详情</a>';
			},
			"sClass" : "center"
		} ];

// 正在使用的收费套餐表格
var priceTypesTable = new PageDataTables({
	tableId : 'priceTypesTable',
	ajaxUrl : contextPath + "/price/priceType/ajaxData.htm?t="
					+ new Date().getTime(),
	exportUrl : contextPath + "/price/priceType/exportToExcel.htm?",
	aoColumns : cloumn,
	beforeload : function() {
		this.paraData = {
			"typeName" : jQuery('#preTypeName').val() == '' ? '' : '%'
					+ jQuery('#preTypeName').val() + '%',
			"flag" : '正常'
		};
	}
});

// 已经结束的收费业务套餐列表
var overPriceTable = new PageDataTables({
	tableId : 'overPriceTable',
	ajaxUrl : contextPath + "/price/priceType/ajaxData.htm?t="
					+ new Date().getTime(),
	exportUrl : contextPath + "/price/priceType/exportToExcel.htm?",
	aoColumns : cloumn,
	beforeload : function() {
		this.paraData = {
			"typeName" : jQuery('#overTypeName').val() == '' ? '' : '%'
					+ jQuery('#overTypeName').val() + '%',
			"flag" : '删除'
		};
	}
});

/**
 * 打开收费套餐详细信息对话框
 */
var openInfoWin = function(id, typeName) {
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 500;
	diag.Title = "收费套餐详细信息";
	diag.URL = contextPath + "/price/priceType/detail.htm?id=" + id;
	diag.MessageTitle = typeName;
	diag.show();
};
