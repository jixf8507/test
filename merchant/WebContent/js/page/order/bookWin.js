jQuery(document).ready(function() {

	jQuery("#priceTypeTableName,#priceTypeTableName1").chosen();

	prepCurSitesCombox.requestData();

	getOrdersTable.reloadData();
	
	// 普通客户
	jQuery('#queryBtn,#normalDiv').click(function() {
		getOrdersTable.reloadData();
	});
	jQuery('#priceTypeTableName').change(function() {
		getOrdersTable.reloadData();
	});

});

var prepCurSitesCombox = new Combox({
	id : 'relityGetSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		jQuery('#siteId').val(jQuery('#relityGetSiteId').val());
		getOrdersTable.reloadData();
	},
	beforeload : function() {
		this.emptyValue = jQuery("#siteId").val();
	}
});

// 待取车审核订单列表
var getOrdersTable = new PageDataTables(
		{
			tableId : 'getOrdersTable',
			iDisplayLength : 5,
			ajaxUrl : contextPath + "/order/order/ajaxData.htm?t="
					+ new Date().getTime(),
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
								"mDataProp" : "typeName",
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
								"mDataProp" : "schemingGetTime",
								"sClass" : "center"
							},
							{
								"mDataProp" : "gsiteName",
								"sClass" : "center"
							},
							{
								"fnRender" : function(obj) {
									var deviceNO = obj.aData['deviceNO'];
									if (deviceNO == 'null' || deviceNO == '') {
										return obj.aData['ckms'];
									}else{
										return obj.aData['curKms'];
									}
								},
								"sClass" : "center"
							},
							{
								"fnRender" : function(obj) {
									var deviceNO = obj.aData['deviceNO'];
									if (deviceNO == 'null' || deviceNO == '') {
										return obj.aData['csurplusKms'];
									}else{
										return obj.aData['dsurplusKms'];
									}
								},
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
								"sClass" : "center"
							},
							{
								"fnRender" : function(obj) {
									var deviceStatus = obj.aData['deviceStatus'];
									if (deviceStatus == '故障') {
										return '<span style="color: red">'+deviceStatus+'</span>';
									}else if(deviceStatus == '正常'){
										return deviceStatus;
									}else{
										return ''; 
									}
								},
								"sClass" : "center"
							},
							{
								"fnRender" : function(obj) {
									var cid = obj.aData['carId'];
									var orderId = obj.aData['id'];
									var carNumber = obj.aData['carNumber'];
									var priceTypeTableName = obj.aData['priceTypeTableName'];
									var deviceStatus = obj.aData['deviceStatus'];
									if(deviceStatus == '故障'){
										return '';
									}else{
										return '<a href="javaScript:openGetWin('
										+ cid
										+ ',\''
										+ carNumber
										+ '\','
										+ orderId
										+ ',\''
										+ priceTypeTableName
										+ '\')">取车</a></a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:deleteFun('
										+ orderId + ',1)" >取消</a>';
									}
								},
								"sClass" : "center"
							}  ],
			beforeload : function() {
				this.paraData = {
						"name" : jQuery('#name').val() == '' ? '' : '%'
							+ jQuery('#name').val() + '%',
					"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
							+ jQuery('#carNumber').val() + '%',
					"phone" : jQuery('#phone').val() == '' ? '' : '%'
							+ jQuery('#phone').val() + '%',
					"relityGetSiteId" : jQuery('#siteId').val(),
					"orderStatus" : '已预约'
				};
			}
		});



//打开分时租赁套餐选择页面
var openGetWin = function(cId, carNumber, orderId, tableName) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "填写取车信息";
	diag.URL = contextPath + "/order/getApply/getCarEdite.htm?cId=" + cId
			+ "&orderId=" + orderId + "&tableName=" + tableName;

	diag.MessageTitle = carNumber;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload(); 
};

//点击取消租车按钮
var deleteFun = function(id,orderType) {
	Dialog.confirm('确认取消该次租赁吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/order/order/cancel.htm?",
			data : "id=" + id+"&orderType="+orderType,
			async : true,
			success : function(data) {
				var map = eval('(' + data + ')');
				if (map.success) {
					Dialog.alert("提示：取消租车订单成功", function() {
						location.reload(true);
					});
				} else {
					Dialog.alert("提示：取消租车订单失败");
				}
			},
			error : function() {
				Dialog.alert("提示：取消租车订单失败");
			}
		});
	});
};
