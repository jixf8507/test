jQuery(document)
		.ready(
				function() {

					siteCombox.requestData();

					jQuery(
							"#useBeginTime,#useEndTime,#repairBeginTime,#repairEndTime,#reduceBeginTime,#reduceEndTime")
							.datepicker();

					jQuery('#useQueryBtn,#useDiv').click(function() {
						useTable.reloadData();
					});
					jQuery('#useExcelBtn').click(function() {
						useTable.exportExcel();
					});

					jQuery('#repairQueryBtn,#repairDiv').click(function() {
						repairTable.reloadData();
					});
					jQuery('#repairExcelBtn').click(function() {
						repairTable.exportExcel();
					});
					
					jQuery('#reduceQueryBtn,#reduceDiv').click(function() {
						reduceTable.reloadData();
					});
					jQuery('#reduceExcelBtn').click(function() {
						reduceTable.exportExcel();
					});

				});

var siteCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	beforeload : function() {

	},
	change : function() {
		useTable.reloadData();
	}
});

var useTable = new PageDataTables({
	tableId : 'useTable',
	iDisplayLength : 6,
	ajaxUrl : contextPath + "/asset/manage/ajaxUseData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/asset/manage/exportUseToExcel.htm?",
	aoColumns : [ {
		"mDataProp" : "useDate",
		"sClass" : "center"
	}, {
		"mDataProp" : "siteName",
		"sClass" : "center"
	}, {
		"mDataProp" : "userName",
		"sClass" : "center"
	}, {
		"mDataProp" : "transactUser",
		"sClass" : "center"
	}, {
		"mDataProp" : "flag",
		"sClass" : "center"
	}, {
		"mDataProp" : "remarks",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"assetsId" : jQuery('#assetsId').val(),
			"siteId" : jQuery('#siteId').val(),
			"beginTime" : jQuery('#useBeginTime').val() == '' ? '' : jQuery(
					'#useBeginTime').val(),
			"endTime" : jQuery('#useEndTime').val() == '' ? '' : jQuery(
					'#useEndTime').val()
					+ " 23:59:59"
		};
	}
});

var repairTable = new PageDataTables({
	tableId : 'repairTable',
	iDisplayLength : 6,
	ajaxUrl : contextPath + "/asset/manage/ajaxRepairData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/asset/manage/exportRepairToExcel.htm?",
	aoColumns : [ {
		"mDataProp" : "repairDate",
		"sClass" : "center"
	}, {
		"mDataProp" : "repairReason",
		"sClass" : "center"
	}, {
		"mDataProp" : "applyUser",
		"sClass" : "center"
	}, {
		"mDataProp" : "transactUser",
		"sClass" : "center"
	}, {
		"mDataProp" : "remarks",
		"sClass" : "center"
	}, {
		"mDataProp" : "manufacturerName",
		"sClass" : "center"
	}, {
		"mDataProp" : "fee",
		"sClass" : "center"
	}, {
		"mDataProp" : "accessories",
		"sClass" : "center"
	}, {
		"mDataProp" : "repairStatus",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"assetsId" : jQuery('#assetsId').val(),
			"beginTime" : jQuery('#repairBeginTime').val() == '' ? '' : jQuery(
					'#repairBeginTime').val(),
			"endTime" : jQuery('#repairEndTime').val() == '' ? '' : jQuery(
					'#repairEndTime').val()
					+ " 23:59:59"
		};
	}
});

var reduceTable = new PageDataTables({
	tableId : 'reduceTable',
	iDisplayLength : 6,
	ajaxUrl : contextPath + "/asset/manage/ajaxReduceData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/asset/manage/exportReduceToExcel.htm?",
	aoColumns : [ {
		"mDataProp" : "reduceDate",
		"sClass" : "center"
	}, {
		"mDataProp" : "reduceReason",
		"sClass" : "center"
	}, {
		"mDataProp" : "reduceStatus",
		"sClass" : "center"
	}, {
		"mDataProp" : "applyUser",
		"sClass" : "center"
	}, {
		"mDataProp" : "transactUser",
		"sClass" : "center"
	}, {
		"mDataProp" : "remarks",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"assetsId" : jQuery('#assetsId').val(),
			"beginTime" : jQuery('#reduceBeginTime').val() == '' ? '' : jQuery(
					'#reduceBeginTime').val(),
			"endTime" : jQuery('#reduceEndTime').val() == '' ? '' : jQuery(
					'#reduceEndTime').val()
					+ " 23:59:59"
		};
	}
});
