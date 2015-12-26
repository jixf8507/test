jQuery(document)
		.ready(
				function() {
					jQuery(
							"#beginTime,#endTime,#cancelBeginTime,#cancelEndTime,#freezeBeginTime,#freezeEndTime")
							.datepicker();
					unitCustomersTable.reloadData();

					jQuery('#addBtn').live("click",function() {
						openAddWin();//新增单位会员
					});
					
					jQuery('#queryBtn,#normalDiv').click(function() {
						unitCustomersTable.bStateSave=false;//清除cookies里面的分页缓存。
						unitCustomersTable.reloadData();
					});
					jQuery('#excelBtn').click(function() {
						unitCustomersTable.exportExcel();
					});
					
					
					jQuery('#freezeQueryBtn,#freezeDiv').click(function() {
						freezeCustomersTable.bStateSave=false;//清除cookies里面的分页缓存。
						freezeCustomersTable.reloadData();
					});
					jQuery('#freezeExcelBtn').click(function() {
						freezeCustomersTable.exportExcel();
					});
					
					
					jQuery('#cancelQueryBtn,#cancelDiv').click(function() {
						cancelCustomersTable.bStateSave=false;//清除cookies里面的分页缓存。
						cancelCustomersTable.reloadData();
					});
					jQuery('#cancelExcelBtn').click(function() {
						cancelCustomersTable.exportExcel();
					});
				});


var unitCustomersTable = new PageDataTables(
		{
			tableId : 'unitCustomersTable',
			ajaxUrl : contextPath + "/customer/unit/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/customer/unit/exportToExcel.htm?",
			aoColumns :[
						{
							"mDataProp" : "enterpriseName",
							"sClass" : "center"
						},
						{
							"mDataProp" : "contactPerson",
							"sClass" : "center"
						},
						{
							"mDataProp" : "contactPhone",
							"sClass" : "center"
						},
						{
							"mDataProp" : "address",
							"sClass" : "center"
						},
						{
							"mDataProp" : "balance",
							"sClass" : "center"
						},
						{
							"mDataProp" : "moneyOfassure",
							"sClass" : "center"
						},
						{
							"mDataProp" : "status",
							"sClass" : "center"
						},
						{
							"mDataProp" : "transactUser",
							"sClass" : "center"
						},
						{
							"mDataProp" : "createdTime",
							"sClass" : "center"
						},
						{
							"fnRender" : function(obj) {
								var id = obj.aData['id'];
								var name = obj.aData['enterpriseName'];
								return '<a href="javaScript:openEditeWin('
										+ id
										+ ',\''
										+ name
										+ '\');">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;'
										+ '<div class="menu" style="margin-right:35px; position:relative;"><ul><li><a class="drop" href="javaScript:openMore('
										+ id
										+ ');">更多</a><ul id="ulList"><li><a href="javaScript:openDetailWin('
										+ id
										+ ',\''
										+ name
										+ '\');">查看详细</a></li><li><a href="javaScript:addUserWin('
										+ id
										+ ',\''+name+'\');">添加用户</a></li><li><a href="javaScript:change('
										+ id
										+ ',\''
										+ name
										+ '\',\'freeze\');">冻结企业</a></li><li><a href="javaScript:change('
										+ id
										+ ',\''
										+ name
										+ '\',\'cancel\');">注销企业</a></li></ul><li></ul></div>';
							},
							"sClass" : "center"
						} ],
						addButton : [
						             {"tableId":"unitCustomersTable","outId":"addBtn","value":"新增"},
						            ],
			beforeload : function() {
				this.paraData = {
					"enterpriseName" : jQuery('#enterpriseName').val() == '' ? ''
							: '%' + jQuery('#enterpriseName').val() + "%",
					"contactPerson" : jQuery('#contactPerson').val() == '' ? ''
							: '%' + jQuery('#contactPerson').val() + "%",
					"contactPhone" : jQuery('#contactPhone').val() == '' ? ''
							: '%' + jQuery('#contactPhone').val() + "%",
					"beginTime" : jQuery('#beginTime').val(),
					"endTime" : jQuery('#endTime').val() == '' ? '' : jQuery(
							'#endTime').val()
							+ ' 23:59:59',
					"status" : '正常'
				};
			}
		});



