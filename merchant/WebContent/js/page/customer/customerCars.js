jQuery(document).ready(function() {
	normalCustomersTable.reloadData();
	
	// 点击查询按钮事件
	jQuery('#normalQueryBtn').click(function() {
		normalCustomersTable.bStateSave=false; 
		normalCustomersTable.reloadData();
	});

	// 点击查询按钮事件
	jQuery('#normalExcelBtn').click(function() {
		normalCustomersTable.exportExcel();
	});
	
	// 点击新增客户事件
	jQuery("#addBtn").live('click',function(){
		addCar();
	 });

});
var column = [ // 设定各列宽度
		{
			"mDataProp" : "carNumber",
			"sClass" : "center"
		},
		{
			"mDataProp" : "vin",
			"sClass" : "center"
		},
		{
			"mDataProp" : "idCard",
			"sClass" : "center"
		},
		{
			"mDataProp" : "phone",
			"sClass" : "center"
		},
		{
			"mDataProp" : "name",
			"sClass" : "center"
		},

		{
			"mDataProp" : "deviceNO",
			"sClass" : "center"
		},
		{
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				var carNumber = obj.aData['carNumber'];
				return '<a href="javaScript:openEditeWin('
						+ id
						+ ',\''
						+ carNumber
						+ '\');">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp; <a href="javaScript:deleteFun('
						+ id + ',\'' + carNumber + '\');">删除</a>';
			},
			"sClass" : "center"
		} ];

var normalCustomersTable = new PageDataTables({
	tableId : 'normalCustomersTable',
	ajaxUrl : contextPath + "/customer/cars/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/customer/cars/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : column,
	addButton : [
	             {"outId":"addBtn","value":"新增"},
	               
	            ],
	beforeload : function() {
		this.paraData = {
			"vin" : jQuery('#vin').val() == '' ? '' : '%'
					+ jQuery('#vin').val() + '%',
			"phone" : jQuery('#normalPhone').val() == '' ? '' : '%'
					+ jQuery('#normalPhone').val() + "%",
					"idCard" : jQuery('#normalIdCard').val() == '' ? '' : '%'
						+ jQuery('#normalIdCard').val() + "%",
			"name" : jQuery('#normalName').val() == '' ? '' : '%'
					+ jQuery('#normalName').val() + "%",
			"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
					+ jQuery('#carNumber').val() + "%"
		};
	}
});

// 打开租赁套餐选择页面
var openEditeWin = function(id, carNumber) {
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 500;
	diag.Title = "客户车辆编辑";
	diag.URL = contextPath + "/customer/cars/edite.htm?id=" + id;
	diag.MessageTitle = carNumber;
	// diag.Message = "租赁套餐列表";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};// 点击确定后调用的方法
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};

// 点击删除按钮
var deleteFun = function(id, carNumber) {
	Dialog.confirm('警告：确认删除车辆(' + carNumber + ')吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/customer/cars/delete.htm?",
			data : "id=" + id,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：删除成功",function(){
						location.reload(true);
					});
					
				} else {
					Dialog.alert("提示：删除失败");
				}
			},
			error : function() {
				Dialog.alert("提示：系统异常");
			}
		});
	});
};

//点击弹出新增服务中心按钮。
var addCar = function(){
	var diag =new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title="新增车辆";
	diag.URL=contextPath + "/customer/cars/add.htm";
	diag.MessageTitle="无";
	diag.OKEvent=function(){
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
	
};

