jQuery(document).ready(
		function() {

			jQuery("#payment,#status,#payment1,#status1").chosen();

			jQuery("#beginTime,#endTime,#beginTime1,#endTime1").datepicker();

			sitesCombox.requestData();
			
			chargeRecordsTable.reloadData();

			jQuery('#queryBtn,#normalDiv').click(function() {
				chargeRecordsTable.bStateSave = false; 
				chargeRecordsTable.reloadData();
			}); 
			jQuery('#priceTypeTableName,#status,#payment').change(function() {
				chargeRecordsTable.bStateSave = false; 
				chargeRecordsTable.reloadData();
			});
			jQuery('#excelBtn').click(function() {
				chargeRecordsTable.exportExcel();
			});

			jQuery('#queryBtn1,#unitDiv').click(function() {
				getSitesCombox.requestData();
				unitRecordsTable.bStateSave = false; 
				unitRecordsTable.reloadData();
			});
			jQuery('#priceTypeTableName1,#status1,#payment1').change(function() {
				unitRecordsTable.bStateSave = false; 
				unitRecordsTable.reloadData();
			});
			jQuery('#excelBtn1').click(function() {
				unitRecordsTable.exportExcel();
			});

		});

var sitesCombox = new Combox({
	id : 'rSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		chargeRecordsTable.bStateSave = false; 
		chargeRecordsTable.reloadData();
	}
});

var getSitesCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		unitRecordsTable.bStateSave = false; 
		unitRecordsTable.reloadData();
	}
});

