jQuery(document).ready(function() {

	jQuery('#flag').chosen();
	
	assetRepairTable.reloadData();
	siteCombox.requestData();

	jQuery("#beginTime,#endTime").datepicker();

	jQuery('#flag').change(function() {
		assetRepairTable.bStateSave=false; 
		assetRepairTable.reloadData();
	});

	jQuery('#queryBtn').click(function() {
		assetRepairTable.bStateSave=false; 
		assetRepairTable.reloadData();
	});

	jQuery('#excelBtn').click(function() {
		assetRepairTable.exportExcel();
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
		assetRepairTable.bStateSave=false; 
		assetRepairTable.reloadData();
	}
});

var assetRepairTable = new PageDataTables(
		{
			tableId : 'assetRepairTable',
			ajaxUrl : contextPath + "/asset/repair/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/asset/repair/exportToExcel.htm?",
			aoColumns : [
					{
						"fnRender" : function(obj) {
							return 'WX_' + obj.aData['id'];
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "repairDate",
						"sClass" : "center"
					},
					{
						"mDataProp" : "repairReason",
						"sClass" : "center"
					},
					{
						"mDataProp" : "manufacturerName",
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
						"mDataProp" : "fee",
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
					             {"tableId":"assetRepairTable","outId":"addBtn","value":"新增"},
					            ],
			beforeload : function() {
				this.paraData = {
					"applyUser" : jQuery('#applyUser').val() == '' ? '' : '%'
							+ jQuery('#applyUser').val() + "%",
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
	diag.Title = "资产维修信息修改";
	diag.MessageTitle = "单据号：  WX_" + id;
	diag.URL = contextPath + "/asset/repair/edit.htm?id=" + id;
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
	diag.Title = "资产维修详细信息";
	diag.MessageTitle = "单据号： WX_" + id;
	diag.URL = contextPath + "/asset/repair/detail.htm?id=" + id;
	diag.show();
};

var openAddWin = function() {
	var diag = new Dialog();
	diag.Width = 810;
	diag.Height = 600;
	diag.Title = "新增资产维修";
	diag.URL = contextPath + "/asset/repair/add.htm?";
	diag.MessageTitle = "新增资产维修";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};