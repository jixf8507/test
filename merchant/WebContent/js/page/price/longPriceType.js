jQuery(document).ready(function() {

	longPriceTypesTable.reloadData();

	// 点击查询按钮事件
	jQuery('#preQueryBtn').click(function() {
		longPriceTypesTable.bStateSave=false;
		longPriceTypesTable.reloadData();
	});

	// 点击导出
	jQuery('#preExcelBtn').click(function() {
		longPriceTypesTable.exportExcel();
	});

	// 点击查询按钮事件
	jQuery('#overQueryBtn,#overDiv').click(function() {
		overLongPriceTable.bStateSave=false;
		overLongPriceTable.reloadData();
	});

	// 点击导出
	jQuery('#overExcelBtn').click(function() {
		overLongPriceTable.exportExcel();
	});
	
	jQuery('#addBtn').live('click',function() {
		addLongPrice();
	});

});


// 正在使用的收费套餐表格
var longPriceTypesTable = new PageDataTables({
	tableId : 'longPriceTypesTable',
	ajaxUrl : contextPath + "/price/priceType/long/ajaxData.htm?t="
					+ new Date().getTime(), 
	exportUrl : contextPath + "/price/priceType/long/exportToExcel.htm?", 
	aoColumns : [  
	      		{
	    			"mDataProp" : "typeName",
	    			"sClass" : "center"
	    		},
	    		{
	    			"mDataProp" : "monthFee",
	    			"sClass" : "center"
	    		},
	    		{
	    			"mDataProp" : "dayFee",
	    			"sClass" : "center"
	    		},
	    		{
	    			"mDataProp" : "hourFee",
	    			"sClass" : "center"
	    		},
	    		{
	    			"mDataProp" : "flag",
	    			"sClass" : "center"
	    		},
	    		{
	    			"fnRender" : function(obj) {
	    				var id = obj.aData['id'];
	    				var typeName = obj.aData['typeName'];
	    				return '<a href="javaScript:openInfoWin('
	    						+ id
	    						+ ',\''
	    						+ typeName
	    						+ '\');">选择车辆</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="javaScript:deleteFun('
	    						+ id + ',\''+typeName+'\')" id="' + id + '" >删除</a>';
	    			},
	    			"sClass" : "center"
	    		} ],
	    		addButton : [
	    		             {"tableId":"longPriceTypesTable","outId":"addBtn","value":"新增"},
	    		            ],
	beforeload : function() {
		this.paraData = {
			"typeName" : jQuery('#preTypeName').val() == '' ? '' : '%'
					+ jQuery('#preTypeName').val() + '%',
			"flag" : '正常'
		};
	}
});

// 已经结束的收费业务套餐列表
var overLongPriceTable = new PageDataTables({
	tableId : 'overLongPriceTable',
	ajaxUrl : contextPath + "/price/priceType/long/ajaxData.htm?t="
					+ new Date().getTime(), 
	exportUrl : contextPath + "/price/priceType/long/exportToExcel.htm?", 
	aoColumns : [  
	      		{
	    			"mDataProp" : "typeName",
	    			"sClass" : "center"
	    		},
	    		{
	    			"mDataProp" : "monthFee",
	    			"sClass" : "center"
	    		},
	    		{
	    			"mDataProp" : "dayFee",
	    			"sClass" : "center"
	    		},
	    		{
	    			"mDataProp" : "hourFee",
	    			"sClass" : "center"
	    		},
	    		{
	    			"mDataProp" : "flag",
	    			"sClass" : "center"
	    		}],
	beforeload : function() {
		this.paraData = {
			"typeName" : jQuery('#overTypeName').val() == '' ? '' : '%'
					+ jQuery('#overTypeName').val() + '%',
			"flag" : '删除'
		};
	}
});


//打开车辆选择对话框
var openInfoWin = function(id, typeName) {
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 500;
	diag.Title = "应用车辆选择";
	diag.URL = contextPath + "/page/price/selectCar.jsp?id=" + id +"&tableName=month_lease_price_type";
	diag.MessageTitle = typeName;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit();
		diag.close();
	}; 
	diag.show();
};

//保存方法
var callBack = function() {
	location.reload();
};

//删除套餐
var deleteFun = function(id,typeName) {
	Dialog.confirm('警告：确认删除   [ ' + typeName + ' ] ?', function() {
		jQuery.ajax({
			type : 'post',
			url : contextPath + '/price/priceType/long/delete.htm',
			data : "id=" + id, 
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：删除失败！请重试.");
				}
				else{
					Dialog.alert("提示：删除成功.",function(){
						location.reload(true);
						});
				}
			},
			error : function() {
				Dialog.alert("错误：删除失败！请重试.");
			}
		});
	});
};

//点击弹出新增长租赁按钮。
var addLongPrice = function(){
	var diag =new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title="新增服务中心";
	diag.URL=contextPath + "/price/priceType/long/add.htm";
	diag.MessageTitle="无";
	diag.OKEvent=function(){
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
	
};

