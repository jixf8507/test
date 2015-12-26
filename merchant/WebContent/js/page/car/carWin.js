jQuery(document).ready(function() {

	curSitesCombox.requestData();
	carTypeCombox.requestData();

	carTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		carTable.reloadData();
	});

});

// 选择框
var curSitesCombox = new Combox({
	id : 'curSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		carTable.reloadData();
	}
});

// 车辆型号选择框
var carTypeCombox = new Combox({
	id : 'carTypeId',
	url : contextPath + '/car/type/ajaxTypes.htm?t=' + new Date().getTime(),
	cText : 'typeName',
	cValue : 'id',
	emptyText : '请选择车辆型号',
	change : function() {
		carTable.reloadData();
	}
});

var cloumn = [ // 设定各列宽度
		{
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				return '<input style="width: 20px;" type="radio" name="id" id="'
						+ id
						+ '" value="'
						+ obj.aData['id']
						+ '_'
						+ obj.aData['carNumber'] + '" >';
			},
			"sClass" : "center"
		}, {
			"mDataProp" : "carNumber",
			"sClass" : "center"
		}, {
			"mDataProp" : "typeName",
			"sClass" : "center"
		}, {
			"mDataProp" : "manufacturerName",
			"sClass" : "center"

		}, {
			"mDataProp" : "siteName",
			"sClass" : "center"
		}, {
			"mDataProp" : "status",
			"sClass" : "center"
		} ];

// 车辆信息列表
var carTable = new PageDataTables({
	tableId : 'carTable',
	ajaxUrl : contextPath + '/car/car/ajaxData.htm?t='
				+ new Date().getTime(),
	exportUrl : contextPath + "/car/car/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : cloumn,
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
					+ jQuery('#carNumber').val() + '%',
			"carTypeId" : jQuery('#carTypeId').val(),
			"curSiteId" : jQuery('#curSiteId').val(),
			"flag" : '正常'
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
