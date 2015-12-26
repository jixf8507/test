jQuery(document).ready(function() {

	jQuery("#beginTime,#endTime").datepicker();

	sitesCombox.requestData();
	sitesTable.reloadData();

	jQuery('#queryBtn').click(function() {
		sitesTable.bStateSave=false; 
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
	emptyText : '请选择租赁点',
	change : function() {
		sitesTable.bStateSave=false; 
		sitesTable.reloadData();
	}
});

var sitesTable = new PageDataTables({
	tableId : 'sitesTable',
	ajaxUrl : contextPath + "/stake/faultRecord/ajaxData.htm?t="
			+ new Date().getTime(), 
	exportUrl : contextPath + "/stake/faultRecord/exportToExcel.htm?", 
	aoColumns : [  
	{
		"mDataProp" : "siteName",
		"sClass" : "center"
	}, {
		"mDataProp" : "factoryNo",
		"sClass" : "center"
	},
	{
		"fnRender" : function(obj) {
			var grooveNo = obj.aData['grooveNo'];
			if(grooveNo == -1){
				return '全部';
			}else{ 
				grooveNo = parseFloat(grooveNo)+1;
				return '左'+ grooveNo;
			}
		},
		"sClass" : "center"
	},{
		"mDataProp" : "faultNo",
		"sClass" : "center"
	}, {
		"mDataProp" : "sTime",
		"sClass" : "center"
	}, {
		"mDataProp" : "eTime",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"siteId" : jQuery('#siteId').val(),
			"factoryNo" : jQuery('#factoryNo').val() == '' ? '' : '%'
					+ jQuery('#factoryNo').val() + '%',
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59',
		};
	}
});
