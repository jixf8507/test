jQuery(document).ready(function() {

	reduceDetailTable.reloadData();
	categoryCombox.requestData();

	jQuery('#queryBtn').click(function() {
		reduceDetailTable.reloadData();
	});
	jQuery('#type').change(function() {
		reduceDetailTable.reloadData();
	});
	jQuery('#excelBtn').click(function() {
		reduceDetailTable.exportExcel();
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
		reduceDetailTable.reloadData();
	}
});

var reduceDetailTable = new PageDataTables({
	tableId : 'reduceDetailTable',
	bSort : false,
	iDisplayLength : 6, 
	bLengthChange : false,
	ajaxUrl : contextPath + "/asset/reduce/ajaxDetailData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/asset/reduce/exportDetailToExcel.htm?",
	aoColumns : [{
		"mDataProp" : "assetsId",
		"sClass" : "center"
	},  {
		"mDataProp" : "assetsName",
		"sClass" : "center"
	}, {
		"mDataProp" : "name",
		"sClass" : "center"
	}, {
		"mDataProp" : "model",
		"sClass" : "center"
	},{
		"mDataProp" : "unit",
		"sClass" : "center"
	}],
	beforeload : function() {
		this.paraData = {
			"reduceId" : jQuery('#id').val(),
			"categoryId" : jQuery('#categoryId').val(),
			"assetsName" : jQuery('#assetsName').val() == '' ? '' :"%"+ jQuery(
					'#assetsName').val() + "%",
		};
	}
});
