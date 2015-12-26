jQuery(document)
		.ready(
				function() {

					jQuery(".chzn-select").chosen();
					jQuery(
							"#beginTime,#endTime,#beginTime1,#endTime1,#beginTime2,#endTime2")
							.datepicker();
					normalCustomersTable.reloadData();

					jQuery("#subStatus").change(function() {
						familyCustomersTable.reloadData();
					});

					// 点击查询按钮事件
					jQuery('#normalQueryBtn').click(function() {
						normalCustomersTable.bStateSave=false;//点击查询的时候清除cookies里面的分页缓存。
						normalCustomersTable.reloadData();
					});
					jQuery('#normalExcelBtn').click(function() {
						normalCustomersTable.exportExcel();
					});
					
					// 点击查询按钮事件
					jQuery('#unitQueryBtn,#unitDiv').click(function() {
						unitCustomersTable.bStateSave=false;//点击查询的时候清除cookies里面的分页缓存。
						unitCustomersTable.reloadData();
					});
					jQuery('#unitExcelBtn').click(function() {
						unitCustomersTable.exportExcel();
					});

					// 点击查询按钮事件
					jQuery('#familyQueryBtn,#familylDiv').click(function() {
						familyCustomersTable.bStateSave=false;//点击查询的时候清除cookies里面的分页缓存。
						familyCustomersTable.reloadData();
					});
					jQuery('#familyExcelBtn').click(function() {
						familyCustomersTable.exportExcel();
					});

				});
var column = [ // 设定各列宽度
		{
			"mDataProp" : "idCard",
			"sClass" : "center"
		},
		{
			"mDataProp" : "phone",
			"sClass" : "center"
		},
		{
			"mDataProp" : "name",
			"sClass" : "center"
		},

		{
			"mDataProp" : "moneyOfassure",
			"sClass" : "center"
		},
		{
			"mDataProp" : "balance",
			"sClass" : "center"
		},
		{
			"mDataProp" : "status",
			"sClass" : "center"
		},
		{
			"mDataProp" : "createdTime",
			"sClass" : "center"
		},
		{
			"mDataProp" : "transactUser",
			"sClass" : "center"
		},
		{
			"fnRender" : function(obj) {
				var id = obj.aData['accountId'];
				var name = obj.aData['name'];
				return '<a href="javaScript:openDetailWin(' + id + ',\'' + name
						+ '\');">查看详细</a>';
			},
			"sClass" : "center"
		} ];

var normalCustomersTable = new PageDataTables({
	tableId : 'normalCustomersTable',
	ajaxUrl : contextPath + "/customer/customer/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/customer/customer/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : column,
	beforeload : function() {
		this.paraData = {
			"idCard" : jQuery('#normalIdCard').val() == '' ? '' : '%'
					+ jQuery('#normalIdCard').val() + "%",
			"phone" : jQuery('#normalPhone').val() == '' ? '' : '%'
					+ jQuery('#normalPhone').val() + "%",
			"name" : jQuery('#normalName').val() == '' ? '' : '%'
					+ jQuery('#normalName').val() + "%",
			"notStatus":"注销",
			"beginTime" : jQuery('#beginTime').val() == '' ? '' : jQuery(
					'#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + " 23:59:59"
		};
	}
});

var unitCustomersTable = new PageDataTables({
	tableId : 'unitCustomersTable',
	ajaxUrl : contextPath + "/customer/unit/ajaxCustomerData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/customer/customer/exportUnitToExcel.htm?",// 这个是请求的地址
	aoColumns : [ // 设定各列宽度
			{
				"mDataProp" : "idCard",
				"sClass" : "center"
			},
			{
				"mDataProp" : "phone",
				"sClass" : "center"
			},
			{
				"mDataProp" : "name",
				"sClass" : "center"
			},
			{
				"mDataProp" : "enterpriseName",
				"sClass" : "center"
			},

			{
				"mDataProp" : "moneyOfassure",
				"sClass" : "center"
			},
			{
				"mDataProp" : "balance",
				"sClass" : "center"
			},
			{
				"mDataProp" : "transactUser",
				"sClass" : "center"
			},
			{
				"mDataProp" : "status",
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var id = obj.aData['accountId'];
					var name = obj.aData['name'];
					return '<a href="javaScript:openDetailWin(' + id + ',\''
							+ name + '\');">查看详细</a>';
				},
				"sClass" : "center"
			} ],
	beforeload : function() {
		this.paraData = {
			"idCard" : jQuery('#unitIdCard').val() == '' ? '' : '%'
					+ jQuery('#unitIdCard').val() + "%",
			"phone" : jQuery('#unitPhone').val() == '' ? '' : '%'
					+ jQuery('#unitPhone').val() + "%",
			"name" : jQuery('#unitName').val() == '' ? '' : '%'
					+ jQuery('#unitName').val() + "%",
			"notStatus":"注销",
			"enterpriseName":  jQuery('#enterpriseName').val() == '' ? '' : '%'
							 + jQuery('#enterpriseName').val() + "%",
			"beginTime" : jQuery('#beginTime1').val() == '' ? '' : jQuery(
					'#beginTime1').val(),
			"endTime" : jQuery('#endTime1').val() == '' ? '' : jQuery(
					'#endTime1').val()
					+ " 23:59:59"
		};
	}
});

