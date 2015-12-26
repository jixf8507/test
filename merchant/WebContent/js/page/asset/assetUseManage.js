jQuery(document).ready(function() {

	jQuery('#flag').chosen();
	assetUseTable.reloadData();
	siteCombox.requestData();

	jQuery("#beginTime,#endTime").datepicker();

	jQuery('#flag').change(function() {
		assetUseTable.bStateSave=false; 
		assetUseTable.reloadData();
	});

	jQuery('#queryBtn').click(function() {
		assetUseTable.bStateSave=false; 
		assetUseTable.reloadData();
	});

	jQuery('#excelBtn').click(function() {
		assetUseTable.exportExcel();
	});

	jQuery('#addBtn').live('click',function() {
		openAddWin();
	});

});

var siteCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	beforeload : function() {

	},
	change : function() {
		assetUseTable.bStateSave=false; 
		assetUseTable.reloadData();
	}
});

var assetUseTable = new PageDataTables(
		{
			tableId : 'assetUseTable',
			ajaxUrl : contextPath + "/asset/use/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/asset/use/exportToExcel.htm?",
			aoColumns : [
					{
						"fnRender" : function(obj) {
							return 'LY_' + obj.aData['id'];
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "useDate",
						"sClass" : "center"
					},
					{
						"mDataProp" : "userName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "siteName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "transactUser",
						"sClass" : "center"
					},
					{
						"mDataProp" : "flag",
						"sClass" : "center"
					},
					{
						"mDataProp" : "remarks",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return '<a href="javaScript:openEditWin('
									+ id
									+ ');">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;'
									+ '<div class="menu" style="margin-right: 22px;"><ul><li><a class="drop" href="javaScript:openMore('
									+ id
									+ ');">更多</a><ul id="ulList"><li><a href="javaScript:returnFun('
									+ id
									+ ');">归还资产</a></li><li><a href="javaScript:openDetailWin('
									+ id
									+ ');">详细信息</a></li></ul><li></ul></div>';
						},
						"sClass" : "center"
					} ],
					addButton : [
					             {"tableId":"assetUseTable","outId":"addBtn","value":"新增"},
					            ],
			beforeload : function() {
				this.paraData = {
					"userName" : jQuery('#userName').val() == '' ? '' : '%'
							+ jQuery('#userName').val() + "%",
					"siteId" : jQuery('#siteId').val(),
					"flag" : jQuery('#flag').val(),
					"beginTime" : jQuery('#beginTime').val(),
					"endTime" : jQuery('#endTime').val() == '' ? '' : jQuery(
							'#endTime').val()
							+ ' 23:59:59'
				};
			}
		});

var openEditWin = function(id) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "资产领用信息修改";
	diag.MessageTitle = "单据号：LY_" + id;
	diag.URL = contextPath + "/asset/use/edit.htm?id=" + id;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};

var openDetailWin = function(id) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "资产领用详细信息";
	diag.MessageTitle = "单据号： LY_" + id;
	diag.URL = contextPath + "/asset/use/detail.htm?id=" + id;
	diag.show();
};

// 点击归还按钮
var returnFun = function(useId) {
	Dialog.confirm('警告：确认归还该单据号下的所有资产吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/asset/use/updateAllAssetFlag.htm?",
			data : "flag=闲置&useId=" + useId,
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
				Dialog.alert("错误：操作失败");
			}
		});
	});
};

var openAddWin = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "新增资产领用";
	diag.URL = contextPath + "/asset/use/add.htm?";
	diag.MessageTitle = "新增资产领用";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};