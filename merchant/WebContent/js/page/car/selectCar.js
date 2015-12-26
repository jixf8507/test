jQuery(document).ready(function() {

	useSitesCombox.requestData();

	useCarsTable.reloadData();

	// 点击使用车辆查询按钮
	jQuery('#useQueryBtn').click(function() {
		useCarsTable.reloadData();
	});

});

// 选择框
var useSitesCombox = new Combox({
	id : 'useCurSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择',
	change : function() {
		useCarsTable.reloadData();
	}
});

// 车辆状态列表显示内容
var cloumn = [
		{
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				var noCarDevice = jQuery('#noCarDevice').val();
				var deviceNO = obj.aData['deviceNO'];
				if (noCarDevice == 'yes' && deviceNO != '') {
					return '';
				}
				return '<input style="width: 20px;" type="radio" name="id" id="'
						+ id
						+ '" value="'
						+ id
						+ '_'
						+ obj.aData['carNumber']
						+ '_' + deviceNO + '" >';
			},
			"sClass" : "center"
		}, {
			"mDataProp" : "carNumber",
			"sClass" : "center"
		}, {
			"mDataProp" : "manufacturerName",
			"sClass" : "center"
		}, {
			"mDataProp" : "typeName",
			"sClass" : "center"
		}, {
			"mDataProp" : "siteName",
			"sClass" : "center"
		}, {
			"mDataProp" : "deviceNO",
			"sClass" : "center"
		} ];

// 已经使用的车辆
var useCarsTable = new PageDataTables({
	tableId : 'useCarsTable',
	ajaxUrl : contextPath + "/car/car/ajaxCarData.htm?t=" + new Date().getTime(),// 这个是请求的地址
	aoColumns : cloumn,
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#useCarNumber').val() == '' ? '' : '%'
					+ jQuery('#useCarNumber').val() + '%',
			"curSiteId" : jQuery('#useCurSiteId').val(),
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
	var arr = selId.split('_');
	var data = {
		carId : arr[0],
		carNumber : arr[1],
		carTableName : 'car'
	};
	callBack(data);
};