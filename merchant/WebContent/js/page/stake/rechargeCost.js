jQuery(document)
		.ready(
				function() {

					sitesCombox.requestData();

					jQuery(
							"#beginTime,#endTime,#beginTime1,#endTime1,#beginTime2,#endTime2,#beginTime3,#endTime3")
							.datepicker();
					yesTable.reloadData();
					// 已付费
					jQuery('#queryBtn,#yesDiv').click(function() {
						yesTable.bStateSave = false;
						yesTable.reloadData();
					});
					jQuery('#excelBtn').click(function() {
						yesTable.exportExcel();
					});
					// 未结算
					jQuery('#queryBtn1,#noDiv').click(function() {
						sitesCombox1.requestData();
						noTable.bStateSave = false;
						noTable.reloadData();
					});
					jQuery('#excelBtn1').click(function() {
						noTable.exportExcel();
					});
					// 预约中
					jQuery('#queryBtn2,#bookDiv').click(function() {
						sitesCombox2.requestData();
						bookTable.bStateSave = false;
						bookTable.reloadData();
					});
					jQuery('#excelBtn2').click(function() {
						bookTable.exportExcel();
					});
					// 已取消
					jQuery('#queryBtn3,#cancelDiv').click(function() {
						sitesCombox3.requestData();
						cancelTable.bStateSave = false;
						cancelTable.reloadData();
					});
					jQuery('#excelBtn3').click(function() {
						cancelTable.exportExcel();
					});
				});

// 选择框
var sitesCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm',
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		yesTable.bStateSave = false;
		yesTable.reloadData();
	}
});

// 选择框
var sitesCombox1 = new Combox({
	id : 'siteId1',
	url : contextPath + '/merchant/site/ajaxSites.htm',
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		noTable.bStateSave = false;
		noTable.reloadData();
	}
});

// 选择框
var sitesCombox2 = new Combox({
	id : 'siteId2',
	url : contextPath + '/merchant/site/ajaxSites.htm',
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		bookTable.bStateSave = false;
		bookTable.reloadData();
	}
});

// 选择框
var sitesCombox3 = new Combox({
	id : 'siteId3',
	url : contextPath + '/merchant/site/ajaxSites.htm',
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		cancelTable.bStateSave = false;
		cancelTable.reloadData();
	}
});

var aoColumns1 = [ {
	"mDataProp" : "siteName",
	"sClass" : "center"
}, {
	"mDataProp" : "factoryNo",
	"sClass" : "center"
}, {
	"mDataProp" : "chargePosition",
	"sClass" : "center"
}, {
	"mDataProp" : "name",
	"sClass" : "center"
}, {
	"mDataProp" : "cardNO",
	"sClass" : "center"
}, {
	"mDataProp" : "CarNo",
	"sClass" : "center"
}, {
	"mDataProp" : "recordTime",
	"sClass" : "center"
}, {
	"mDataProp" : "EQ",
	"sClass" : "center"
}, {
	"mDataProp" : "cost",
	"sClass" : "center"
}, {
	"mDataProp" : "Kind",
	"sClass" : "center"

}, {
	"mDataProp" : "orderStatus",
	"sClass" : "center"

} ];

var yesTable = new PageDataTables({
	tableId : 'yesTable',
	ajaxUrl : contextPath + "/stake/rechargeCost/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/stake/rechargeCost/exportToExcel.htm?",
	aoColumns : [ {
		"mDataProp" : "siteName",
		"sClass" : "center"
	}, {
		"mDataProp" : "factoryNo",
		"sClass" : "center"
	}, {
		"mDataProp" : "chargePosition",
		"sClass" : "center"
	}, {
		"mDataProp" : "cardID",
		"sClass" : "center"
	}, {
		"mDataProp" : "name",
		"sClass" : "center"
	}, {
		"mDataProp" : "cardNO",
		"sClass" : "center"
	}, {
		"mDataProp" : "CarNo",
		"sClass" : "center"
	}, {
		"mDataProp" : "startCharge",
		"sClass" : "center"
	}, {
		"mDataProp" : "lastCharge",
		"sClass" : "center"
	}, {
		"mDataProp" : "startSoc",
		"sClass" : "center"
	}, {
		"mDataProp" : "endSoc",
		"sClass" : "center"
	}, {
		"mDataProp" : "EQ",
		"sClass" : "center"
	}, {
		"mDataProp" : "cost",
		"sClass" : "center"
	}, {
		"mDataProp" : "Kind",
		"sClass" : "center"
	}, {
		"mDataProp" : "orderStatus",
		"sClass" : "center"

	}, {
		"fnRender" : function(obj) {
			var id = obj.aData['id'];
			return '<a href="javaScript:openPrintWin(' + id + ');">票据打印</a>';
		},
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"siteId" : jQuery('#siteId').val(),
			"factoryNo" : jQuery('#factoryNo').val() == '' ? '' : '%'
					+ jQuery('#factoryNo').val() + '%',
			"CarNo" : jQuery('#CarNo').val() == '' ? '' : '%'
					+ jQuery('#CarNo').val() + '%',
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + "23:59:59",
			"orderStatus" : "已付费"
		};
	}
});

