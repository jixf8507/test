jQuery(document).ready(function() {
	sitesTable.reloadData();
	jQuery("#beginTime,#endTime,#beginTime1,#endTime1").datepicker();

	jQuery('#queryBtn,#listDiv').click(function() {
		sitesTable.bStateSave = false;
		sitesTable.reloadData();
	});

	jQuery('#excelBtn').click(function() {
		sitesTable.exportExcel();
	});

	jQuery('#queryBtn1,#delDiv').click(function() {
		delTable.bStateSave = false;
		delTable.reloadData();
	});

	jQuery('#excelBtn1').click(function() {
		delTable.exportExcel();
	});

	// 点击新增事件
	jQuery("#addBtn").live('click', function() {
		addStakeCard();
	});
	// 点击充值事件
	jQuery("#addBtn1").live('click', function() {
		recharge();
	});

});

var sitesTable = new PageDataTables(
		{
			tableId : 'sitesTable',
			ajaxUrl : contextPath + "/stake/card/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/stake/card/exportToExcel.htm?",
			aoColumns : [
					{
						"mDataProp" : "cardID",
						"sClass" : "center"
					},
					{
						"mDataProp" : "cardNumber",
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
						"mDataProp" : "carId",
						"sClass" : "center"
					},
					{
						"mDataProp" : "balance",
						"sClass" : "center"
					},
					{
						"mDataProp" : "openAccountTime",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var cardID = obj.aData['cardID'];
							return '<a href="javaScript:openEditeWin('
									+ id
									+ ',\''
									+ cardID
									+ '\')">编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:deleteFun('
									+ id + ',\'' + cardID
									+ '\',\'删除\')"  >删除</a>';
						},
						"sClass" : "center"
					} ],
			addButton : [ {
				"outId" : "addBtn1",
				"value" : "充值"
			}, {
				"outId" : "addBtn",
				"value" : "新增"
			}, ],
			beforeload : function() {
				this.paraData = {
					"cardID" : jQuery('#cardID').val() == '' ? '' : '%'
							+ jQuery('#cardID').val() + '%',
					"carNumber" : jQuery('#carNumber').val() == '' ? '' : '%'
							+ jQuery('#carNumber').val() + '%',
					"cardNumber" : jQuery('#cardNumber').val() == '' ? '' : '%'
							+ jQuery('#cardNumber').val() + '%',
					"phone" : jQuery('#phone').val() == '' ? '' : '%'
							+ jQuery('#phone').val() + '%',
					"name" : jQuery('#name').val() == '' ? '' : '%'
							+ jQuery('#name').val() + '%',
					"isAnnul" : "正常",
					"beginTime" : jQuery('#beginTime').val(),
					"endTime" : jQuery('#endTime').val() == '' ? '' : jQuery(
							'#endTime').val()
							+ "23:59:59"
				};
			}
		});

var delTable = new PageDataTables({
	tableId : 'delTable',
	ajaxUrl : contextPath + "/stake/card/ajaxData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/stake/card/exportToExcel.htm?",
	aoColumns : [
			{
				"mDataProp" : "cardID",
				"sClass" : "center"
			},
			{
				"mDataProp" : "cardNumber",
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
				"mDataProp" : "carId",
				"sClass" : "center"
			},
			{
				"mDataProp" : "balance",
				"sClass" : "center"
			},
			{
				"mDataProp" : "openAccountTime",
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var id = obj.aData['id'];
					var cardID = obj.aData['cardID'];
					return '<a href="javaScript:deleteFun(' + id + ',\''
							+ cardID + '\',\'恢复\')"  >恢复</a>';
				},
				"sClass" : "center"
			} ],
	beforeload : function() {
		this.paraData = {
			"cardID" : jQuery('#cardID1').val() == '' ? '' : '%'
					+ jQuery('#cardID1').val() + '%',
			"carNumber" : jQuery('#carNumber1').val() == '' ? '' : '%'
					+ jQuery('#carNumber1').val() + '%',
			"cardNumber" : jQuery('#cardNumber1').val() == '' ? '' : '%'
					+ jQuery('#cardNumber1').val() + '%',
			"phone" : jQuery('#phone1').val() == '' ? '' : '%'
					+ jQuery('#phone1').val() + '%',
			"name" : jQuery('#name1').val() == '' ? '' : '%'
					+ jQuery('#name1').val() + '%',
			"isAnnul" : "删除",
			"beginTime" : jQuery('#beginTime1').val(),
			"endTime" : jQuery('#endTime1').val() == '' ? '' : jQuery(
					'#endTime1').val()
					+ "23:59:59"
		};
	}
});

// 打开租赁套餐选择页面
var openEditeWin = function(id, cardID) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "充电卡编辑";
	diag.Message = '选好COM口和波特率后，点击"读取卡号"按钮，此时即可刷卡，卡号会自动读取并显示在内置卡号内。'
			+ '<br/>若要读取另一张充电卡，则需要再次点击"读取卡号"按钮，进行读取。';
	diag.URL = contextPath + "/stake/card/edite.htm?id=" + id;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};

// 删除充电卡
var deleteFun = function(id, cardID, status) {
	Dialog.confirm('警告：确认' + status + '充电卡 [ ' + cardID + ' ] 吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/stake/card/delete.htm?status="
					+ encodeURI(encodeURI(status)),
			data : "id=" + id,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功", function() {
						location.reload(true);
					});

				} else {
					Dialog.alert("提示：操作失败");
				}
			},
			error : function() {
				Dialog.alert("提示：操作失败");
			}
		});
	});
};

// 点击弹出新增充电卡
var addStakeCard = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "新增充电卡";
	diag.URL = contextPath + "/stake/card/add.htm";
	diag.Message = '选好COM口和波特率后，点击"读取卡号"按钮，此时即可刷卡，卡号会自动读取并显示在内置卡号内。'
			+ '<br/>若要读取另一张充电卡，则需要再次点击"读取卡号"按钮，进行读取。';
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

// 点击弹出充值
var recharge = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "充电卡充值";
	diag.URL = contextPath + "/stake/card/rechargePage.htm";
	diag.Message = '选好COM口和波特率后，点击"读取卡号"按钮，此时即可刷卡，卡号会自动读取并显示在内置卡号内。'
			+ '<br/>若要读取另一张充电卡，则需要再次点击"读取卡号"按钮，进行读取。';
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};
