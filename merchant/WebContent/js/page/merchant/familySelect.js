jQuery(document)
		.ready(
				function() {

					jQuery(".chzn-select").chosen();
					jQuery(
							"#beginTime,#endTime,#beginTime1,#endTime1,#beginTime2,#endTime2")
							.datepicker();
					normalCustomersTable.reloadData();

					jQuery("#subStatus").change(function() {
						cancelCustomersTable.reloadData();
					});

					// 点击查询按钮事件
					jQuery('#normalQueryBtn').click(function() {
						normalCustomersTable.bStateSave=false;//点击查询的时候清除cookies里面的分页缓存。
						normalCustomersTable.reloadData();
					});
					jQuery('#normalExcelBtn').click(function() {
						normalCustomersTable.exportExcel();
					});
					
					// 点击查询按钮事件
					jQuery('#freezeQueryBtn,#freezeDiv').click(function() {
						freezeCustomersTable.bStateSave=false;//点击查询的时候清除cookies里面的分页缓存。
						freezeCustomersTable.reloadData();
					});
					jQuery('#freezeExcelBtn').click(function() {
						freezeCustomersTable.exportExcel();
					});

					// 点击查询按钮事件
					jQuery('#cancelQueryBtn,#cancelDiv').click(function() {
						cancelCustomersTable.bStateSave=false;//点击查询的时候清除cookies里面的分页缓存。
						cancelCustomersTable.reloadData();
					});
					jQuery('#cancelExcelBtn').click(function() {
						cancelCustomersTable.exportExcel();
					});
					
					
//					jQuery(".checkall").click(function() {
//						var thiz = this;
//						jQuery("input[name=customerId]").each(function() {
//							jQuery(this).attr('checked', thiz.checked);
//						});
//					});
					
					
					//点击单选按妞
//					jQuery("input:[name=customerId]:checked").live("click",function(){
//						
//					});
						
						
	

				});

/**
 * 提交form表单
 */
var submit = function(callBack) {
	var id =jQuery("input:[name=customerId]:checked").val();
	var cname =jQuery("#cname_"+id).text();
	var phone = jQuery("#phone_"+id).text();
	var address = jQuery("#address_"+id).text();
	var idCard = jQuery("#idCard_"+id).text();
	
	if (id=="" || id==undefined) {
		Dialog.alert("提示：请选择用户");
		return false;
	}
	callBack(id,cname,phone,address,idCard);
};








var column = [ // 设定各列宽度
           {
   			"fnRender" : function(obj) {
   				var id = obj.aData['id'];
   				return '<input style="width: 20px;" type="radio" name="customerId" id="'
   					+ id
   					+ '" title="'
   					+ id
   					+ '"value="' 
   					+ id
   					+ '" >';
   			},
   			"sWidth" : "4%",
   			"sClass" : "center"
   		},
		{
			/*"mDataProp" : "idCard",*/
   			"fnRender" : function(obj) {
   				var idCard = obj.aData['idCard'];
   				var id = obj.aData['id'];
   				return '<span name="idCard" value="'+idCard+'" id="idCard_'+id+'" >'+idCard+'</span>';
   			},
			"sClass" : "center"
		},
		{
			/*"mDataProp" : "phone",*/
   			"fnRender" : function(obj) {
   				var phone = obj.aData['phone'];
   				var id = obj.aData['id'];
   				return '<span name="phone" value="'+phone+'" id="phone_'+id+'" >'+phone+'</span>';
   			},
			"sClass" : "center"
		},
		{
			/*"mDataProp" : "name",*/
			"fnRender" : function(obj) {
   				var cname = obj.aData['name'];
   				var id = obj.aData['id'];
   				return '<span name="cname" value="'+cname+'" id="cname_'+id+'" >'+cname+'</span>';
   			},
			"sClass" : "center"
		},
		{
			/*"mDataProp" : "address",*/
			"fnRender" : function(obj) {
   				var address = obj.aData['address'];
   				var id = obj.aData['id'];
   				return '<span name="address" value="'+address+'" id="address_'+id+'" >'+address+'</span>';
   			},
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
			"mDataProp" : "createdTime",
			"sClass" : "center"
		},
		{
			"mDataProp" : "transactUser",
			"sClass" : "center"
		}
	 ];

