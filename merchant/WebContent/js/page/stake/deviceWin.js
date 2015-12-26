jQuery(document).ready(function() {

	sitesCombox.requestData();
	sitesTable.reloadData();

	jQuery('#status').chosen();

	jQuery('#queryBtn').click(function() {
		sitesTable.bStateSave = false;
		sitesTable.reloadData();
	});
	jQuery('#status').change(function() {
		sitesTable.bStateSave = false;
		sitesTable.reloadData();
	});

});

// 选择框
var sitesCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择充电站',
	change : function() {
		sitesTable.bStateSave = false;
		sitesTable.reloadData();
	}
});

var sitesTable = new PageDataTables(
		{
			tableId : 'sitesTable',
			ajaxUrl : contextPath + "/stake/device/ajaxData.htm?t="
					+ new Date().getTime(),
			aoColumns : [
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return '<input style="width: 20px;" type="radio" name="id" id="'
									+ id
									+ '" value="'
									+ obj.aData['id']
									+ '_'
									+ obj.aData['factoryNo']
									+ '_'
									+ obj.aData['deviceNo']
									+ '_'
									+ obj.aData['nameplate']
									+ '_'
									+ obj.aData['siteName']
									+ '_'
									+ obj.aData['deviceTypeNo']
									+ '_'
									+ obj.aData['site_code']
									+ '_'
									+ obj.aData['lat']
									+ '_'
									+ obj.aData['lng'] + '" >';
						},
						"sClass" : "center"
					}, {
						"mDataProp" : "factoryNo",
						"sClass" : "center"
					}, {
						"mDataProp" : "deviceNo",
						"sClass" : "center"
					}, {
						"mDataProp" : "nameplate",
						"sClass" : "center"
					}, {
						"mDataProp" : "siteName",
						"sClass" : "center"
					}, {
						"mDataProp" : "area_name",
						"sClass" : "center"
					}, {
						"mDataProp" : "deviceTypeName",
						"sClass" : "center"
					}, {
						"mDataProp" : "manufacturer",
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"siteId" : jQuery('#siteId').val(),
					"factoryNo" : jQuery('#factoryNo').val() == '' ? '' : '%'
							+ jQuery('#factoryNo').val() + '%',
					"nameplate" : jQuery('#nameplate').val() == '' ? '' : '%'
							+ jQuery('#nameplate').val() + '%',
					"flag" : '正常',
					"deviceTypeNo" : 2,
					"isAllowParking" : 1
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