var freezeCustomersTable = new PageDataTables(
		{
			tableId : 'freezeCustomersTable',
			ajaxUrl : contextPath + "/customer/unit/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/customer/unit/exportToExcel.htm?",
			aoColumns : [
							{
								"mDataProp" : "enterpriseName",
								"sClass" : "center"
							},
							{
								"mDataProp" : "contactPerson",
								"sClass" : "center"
							},
							{
								"mDataProp" : "contactPhone",
								"sClass" : "center"
							},
							{
								"mDataProp" : "address",
								"sClass" : "center"
							},
							{
								"mDataProp" : "balance",
								"sClass" : "center"
							},
							{
								"mDataProp" : "moneyOfassure",
								"sClass" : "center"
							},
							{
								"mDataProp" : "status",
								"sClass" : "center"
							},
							{
								"mDataProp" : "deleteUser",
								"sClass" : "center"
							},
							{
								"mDataProp" : "deletedTime",
								"sClass" : "center"
							},
							{
								"fnRender" : function(obj) {
									var id = obj.aData['id'];
									var name = obj.aData['enterpriseName'];
									return '<a href="javaScript:openEditeWin('
											+ id
											+ ',\''
											+ name
											+ '\');">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;'
											+ '<div class="menu" style="margin-right:35px; position:relative;"><ul><li><a class="drop" href="javaScript:openMore('
											+ id
											+ ');">更多</a><ul id="ulList"><li><a href="javaScript:openDetailWin('
											+ id
											+ ',\''
											+ name
											+ '\');">查看详细</a></li><li><a href="javaScript:changeStatus('
											+ id
											+ ',\''
											+ name
											+ '\',\'正常\');">恢复正常</a></li></ul><li></ul></div>';
								},
								"sClass" : "center"
							} ],
			beforeload : function() {
				this.paraData = {
					"enterpriseName" : jQuery('#freezeEnterpriseName').val() == '' ? ''
							: '%' + jQuery('#freezeEnterpriseName').val() + "%",
					"contactPerson" : jQuery('#freezeContactPerson').val() == '' ? ''
							: '%' + jQuery('#freezeContactPerson').val() + "%",
					"contactPhone" : jQuery('#freezeContactPhone').val() == '' ? ''
							: '%' + jQuery('#freezeContactPhone').val() + "%",
					"deletedBeginTime" : jQuery('#freezeBeginTime').val(),
					"deletedEndTime" : jQuery('#freezeEndTime').val() == '' ? '' : jQuery(
							'#freezeEndTime').val()
							+ ' 23:59:59',
					"status" : '冻结'
				};
			}
		});

var cancelCustomersTable = new PageDataTables(
		{
			tableId : 'cancelCustomersTable',
			ajaxUrl : contextPath + "/customer/unit/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/customer/unit/exportToExcel.htm?",
			aoColumns : [
							{
								"mDataProp" : "enterpriseName",
								"sClass" : "center"
							},
							{
								"mDataProp" : "contactPerson",
								"sClass" : "center"
							},
							{
								"mDataProp" : "contactPhone",
								"sClass" : "center"
							},
							{
								"mDataProp" : "address",
								"sClass" : "center"
							},
							{
								"mDataProp" : "balance",
								"sClass" : "center"
							},
							{
								"mDataProp" : "moneyOfassure",
								"sClass" : "center"
							},
							{
								"mDataProp" : "status",
								"sClass" : "center"
							},
							{
								"mDataProp" : "deleteUser",
								"sClass" : "center"
							},
							{
								"mDataProp" : "deletedTime",
								"sClass" : "center"
							},
							{
								"fnRender" : function(obj) {
									var id = obj.aData['id'];
									var name = obj.aData['enterpriseName'];
									var subStatus = obj.aData['subStatus'];
									if (subStatus == '已退款') {
										return subStatus+'&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:openDetailWin('
											+ id
											+ ',\''
											+ name
											+ '\');">详细</a>';
									}else{
										return '<a href="javaScript:openReturnWin('
										+ id
										+ ',\''
										+ name
										+ '\',\''+subStatus+'\');">&nbsp;&nbsp;&nbsp;退款</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:openDetailWin('
										+ id
										+ ',\''
										+ name
										+ '\');">详细</a>';
									}
								},
								"sClass" : "center"
							} ],
			beforeload : function() {
				this.paraData = {
					"enterpriseName" : jQuery('#cancelEnterpriseName').val() == '' ? ''
							: '%' + jQuery('#cancelEnterpriseName').val() + "%",
					"contactPerson" : jQuery('#cancelContactPerson').val() == '' ? ''
							: '%' + jQuery('#cancelContactPerson').val() + "%",
					"contactPhone" : jQuery('#cancelContactPhone').val() == '' ? ''
							: '%' + jQuery('#cancelContactPhone').val() + "%",
					"deletedBeginTime" : jQuery('#cancelBeginTime').val(),
					"deletedEndTime" : jQuery('#cancelEndTime').val() == '' ? '' : jQuery(
							'#cancelEndTime').val()
							+ ' 23:59:59',
					"status" : '注销'
				};
			}
		});

var openAddWin = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "新增单位会员";
	diag.URL = contextPath + "/customer/unit/add.htm?";
	diag.MessageTitle = "新增单位会员信息";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

var openEditeWin = function(id, name) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "编辑单位会员信息";
	diag.URL = contextPath + "/customer/unit/edite.htm?id=" + id;
	diag.MessageTitle = name;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

var openReturnWin = function(id, name,subStatus) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "注销单位会员退款";
	diag.URL = contextPath + "/customer/unit/refund.htm?id=" + id;
	diag.MessageTitle = name;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	}; 
	diag.show();
};

var addUserWin = function(id,name) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "添加商家用户";
	diag.URL = contextPath + "/page/customer/selectCustomer.jsp?id="+id;
	diag.MessageTitle = name;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	}; 
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};

var openDetailWin = function(id, name) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "单位会员详细信息";
	diag.MessageTitle = "企业名称： " + name;
	diag.URL = contextPath + "/customer/unit/detail.htm?id=" + id;
	diag.show();
};


//点击冻结,注销
var change = function(id, name, status) {

	var diag = new Dialog();
	diag.Title = "请选择原因";
	diag.MessageTitle =  name;
	diag.Width = 600;
	diag.Height = 380;
	diag.URL = contextPath + "/customer/account/" + status + ".htm?id=" + id;
	diag.OKEvent = function() {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/customer/unit/changeStatusSubmit.htm?',
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

var changeStatus = function(id, name, status) {
	Dialog.confirm('警告：确认更改  [' + name + '] 状态为 \"'+status+'\" ?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/customer/unit/changeStatus.htm?",
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
