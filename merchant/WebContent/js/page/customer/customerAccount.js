jQuery(document)
		.ready(
				function() {
					normalCustomersTable.reloadData();

					jQuery(
							"#beginTime,#endTime,#beginTime1,#endTime1,#beginTime2,#endTime2")
							.datepicker();
					jQuery("#subStatus").chosen();

					jQuery("#subStatus").change(function() {
						cancelCustomersTable.bStateSave=false;//点击查询的时候清除cookies里面的额分页缓存。
						cancelCustomersTable.reloadData();
					});

					// 点击查询按钮事件
					jQuery('#normalQueryBtn,#normalDiv').click(function() {
						normalCustomersTable.bStateSave=false;//点击查询的时候清除cookies里面的额分页缓存。
						normalCustomersTable.reloadData();
					});

					// 点击查询按钮事件
					jQuery('#normalExcelBtn').click(function() {
						normalCustomersTable.exportExcel();
					});

					// 点击查询按钮事件
					jQuery('#freezeQueryBtn,#freezeDiv').click(function() {
						freezeCustomersTable.bStateSave=false;//点击查询的时候清除cookies里面的额分页缓存。
						freezeCustomersTable.reloadData();
					});

					// 点击查询按钮事件
					jQuery('#freezeExcelBtn').click(function() {
						freezeCustomersTable.exportExcel();
					});

					// 点击查询按钮事件
					jQuery('#cancelQueryBtn,#cancelDiv').click(function() {
						cancelCustomersTable.bStateSave=false;//点击查询的时候清除cookies里面的额分页缓存。
						cancelCustomersTable.reloadData();
					});

					// 点击查询按钮事件
					jQuery('#cancelExcelBtn').click(function() {
						cancelCustomersTable.exportExcel();
					});
				});

var normalCustomersTable = new PageDataTables(
		{
			tableId : 'normalCustomersTable',
			ajaxUrl : contextPath + "/customer/customer/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/customer/customer/exportToExcel.htm?",
			aoColumns : [
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
							var accountId = obj.aData['accountId'];
							var subStatus = obj.aData['subStatus'];
							var name = obj.aData['name'];
							if("申请退款"!=subStatus){
								return '<a href="javaScript:change('
								+ accountId
								+ ',\''
								+ name
								+ '\',\'freeze\')" >冻结</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:backMoney('
								+ accountId + ',\'' + name
								+ '\',\'cancel\')" >退款</a>';
							}else {
								return "退款处理";
							}
						},
						"sClass" : "center"
					} ],
			beforeload : function() {
				this.paraData = {
					"idCard" : jQuery('#normalIdCard').val() == '' ? '' : '%'
							+ jQuery('#normalIdCard').val() + "%",
					"phone" : jQuery('#normalPhone').val() == '' ? '' : '%'
							+ jQuery('#normalPhone').val() + "%",
					"name" : jQuery('#normalName').val() == '' ? '' : '%'
							+ jQuery('#normalName').val() + "%",
					"status" : '正常',
					"beginTime" : jQuery('#beginTime').val() == '' ? ''
							: jQuery('#beginTime').val(),
					"endTime" : jQuery('#endTime').val() == '' ? '' : jQuery(
							'#endTime').val()
							+ " 23:59:59"
				};
			}
		});

var freezeCustomersTable = new PageDataTables({
	tableId : 'freezeCustomersTable',
	ajaxUrl : contextPath + "/customer/customer/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/customer/customer/exportToExcel.htm?",
	aoColumns : [
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
				"mDataProp" : "describe",
				"sClass" : "center"
			},
			{
				"mDataProp" : "deletedTime",
				"sClass" : "center"
			},
			{
				"mDataProp" : "deletedUser",
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var accountId = obj.aData['accountId'];
					return '<a href="javaScript:updateStatusFun(' + accountId
							+ ',\'正常\')">解冻</a>';
				},
				"sClass" : "center"
			} ],
	beforeload : function() {
		this.paraData = {
			"idCard" : jQuery('#freezeIdCard').val() == '' ? '' : '%'
					+ jQuery('#freezeIdCard').val() + "%",
			"phone" : jQuery('#freezePhone').val() == '' ? '' : '%'
					+ jQuery('#freezePhone').val() + "%",
			"name" : jQuery('#freezeName').val() == '' ? '' : '%'
					+ jQuery('#freezeName').val() + "%",
			"status" : '冻结',
			"delBeginTime" : jQuery('#beginTime1').val() == '' ? '' : jQuery(
					'#beginTime1').val(),
			"delEndTime" : jQuery('#endTime1').val() == '' ? '' : jQuery(
					'#endTime1').val()
					+ " 23:59:59"

		};
	}
});

