jQuery(document).ready(function() {

	jQuery('#reduceStatus').chosen();

	assetReduceTable.reloadData();
	siteCombox.requestData();

	jQuery("#beginTime,#endTime").datepicker();

	jQuery('#reduceStatus').change(function() {
		assetReduceTable.bStateSave=false; 
		assetReduceTable.reloadData();
	});

	jQuery('#queryBtn').click(function() {
		assetReduceTable.bStateSave=false; 
		assetReduceTable.reloadData();
	});

	jQuery('#excelBtn').click(function() {
		assetReduceTable.exportExcel();
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
		assetReduceTable.bStateSave=false; 
		assetReduceTable.reloadData();
	}
});

var assetReduceTable = new PageDataTables(
		{
			tableId : 'assetReduceTable',
			ajaxUrl : contextPath + "/asset/reduce/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/asset/reduce/exportToExcel.htm?",
			aoColumns : [
					{
						"fnRender" : function(obj) {
							return 'CZ_' + obj.aData['id'];
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "reduceDate",
						"sClass" : "center"
					},
					{
						"mDataProp" : "reduceReason",
						"sClass" : "center"
					},
					{
						"mDataProp" : "reduceStatus",
						"sClass" : "center"
					},
					{
						"mDataProp" : "applyUser",
						"sClass" : "center"
					},
					{
						"mDataProp" : "transactUser",
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
									+ ');">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:openDetailWin('
									+ id + ');">详细</a>';
						},
						"sClass" : "center"
					} ],
					addButton : [
					             {"tableId":"assetReduceTable","outId":"addBtn","value":"新增"},
					            ],
			beforeload : function() {
				this.paraData = {
					"applyUser" : jQuery('#applyUser').val() == '' ? '' : '%'
							+ jQuery('#applyUser').val() + "%",
					"reduceStatus" : jQuery('#reduceStatus').val(),
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
	diag.Title = "资产处置信息修改";
	diag.MessageTitle = "单据号：CZ_ " + id;
	diag.URL = contextPath + "/asset/reduce/edit.htm?id=" + id;
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
	diag.Title = "资产处置详细信息";
	diag.MessageTitle = "单据号： CZ_" + id;
	diag.URL = contextPath + "/asset/reduce/detail.htm?id=" + id;
	diag.show();
};

var openAddWin = function() {
	var diag = new Dialog();
	diag.Width = 810;
	diag.Height = 600;
	diag.Title = "新增资产处置";
	diag.URL = contextPath + "/asset/reduce/add.htm?";
	diag.MessageTitle = "新增资产处置";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};