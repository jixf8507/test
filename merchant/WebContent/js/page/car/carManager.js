jQuery(document).ready(function() {

	curSitesCombox.requestData();
	carTypeCombox.requestData();
	editeCurSitesCombox.requestData();
	editeCarTypeCombox.requestData();

	carTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		carTable.bStateSave = false;
		carTable.reloadData();
	});

	// 点击导出
	jQuery('#excelBtn').click(function() {
		carTable.exportExcel();
	});

	// 点击查询按钮事件
	jQuery('#editeQueryBtn,#editDiv').click(function() {
		carEditeTables.bStateSave = false;
		carEditeTables.reloadData();
	});

	jQuery(".checkall").click(function() {
		var thiz = this;
		jQuery("input[name=cids]").each(function() {
			jQuery(this).attr('checked', thiz.checked);
		});
	});
	jQuery('#publishBtn').click(function() {
		publish();
	});

});

// 选择框
var curSitesCombox = new Combox({
	id : 'curSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		carTable.bStateSave = false;
		carTable.reloadData();
	}
});

// 车辆型号选择框
var carTypeCombox = new Combox({
	id : 'carTypeId',
	url : contextPath + '/car/type/ajaxTypes.htm?t=' + new Date().getTime(),
	cText : 'typeName',
	cValue : 'id',
	emptyText : '请选择车辆型号',
	change : function() {
		carTable.bStateSave = false;
		carTable.reloadData();
	}
});

// 选择框
var editeCurSitesCombox = new Combox({
	id : 'editeCurSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		carEditeTables.bStateSave = false;
		carEditeTables.reloadData();
	}
});

// 车辆型号选择框
var editeCarTypeCombox = new Combox({
	id : 'editeCarTypeId',
	url : contextPath + '/car/type/ajaxTypes.htm?t=' + new Date().getTime(),
	cText : 'typeName',
	cValue : 'id',
	emptyText : '请选择车辆型号',
	change : function() {
		carEditeTables.bStateSave = false;
		carEditeTables.reloadData();
	}
});

var cloumns = [
		{
			"fnRender" : function(obj) {
				return '<img src="'
						+ contextPath
						+ obj.aData['littleIcon']
						+ '" onError="imgError(this)" alt="汽车图片" width="50px;" height="50px;" />';
			},
			"sClass" : "center"
		},
		{
			"mDataProp" : "carNumber",
			"sClass" : "center"
		},
		{
			"mDataProp" : "vin",
			"sClass" : "center"
		},
		{

			"mDataProp" : "typeName",
			"sClass" : "center"
		},
		{
			"mDataProp" : "manufacturerName",
			"sClass" : "center"
		},
		{
			"mDataProp" : "color",
			"sClass" : "center"
		},
		{
			"mDataProp" : "describe",
			"sClass" : "center"
		},
		{
			"mDataProp" : "siteName",
			"sClass" : "center"
		},
		{
			"mDataProp" : "deviceNO",
			"sClass" : "center"
		},
		{
			"mDataProp" : "status",
			"sClass" : "center"
		},
		/*{
			"mDataProp" : "flag",
			"sClass" : "center"
		},*/
		{
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				var carNumber = obj.aData['carNumber'];
				var carStatus = obj.aData['status'];
				var deviceNO = obj.aData['deviceNO'];
				// 添加一个其他按钮
				var otherBtn = '<a href="javaScript:openSelectOther(' + id
						+ ',\'' + carNumber + '\');">其他</a>';
				// 如果车辆状态是使用的时候不显示“其他”按钮
				if (carStatus == '使用') {
					otherBtn = '';
				}

				return '<a href="javaScript:openSelectPriceWin('
						+ id
						+ ',\''
						+ carNumber
						+ '\');">分时</a>&nbsp;&nbsp;|&nbsp;&nbsp;'
						+ '<div class="menu" style="margin-right: 5px;"><ul><li><a class="drop" href="javaScript:openMore('
						+ id
						+ ');">更多</a><ul id="ulList"><li><a href="javaScript:openSelectLongPriceWin('
						+ id
						+ ',\''
						+ carNumber
						+ '\');">长期</a></li><li><a href="javaScript:openUpdateWin('
						+ id
						+ ',\''
						+ carNumber
						+ '\');">修改</a></li><li><a href="javaScript:openMapWin(\''
						+ deviceNO
						+ '\','
						+ id
						+ ',\''
						+ carNumber
						+ '\');">位置</a></li><li><a href="javaScript:openPictureWin('
						+ id + ',\'' + carNumber + '\');">图片</a></li><li>'
						+ otherBtn + '</li></ul><li></ul></div>';
			},
			"sClass" : "center"
		} ];

var carTable = new PageDataTables({
	tableId : 'carTable',
	ajaxUrl : contextPath + "/car/car/ajaxCarData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/car/car/exportToExcel.htm?",
	aoColumns : cloumns,
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
					+ jQuery('#carNumber').val() + '%',
			"vin" : jQuery('#vin').val() == '' ? '' : '%'
					+ jQuery('#vin').val() + '%',
			"carTypeId" : jQuery('#carTypeId').val(),
			"curSiteId" : jQuery('#curSiteId').val(),
			"flag" : '正常'
		};
	}
});