var cancelCustomersTable = new PageDataTables({
	tableId : 'cancelCustomersTable',
	ajaxUrl : contextPath + "/customer/customer/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/customer/customer/exportToExcel.htm?",
	aoColumns : [
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
				"mDataProp" : "describe",
				"sClass" : "center"
			},
			{
				"mDataProp" : "deletedTime",
				"sClass" : "center"
			},
			{
				"mDataProp" : "deletedUser",
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var accountId = obj.aData['accountId'];
					var cardNO = obj.aData['name'];
					var subStatus = obj.aData['subStatus'];
					if (subStatus == '已退款' || subStatus == '已补卡') {
						return subStatus;
					}
					return '<a href="javaScript:openEditeWin(' + accountId
							+ ',\'' + cardNO + '\');">退款</a>';
				},
				"sClass" : "center"
			} ],
	beforeload : function() {
		this.paraData = {
			"idCard" : jQuery('#cancelIdCard').val() == '' ? '' : '%'
					+ jQuery('#cancelIdCard').val() + "%",
			"phone" : jQuery('#cancelPhone').val() == '' ? '' : '%'
					+ jQuery('#cancelPhone').val() + "%",
			"name" : jQuery('#cancelName').val() == '' ? '' : '%'
					+ jQuery('#cancelName').val() + "%",
			"(subStatus" : "申请退款"/*jQuery('#subStatus').val()*/,
			"or subStatus)":"已退款",
			"status" : '正常',
			"subStatus" : jQuery('#subStatus').val() == '退款状态'?'':jQuery('#subStatus').val(),
			"delBeginTime" : jQuery('#beginTime2').val() == '' ? '' : jQuery(
					'#beginTime2').val(),
			"delEndTime" : jQuery('#endTime2').val() == '' ? '' : jQuery(
					'#endTime2').val()
					+ " 23:59:59"
		};
	}
});

// 点击冻结,注销
var change = function(id, name, status) {

	var diag = new Dialog();
	diag.Title = "请选择原因";
	diag.MessageTitle = name;
	diag.Width = 600;
	diag.Height = 380;
	diag.URL = contextPath + "/customer/account/" + status + ".htm?id=" + id;
	diag.OKEvent = function() {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/customer/account/changeStatusSubmit.htm?',
			data : diag.innerFrame.contentWindow.jQuery('#form1').serialize(),
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：操作失败！请重试.");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功.", function() {
						diag.close();
						location.reload(true);
					});
				} else {
					Dialog.alert(data['info']);
				}
			}
		});
	};
	diag.show();
};

//点击退款
var backMoney = function(id, name, status) {

	var diag = new Dialog();
	diag.Title = "请选择原因";
	diag.MessageTitle = name;
	diag.Width = 600;
	diag.Height = 380;
	diag.URL = contextPath + "/customer/account/" + status + ".htm?id=" + id;
	diag.OKEvent = function() {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/customer/account/backMoneySubmit.htm?',
			data : diag.innerFrame.contentWindow.jQuery('#form1').serialize(),
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：操作失败！请重试.");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功.", function() {
						diag.close();
						location.reload(true);
					});
				} else {
					Dialog.alert(data['info']);
				}
			}
		});
	};
	diag.show();
};






// 解冻
var updateStatusFun = function(id, status) {
	Dialog.confirm('警告：确认变更卡状态吗？', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/customer/account/changeStatus.htm?",
			data : {
				id : id,
				status : status
			},
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功", function() {
						location.reload();
					});

				} else {
					Dialog.alert(data['info']);
				}
			},
			error : function() {
				Dialog.alert("提示：操作失败");
			}
		});
	});
};

var openEditeWin = function(id, cardNO) {
	var diag = new Dialog();
	diag.Width = 750;
	diag.Height = 500;
	diag.Title = "注销用户退款";
	diag.URL = contextPath + "/customer/account/refund.htm?id=" + id;
	diag.MessageTitle = cardNO;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

var openrepairCardWin = function(id, cardNO) {
	var diag = new Dialog();
	diag.Width = 750;
	diag.Height = 500;
	diag.Title = "补卡";
	diag.URL = contextPath + "/customer/account/repair.htm?id=" + id;
	diag.MessageTitle = cardNO;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};
