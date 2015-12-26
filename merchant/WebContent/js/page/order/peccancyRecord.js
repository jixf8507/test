jQuery(document).ready(function() {

	jQuery("#beginTime,#endTime,#beginTime1,#endTime1").datepicker();

	peccancyRecordsTable.reloadData();
	
	// 查询为付费停车记录
	jQuery('#queryBtn,#unHandle').click(function() {
		peccancyRecordsTable.bStateSave=false;
		peccancyRecordsTable.reloadData();
	});

	// 根据停车场查找为付费停车记录
	jQuery('#excelBtn').click(function() {
		peccancyRecordsTable.exportExcel();
	});

	// 查询为付费停车记录
	jQuery('#queryBtn1,#handle').click(function() {
		peccancyRecordsTable1.bStateSave=false;
		peccancyRecordsTable1.reloadData();
	});

	// 根据停车场查找为付费停车记录
	jQuery('#excelBtn1').click(function() {
		peccancyRecordsTable1.exportExcel();
	});
	
	// 点击新增违章记录事件
	jQuery('#addBtn').live('click',function() {
		addPeccancyRecords();
	});
	jQuery('#loadFile').click(function(){
		openloadFile();
	});

});

//上传文件弹出窗
var openloadFile=function(){
	var diag =new Dialog();
	diag.Width = 700;
	diag.Height = 500;
	diag.Title="上传文件";
	diag.URL=contextPath + "/order/peccancyRecord/loadFile.htm?";
	diag.MessageTitle="上传文件";
	diag.OKEvent=function(){
		diag.innerFrame.contentWindow.submit(callBack);
		
	};
	diag.show();
};

//刷新页面方法
var callBack=function(){
	location.reload();
};

// 租赁违章记录分页查询
var peccancyRecordsTable = new PageDataTables({
	tableId : 'peccancyRecordsTable',
	ajaxUrl : contextPath + "/order/peccancyRecord/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/order/peccancyRecord/exportToExcel.htm?",
	aoColumns : [
			{
				"fnRender" : function(obj) {
					return 'ZCDD_' + obj.aData['orderId'];
				},
				"sClass" : "center"
			},
			{
				"mDataProp" : "carNumber",
				"sClass" : "center"
			},
			{
				"mDataProp" : "peccancyTime",
				"sClass" : "center"
			},
			{
				"mDataProp" : "name",
				"sClass" : "center"
			},
			{
				"mDataProp" : "phone",
				"sClass" : "center"
			},
			{
				"mDataProp" : "address",
				"sClass" : "center"
			},
			{
				"mDataProp" : "info",
				"sClass" : "center"
			},
			{
				"mDataProp" : "checkMan",
				"sClass" : "center"
			},
			{
				"mDataProp" : "payCost",
				"sClass" : "center"
			},
			{
				"mDataProp" : "payStatus",
				"sClass" : "center"
			},

			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var carNumber = obj.aData['carNumber'];
					return '<a href="javaScript:openHandleWin(' + id + ',\''
							+ carNumber + '\')">处理</a>';
				},
				"sClass" : "center"
			} ],
	beforeload : function() {
		this.paraData = {
			"phone" : jQuery('#phone').val() == '' ? '' : '%'
					+ jQuery('#phone').val() + '%',
			"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
					+ jQuery('#carNumber').val() + '%',
			"beginTime" : jQuery('#beginTime') == '' ? ''
					: jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59',
			"payStatus" : '未处理'
		};
	}
});

// 租赁违章记录分页查询
var peccancyRecordsTable1 = new PageDataTables({
	tableId : 'peccancyRecordsTable1',
	ajaxUrl : contextPath + "/order/peccancyRecord/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/order/peccancyRecord/exportToExcel.htm?",
	aoColumns : [ {
		"fnRender" : function(obj) {
			return 'ZCDD_' + obj.aData['orderId'];
		},
		"sClass" : "center"
	}, {
		"mDataProp" : "carNumber",
		"sClass" : "center"
	}, {
		"mDataProp" : "peccancyTime",
		"sClass" : "center"
	}, {
		"mDataProp" : "name",
		"sClass" : "center"
	}, {
		"mDataProp" : "phone",
		"sClass" : "center"
	}, {
		"mDataProp" : "address",
		"sClass" : "center"
	}, {
		"mDataProp" : "info",
		"sClass" : "center"
	}, {
		"mDataProp" : "checkMan",
		"sClass" : "center"
	}, {
		"mDataProp" : "payCost",
		"sClass" : "center"
	}, {
		"mDataProp" : "payStatus",
		"sClass" : "center"
	} ],
	beforeload : function() {
		this.paraData = {
			"phone" : jQuery('#phone1').val() == '' ? '' : '%'
					+ jQuery('#phone1').val() + '%',
			"carNumber" : jQuery('#carNumber1').val() == '' ? '' : '%'
					+ jQuery('#carNumber1').val() + '%',
			"beginTime" : jQuery('#beginTime1') == '' ? '' : jQuery(
					'#beginTime1').val(),
			"endTime" : jQuery('#endTime1').val() == '' ? '' : jQuery(
					'#endTime1').val()
					+ ' 23:59:59',
			"payStatus" : '已处理'
		};
	}
});

function openHandleWin(id, carNumber) {
	var diag = new Dialog();
	diag.Title = "租赁违章处理";
	diag.MessageTitle = carNumber;
	diag.Width = 850;
	diag.Height = 500;
	diag.URL = contextPath + "/order/peccancyRecord/edit.htm?id=" + id;
	diag.OKEvent = function() {
		if (diag.innerFrame.contentWindow.jQuery('#form1').valid()) {
			jQuery.ajax({
				cache : true,
				type : "POST",
				url : contextPath + '/order/peccancyRecord/saveEdit.htm?',
				data : diag.innerFrame.contentWindow.jQuery('#form1')
						.serialize(),
				async : false,
				dataType : 'json',
				error : function(request) {
					Dialog.alert("错误：操作失败！请重试.");
				},
				success : function(msg) {
					if (msg.success) {
						Dialog.alert("提示：操作成功.", function() {
							diag.close();
							location.reload(true);
						});
					} else {
						Dialog.alert("提示：操作失败！请重试.");
					}
				}
			});
		}
	};
	diag.show();
};


//保存方法
var callBack=function(){
	location.reload();
};

//点击弹出新增违章记录按钮。
var addPeccancyRecords = function(){
	var diag =new Dialog();
	diag.Width = 700;
	diag.Height = 500;
	diag.Title="新增违章记录";
	diag.URL=contextPath + "/order/peccancyRecord/add.htm";
	diag.MessageTitle="无";
	diag.OKEvent=function(){
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
	
};

