jQuery(document).ready(function() {

	 releaseTable.reloadData();
//	workTypeCombox.requestData();

	jQuery('#urgency').chosen();
	jQuery("#beginTime,#endTime").datepicker();

	jQuery('#queryBtn').click(function() {
		releaseTable.bStateSave = false;
		releaseTable.reloadData();
	});

	jQuery('#urgency').change(function() {
		releaseTable.bStateSave = false;
		releaseTable.reloadData();
	});

	jQuery('#excelBtn').click(function() {
		releaseTable.exportExcel();
	});

	jQuery('#addBtn').live('click', function() {
		addRelease();
	});
});

// 选择框
var workTypeCombox = new Combox({
	id : 'typeName',
	url : contextPath + '/work/manage/ajaxWorkType.htm?t='
			+ new Date().getTime(),
	cText : 'typeName',
	cValue : 'typeName',
	emptyText : '请选择工单类型',
	change : function() {
		releaseTable.bStateSave = false;
		releaseTable.reloadData();
	}
});

var releaseTable = new PageDataTables(
		{
			tableId : 'releaseTable',
			ajaxUrl : contextPath + "/work/order/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/work/order/exportToExcel.htm?",
			aoColumns : [
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return ' GD_' + id;
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "typeName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "workName",
						"sClass" : "center"
					},
					{
						"mDataProp" : "workDes",
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
						"mDataProp" : "createdTime",
						"sClass" : "center"
					},
					{
						"mDataProp" : "transactUser",
						"sClass" : "center"
					},
					{
						"mDataProp" : "urgency",
						"sClass" : "center"
					},
					{
						"mDataProp" : "workStatus",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return '<a href="javaScript:openupdateWin('
									+ id
									+ '\);">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:openDetailWin('
									+ id + '\);">删除</a>';
						},
						"sClass" : "center"
					} ],
			addButton : [ {
				"outId" : "addBtn",
				"value" : "新增工单"
			}, ],
			beforeload : function() {
				this.paraData = {
					"typeName" : jQuery('#typeName').val() == '' ? '' : "%"
							+ jQuery('#typeName').val() + "%",
					"workName" : jQuery('#workName').val() == '' ? '' : "%"
							+ jQuery('#workName').val() + "%",
					"userName" : jQuery('#userName').val() == '' ? '' : "%"
							+ jQuery('#userName').val() + "%",
					"mobilePhone" : jQuery('#mobilePhone').val() == '' ? ''
							: "%" + jQuery('#mobilePhone').val() + "%",
					"urgency" : jQuery('#urgency').val(),
					"beginTime" : jQuery('#beginTime').val(),
					"endTime" : jQuery('#endTime').val() == '' ? '' : jQuery(
							'#endTime').val()
							+ "23:59:59",
					"workStatus" : "待处理",
				};
			}
		});

var addRelease = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "发布新工单";
	diag.URL = contextPath + "/work/order/add.htm?";
	diag.MessageTitle = "发布新工单";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};

var openupdateWin = function(id) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "修改工单";
	diag.URL = contextPath + "/work/order/updatepage.htm?id=" + id;
	diag.MessageTitle = "修改工单";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit1(callBack);
	};
	diag.show();
};

var openDetailWin = function(id) {
	Dialog.confirm("确认删除该条工单记录吗？", function() {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/work/order/delete.htm?',
			data : "id=" + id,// 你的formid
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功", function() {
						location.reload();
					});
				}
			}
		});
	});
};
