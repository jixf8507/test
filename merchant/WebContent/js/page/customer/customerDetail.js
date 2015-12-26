jQuery(document).ready(function() {

	jQuery(".chzn-select").chosen();

	jQuery("#beginTime,#endTime").datepicker();

	jQuery('#queryBtn,#tradeDiv').click(function() {
		tradeTable.bStateSave=false;
		tradeTable.reloadData();
	});
	jQuery('#type').change(function() {
		tradeTable.bStateSave=false;
		tradeTable.reloadData();
	});
	jQuery('#excelBtn').click(function() {
		tradeTable.exportExcel();
	});

});

var tradeTable = new PageDataTables({
	tableId : 'tradeTable',
	iDisplayLength : 6,
	ajaxUrl : contextPath + "/customer/accountRecord/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/customer/accountRecord/exportToExcel.htm?",
	aoColumns : [ {
		"mDataProp" : "createdTime",
		"sClass" : "center"
	}, {
		"mDataProp" : "addBalance",
		"sClass" : "center"
	}, {
		"mDataProp" : "newBalance",
		"sClass" : "center"
	}, {
		"mDataProp" : "type",
		"sClass" : "center"
	}, {
		"mDataProp" : "describe",
		"sClass" : "center"
	}, {
		"mDataProp" : "transactUser",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"accountId" : jQuery('#customerId').val(),
			"type" : jQuery('#type').val(),
			"beginTime" : jQuery('#beginTime').val() == '' ? '' : jQuery(
					'#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + " 23:59:59"
		};
	}
});

var imgError = function(thiz) {
	thiz.src = contextPath + '/img/car.png';
};
