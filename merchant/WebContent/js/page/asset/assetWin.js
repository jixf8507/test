jQuery(document).ready(function() {

	assetsTable.reloadData();
	categoryCombox.requestData();

	jQuery('#queryBtn').click(function() {
		assetsTable.reloadData();
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
		assetsTable.reloadData();
	}

});

var assetsTable = new PageDataTables(
		{
			tableId : 'assetsTable',
			iDisplayLength : 6,
			ajaxUrl : contextPath + "/asset/manage/ajaxData.htm?t="
					+ new Date().getTime(),
			aoColumns : [
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var assetIds = jQuery('#assetIds').val();
							var arr = assetIds.split(',');
							for (var i = 0; i < arr.length - 1; i++) {
								if (id == arr[i]) {
									return '<input style="width: 20px;" type="checkbox" name="id" id="'
											+ id
											+ '" title="'
											+ id
											+ '" checked="checked" value="'
											+ obj.aData['id']
											+ '_'
											+ obj.aData['name']
											+ '_'
											+ obj.aData['assetsName']
											+ '_'
											+ obj.aData['model'] + '" >';
								}
							}
							return '<input style="width: 20px;" type="checkbox" name="id" id="'
									+ id
									+ '" title="'
									+ id
									+ '" value="'
									+ obj.aData['id']
									+ '_'
									+ obj.aData['name']
									+ '_'
									+ obj.aData['assetsName']
									+ '_'
									+ obj.aData['model'] + '" >';

						},
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
					}, {
						"mDataProp" : "manufacturerName",
						"sClass" : "center"
					}, {
						"mDataProp" : "fee",
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"assetsName" : jQuery('#assetsName').val() == '' ? '' : '%'
							+ jQuery('#assetsName').val() + "%",
					"categoryId" : jQuery('#categoryId').val(),
					"flag" : jQuery('#flag').val(),
					"flag1" : "销毁",
					"flag2" : "出售"
				};
			}
		});

/**
 * 提交form表单
 */
var submit = function(callBackCheckbox) {
	var obj = document.getElementsByName('id');
	var s = '';
	for (var i = 0; i < obj.length; i++) {
		if (obj[i].checked) {
			s += obj[i].value + ',';
		}
	}
	callBackCheckbox(s);
};
