jQuery(document).ready(function() {

	carManufacturerCombox.requestData();

	typesTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		typesTable.bStateSave=false; 
		typesTable.reloadData();
	});

	// 点击查询按钮事件
	jQuery('#carManufacturerId').change(function() {
		typesTable.bStateSave=false; 
		typesTable.reloadData();
	});

	// 点击导出
	jQuery('#excelBtn').click(function() {
		typesTable.exportExcel();
	});

});

// 选择框
var carManufacturerCombox = new Combox({
	id : 'carManufacturerId',
	url : contextPath + '/car/manufacturer/ajaxManufacturer.htm?t='
	+ new Date().getTime(),
	cText : 'manufacturerName',
	cValue : 'id',
	emptyText : '请选择供应商',
	change : function() {
		typesTable.bStateSave=false; 
		typesTable.reloadData();
	}
});

var cloumn = [ // 设定各列宽度
		{
			"mDataProp" : "typeName",
			"sClass" : "center"
		},
		{
			"mDataProp" : "fullName",
			"sClass" : "center"
		},
		{
			"mDataProp" : "seatCount",
			"sClass" : "center"
		},
		{
			"mDataProp" : "color",
			"sClass" : "center"
		},
		{
			"mDataProp" : "energy",
			"sClass" : "center"
		},
		{
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				var typeName = obj.aData['typeName'];
				return '<a href="javaScript:openUpdateWin('
						+ id
						+ ',\''
						+ typeName
						+ '\');">修改</a> &nbsp;&nbsp;|&nbsp;&nbsp; <a href="javaScript:deleteFun('
						+ id + ',\'' + typeName + '\');" >删除</a>';
			},
			"sClass" : "center"
		} ];

// 车辆型号表格
var typesTable = new PageDataTables({
	tableId : 'typesTable',
	ajaxUrl : contextPath + "/car/type/ajaxData.htm?t=" + new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/car/type/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : cloumn,
	beforeload : function() {
		this.paraData = {
			"typeName" : jQuery('#typeName').val() == '' ? '' : '%'
					+ jQuery('#typeName').val() + '%',
			"carManufacturerId" : jQuery('#carManufacturerId').val()
		};
	}
});

// 打开租赁套餐选择页面
var openUpdateWin = function(id, typeName) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "车辆型号编辑";
	diag.URL = contextPath + "/car/type/edite.htm?id=" + id;
	diag.MessageTitle = typeName;
	// diag.Message = "租赁套餐列表";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};// 点击确定后调用的方法
	diag.show();
};

// 点击删除按钮
var deleteFun = function(id, typeName) {
	Dialog.confirm('警告：确认删除车辆型号(' + typeName + ')吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/car/type/delete.htm?",
			data : "id=" + id,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：删除成功");
					location.reload(true);
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

// 保存方法
var callBack = function() {
	location.reload();
};
