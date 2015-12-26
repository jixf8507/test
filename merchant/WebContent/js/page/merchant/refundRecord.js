jQuery(document).ready(function() {

	jQuery("#type,#type1").chosen();

	sitesCombox.requestData();

	jQuery("#beginTime,#endTime,#beginTime1,#endTime1").datepicker();

	normalCustomersTable.reloadData();
	
	// 普通客户
	jQuery('#queryBtn').click(function() {
		normalCustomersTable.bStateSave = false; 
		normalCustomersTable.reloadData();
	});
	jQuery('#type').change(function() {
		normalCustomersTable.bStateSave = false; 
		normalCustomersTable.reloadData();
	});
	jQuery('#excelBtn').click(function() {
		normalCustomersTable.exportExcel();
	});

	// 单位会员
	jQuery('#queryBtn1,#unitDiv').click(function() {
		unitCustomersTable.bStateSave = false; 
		unitCustomersTable.reloadData();
	});
	jQuery('#type1').change(function() {
		unitCustomersTable.bStateSave = false; 
		unitCustomersTable.reloadData();
	});
	jQuery('#excelBtn1').click(function() {
		unitCustomersTable.exportExcel();
	});
});

var sitesCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		normalCustomersTable.bStateSave = false; 
		normalCustomersTable.reloadData();
	}
});

var normalCustomersTable = new PageDataTables({
	tableId : 'normalCustomersTable',
	ajaxUrl : contextPath + "/merchant/refundRecord/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/merchant/refundRecord/exportToExcel.htm?",
	aoColumns : [ {
		"mDataProp" : "idCard",
		"sClass" : "center"
	}, {
		"mDataProp" : "phone",
		"sClass" : "center"
	}, {
		"mDataProp" : "name",
		"sClass" : "center"
	}, {
		"mDataProp" : "addBalance",
		"sClass" : "center"
	}, {
		"mDataProp" : "type",
		"sClass" : "center"
	}, {
		"mDataProp" : "createdTime",
		"sClass" : "center"
	}, {
		"mDataProp" : "transactUser",
		"sClass" : "center"
	}, {
		"mDataProp" : "siteName",
		"sClass" : "center"
	}, {
		"mDataProp" : "describe",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"idCard" : jQuery('#idCard').val() == '' ? '' : '%'
					+ jQuery('#idCard').val() + '%',
			"phone" : jQuery('#phone').val() == '' ? '' : '%'
					+ jQuery('#phone').val() + '%',
			"name" : jQuery('#name').val() == '' ? '' : '%'
					+ jQuery('#name').val() + '%',
			"type" : jQuery('#type').val(),
			"siteId" : jQuery('#siteId').val(),
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59'
		};
	}
});

var unitCustomersTable = new PageDataTables({
	tableId : 'unitCustomersTable',
	ajaxUrl : contextPath + "/customer/unit/ajaxTradeData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/merchant/refundRecord/exportUnitToExcel.htm?",
	aoColumns : [ {
		"mDataProp" : "enterpriseName",
		"sClass" : "center"
	}, {
		"mDataProp" : "contactPerson",
		"sClass" : "center"
	}, {
		"mDataProp" : "contactPhone",
		"sClass" : "center"
	}, {
		"mDataProp" : "addBalance",
		"sClass" : "center"
	}, {
		"mDataProp" : "type",
		"sClass" : "center"
	}, {
		"mDataProp" : "createdTime",
		"sClass" : "center"
	}, {
		"mDataProp" : "transactUser",
		"sClass" : "center"
	}],
	beforeload : function() {
		this.paraData = {
			"contactPerson" : jQuery('#contactPerson').val() == '' ? '' : '%'
					+ jQuery('#contactPerson').val() + '%',
			"enterpriseName" : jQuery('#enterpriseName').val() == '' ? '' : '%'
					+ jQuery('#enterpriseName').val() + '%',
			"contactPhone" : jQuery('#contactPhone').val() == '' ? '' : '%'
					+ jQuery('#contactPhone').val() + '%',
			"returnCondition" : "yes",
			"type" : jQuery('#type1').val(),
			"beginTime" : jQuery('#beginTime1').val(),
			"endTime" : jQuery('#endTime1').val() == '' ? ''
					: jQuery('#endTime1').val() + ' 23:59:59'
		};
	}
});
