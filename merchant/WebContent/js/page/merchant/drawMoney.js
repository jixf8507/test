jQuery(document).ready(function() {

	jQuery(".chzn-select").chosen();
	jQuery("#beginTime,#endTime").datepicker();

	drawsTable.reloadData();
		
	
	// 点击查询按钮事件
	jQuery('#queryBtn').click(function() {
		drawsTable.bStateSave = false; 
		drawsTable.reloadData();
	});

	// 点击查询按钮事件
	jQuery('#status').change(function() {
		drawsTable.bStateSave = false; 
		drawsTable.reloadData();
	});

	// 点击查询按钮事件
	jQuery('#excelBtn').click(function() {
		drawsTable.exportExcel();
	});
	
	jQuery('#addBtn').live('click',function() {
		applyMoney();
	});
	
	
	// /// FORM VALIDATION /////
	jQuery.validator.addMethod("positiveNumber", function(value, element) {
		var reg = /^\d+(\.\d{1,2})?$/;
		return this.optional(element) || reg.test(value);
	}, "please enter a vaild number");

	jQuery("#form1").validate({
		rules : {
			'money' : {
				"required" : true,
				"positiveNumber" : true
			},
			'remarks' : "required"
		},
		messages : {
			'money' : {
				"required" : "提款金额不能为空",
				"positiveNumber" : "格式不正确"
			},
			'remarks' : "提款说明内容不能为空"
		}
	});

	jQuery('#money').change(function() {
		var balance = jQuery('#balance').val();
		var add = jQuery('#money').val();
		if (Number(add) > Number(balance)) {
			Dialog.alert("当前可提款余额不足");
			jQuery('#money').val("");
		}
	});

});

var drawsTable = new PageDataTables({
	tableId : 'drawsTable',
	ajaxUrl : contextPath + "/merchant/drawMoney/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/merchant/drawMoney/exportToExcel.htm?",
	aoColumns : [ {

		"mDataProp" : "money",
		"sClass" : "center"
	}, {
		"mDataProp" : "remarks",
		"sClass" : "center"
	}, {
		"mDataProp" : "status",
		"sClass" : "center"
	}, {
		"mDataProp" : "createdTime",
		"sClass" : "center"
	} ], 
	addButton : [
	             {"outId":"addBtn","value":"申请提款"},
	               
	            ],
	beforeload : function() {
		this.paraData = { 
			"status" : jQuery('#status').val(),
			"beginTime" : jQuery('#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + ' 23:59:59',
		};
	}
});

//保存方法
var callBack = function() {
	location.reload();
};

//点击弹出申请提款页面按钮。
var applyMoney = function(){
	var diag =new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title="申请退款";
	diag.URL=contextPath + "/merchant/drawMoney/applyMoney.htm";
	diag.MessageTitle="无";
	diag.OKEvent=function(){
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

