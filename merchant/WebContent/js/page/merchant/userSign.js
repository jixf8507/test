jQuery(document).ready(function() {

	jQuery('#status').chosen();
	sitesCombox.requestData();

	jQuery("#beginTime,#endTime").datepicker();
	userSignTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		userSignTable.bStateSave = false; 
		userSignTable.reloadData();
	});
	jQuery('#status').change(function() {
		userSignTable.bStateSave = false; 
		userSignTable.reloadData();
	});

	// 点击查询按钮事件
	jQuery('#excelBtn').click(function() {
		userSignTable.exportExcel();
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
		userSignTable.bStateSave = false; 
		userSignTable.reloadData();
	}
});

var userSignTable = new PageDataTables(
		{
			tableId : 'userSignTable',
			ajaxUrl : contextPath + "/merchant/user/ajaxSignData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/merchant/user/exportSignToExcel.htm?",
			aoColumns : [
					{
						"mDataProp" : "singDate",
						"sClass" : "center"
					},
					{
						"mDataProp" : "userName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "mobilePhone",
						"sClass" : "center"
					},
					{
						"mDataProp" : "siteName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "inTime",
						"sClass" : "center"
					},
					{
						"mDataProp" : "inDescribe",
						"sClass" : "center"
					},
					{
						"mDataProp" : "outSiteName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "outTime",
						"sClass" : "center"
					},
					{
						"mDataProp" : "outDescribe",
						"sClass" : "center"
					},
					{
						"mDataProp" : "status",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var singDate = obj.aData['singDate'];
							var userName = obj.aData['userName'];
							var siteId = obj.aData['siteId'];
							var outSiteId = obj.aData['outSiteId'];
							return '<a href="javaScript:openAddressWin('
									+ id
									+ ',\''
									+ singDate
									+ '\',\''
									+ userName
									+ '\',\'in\',\''
									+ siteId
									+ '\')">签到</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:openAddressWin('
									+ id + ',\'' + singDate + '\',\''
									+ userName + '\',\'out\',\'' + outSiteId
									+ '\')">签出</a>';
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"name" : jQuery('#name').val() == '' ? '' : '%'
							+ jQuery('#name').val() + '%',
					"mobilePhone" : jQuery('#mobilePhone').val() == '' ? ''
							: '%' + jQuery('#mobilePhone').val() + '%',
					"siteId" : jQuery('#siteId').val(),
					"beginTime" : jQuery('#beginTime').val(),
					"endTime" : jQuery('#endTime').val(),
					"status" : jQuery('#status').val()
				};
			}
		});

var openAddressWin = function(id, singDate, userName, type, siteId) {
	if(siteId==''){
		Dialog.alert("提示：暂无签出信息");
		return;
	}
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 650;
	diag.Title = "查看签到地点坐标";
	diag.MessageTitle = singDate + "&nbsp;&nbsp;&nbsp;" + userName;
	diag.URL = contextPath + "/merchant/user/signPoint.htm?id=" + id + "&type="
			+ type + "&siteId=" + siteId;
	diag.show();
};
