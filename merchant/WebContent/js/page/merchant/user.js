jQuery(document).ready(function() {

	// /// 选择框条件查询 /////
	jQuery("#right,#rights").chosen();

	querySitesCombox.requestData();
	userTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		userTable.bStateSave = false; 
		userTable.reloadData();
	});
	jQuery('#right').change(function() {
		userTable.bStateSave = false; 
		userTable.reloadData();
	});

	// 点击查询按钮事件
	jQuery('#excelBtn').click(function() {
		userTable.exportExcel();
	});
	
	jQuery('#addBtn').live('click',function() {
		addUser();
	});

});

// 选择框
var querySitesCombox = new Combox({
	id : 'querySiteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
	+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	change : function() {
		userTable.bStateSave = false; 
		userTable.reloadData();
	}
});

var columns = [ // 设定各列宽度
		{
			"mDataProp" : "email",
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
			"mDataProp" : "rights",
			"sClass" : "center"
		},
		{
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				var userName = obj.aData['userName'];
				return '<a href="javaScript:openRightsWin(' + id + ',\''
						+ userName + '\')" >设置权限</a>';
			},
			"sClass" : "center"
		},
		{
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				var userName = obj.aData['userName'];
				return '<a href="javaScript:openUpdateWin('
						+ id
						+ ',\''
						+ userName
						+ '\')">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:deleteFun('
						+ id + ')" >删除</a>';
			},
			"sClass" : "center"
		} ];

// 用户列表
var userTable = new PageDataTables({
	tableId : 'userTable',
	ajaxUrl : contextPath + "/merchant/user/ajaxData.htm?t="
			+ new Date().getTime(), 
	exportUrl : contextPath + "/merchant/user/exportToExcel.htm?", 
	aoColumns : columns,
	addButton : [
	             {"outId":"addBtn","value":"新增"},
	               
	            ],
	beforeload : function() {
		this.paraData = {
			"userName" : jQuery('#userName').val() == '' ? '' : '%'
					+ jQuery('#userName').val() + '%',
					"mobilePhone" : jQuery('#mobilePhone').val() == '' ? '' : '%'
						+ jQuery('#mobilePhone').val() + '%',
			"email" : jQuery('#queryEmail').val() == '' ? '' : '%'
					+ jQuery('#queryEmail').val() + '%',
			"siteId" : jQuery('#querySiteId').val(),
			"right" : jQuery('#right').val()

		};
	}
});

// /// 删除行 /////
var deleteFun = function(id) {
	Dialog.confirm('提示：确认删除?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/merchant/user/delete.htm?",
			data : "id=" + id,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功！",function(){
						location.reload(true);
					});
					
				}
			},
			error : function() {
				Dialog.alert("提示：验证失败");
			}
		});
	});
};

// 打开租赁套餐选择页面
var openUpdateWin = function(id, userName) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "员工编辑";
	diag.URL = contextPath + "/merchant/user/edite.htm?id=" + id;
	diag.MessageTitle = userName;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	}; 
	diag.show();
};

//点击弹出新增员工按钮。
var addUser = function(){
	var diag =new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title="新增员工";
	diag.URL=contextPath + "/merchant/user/add.htm";
	diag.MessageTitle="新增员工";
	diag.OKEvent=function(){
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
	
};

// 保存方法
var callBack = function() {
	location.reload();
};

// 打开租赁套餐选择页面
var openRightsWin = function(id, userName) {
	var diag = new Dialog();
	diag.Width = 400; 
	diag.Height = 500;
	diag.Title = "员工权限设置";
	diag.URL = contextPath + "/page/merchant/userRights.jsp?id=" + id;
	diag.MessageTitle = userName;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	}; 
	diag.show();
};
