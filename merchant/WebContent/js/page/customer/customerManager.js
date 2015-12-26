jQuery(document).ready(function() {
	jQuery("#beginTime,#endTime").datepicker();
	sitesCombox.requestData();
	normalCustomersTable.reloadData();
	

	// 点击查询按钮事件
	jQuery('#normalQueryBtn').click(function() {
		normalCustomersTable.bStateSave=false;//点击查询的时候清除cookies里面的额分页缓存。
		normalCustomersTable.reloadData();
	});

	// 点击查询按钮事件
	jQuery('#normalExcelBtn').click(function() {
		normalCustomersTable.exportExcel();
	});
	
	// 点击新增客户事件
	jQuery("#addBtn").live('click',function(){
		 addCustomer();
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
		normalCustomersTable.bStateSave=false;
		normalCustomersTable.reloadData();
	}
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
						"mDataProp" : "bankCardNO",
						"sClass" : "center"
					},
					{
						"mDataProp" : "bankType",
						"sClass" : "center"
					},
					{
						"mDataProp" : "siteName",
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
							var id = obj.aData['accountId'];
							var name = obj.aData['name'];
							return '<a href="javaScript:openEditeWin('
									+ id
									+ ',\''
									+ name
									+ '\');">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:openDetailWin('
									+ id + ',\'' + name + '\');">详细</a>';
						},
						"sClass" : "center"
					} ],
					addButton : [
					             {"outId":"addBtn","value":"新增"},
					               
					            ],
			beforeload : function() {
				this.paraData = {
					"idCard" : jQuery('#normalIdCard').val() == '' ? '' : '%'
							+ jQuery('#normalIdCard').val() + "%",
					"phone" : jQuery('#normalPhone').val() == '' ? '' : '%'
							+ jQuery('#normalPhone').val() + "%",
					"name" : jQuery('#normalName').val() == '' ? '' : '%'
							+ jQuery('#normalName').val() + "%",
					"siteId" : jQuery('#siteId').val(),
					"beginTime" : jQuery('#beginTime').val(),
					"endTime" : jQuery('#endTime').val() == '' ? '' : jQuery(
							'#endTime').val()
							+ ' 23:59:59',
					"status" : '正常'
				};
			}
		});

var openEditeWin = function(id, name) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "编辑用户信息";
	diag.URL = contextPath + "/customer/customer/edite.htm?id=" + id;
	diag.MessageTitle = name;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};// 点击确定后调用的方法
	
// 编辑保存方法 ,目的是为了编辑完毕的时候，保存不会重新刷新到第一页。/*代码冗余：废弃xuwei
//	var editCallBack = function() {
//		normalCustomersTable.bStateSave=true;
//		normalCustomersTable.reloadData();
//		diag.close();
//	};
	diag.show();
};

//点击弹出新增客户页面按钮。
var addCustomer = function(){
	var diag =new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title="新增客户";
	diag.URL=contextPath + "/customer/customer/add.htm";
	diag.MessageTitle="无";
	diag.OKEvent=function(){
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
	diag.Title = "客户详细信息";
	diag.MessageTitle = "姓名： " + name;
	diag.URL = contextPath + "/customer/customer/detail.htm?id=" + id;
	diag.show();
};
