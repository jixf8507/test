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
	// 点击导出
	jQuery('#excelBtn').click(function() {
		sitesTable.exportExcel();
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
					+ new Date().getTime(),// 这个是请求的地址
			exportUrl : contextPath + "/stake/device/exportToExcel.htm?",// 这个是请求的地址
			aoColumns : [
					{
						"fnRender" : function(obj) {
							return '<img src="'
									+ contextPath
									+ obj.aData['imgUrl']
									+ '" onError="imgError(this)" alt="汽车图片" width="50px;" height="50px;" />';
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
						"mDataProp" : "name",
						"sClass" : "center"
					}, {
						"fnRender" : function(obj) {
							var status = obj.aData['isupdateprice'];
							if (status == '1') {
								return '已下发';
							} else {
								return '未下发';
							}
						},
						"sClass" : "center"
					}, {
						"mDataProp" : "deviceTypeName",
						"sClass" : "center"
					}, {
						"mDataProp" : "manufacturer",
						"sClass" : "center"
					}, {
						"mDataProp" : "devicePower",
						"sClass" : "center"
					}, {
						"mDataProp" : "fixedVoltage",
						"sClass" : "center"
					}, {
						"fnRender" : function(obj) {
							var status = obj.aData['status'];
							if (status == '废弃') {
								return '不联网';
							} else {
								return status;
							}
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"status" : jQuery('#status').val(),
					"siteId" : jQuery('#siteId').val(),
					"factoryNo" : jQuery('#factoryNo').val() == '' ? '' : '%'
							+ jQuery('#factoryNo').val() + '%',
					"flag" : '正常'
				};
			}
		});

function imgError(thiz) {
	thiz.src = contextPath + '/img/car.png';
}
