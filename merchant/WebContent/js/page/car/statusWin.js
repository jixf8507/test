jQuery(document).ready(
		function() {

			sitesCombox.requestData();

			carsTable.reloadData();

			jQuery('#curStatus').chosen();

			jQuery('#queryBtn').click(function() {
				carsTable.reloadData();
			});
			jQuery('#curStatus').change(function() {
				carsTable.reloadData();
			});

		});

// 选择框
var sitesCombox = new Combox({
	id : 'curSiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		jQuery('#siteId').val(jQuery('#curSiteId').val());
			carsTable.reloadData();
	},
	beforeload : function() {
		this.emptyValue = jQuery("#siteId").val();
	}
});

var imgError = function(thiz) {
	thiz.src = contextPath + '/img/car.png';
};

var aoColumns = [
		{
			"fnRender" : function(obj) {
				return '<img src="'
						+ contextPath
						+ obj.aData['littleIcon']
						+ '" onError="imgError(this)" alt="汽车图片" width="50px;" height="50px;" />';
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
			"fnRender" : function(obj) {
				var deviceNO = obj.aData['deviceNO'];
				if (deviceNO == 'null' || deviceNO == '') {
					return obj.aData['kms'];
				} else {
					return obj.aData['curKms'];
				}
			},
			"sClass" : "center"
		}, {
			"fnRender" : function(obj) {
				var deviceNO = obj.aData['deviceNO'];
				if (deviceNO == 'null' || deviceNO == '') {
					return obj.aData['csurplusKms'];
				} else {
					return obj.aData['dsurplusKms'];
				}
			},
			"sClass" : "center"
		}, {
			"mDataProp" : "status",
			"sClass" : "center"
		} ];

var carsTable = new PageDataTables({
	tableId : 'carsTable',
	iDisplayLength : 5,
	ajaxUrl : contextPath + "/car/car/ajaxCarData.htm?t=" + new Date().getTime(),
	aoColumns : aoColumns,
	beforeload : function() {
		this.paraData = {
			"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
					+ jQuery('#carNumber').val() + '%',
			"curSiteId" : jQuery('#siteId').val(),
			"status" : jQuery('#curStatus').val(),
			"flag" : '正常'
		};
	}
});