var normalCustomersTable = new PageDataTables({
	tableId : 'normalCustomersTable',
	ajaxUrl : contextPath + "/customer/customer/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/customer/customer/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : column,
	beforeload : function() {
		this.paraData = {
			"idCard" : jQuery('#normalIdCard').val() == '' ? '' : '%'
					+ jQuery('#normalIdCard').val() + "%",
			"phone" : jQuery('#normalPhone').val() == '' ? '' : '%'
					+ jQuery('#normalPhone').val() + "%",
			"name" : jQuery('#normalName').val() == '' ? '' : '%'
					+ jQuery('#normalName').val() + "%",
			"status" : '正常',
			"beginTime" : jQuery('#beginTime').val() == '' ? '' : jQuery(
					'#beginTime').val(),
			"endTime" : jQuery('#endTime').val() == '' ? ''
					: jQuery('#endTime').val() + " 23:59:59"
		};
	}
});

var freezeCustomersTable = new PageDataTables({
	tableId : 'freezeCustomersTable',
	ajaxUrl : contextPath + "/customer/customer/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/customer/customer/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : [ // 设定各列宽度
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
				"mDataProp" : "describe",
				"sClass" : "center"
			},
			{
				"mDataProp" : "deletedTime",
				"sClass" : "center"
			},
			{
				"mDataProp" : "deletedUser",
				"sClass" : "center"
			}
		],
	beforeload : function() {
		this.paraData = {
			"idCard" : jQuery('#freezeIdCard').val() == '' ? '' : '%'
					+ jQuery('#freezeIdCard').val() + "%",
			"phone" : jQuery('#freezePhone').val() == '' ? '' : '%'
					+ jQuery('#freezePhone').val() + "%",
			"name" : jQuery('#freezeName').val() == '' ? '' : '%'
					+ jQuery('#freezeName').val() + "%",
			"status" : '冻结',
			"delBeginTime" : jQuery('#beginTime1').val() == '' ? '' : jQuery(
					'#beginTime1').val(),
			"delEndTime" : jQuery('#endTime1').val() == '' ? '' : jQuery(
					'#endTime1').val()
					+ " 23:59:59"
		};
	}
});

var cancelCustomersTable = new PageDataTables({
	tableId : 'cancelCustomersTable',
	ajaxUrl : contextPath + "/customer/customer/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/customer/customer/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : [ // 设定各列宽度
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
				"mDataProp" : "describe",
				"sClass" : "center"
			},
			{
				"mDataProp" : "deletedTime",
				"sClass" : "center"
			},
			{
				"mDataProp" : "deletedUser",
				"sClass" : "center"
			},
			{
				"fnRender" : function(obj) {
					var subStatus = obj.aData['subStatus'];
					if (subStatus == '已退款' || subStatus == '已补卡') {
						return subStatus;
					}
					return '未退款';
				},
				"sClass" : "center"
			}
			],
	beforeload : function() {
		this.paraData = {
			"idCard" : jQuery('#cancelIdCard').val() == '' ? '' : '%'
					+ jQuery('#cancelIdCard').val() + "%",
			"phone" : jQuery('#cancelPhone').val() == '' ? '' : '%'
					+ jQuery('#cancelPhone').val() + "%",
			"name" : jQuery('#cancelName').val() == '' ? '' : '%'
					+ jQuery('#cancelName').val() + "%",
			"subStatus" : jQuery('#subStatus').val(),
			"status" : '注销',
			"delBeginTime" : jQuery('#beginTime2').val() == '' ? '' : jQuery(
					'#beginTime2').val(),
			"delEndTime" : jQuery('#endTime2').val() == '' ? '' : jQuery(
					'#endTime2').val()
					+ " 23:59:59"
		};
	}
});

var openDetailWin = function(id, name) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "客户详细信息";
	diag.MessageTitle = "姓名： " + name;
	diag.URL = contextPath + "/customer/customer/detail.htm?id=" + id;
	diag.show();
};