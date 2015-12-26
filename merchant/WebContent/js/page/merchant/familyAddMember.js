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
					
					
					//点击单选按妞打勾
					jQuery("input:[name=customerId]:checked").live("click",function(){
						var thiz = this; 
						ids +=jQuery(this).val()+",";
						return checkMembers(thiz);
					});
					
						
	

				});



//要加入家庭的成员id
var ids="";
//submit提交的时候，遍历每一个复选框把id值加入ids中
var eachCheckBox = function(){
	//遍历每一个复选框
	ids = jQuery("input:checkbox[name=customerId]:checked").map(function(index,elem) {
				return jQuery(elem).val();
			}).get().join(',');
};

//校验客户是否已经有家庭关系,有家庭关系则不允许选择
var checkMembers = function(thiz){
	var customer = jQuery(thiz);
	//检查用户是否已经是家庭户主的户主，如果是户主则不允许在建立家庭
	jQuery.ajax({
		type : "POST",
		url : contextPath + "/merchant/family/checkMembers.htm?",
		data :"customerid=" + customer.val(),
		async : true,
		dataType : 'json',
		success : function(data) {
			if (false ==data.success ) {
				Dialog.alert("提示：该客户已经是家庭"+data.info+" 的成员，不能添加");
				customer.attr("checked",false);//取消对复选框的选择
				return false;
			}
		},
		error : function() {
			Dialog.error("提示：验证客户失败");
		}
	});
	
};




/**
 * 提交form表单
 */
var submit = function(callBack) {
	eachCheckBox();
	jQuery.ajax({
		cache : true,
		type : "POST",
		url : contextPath + '/merchant/family/saveAddMember.htm?',
		data : "idsStr=" + ids +"&familyId=" + jQuery("#familyId").val(),
		async : false,
		dataType : 'json',
		error : function(request) {
			Dialog.alert("提示：操作失败");
		},
		success : function(data) {
			if (data.success) {
				Dialog.alert("提示：操作成功",function(){
					callBack();
				});
				
			}
		}
	});
};








var column = [ // 设定各列宽度
           {
   			"fnRender" : function(obj) {
   				var id = obj.aData['id'];
   				return '<input style="width: 20px;" type="checkbox" name="customerId" id="'
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
	bSort : false,
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