var noTable = new PageDataTables({
	tableId : 'noTable',
	ajaxUrl : contextPath + "/stake/rechargeCost/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/stake/rechargeCost/exportToExcel.htm?",
	aoColumns : [
			{
				"mDataProp" : "siteName",
				"sClass" : "center"
			},
			{
				"mDataProp" : "factoryNo",
				"sClass" : "center"
			},
			{
				"mDataProp" : "chargePosition",
				"sClass" : "center"
			},
			{
				"mDataProp" : "cardID",
				"sClass" : "center"
			},
			{
				"mDataProp" : "name",
				"sClass" : "center"
			},
			{
				"mDataProp" : "cardNO",
				"sClass" : "center"
			},
			{
				"mDataProp" : "CarNo",
				"sClass" : "center"
			},
			{
				"mDataProp" : "startCharge",
				"sClass" : "center"
			},
			{
				"mDataProp" : "lastCharge",
				"sClass" : "center"
			},
			{
				"mDataProp" : "startSoc",
				"sClass" : "center"
			},
			{
				"mDataProp" : "endSoc",
				"sClass" : "center"
			},
			{
				"mDataProp" : "EQ",
				"sClass" : "center"
			},
			{
				"mDataProp" : "cost",
				"sClass" : "center"
			},
			{
				"mDataProp" : "Kind",
				"sClass" : "center"
			},
			{
				"mDataProp" : "orderStatus",
				"sClass" : "center"

			},
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					return '<a href="javaScript:openChargeWin(' + id + ');">结算</a>';
				},
				"sClass" : "center"
			} ],
	beforeload : function() {
		this.paraData = {
			"siteId" : jQuery('#siteId1').val(),
			"factoryNo" : jQuery('#factoryNo1').val() == '' ? '' : '%'
					+ jQuery('#factoryNo1').val() + '%',
			"CarNo" : jQuery('#CarNo1').val() == '' ? '' : '%'
					+ jQuery('#CarNo1').val() + '%',
			"beginTime" : jQuery('#beginTime1').val(),
			"endTime" : jQuery('#endTime1').val() == '' ? '' : jQuery(
					'#endTime1').val()
					+ "23:59:59",
			"orderStatus" : "未结算"
		};
	}
});

var bookTable = new PageDataTables({
	tableId : 'bookTable',
	ajaxUrl : contextPath + "/stake/rechargeCost/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/stake/rechargeCost/exportToExcel.htm?",
	aoColumns : aoColumns1,
	beforeload : function() {
		this.paraData = {
			"siteId" : jQuery('#siteId2').val(),
			"factoryNo" : jQuery('#factoryNo2').val() == '' ? '' : '%'
					+ jQuery('#factoryNo2').val() + '%',
			"CarNo" : jQuery('#CarNo2').val() == '' ? '' : '%'
					+ jQuery('#CarNo2').val() + '%',
			"beginTime" : jQuery('#beginTime2').val(),
			"endTime" : jQuery('#endTime2').val() == '' ? '' : jQuery(
					'#endTime2').val()
					+ "23:59:59",
			"orderStatus" : "预约中"
		};
	}
});

var cancelTable = new PageDataTables({
	tableId : 'cancelTable',
	ajaxUrl : contextPath + "/stake/rechargeCost/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/stake/rechargeCost/exportToExcel.htm?",
	aoColumns : aoColumns1,
	beforeload : function() {
		this.paraData = {
			"siteId" : jQuery('#siteId3').val(),
			"factoryNo" : jQuery('#factoryNo3').val() == '' ? '' : '%'
					+ jQuery('#factoryNo3').val() + '%',
			"CarNo" : jQuery('#CarNo3').val() == '' ? '' : '%'
					+ jQuery('#CarNo3').val() + '%',
			"beginTime" : jQuery('#beginTime3').val(),
			"endTime" : jQuery('#endTime3').val() == '' ? '' : jQuery(
					'#endTime3').val()
					+ "23:59:59",
			"orderStatus" : "已取消"
		};
	}
});

// 打开票据打印页面
var openPrintWin = function(id) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "票据打印";
	diag.URL = contextPath + "/stake/rechargeCost/print.htm?id=" + id;
	diag.MessageTitle = "票据打印";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 打开结算充电订单（刷卡充电方式）页面
var openChargeWin = function(id) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "结算充电订单";
	diag.URL = contextPath + "/stake/rechargeCost/charge.htm?id=" + id;
	diag.MessageTitle = "结算充电订单";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

var callBack = function() {
	location.reload();
};