var chargeRecordsTable = new PageDataTables(
		{
			tableId : 'chargeRecordsTable',
			ajaxUrl : contextPath + "/order/order/ajaxTableData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/order/order/exportToExcel.htm?",
			aoColumns : [
					{
						"fnRender" : function(obj) {
							return 'ZCDD_' + obj.aData['id'];
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "carNumber",
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
						"mDataProp" : "realityReturnTime",
						"sClass" : "center"
					},
					{
						"mDataProp" : "rsiteName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "kms",
						"sClass" : "center"
					},
					{
						"mDataProp" : "perKmsCost",
						"sClass" : "center"
					},
					{
						"mDataProp" : "useCost",
						"sClass" : "center"
					},
					{
						"mDataProp" : "otherCost",
						"sClass" : "center"
					},
					{
						"mDataProp" : "maintenanceCost",
						"sClass" : "center"
					},
					{
						"mDataProp" : "couponCost",
						"sClass" : "center"
					},
					{
						"mDataProp" : "totalCost",
						"sClass" : "center"
					},
					{
						"mDataProp" : "menForCharge",
						"sClass" : "center"
					},
					{
						"mDataProp" : "payment",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var subOrderStatus = obj.aData['subOrderStatus'];
							if (subOrderStatus == '已入账') {
								return '<a href="javaScript:openDetailWin('
								+ id + ')">查看详情</a>&nbsp;&nbsp;|<br/>已核对入账';
							} else {
								return '<a href="javaScript:openDetailWin('
								+ id + ')">查看详情</a>&nbsp;&nbsp;|<br/><a href="javaScript:openCheckWin('
								+ id
								+ ')">核对入账</a>';
							}
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"status" : jQuery('#status').val() == '已入账' ? 'yes' : '',
					"status1" : jQuery('#status').val() != '已入账'
							&& jQuery('#status').val() != '' ? 'yes' : '',
					"relityReturnSiteId" : jQuery('#rSiteId').val(),
					"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
							+ jQuery('#carNumber').val() + '%',
					"phone" : jQuery('#phone').val() == '' ? '' : '%'
							+ jQuery('#phone').val() + '%',
					"name" : jQuery('#name').val() == '' ? '' : '%'
							+ jQuery('#name').val() + '%',
					"beginTime" : jQuery('#beginTime').val(),
					"endTime" : jQuery('#endTime').val() == '' ? '' : jQuery(
							'#endTime').val()
							+ ' 23:59:59',
					"orderStatus" : '已付费',
					"priceTypeTableName" : jQuery('#priceTypeTableName').val() == '' ? ''
							: jQuery('#priceTypeTableName').val(),
					"menForCharge" : jQuery('#menForCharge').val() == '' ? ''
							: jQuery('#menForCharge').val(),
					"orderId" : jQuery('#orderId').val() == '' ? ''
							: Number(jQuery('#orderId').val()),
					"orderType" : 1,
					"payment" : jQuery('#payment').val()

				};
			}
		});

var unitRecordsTable = new PageDataTables(
		{
			tableId : 'unitRecordsTable',
			ajaxUrl : contextPath + "/order/unitOrder/ajaxTableData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/order/unitOrder/exportToExcel.htm?",
			aoColumns : [
					{
						"fnRender" : function(obj) {
							return 'DWHY_' + obj.aData['id'];
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "carNumber",
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
						"mDataProp" : "realityReturnTime",
						"sClass" : "center"
					},
					{
						"mDataProp" : "rsiteName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "kms",
						"sClass" : "center"
					},
					{
						"mDataProp" : "perKmsCost",
						"sClass" : "center"
					},
					{
						"mDataProp" : "useCost",
						"sClass" : "center"
					},
					{
						"mDataProp" : "otherCost",
						"sClass" : "center"
					},
					{
						"mDataProp" : "maintenanceCost",
						"sClass" : "center"
					},
					{
						"mDataProp" : "couponCost",
						"sClass" : "center"
					},
					{
						"mDataProp" : "totalCost",
						"sClass" : "center"
					},
					{
						"mDataProp" : "menForCharge",
						"sClass" : "center"
					},
					{
						"mDataProp" : "payment",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var subOrderStatus = obj.aData['subOrderStatus'];
							if (subOrderStatus == '已入账') {
								return '<a href="javaScript:openDetailWin('
										+ id
										+ ')">查看详情</a>&nbsp;&nbsp;|<br/>已核对入账';
							} else {
								return '<a href="javaScript:openDetailWin('
										+ id
										+ ')">查看详情</a>&nbsp;&nbsp;|<br/><a href="javaScript:openCheckWin('
										+ id + ')">核对入账</a>';
							}
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"status" : jQuery('#status1').val() == '已入账' ? 'yes' : '',
					"status1" : jQuery('#status1').val() != '已入账'
							&& jQuery('#status1').val() != '' ? 'yes' : '',
					"carNumber" : jQuery('#carNumber1').val() == '' ? '' : '%'
							+ jQuery('#carNumber1').val() + '%',
					"enterpriseName" : jQuery('#enterpriseName').val() == '' ? ''
							: '%' + jQuery('#enterpriseName').val() + '%',
					"contactPhone" : jQuery('#contactPhone').val() == '' ? ''
							: '%' + jQuery('#contactPhone').val() + '%',
					"relityReturnSiteId" : jQuery('#siteId').val(),
					"beginTime" : jQuery('#beginTime1').val(),
					"endTime" : jQuery('#endTime1').val() == '' ? '' : jQuery(
							'#endTime1').val()
							+ ' 23:59:59',
					"orderStatus" : '已付费',
					"priceTypeTableName" : jQuery('#priceTypeTableName1').val() == '' ? ''
							: jQuery('#priceTypeTableName1').val(),
					"menForCharge" : jQuery('#menForCharge1').val() == '' ? ''
							: jQuery('#menForCharge1').val(),
					"orderId" : jQuery('#orderId1').val() == '' ? ''
							: Number(jQuery('#orderId1').val()),
							"payment" : jQuery('#payment1').val()
				};
			}
		});

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

// 打开核对租赁收费页面
var openCheckWin = function(id) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 650;
	diag.Title = "核对租赁收费";
	diag.URL = contextPath + "/order/order/checkFee.htm?id=" + id;
	diag.MessageTitle = 'ZCDD_' + id;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};