jQuery(document).ready(function() {

	jQuery('#flag').chosen();
	
	categoryCombox.requestData();
	assetTable.reloadData();
	
	jQuery("#beginTime,#endTime").datepicker();

	jQuery('#flag').change(function() {
		assetTable.bStateSave=false; 
		assetTable.reloadData();
	});
	
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

var categoryCombox = new Combox({
	id : 'categoryId',
	url : contextPath + '/asset/category/ajaxCategoryData.htm?t='
	+ new Date().getTime(),
	cText : 'name',
	cValue : 'id',
	emptyText : '请选择资产类型',
	change : function() {
		assetTable.bStateSave=false; 
		assetTable.reloadData();
	}
});

var assetTable = new PageDataTables({
	tableId : 'assetTable',
	ajaxUrl : contextPath + "/asset/manage/ajaxData.htm?t="
			+ new Date().getTime(), 
	exportUrl : contextPath + "/asset/manage/exportToExcel.htm?", 
	aoColumns :  [
	              {
	            	  "mDataProp" : "id",
	            	  "sClass" : "center"
	              },
	  			{
					"mDataProp" : "assetsName",
					"sClass" : "center"
				},
				{
					"mDataProp" : "name", 
					"sClass" : "center"
				},
				{
					"mDataProp" : "model",
					"sClass" : "center"
				},
				{
					"mDataProp" : "manufacturerName",
					"sClass" : "center"
				},
				{
					"mDataProp" : "purchaseDate",
					"sClass" : "center"
				},
				{
					"mDataProp" : "increasingMode",
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
					"mDataProp" : "flag",
					"sClass" : "center"
				},
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var assetsName = obj.aData['assetsName'];
					return '<a href="javaScript:openEditWin(' + id + ',\''
							+ assetsName + '\');">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:openDetailWin(' + id + ',\''
							+ assetsName + '\');">详细</a>';
				},
				"sClass" : "center"
			}],
			addButton : [
			             {"tableId":"assetTable","outId":"addBtn","value":"新增"},
			            ],
	beforeload : function() {
		this.paraData = {
			"assetsName" : jQuery('#assetsName').val() == '' ? '' : '%'
					+ jQuery('#assetsName').val() + "%",
			"siteId" : jQuery('#siteId').val(),
			"categoryId" : jQuery('#categoryId').val(),
			"flag" : jQuery('#flag').val(),
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59'
		};
	}
});


var openEditWin = function(id, assetsName) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "编辑固定资产信息";
	diag.URL = contextPath + "/asset/manage/edit.htm?id=" + id;
	diag.MessageTitle = assetsName;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	}; 
	diag.show();
};


//保存方法
var callBack = function() {
	location.reload();
};

var openDetailWin = function(id, assetsName) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "固定资产详细信息";
	diag.MessageTitle =  assetsName;
	diag.URL = contextPath + "/asset/manage/detail.htm?id=" + id;
	diag.show();
};

var openAddWin = function() {
	var diag = new Dialog();
	diag.Width = 810;
	diag.Height = 600;
	diag.Title = "新增资产";
	diag.URL = contextPath + "/asset/manage/add.htm?";
	diag.MessageTitle = "新增资产";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};