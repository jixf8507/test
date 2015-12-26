jQuery(document).ready(function() {

	repairDetailTable.reloadData();
	categoryCombox.requestData();

	jQuery('#queryBtn').click(function() {
		repairDetailTable.reloadData();
	});
	jQuery('#type').change(function() {
		repairDetailTable.reloadData();
	});
	jQuery('#excelBtn').click(function() {
		repairDetailTable.exportExcel();
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
		repairDetailTable.reloadData();
	}
});

var repairDetailTable = new PageDataTables({
	tableId : 'repairDetailTable',
	iDisplayLength : 6,
	bLengthChange : false,
	ajaxUrl : contextPath + "/asset/repair/ajaxDetailData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/asset/repair/exportDetailToExcel.htm?",
	aoColumns : [ {
		"mDataProp" : "assetsId",
		"sClass" : "center"
	}, {
		"mDataProp" : "assetsName",
		"sClass" : "center"
	}, {
		"mDataProp" : "name",
		"sClass" : "center"
	}, {
		"mDataProp" : "model",
		"sClass" : "center"
	},{
		"mDataProp" : "repairReason",
		"sClass" : "center"
	}, {
		"mDataProp" : "repairStatus",
		"sClass" : "center"
	}, {
		"mDataProp" : "fee",
		"sClass" : "center"
	} ,
	 {
		"mDataProp" : "accessories",
		"sClass" : "center"
	}
	/*{
		"fnRender" : function(obj) {
			var id = obj.aData['id'];
			return '<a href="javaScript:openEditWin(' + id + ');">归还</a>';
		},
		"sClass" : "center"
	}*/],
	beforeload : function() {
		this.paraData = {
			"repairId" : jQuery('#id').val(),
			"categoryId" : jQuery('#categoryId').val(),
			"assetsName" : jQuery('#assetsName').val() == '' ? '' :"%"+ jQuery(
					'#assetsName').val() + "%",
		};
	}
});
