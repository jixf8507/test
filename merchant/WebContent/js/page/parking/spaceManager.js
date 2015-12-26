jQuery(document).ready(function() {

	sitesCombox.requestData();
	sitesTable.reloadData();
	
	jQuery('#status').chosen();

	jQuery('#queryBtn').click(function() {
		sitesTable.bStateSave = false;
		sitesTable.reloadData();
	});
	
	jQuery('#status').change(function() {
		sitesTable.bStateSave = false;
		sitesTable.reloadData();
	});

	// 点击导出
	jQuery('#excelBtn').click(function() {
		sitesTable.exportExcel();
	});
	jQuery('#addBtn').live("click", function() {
		openaddspace();
	});
});

// 选择框
var sitesCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		sitesTable.bStateSave = false;
		sitesTable.reloadData();
	}
});

var sitesTable = new PageDataTables(
		{
			tableId : 'sitesTable',
			ajaxUrl : contextPath + "/parking/space/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/parking/space/exportToExcel.htm?",
			aoColumns : [
					{
						"mDataProp" : "siteName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "spaceNO",
						"sClass" : "center"
					},
					{
						"mDataProp" : "area",
						"sClass" : "center"
					},
					{
						"mDataProp" : "factoryNo",
						"sClass" : "center"
					},
					{
						"mDataProp" : "deviceNo",
						"sClass" : "center"
					},
					{
						"mDataProp" : "nameplate",
						"sClass" : "center"
					},
					{
						"mDataProp" : "position",
						"sClass" : "center"
					},
					{
						"mDataProp" : "status",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var spaceNO = obj.aData['spaceNO'];
							return '<a href="javaScript:openEditeWin('
									+ id
									+ ',\''
									+ spaceNO
									+ '\')">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:deleteFun('
									+ id + ')" >删除</a>';
						},
						"sClass" : "center"
					} ],
			addButton : [ {
				"tableId" : "sitesTable",
				"outId" : "addBtn",
				"value" : "新增"
			}, ],
			beforeload : function() {
				this.paraData = {
					"siteId" : jQuery('#siteId').val(),
					"status" : jQuery('#status').val(),
					"spaceNO" : jQuery('#spaceNO').val() == '' ? '' : '%'
							+ jQuery('#spaceNO').val() + '%',
					"factoryNoLike" : jQuery('#factoryNo').val() == '' ? '' : '%'
							+ jQuery('#factoryNo').val() + '%'
				};
			}
		});

// 打开新增停车位窗口
var openaddspace = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "新增停车位";
	diag.URL = contextPath + "/parking/space/add.htm?";
	diag.MessageTitle = "新增停车位";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};// 点击确定后调用的方法
	diag.show();
};

// 打开租赁套餐选择页面
var openEditeWin = function(id, spaceNO) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "停车位编辑";
	diag.URL = contextPath + "/parking/space/edite.htm?id=" + id;
	diag.MessageTitle = spaceNO;
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
var deleteFun = function(id) {
	Dialog.confirm('警告：确认删除该停车位吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/parking/space/delete.htm?",
			data : "id=" + id,
			async : true,
			success : function(data) {
				var map = eval('(' + data + ')');
				if (map.success) {
					Dialog.alert("提示：删除成功", function() {
						location.reload(true);
					});

				} else {
					Dialog.alert("提示：删除失败");
				}
			},
			error : function() {
				Dialog.alert("提示：删除失败");
			}
		});
	});
};