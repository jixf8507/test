jQuery(document).ready(function() {

	controlSitesCombox.requestData();

	getControlOrdersTable.reloadData();
	
	// 车辆调度
	jQuery('#queryBtn3,#controlDiv').click(function() {
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

var controlSitesCombox = new Combox({
	id : 'relityGetSiteId3',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		jQuery('#siteId').val(jQuery('#relityGetSiteId3').val());
		getControlOrdersTable.reloadData();
	},
	beforeload : function() {
		this.emptyValue = jQuery("#siteId").val();
	}
});

var officialSitesCombox = new Combox({
	id : 'relityGetSiteId4',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		jQuery('#siteId').val(jQuery('#relityGetSiteId4').val());
		getOfficialOrdersTable.reloadData();
	},
	beforeload : function() {
		this.emptyValue = jQuery("#siteId").val();
	}
});

// 车辆调度待取车审核订单列表
var getControlOrdersTable = new PageDataTables(
		{
			tableId : 'getControlOrdersTable',
			iDisplayLength : 5,
			ajaxUrl : contextPath + "/order/otherOrder/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/order/otherOrder/exportToExcel.htm?",
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
						"mDataProp" : "userName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "mobilePhone",
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
						"mDataProp" : "orderDescribe",
						"sClass" : "center"
					},
					{
						"mDataProp" : "orderStatus",
						"sClass" : "center"
					},
					{
						"mDataProp" : "menForGet",
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
							var deviceNO = obj.aData['deviceNO'];
							if (deviceNO == 'null' || deviceNO == '') {
								return '<a href="javaScript:openApplyWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',3)">审查</a>&nbsp;&nbsp;|<br/>'
										+ '<div class="menu" style="margin-right: 20px;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:deleteFun('
										+ id
										+ ',\''
										+ currentCarStatus
										+ '\',\'车辆调度\',3)">取消调度</a></li><li><a href="javaScript:openOtherDetailWin('
										+ id
										+ ',3)">查看详情</a></li></ul><li></ul></div>';
							} else {
								return '<a href="javaScript:openApplyWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',3)">审查</a>&nbsp;&nbsp;|<br/><span class="menu" style="margin-right: 20px;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:openOtherDetailWin('
										+ id
										+ ',3)">查看详情</a></li></ul><li></ul></span>';
							}

						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"carNumber" : jQuery('#carNumber3').val() == '' ? '' : '%'
							+ jQuery('#carNumber3').val() + '%',
					"relityGetSiteId" : jQuery('#siteId').val(),
					"type" : 'getApply',
					"orderStatus" : '已取车',
					"orderType" : 3
				};
			}
		});

// 公务用车待取车审核订单列表
var getOfficialOrdersTable = new PageDataTables(
		{
			tableId : 'getOfficialOrdersTable',
			iDisplayLength : 5,
			ajaxUrl : contextPath + "/order/otherOrder/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/order/otherOrder/exportToExcel.htm?",
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
						"mDataProp" : "userName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "mobilePhone",
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
						"mDataProp" : "orderDescribe",
						"sClass" : "center"
					},
					{
						"mDataProp" : "orderStatus",
						"sClass" : "center"
					},
					{
						"mDataProp" : "menForGet",
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
							var deviceNO = obj.aData['deviceNO'];
							if (deviceNO == 'null' || deviceNO == '') {
								return '<a href="javaScript:openApplyWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',4)">审查</a>&nbsp;&nbsp;|<br/>'
										+ '<div class="menu" style="margin-right: 20px;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:deleteFun('
										+ id
										+ ',\''
										+ currentCarStatus
										+ '\',\'公务用车\',3)">取消用车</a></li><li><a href="javaScript:openOtherDetailWin('
										+ id
										+ ',4)">查看详情</a></li></ul><li></ul></div>';
							} else {
								return '<a href="javaScript:openApplyWin('
										+ id
										+ ',\''
										+ carNumber
										+ '\',4)">审查</a>&nbsp;&nbsp;|<br/><span class="menu" style="margin-right: 20px;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:openOtherDetailWin('
										+ id
										+ ',4)">查看详情</a></li></ul><li></ul></span>';
							}

						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"carNumber" : jQuery('#carNumber4').val() == '' ? '' : '%'
							+ jQuery('#carNumber4').val() + '%',
					"relityGetSiteId" : jQuery('#siteId').val(),
					"orderStatus" : '已取车',
					"type" : 'getApply',
					"orderType" : 4
				};
			}
		});

//打开租赁套餐选择页面
var openApplyWin = function(id, carNumber, orderType) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 650;
	diag.Title = "取车审查";
	diag.URL = contextPath + "/order/getConfirm/edite.htm?id=" + id
			+ "&orderType=" + orderType;
	diag.MessageTitle = carNumber;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
		diag.close();
	};
	diag.show();
};

// 打开租赁详情页面
var openOtherDetailWin = function(id,orderType) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 650;
	diag.Title = "查看订单详情";
	diag.URL = contextPath + "/order/otherOrder/detail.htm?id=" + id;
	diag.MessageTitle = 'ZCDD_' + id;
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload(); 
};

// 点击取消租车按钮
var deleteFun = function(id, currentCarStatus,type,orderType) {
	Dialog.confirm('确认取消该次'+type+'吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/order/order/cancel.htm?",
			data : "id=" + id+"&orderType="+orderType,
			async : true,
			success : function(data) {
				var map = eval('(' + data + ')');
				if (map.success) {
					Dialog.alert("提示：取消"+type+"订单成功", function() {
						callBack();
					});
				} else {
					Dialog.alert("提示：取消"+type+"订单失败");
				}
			},
			error : function() {
				Dialog.alert("错误：取消"+type+"订单失败");
			}
		});
	});
};