var familyCustomersTable = new PageDataTables({
	tableId : 'familyCustomersTable',
	ajaxUrl : contextPath + "/merchant/family/ajaxCustomerData2.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/customer/customer/exportFamilyToExcel.htm?",// 这个是请求的地址
	aoColumns : [ // 设定各列宽度
			{
				"mDataProp" : "idCard",
				"sClass" : "center"
			},
			{
				"mDataProp" : "phone",
				"sClass" : "center"
			},
			{
				"mDataProp" : "name",
				"sClass" : "center"
			},
			{
				"mDataProp" : "householder",
				"sClass" : "center"
			},
			{
				"mDataProp" : "moneyOfassure",
				"sClass" : "center"
			},
			{
				"mDataProp" : "balance",
				"sClass" : "center"
			},
			{
				"mDataProp" : "transactUser",
				"sClass" : "center"
			},
			{
				"mDataProp" : "status",
				"sClass" : "center"
			},
			/*{
				"fnRender" : function(obj) {
					var subStatus = obj.aData['subStatus'];
					if (subStatus == '已退款' || subStatus == '已补卡') {
						return subStatus;
					}
					return '未退款';
				},
				"sClass" : "center"
			},*/
			{
				"fnRender" : function(obj) {
					var id = obj.aData['accountId'];
					var name = obj.aData['name'];
					return '<a href="javaScript:openDetailWin(' + id + ',\''
							+ name + '\');">查看详细</a>';
				},
				"sClass" : "center"
			} ],
	beforeload : function() {
		this.paraData = {
			"idCard" : jQuery('#familyIdCard').val() == '' ? '' : '%'
					+ jQuery('#familyIdCard').val() + "%",
			"phone" : jQuery('#familyPhone').val() == '' ? '' : '%'
					+ jQuery('#familyPhone').val() + "%",
			"name" : jQuery('#familyName').val() == '' ? '' : '%'
					+ jQuery('#familyName').val() + "%",
			"subStatus" : jQuery('#subStatus').val(),
			"notStatus" :"注销",
			"householder":jQuery('#householder').val() == '' ? '' : '%'
						+ jQuery('#householder').val() + "%",
			"beginTime" : jQuery('#beginTime2').val() == '' ? '' : jQuery(
					'#beginTime2').val(),
			"endTime" : jQuery('#endTime2').val() == '' ? '' : jQuery(
					'#endTime2').val()
					+ " 23:59:59"
		};
	}
});

var openDetailWin = function(id, name) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "客户详细信息";
	diag.MessageTitle = "姓名： " + name;
	diag.URL = contextPath + "/customer/customer/detail.htm?id=" + id;
	diag.show();
};