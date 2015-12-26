jQuery(document).ready(function() {
	
	assetTable.reloadData();

	jQuery('#queryBtn').click(function() {
		assetTable.bStateSave=false; 
		assetTable.reloadData();
	});

	jQuery('#excelBtn').click(function() {
		assetTable.exportExcel();
	});

	jQuery('#addBtn').live('click',function() {
		openAddWin();
	});
});

var assetTable = new PageDataTables({
			tableId : 'assetTable',
			ajaxUrl : contextPath + "/asset/category/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/asset/category/exportToExcel.htm?",
			aoColumns : [
					{
						"mDataProp" : "name",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var name = obj.aData['name'];
							return '<a href="javaScript:openEditeWin('
									+ id
									+ ',\''
									+ name
									+ '\');">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:delFun('
									+ id + ',\'' + name + '\');">删除</a>';
						},
						"sClass" : "center"
					} ],
					addButton : [
					             {"tableId":"assetTable","outId":"addBtn","value":"新增"},
					            ],
			beforeload : function() {
				this.paraData = {
					"name" : jQuery('#name').val() == '' ? '' : '%'
							+ jQuery('#name').val() + "%"
				};
			}
		});

var openEditeWin = function(id, name) {
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 300;
	diag.Title = "固定资产种类修改";
	diag.URL = contextPath + "/asset/category/edit.htm?id=" + id;
	diag.MessageTitle = name;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};

// 点击删除按钮
var delFun = function(id, name) {
	Dialog.confirm('警告：确认资产种类 [' + name + '] 吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/asset/category/delete.htm?",
			data : "id=" + id,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：删除成功", function() {
						location.reload(true);
					});

				} else {
					Dialog.alert("提示：删除失败");
				}
			},
			error : function() {
				Dialog.alert("错误：删除失败");
			}
		});
	});
};

var openAddWin = function() {
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 300;
	diag.Title = "新增资产种类";
	diag.URL = contextPath + "/asset/category/add.htm?";
	diag.MessageTitle = "新增资产种类";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};