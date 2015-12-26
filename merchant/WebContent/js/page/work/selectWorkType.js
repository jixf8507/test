jQuery(document).ready(function() {
	workTypeTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		workTypeTable.reloadData();
	});
});

var workTypeTable = new PageDataTables(
		{
			tableId : 'workTypeTable',
			ajaxUrl : contextPath + "/work/manage/ajaxData.htm?t="
					+ new Date().getTime(),
			aoColumns : [
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return '<input style="width: 20px;" type="radio" name="id" id="'
									+ id
									+ '" value="'
									+ obj.aData['userId']
									+ '_'
									+ obj.aData['typeName']
									+ '_'
									+ obj.aData['workDes']
									+ '_'
									+ obj.aData['userName']
									+ '_'
									+ obj.aData['mobilePhone'] + '">';

						},
						"sWidth" : "4%",
						"sClass" : "center"
					}, {
						"mDataProp" : "typeName",
						"sClass" : "center"
					}, {
						"mDataProp" : "workDes",
						"sClass" : "center"
					}, {
						"mDataProp" : "userName",
						"sClass" : "center"
					}, {
						"mDataProp" : "mobilePhone",
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"typeName" : jQuery('#typeName').val() == '' ? '' : '%'
							+ jQuery('#typeName').val() + "%",
					"userName" : jQuery('#userName').val() == '' ? '' : '%'
							+ jQuery('#userName').val() + "%",
					"mobilePhone" : jQuery('#mobilePhone').val() == '' ? ''
							: '%' + jQuery('#mobilePhone').val() + "%"

				};
			}
		});

/**
 * 提交form表单
 */
var submit = function(callBack) {
	var selId = '';
	jQuery("input[name=id]").each(function() {
		if (this.checked) {
			selId = jQuery(this).val();
		}
	});
	callBack(selId);
};
