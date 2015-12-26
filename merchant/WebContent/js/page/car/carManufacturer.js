jQuery(document).ready(function() {

	manufacturerTable.reloadData();

	jQuery('#queryBtn').click(function() {
		manufacturerTable.bStateSave=false; 
		manufacturerTable.reloadData();
	});
	// 点击导出
	jQuery('#excelBtn').click(function() {
		manufacturerTable.exportExcel();
	});

});

var cloumn = [ // 设定各列宽度
		{
			"mDataProp" : "manufacturerName",
			"sClass" : "center"
		},
		{
			"mDataProp" : "fullName",
			"sClass" : "center"
		},
		{
			"mDataProp" : "address",
			"sClass" : "center"
		},
		{
			"mDataProp" : "linkman",
			"sClass" : "center"
		},
		{
			"mDataProp" : "linkphone",
			"sClass" : "center"
		},
		{
			"mDataProp" : "email",
			"sClass" : "center"
		},
		{
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				var manufacturerName = obj.aData['manufacturerName'];
				return '<a href="javaScript:openUpdateWin('
						+ id
						+ ',\''
						+ manufacturerName
						+ '\');">编辑</a> &nbsp;&nbsp;|&nbsp;&nbsp; <a href="javaScript:deleteFun('
						+ id + ',\'' + manufacturerName + '\');" >删除</a>';
			},
			"sClass" : "center"
		} ];
// 车辆供应商列表
var manufacturerTable = new PageDataTables({
	tableId : 'manufacturerTable',
	ajaxUrl : contextPath + "/car/manufacturer/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/car/manufacturer/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : cloumn,
	beforeload : function() {
		this.paraData = {
			"manufacturerName" : jQuery('#manufacturerName').val() == '' ? ''
					: '%' + jQuery('#manufacturerName').val() + '%'
		};
	}
});

// 打开租赁套餐选择页面
var openUpdateWin = function(id, manufacturerName) {
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 500;
	diag.Title = "供应商信息编辑";
	diag.URL = contextPath + "/car/manufacturer/edite.htm?id=" + id;
	diag.MessageTitle = manufacturerName;
	// diag.Message = "租赁套餐列表";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};// 点击确定后调用的方法
	diag.show();
};

// 点击删除按钮
var deleteFun = function(id, manufacturerName) {
	Dialog.confirm('警告：确认删除供应商(' + manufacturerName + ')吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/car/manufacturer/delete.htm?",
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
