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
								"mDataProp" : "type",
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
												+ '\',1)">审查</a>&nbsp;&nbsp;|<br/>'
												+ '<div class="menu" style="margin-right: 10px;"><ul><li><a class="drop" href="javaScript:openMore('
												+ id
												+ ');">更多</a><ul id="ulList"><li><a href="javaScript:deleteFun('
												+ id
												+ ',\''
												+ currentCarStatus
												+ '\',\'租车\',1)">取消租车</a></li><li><a href="javaScript:openDetailWin('
												+ id
												+ ',1)">查看详情</a></li></ul><li></ul></div>';
									} else {
										return '<a href="javaScript:openApplyWin('
												+ id
												+ ',\''
												+ carNumber
												+ '\',1)">审查</a>&nbsp;&nbsp;|<br/><span class="menu" style="margin-right: 10px;"><ul><li><a class="drop" href="javaScript:openMore('
												+ id
												+ ');">更多</a><ul id="ulList"><li><a href="javaScript:openDetailWin('
												+ id
												+ ',1)">查看详情</a></li></ul><li></ul></span>';
									}

								},
								"sClass" : "center"
							} ],
			beforeload : function() {
				this.paraData = {
					"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
							+ jQuery('#carNumber').val() + '%',
					"relityGetSiteId" : jQuery('#siteId').val(),
					"type" : 'getApply',
					"orderStatus" : '已取车',
					"priceTypeTableName" : jQuery('#priceTypeTableName').val() == '' ? ''
							: jQuery('#priceTypeTableName').val()
				};
			}
		});


// 打开租赁套餐选择页面
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
var openDetailWin = function(id,orderType) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 650;
	diag.Title = "查看订单详情";
	diag.URL = contextPath + "/order/order/detail.htm?id=" + id+"&orderType="+orderType;
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