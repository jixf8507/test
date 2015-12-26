jQuery(document)
		.ready(
				function() {

					waitHandleTable.reloadData();

					jQuery('#urgency,#urgency1,#urgency2').chosen();
					jQuery(
							"#beginTime,#endTime,#beginTime1,#endTime1,#beginTime2,#endTime2")
							.datepicker();

					// 待处理
					jQuery('#queryBtn2,#waitDiv').click(function() {
						waitHandleTable.bStateSave = false;
						waitHandleTable.reloadData();
					});

					jQuery('#urgency2').change(function() {
						waitHandleTable.bStateSave = false;
						waitHandleTable.reloadData();
					});

					jQuery('#excelBtn2').click(function() {
						waitHandleTable.exportExcel();
					});

					// 处理中
					jQuery('#queryBtn,#onDiv').click(function() {
						onHandleTable.bStateSave = false;
						onHandleTable.reloadData();
					});

					jQuery('#urgency').change(function() {
						onHandleTable.bStateSave = false;
						onHandleTable.reloadData();
					});

					jQuery('#excelBtn').click(function() {
						onHandleTable.exportExcel();
					});

					// 已处理
					jQuery('#queryBtn1,#overDiv').click(function() {
						overHandleTable.bStateSave = false;
						overHandleTable.reloadData();
					});

					jQuery('#urgency1').change(function() {
						overHandleTable.bStateSave = false;
						overHandleTable.reloadData();
					});

					jQuery('#excelBtn1').click(function() {
						overHandleTable.exportExcel();
					});

				});

var aoColumns = [
		{
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				return ' GD_' + id;
			},
			"sClass" : "center"
		},
		{
			"mDataProp" : "typeName",
			"sClass" : "center"
		},
		{
			"mDataProp" : "workName",
			"sClass" : "center"
		},
		{
			"mDataProp" : "workDes",
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
			"mDataProp" : "createdTime",
			"sClass" : "center"
		},
		{
			"mDataProp" : "transactUser",
			"sClass" : "center"
		},
		{
			"mDataProp" : "urgency",
			"sClass" : "center"
		},
		{
			"mDataProp" : "workStatus",
			"sClass" : "center"
		},
		{
			"fnRender" : function(obj) {
				return '<a href="javaScript:openHandleWin(' + obj.aData['id']
						+ ');">处理</a>';
			},
			"sClass" : "center"
		} ];

var waitHandleTable = new PageDataTables({
	tableId : 'waitHandleTable',
	ajaxUrl : contextPath + "/work/order/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/work/order/exportToExcel.htm?",
	aoColumns : aoColumns,
	beforeload : function() {
		this.paraData = {
			"typeName" : jQuery('#typeName2').val() == '' ? '' : "%"
					+ jQuery('#typeName2').val() + "%",
			"workName" : jQuery('#workName2').val() == '' ? '' : "%"
					+ jQuery('#workName2').val() + "%",
			"userName" : jQuery('#userName2').val() == '' ? '' : "%"
					+ jQuery('#userName2').val() + "%",
			"mobilePhone" : jQuery('#mobilePhone2').val() == '' ? '' : "%"
					+ jQuery('#mobilePhone2').val() + "%",
			"urgency" : jQuery('#urgency2').val(),
			"beginTime" : jQuery('#beginTime2').val(),
			"endTime" : jQuery('#endTime2').val() == '' ? '' : jQuery(
					'#endTime2').val()
					+ "23:59:59",
			"workStatus" : "待处理",
		};
	}
});

