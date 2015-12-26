jQuery(document).ready(function() {
	workTypeTable.reloadData();
     
	jQuery("#beginTime,#endTime").datepicker();
	// 点击查询按钮事件
	jQuery('#QueryBtn').click(function() {
		workTypeTable.bStateSave = false;// 点击查询的时候清除cookies里面的额分页缓存。
		workTypeTable.reloadData();
	});
    jQuery('#addBtn').live('click',function(){
    	addworkType();
    });
	// 点击查询按钮事件
	jQuery('#ExcelBtn').click(function() {
		workTypeTable.exportExcel();
	});
});

var workTypeTable = new PageDataTables(
		{
			tableId : 'workTypeTable',
			ajaxUrl : contextPath + "/work/manage/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/work/manage/exportToExcel.htm?",
			aoColumns : [
					{
						"mDataProp" : "typeName",
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
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							return '<a href="javaScript:openupdateWin('
							+ id
							+ '\);">修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:openDetailWin('
							+ id
							+'\);">删除</a>';
						},
						"sClass" : "center"
					} 
					],
					addButton : [ {
						"outId" : "addbtn",
						"value" : "新增工单类型"
					}],
					addButton : [ {
						"outId" : "addBtn",
						"value" : "新增"
					}, ],
			beforeload : function() {
				this.paraData = {
					"typeName" : jQuery('#typeName').val() == '' ? '' : "%"
							+ jQuery('#typeName').val() + "%",
					"userName" : jQuery('#userName').val() == '' ? '' : "%"
							+ jQuery('#userName').val() + "%",
					"mobilePhone" : jQuery('#mobilePhone').val() == '' ? ''
							: "%" + jQuery('#mobilePhone').val() + "%",
					"beginTime" : jQuery('#beginTime').val(),
					"endTime" : jQuery('#endTime').val() == '' ? '' : jQuery(
							'#endTime').val()
							+ "23:59:59"

				};
			}
		});



// 点击弹出新增客户页面按钮。
var addworkType = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "新增工单类型";
	diag.URL = contextPath + "/work/manage/add.htm";
	diag.MessageTitle = "新增工单类型";
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
	diag.Title = "修改工单类型";
	diag.URL = contextPath + "/work/manage/updatepage.htm?id=" + id;
	diag.MessageTitle = "修改工单类型";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit1(callBack);
	};
	diag.show();
};

var openDetailWin = function(id){
	Dialog.confirm("确认删除该条工单类型记录吗？", function(){
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/work/manage/delete.htm?',
			data : "id="+id,// 你的formid
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("提示：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功",function(){
							location.reload();
					});	
				}
			}
		});
	});
};