var editeCloumns = [
		{
			"fnRender" : function(obj) {
				return '<input  style="width: 20px;" type="checkbox" name="cids" value="'
						+ obj.aData['id'] + '" />';
			},
			"sWidth" : "4%",
			"sClass" : "center"
		},
		{
			"fnRender" : function(obj) {
				return '<img src="'
						+ contextPath
						+ obj.aData['littleIcon']
						+ '" onError="imgError(this)" alt="汽车图片" width="50px;" height="50px;" />';
			},
			"sClass" : "center"
		},
		{
			"mDataProp" : "carNumber",
			"sClass" : "center"
		},
		{
			"mDataProp" : "vin",
			"sClass" : "center"
		},
		{
			"mDataProp" : "typeName",
			"sClass" : "center"
		},
		{
			"mDataProp" : "color",
			"sClass" : "center"
		},
		{
			"mDataProp" : "describe",
			"sClass" : "center"
		},
		{
			"mDataProp" : "siteName",
			"sClass" : "center"
		},
		{
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				var carNumber = obj.aData['carNumber'];
				return '<a href="javaScript:openUpdateWin('
						+ id
						+ ',\''
						+ carNumber
						+ '\');">修改</a> &nbsp;&nbsp;|&nbsp;&nbsp; <a href="javaScript:deleteFun('
						+ id + ',\'' + carNumber + '\');" >删除</a>';
			},
			"sClass" : "center"
		} ];
var carEditeTables = new PageDataTables({
	tableId : 'carEditeTables',
	ajaxUrl : contextPath + "/car/car/ajaxCarData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/car/car/exportToExcel.htm?",
	aoColumns : editeCloumns,
	bSort : false,
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#editeCarNumber').val() == '' ? '' : '%'
					+ jQuery('#editeCarNumber').val() + '%',
			"vin" : jQuery('#editeVin').val() == '' ? '' : '%'
					+ jQuery('#editeVin').val() + '%',
			"carTypeId" : jQuery('#editeCarTypeId').val(),
			"curSiteId" : jQuery('#editeCurSiteId').val(),
			"flag" : '编辑'
		};
	}
});

// 图片出错加载默认图片
var imgError = function(thiz) {
	thiz.src = contextPath + '/img/car.png';
};

// 打开车辆图片页面
var openPictureWin = function(id, carNumber) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 650;
	diag.Title = "车辆图片";
	diag.URL = contextPath + "/car/car/carPicture.htm?carId=" + id;
	diag.MessageTitle = carNumber;
	diag.show();
};

// 打开车辆位置页面
var openMapWin = function(deviceNO, id, carNumber) {
	if (deviceNO == '' || deviceNO == null) {
		Dialog.alert("提示：该车未安装设备，位置信息暂获取不到");
		return;
	}
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 650;
	diag.Title = "车辆位置";
	diag.URL = contextPath + "/page/car/carMap.jsp?carId=" + id;
	diag.MessageTitle = carNumber;
	diag.show();
};

// 打开租赁套餐选择页面
var openSelectPriceWin = function(id, carNumber) {
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 300;
	diag.Title = "车辆分时租赁收费套餐选择";
	diag.URL = contextPath + "/page/car/carPriceType.jsp?id=" + id
			+ "&tableName=wh_hours_price_type";
	diag.MessageTitle = carNumber;
	diag.Message = "分时租赁套餐列表";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 打开长期租赁套餐选择页面
var openSelectLongPriceWin = function(id, carNumber) {
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 300;
	diag.Title = "车辆长期租赁收费套餐选择";
	diag.URL = contextPath + "/page/car/carPriceType.jsp?id=" + id
			+ "&tableName=month_lease_price_type";
	diag.MessageTitle = carNumber;
	diag.Message = "长期租赁套餐列表";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};

// 打开租赁套餐选择页面
var openUpdateWin = function(id, carNumber) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "车辆信息编辑";
	diag.URL = contextPath + "/car/car/edite.htm?id=" + id;
	diag.MessageTitle = carNumber;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 点击删除按钮
var deleteFun = function(id, carNumber) {
	Dialog.confirm('警告：确认删除车辆(' + carNumber + ')吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/car/car/delete.htm?",
			data : "id=" + id,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：删除成功", function() {
						location.reload(true);
					});

				} else {
					Dialog.alert("提示：" + data.info);
				}
			},
			error : function() {
				Dialog.alert("提示：系统异常");
			}
		});
	});
};

// 打开汽车管理选择其他页面
var openSelectOther = function(id, carNumber) {
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 500;
	diag.Title = "车辆信息状态编辑";
	diag.URL = contextPath + "/car/car/editeStatus.htm?id=" + id;
	diag.MessageTitle = carNumber;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

var publish = function() {
	var sel = false;
	var plds = '';
	jQuery("input[name=cids]").each(function() {
		if (jQuery(this).is(':checked')) {
			sel = true;
			plds += jQuery(this).val() + ",";
		}
	});

	if (!sel) {
		Dialog.alert('提示：没有选择数据');
		return;
	}
	Dialog.confirm('提示：确认发布?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/car/car/publish.htm?",
			data : "cids=" + plds,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功", function() {
						location.reload(true);
					});

				} else {
					Dialog.alert("提示：操作失败");
				}
			},
			error : function() {
				Dialog.alert("提示：系统异常");
			}
		});
	});
};
