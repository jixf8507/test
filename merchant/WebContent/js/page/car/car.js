jQuery(document).ready(function() {

	curSitesCombox.requestData();
	carTypeCombox.requestData();

	carTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		carTable.reloadData();
	});

	// 点击导出
	jQuery('#excelBtn').click(function() {
		carTable.exportExcel();
	});

});

// 选择框
var curSitesCombox = new Combox({
	id : 'curSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择',
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
				return '<img src="'
						+ contextPath
						+ '/'
						+ obj.aData['littleIcon']
						+ '" onError="imgError(this)" alt="汽车图片" width="50px;" height="50px;" />';
			},
			"sClass" : "center"
		}, {
			"mDataProp" : "carNumber",
			"sClass" : "center"
		}, {
			"mDataProp" : "vin",
			"sClass" : "center"
		}, {
			"mDataProp" : "typeName",
			"sClass" : "center"
		}, {
			"mDataProp" : "manufacturerName",
			"sClass" : "center"
		}, {
			"mDataProp" : "color",
			"sClass" : "center"
		}, {
			"mDataProp" : "describe",
			"sClass" : "center"
		}, {
			"mDataProp" : "siteName",
			"sClass" : "center"
		}, {
			"mDataProp" : "status",
			"sClass" : "center"
		}, {
			"mDataProp" : "flag",
			"sClass" : "center"
		} ];

// 车辆信息列表
var carTable = new PageDataTables({
	tableId : 'carTable',
	ajaxUrl : contextPath + "/car/car/ajaxData.htm?t=" + new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/car/car/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : cloumn,
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
					+ jQuery('#carNumber').val() + '%',
			"vin" : jQuery('#vin').val() == '' ? '' : '%'
					+ jQuery('#vin').val() + '%',
			"carTypeId" : jQuery('#carTypeId').val(),
			"curSiteId" : jQuery('#curSiteId').val(),
			"flag" : '正常'
		};
	}
});

function imgError(thiz) {
	thiz.src = contextPath + '/img/car.png';
}
