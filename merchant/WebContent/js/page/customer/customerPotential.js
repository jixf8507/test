jQuery(document).ready(function() {
	normalCustomersTable.reloadData();

	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		normalCustomersTable.bStateSave=false;//点击查询的时候清除cookies里面的额分页缓存。
		normalCustomersTable.reloadData();
	});

});

var normalCustomersTable = new PageDataTables({
	tableId : 'normalCustomersTable',
	ajaxUrl : contextPath + "/customer/potential/ajaxData.htm?t="
			+ new Date().getTime(),
	// exportUrl : contextPath +
	// "/customer/index!exportToExcel.action?",
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
				"mDataProp" : "sex",
				"sClass" : "center"
			},
			{
				"mDataProp" : "address",
				"sClass" : "center"

			},
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var name = obj.aData['name'];
					return '<a href="javaScript:openDetailWin(' + id + ',\''
							+ name + '\')">审核办卡</a>';
				},
				"sClass" : "center"
			} ],
	beforeload : function() {
		this.paraData = {
			"idCard" : jQuery('#idCard').val() == '' ? '' : '%'
					+ jQuery('#idCard').val() + "%",
			"phone" : jQuery('#phone').val() == '' ? '' : '%'
					+ jQuery('#phone').val() + "%",
			"name" : jQuery('#name').val() == '' ? '' : '%'
					+ jQuery('#name').val() + "%"
		};
	}
});

var openDetailWin = function(id, name) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "审核用户信息";
	diag.URL = contextPath + "/customer/potential/check.htm?id=" + id;
	diag.MessageTitle = name;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};// 点击确定后调用的方法
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};
