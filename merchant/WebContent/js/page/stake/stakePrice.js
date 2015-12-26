jQuery(document).ready(function() {

	jQuery("#beginTime,#endTime").datepicker();
	stakePriceTable.reloadData();

	jQuery('#queryBtn').click(function() {
		stakePriceTable.bStateSave=false; 
		stakePriceTable.reloadData();
	});

	jQuery('#excelBtn').click(function() {
		stakePriceTable.exportExcel();
	});
	
	jQuery('#addBtn').live('click',function() {
		openAddWin();
	});

});

var stakePriceTable = new PageDataTables(
		{
			tableId : 'stakePriceTable',
			ajaxUrl : contextPath + "/stake/price/ajaxData.htm?t="
					+ new Date().getTime(),
			exportUrl : contextPath + "/stake/price/exportToExcel.htm?",
			aoColumns : [
					{
						"mDataProp" : "name",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var Hour1 = obj.aData['Hour1'];
							var Minute1 = obj.aData['Minute1'];
							var Hour2 = obj.aData['Hour2'];
							var Minute2 = obj.aData['Minute2'];
							return '' + Hour1 + ':' + Minute1 + ' -- ' + Hour2
									+ ':' + Minute2 + '';
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "PriceA",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var Hour3 = obj.aData['Hour3'];
							var Minute3 = obj.aData['Minute3'];
							var Hour2 = obj.aData['Hour2'];
							var Minute2 = obj.aData['Minute2'];
							return '' + Hour2 + ':' + Minute2 + ' -- ' + Hour3
									+ ':' + Minute3 + '';
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "PriceB",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var Hour3 = obj.aData['Hour3'];
							var Minute3 = obj.aData['Minute3'];
							var Hour4 = obj.aData['Hour4'];
							var Minute4 = obj.aData['Minute4'];
							return '' + Hour3 + ':' + Minute3 + ' -- ' + Hour4
									+ ':' + Minute4 + '';
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "PriceC",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var Hour1 = obj.aData['Hour1'];
							var Minute1 = obj.aData['Minute1'];
							var Hour4 = obj.aData['Hour4'];
							var Minute4 = obj.aData['Minute4'];
							return '' + Hour4 + ':' + Minute4 + ' -- 次日' + Hour1
									+ ':' + Minute1 + '';
						},
						"sClass" : "center"
					},
					{
						"mDataProp" : "PriceD",
						"sClass" : "center"
					},
					{
						"mDataProp" : "createdTime",
						"sClass" : "center"
					},
					{
						"mDataProp" : "memo",
						"sClass" : "center"
					},
					{
						"fnRender" : function(obj) {
							var id = obj.aData['id'];
							var name = obj.aData['name'];
							return '<a href="javaScript:openMemuWin('
							+ id
							+ ',\''+name+'\')">选择</a>&nbsp;&nbsp;|&nbsp;&nbsp;' 
							+ '<div class="menu" style="margin-right: 3px;"><ul><li><a class="drop" href="javaScript:openMore('
							+ id
							+ ');">更多</a><ul id="ulList"><li><a href="javaScript:openEditeWin('
							+ id
							+ ',\''+name+'\')">编辑</a></li><li><a href="javaScript:deleteFun('
							+ id + ',\'' + name + '\')"  >删除</a></li></ul><li></ul></div>';
						},
						"sClass" : "center"
					} ],
					addButton : [
					             {"tableId":"stakePriceTable","outId":"addBtn","value":"新增"},
					            ],
			beforeload : function() {
				this.paraData = {
					"name" : jQuery('#name').val() == '' ? '' : '%'
							+ jQuery('#name').val() + '%',
					"beginTime" : jQuery('#beginTime').val(),
					"endTime" : jQuery('#endTime').val() == '' ? '' : jQuery(
							'#endTime').val()
							+ ' 23:59:59'
				};
			}
		});

var openMemuWin = function(id,name) {
	var diag = new Dialog();
	diag.Width = 600;
	diag.Height = 500;
	diag.Title = "充电桩选择";
	diag.URL = contextPath + "/page/stake/selectStake.jsp?id="+id;
	diag.MessageTitle = name;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

var openAddWin = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "新增充电套餐";
	diag.URL = contextPath + "/stake/price/add.htm";
	diag.MessageTitle = "新增充电套餐";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};

var openEditeWin = function(id, name) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "充电套餐编辑";
	diag.URL = contextPath + "/stake/price/edite.htm?id=" + id;
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

// 删除套餐
var deleteFun = function(id, name) {
	Dialog.confirm('警告：确认删除充电套餐 [' + name + '] 吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/stake/price/delete.htm?",
			data : "id=" + id,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：删除成功", function() {
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
