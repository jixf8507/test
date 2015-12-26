jQuery(document).ready(function() {

	useDetailTable.reloadData();
	categoryCombox.requestData();

	jQuery('#queryBtn').click(function() {
		useDetailTable.reloadData();
	});
	jQuery('#type').change(function() {
		useDetailTable.reloadData();
	});
	jQuery('#excelBtn').click(function() {
		useDetailTable.exportExcel();
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
		useDetailTable.reloadData();
	}
});

var useDetailTable = new PageDataTables({
	tableId : 'useDetailTable',
	bSort : false,
	iDisplayLength : 6, 
	bLengthChange : false,
	ajaxUrl : contextPath + "/asset/use/ajaxDetailData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/asset/use/exportDetailToExcel.htm?",
	aoColumns : [{
		"mDataProp" : "assetsId",
		"sClass" : "center"
	},{
		"mDataProp" : "assetsName",
		"sClass" : "center"
	}, {
		"mDataProp" : "name",
		"sClass" : "center"
	}, {
		"mDataProp" : "model",
		"sClass" : "center"
	}, {
		"mDataProp" : "unit",
		"sClass" : "center"
	} , {
		"mDataProp" : "flag",
		"sClass" : "center"
	}],
	beforeload : function() {
		this.paraData = {
			"useId" : jQuery('#id').val(),
			"categoryId" : jQuery('#categoryId').val(),
			"assetsName" : jQuery('#assetsName').val() == '' ? '' :"%"+ jQuery(
					'#assetsName').val() + "%",
		};
	}
});
