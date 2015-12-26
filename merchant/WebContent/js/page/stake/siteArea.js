jQuery(document).ready(function() {

	sitesCombox.requestData();
	sitesTable.reloadData();

	jQuery('#addBtn').live('click', function() {
		openAddWin();
	});

	jQuery('#queryBtn').click(function() {
		sitesTable.bStateSave = false;
		sitesTable.reloadData();
	});

	// 点击导出
	jQuery('#excelBtn').click(function() {
		sitesTable.exportExcel();
	});
});

// 选择框
var sitesCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择',
	change : function() {
		sitesTable.bStateSave = false;
		sitesTable.reloadData();
	}
});

var sitesTable = new PageDataTables(
		{
			tableId : 'sitesTable',
			ajaxUrl : contextPath + "/stake/area/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/stake/area/exportToExcel.htm?",
			aoColumns : [
					{
						"mDataProp" : "area_code",
						"sClass" : "center"
					},
					{
						"mDataProp" : "area_name",
						"sClass" : "center"
					},
					{
						"mDataProp" : "siteName",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var area_name = obj.aData['area_name'];
							return '<a href="javaScript:openEditeWin('
									+ id
									+ ',\''
									+ area_name
									+ '\')">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:deleteFun('
									+ id + ',\'' + area_name + '\')"  >删除</a>';
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
					"areaName" : jQuery('#areaName').val() == '' ? '' : '%'
							+ jQuery('#areaName').val() + '%'
				};
			}
		});

// 新增充电区域窗口
var openAddWin = function() {
	var diag = new Dialog();
	diag.Width = 700;
	diag.Height = 400;
	diag.Title = "新增充电区域";
	diag.URL = contextPath + "/stake/area/add.htm";
	diag.MessageTitle = "新增充电区域";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 打开租赁套餐选择页面
var openEditeWin = function(id, area_name) {
	var diag = new Dialog();
	diag.Width = 700;
	diag.Height = 400;
	diag.Title = "充电区域编辑";
	diag.URL = contextPath + "/stake/area/edite.htm?id=" + id;
	diag.MessageTitle = area_name;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);

	};
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};

// 删除套餐
var deleteFun = function(id, area_name) {
	Dialog.confirm('警告：确认删除充电区域 [' + area_name + '] 吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/stake/area/delete.htm?",
			data : "id=" + id,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：删除成功", function() {
						location.reload(true);
					});

				} else {
					Dialog.alert("提示：操作失败");
				}
			},
			error : function() {
				Dialog.alert("提示：操作失败");
			}
		});
	});
};