var onHandleTable = new PageDataTables({
	tableId : 'onHandleTable',
	ajaxUrl : contextPath + "/work/order/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/work/order/exportToExcel.htm?",
	aoColumns : [
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					return ' GD_' + id;
				},
				"sClass" : "center"
			},
			{
				"mDataProp" : "typeName",
				"sClass" : "center"
			},
			{
				"mDataProp" : "workName",
				"sClass" : "center"
			},
			{
				"mDataProp" : "workDes",
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
				"mDataProp" : "createdTime",
				"sClass" : "center"
			},
			{
				"mDataProp" : "transactUser",
				"sClass" : "center"
			},
			{
				"mDataProp" : "urgency",
				"sClass" : "center"
			},
			{
				"mDataProp" : "updatedTime",
				"sClass" : "center"
			},
			{
				"mDataProp" : "transactDes",
				"sClass" : "center"
			},
			{
				"mDataProp" : "workStatus",
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					return '<a href="javaScript:openHandleWin('
							+ obj.aData['id'] + ');">处理</a>';
				},
				"sClass" : "center"
			} ],
	beforeload : function() {
		this.paraData = {
			"typeName" : jQuery('#typeName').val() == '' ? '' : "%"
					+ jQuery('#typeName').val() + "%",
			"workName" : jQuery('#workName').val() == '' ? '' : "%"
					+ jQuery('#workName').val() + "%",
			"userName" : jQuery('#userName').val() == '' ? '' : "%"
					+ jQuery('#userName').val() + "%",
			"mobilePhone" : jQuery('#mobilePhone').val() == '' ? '' : "%"
					+ jQuery('#mobilePhone').val() + "%",
			"urgency" : jQuery('#urgency').val(),
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + "23:59:59",
			"workStatus" : "处理中",
		};
	}
});

var overHandleTable = new PageDataTables({
	tableId : 'overHandleTable',
	ajaxUrl : contextPath + "/work/order/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/work/order/exportToExcel.htm?",
	aoColumns : [ {
		"fnRender" : function(obj) {
			var id = obj.aData['id'];
			return ' GD_' + id;
		},
		"sClass" : "center"
	}, {
		"mDataProp" : "typeName",
		"sClass" : "center"
	}, {
		"mDataProp" : "workName",
		"sClass" : "center"
	}, {
		"mDataProp" : "workDes",
		"sClass" : "center"
	}, {
		"mDataProp" : "userName",
		"sClass" : "center"
	}, {
		"mDataProp" : "mobilePhone",
		"sClass" : "center"
	}, {
		"mDataProp" : "createdTime",
		"sClass" : "center"
	}, {
		"mDataProp" : "transactUser",
		"sClass" : "center"
	}, {
		"mDataProp" : "urgency",
		"sClass" : "center"
	}, {
		"mDataProp" : "updatedTime",
		"sClass" : "center"
	}, {
		"mDataProp" : "transactDes",
		"sClass" : "center"
	}, {
		"mDataProp" : "workStatus",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"typeName" : jQuery('#typeName1').val() == '' ? '' : "%"
					+ jQuery('#typeName1').val() + "%",
			"workName" : jQuery('#workName1').val() == '' ? '' : "%"
					+ jQuery('#workName1').val() + "%",
			"userName" : jQuery('#userName1').val() == '' ? '' : "%"
					+ jQuery('#userName1').val() + "%",
			"mobilePhone" : jQuery('#mobilePhone1').val() == '' ? '' : "%"
					+ jQuery('#mobilePhone1').val() + "%",
			"urgency" : jQuery('#urgency1').val(),
			"beginTime" : jQuery('#beginTime1').val(),
			"endTime" : jQuery('#endTime1').val() == '' ? '' : jQuery(
					'#endTime1').val()
					+ "23:59:59",
			"workStatus" : "已处理",
		};
	}
});

var addRelease = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "发布新工单";
	diag.URL = contextPath + "/work/order/add.htm?";
	diag.MessageTitle = "发布新工单";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};

var openHandleWin = function(id) {
	var diag = new Dialog();
	diag.Width = 700;
	diag.Height = 500;
	diag.Title = "工单处理信息";
	diag.URL = contextPath + "/work/order/doWork.htm?id=" + id;
	diag.MessageTitle = "工单编号：GD_" + id;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};
