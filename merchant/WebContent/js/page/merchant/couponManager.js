jQuery(document).ready(function() {

//	provinceCombox.requestData();
//	cityCombox.requestData();

	unUsedCouponTable.reloadData();
	

	
	
	// 点击查询按钮事件
	jQuery('#unUsedCouponQueryBtn').click(function() {
		unUsedCouponTable.bStateSave=false;
		unUsedCouponTable.reloadData();
	});
	
	// 点击查询按钮事件#bolishCouponQueryBtn,
	jQuery('#bolishCouponQueryBtn,#bolishCouponDiv').click(function() {
		bolishTable.bStateSave=false;
		bolishTable.reloadData();
	});
	
	
	// 点击查询按钮事件#usedCouponQueryBtn,
	jQuery('#usedCouponQueryBtn,#usedCouponDiv').click(function() {
		usedCouponTable.bStateSave=false;
		usedCouponTable.reloadData();
	});
	
	//点击查询按钮事件#outTimeCouponQueryBtn,
	jQuery('#outTimeCouponQueryBtn,#outTimeCouponDiv').click(function() {
		outTimeCouponTable.bStateSave=false;
		outTimeCouponTable.reloadData();
	});
	
	

	// 点击导出excel按钮事件
	jQuery('#unUsedExcelBtn').click(function() {
		unUsedCouponTable.exportExcel();
	});
	jQuery('#bolishExcelBtn').click(function() {
		bolishTable.exportExcel();
	});
	jQuery('#usedExcelBtn').click(function() {
		usedCouponTable.exportExcel();
	});
	jQuery('#outTimeExcelBtn').click(function() {
		outTimeCouponTable.exportExcel();
	});
	
	
	
	

	//批量新增优惠券
	jQuery('#addBtn').live('click',function() {
		addCoupon();
	});
	
	
	
	jQuery('#batchBtn').live('click',function() {
		/**
		 * 批量作废
		 */
		batchBolishCoupon();
	});
	
	jQuery(".checkall").click(function() {
		var thiz = this;
		jQuery("input[name=unUsedId]").each(function() {
			jQuery(this).attr('checked', thiz.checked);
		});
	});
	
	
});

//批量废除
var batchBolishCoupon = function (){
	var sel = false;
	var ids = '';
	jQuery("input:[name=unUsedId]checkbox:checked").each(function (index){
		sel = true;
		ids+=jQuery(this).val()+",";
	});
	
	if (!sel) {
		Dialog.alert('没有选择数据');
		return;
	}

	Dialog.confirm('确认批量删除优惠券吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/merchant/coupon/batchDelete.htm?",
			data : "idsJson="+ids,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：优惠券已作废",function(){
						location.reload(true);
					});
				} else {
					Dialog.alert("提示：优惠券不能作废");
				}
			},
			error : function() {
				Dialog.alert("提示：操作失败,请重试");
			}
		});
	});
};


//保存方法
var callBack = function() {
	location.reload();
};

//// 选择框
//var provinceCombox = new Combox(
//		{
//			id : 'province',
//			url : contextPath + '/merchant/site/ajaxProvinces.htm?t='
//					+ new Date().getTime(),
//			cText : 'proName',
//			cValue : 'proName',
//			emptyText : '请选择省',
//			change : function() {
//				jQuery("#cityDiv")
//						.html(
//								'<select name="city" id="city" style="width: 200px;"><option value="">请选择</option></select>');
//				cityCombox.requestData();
//				sitesTable.reloadData();
//				loadAddBtn2("sitesTable","addBtn","新增");
//			}
//		});
//
var columns = [ // 设定各列宽度
       {
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				return '<input style="width: 20px;" type="checkbox" name="unUsedId" id="'
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
			/*"mDataProp" : "id",*/
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				return 'ykzc-'+id;
			},
			"sClass" : "center"
		},
		{
			"mDataProp" : "couponName",
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
		/*{
			"mDataProp" : "couponNo",
			"sClass" : "center"
		},*/
		{
			"mDataProp" : "createdTime",
			"sClass" : "center"
		},
		{
			"mDataProp" : "toDate",
			"sClass" : "center"
		},
		{
			/*"mDataProp" : "id",*/
			"fnRender" : function(obj) {
				var couponType = obj.aData['couponType'];
				if("1"==couponType){
					return "现金优惠券";
					
				}else if ("2"==couponType){
					return "里程优惠券";
				}
				
			},
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
			"fnRender" : function(obj) {
				var id = obj.aData['id'];
				var status = obj.aData['status'];
				
				var html = "";
				
				if(status=='待用'){
					html+='<a href="javaScript:abolishCoupon('
						+ id
						+')">作废</a>|&nbsp;&nbsp;'
						+'<a href="javaScript:bindCustomer('
						+ id
						+')">绑定用户</a>';
					
				}
				return html;
			},
			"sClass" : "center"
		} ];


var abolishColumns = [ // 设定各列宽度
         		{
         			/*"mDataProp" : "id",*/
         			"fnRender" : function(obj) {
         				var id = obj.aData['id'];
         				return 'ykzc-'+id;
         			},
         			"sClass" : "center"
         		},
         		{
         			"mDataProp" : "couponName",
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
         		/*{
         			"mDataProp" : "couponNo",
         			"sClass" : "center"
         		},*/
         		{
         			"mDataProp" : "createdTime",
         			"sClass" : "center"
         		},
         		{
         			"mDataProp" : "toDate",
         			"sClass" : "center"
         		},
        		{
        			/*"mDataProp" : "id",*/
        			"fnRender" : function(obj) {
        				var couponType = obj.aData['couponType'];
        				if("1"==couponType){
        					return "现金优惠券";
        					
        				}else if ("2"==couponType){
        					return "里程优惠券";
        				}
        				
        			},
        			"sClass" : "center"
        		}
         		];




//已使用优惠券要显示姓名和电话。
var usedColumns = [ // 设定各列宽度
                		{
                			/*"mDataProp" : "id",*/
                			"fnRender" : function(obj) {
                				var id = obj.aData['id'];
                				return 'ykzc-'+id;
                			},
                			"sClass" : "center"
                		},
                		{
                			"mDataProp" : "couponName",
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
                		/*{
                			"mDataProp" : "couponNo",
                			"sClass" : "center"
                		},*/
                		{
                			"mDataProp" : "createdTime",
                			"sClass" : "center"
                		},
                		{
                			"mDataProp" : "toDate",
                			"sClass" : "center"
                		},
                		{
                			/*"mDataProp" : "id",*/
                			"fnRender" : function(obj) {
                				var couponType = obj.aData['couponType'];
                				if("1"==couponType){
                					return "现金优惠券";
                					
                				}else if ("2"==couponType){
                					return "里程优惠券";
                				}
                				
                			},
                			"sClass" : "center"
                		},
                		{
                			"mDataProp" : "name",
                			"sClass" : "center"
                		},
                		{
                			"mDataProp" : "phone",
                			"sClass" : "center"
                		}
                ];








// 待用优惠券列表
var unUsedCouponTable = new PageDataTables({
	tableId : 'unUsedCouponTable',
	ajaxUrl : contextPath + "/merchant/coupon/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/merchant/coupon/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : columns,
	bSort : false,
	addButton :[
	            {"outId":"batchBtn","value":"批量作废"},
	            {"outId":"addBtn","value":"添加优惠券"}
	            
	],
	beforeload : function() {
		this.paraData = {
			"id" : jQuery('#unUsedId').val() == '' ? '' : jQuery('#unUsedId').val(),
			"couponName" : jQuery('#unUsedName').val() == '' ? '' : "%"+jQuery('#unUsedName').val()+"%",
			"status":"待用"
		};
	}
});

//作废优惠券列表
var bolishTable = new PageDataTables({
	tableId : 'bolishTable',
	ajaxUrl : contextPath + "/merchant/coupon/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/merchant/coupon/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : abolishColumns,
	beforeload : function() {
		this.paraData = {
			"id" : jQuery('#bolishId').val() == '' ? '' : jQuery('#bolishId').val(),
			"couponName" : jQuery('#bolishName').val() == '' ? '' : "%"+jQuery('#bolishName').val()+"%",
			"status":"作废"
		};
	}
});

//已用优惠券列表
var usedCouponTable = new PageDataTables({
	tableId : 'usedCouponTable',
	ajaxUrl : contextPath + "/merchant/coupon/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/merchant/coupon/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : usedColumns,
	beforeload : function() {
		this.paraData = {
			"id" : jQuery('#usedId').val() == '' ? '' : jQuery('#usedId').val(),
			"couponName" : jQuery('#usedName').val() == '' ? '' : "%"+jQuery('#usedName').val()+"%",
			"status":"已用"
		};
	}
});

//过期优惠券列表
var outTimeCouponTable = new PageDataTables({
	tableId : 'outTimeCouponTable',
	ajaxUrl : contextPath + "/merchant/coupon/ajaxData.htm?t="
			+ new Date().getTime(),// 这个是请求的地址
	exportUrl : contextPath + "/merchant/coupon/exportToExcel.htm?",// 这个是请求的地址
	aoColumns : abolishColumns,
	beforeload : function() {
		this.paraData = {
			"id" : jQuery('#outTimeId').val() == '' ? '' : jQuery('#outTimeId').val(),
			"couponName" : jQuery('#outTimeName').val() == '' ? '' : "%"+jQuery('#outTimeName').val()+"%",
			"status":"过期"
		};
	}
});








//点击作废优惠券按钮
var abolishCoupon = function(id) {
	Dialog.confirm('确认作废该优惠券吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/merchant/coupon/delete.htm?",
			data : "id=" + id+"&status=作废" ,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：优惠券已作废",function(){
						location.reload(true);
					});
				} else {
					Dialog.alert("提示：该优惠券不能作废");
				}
			},
			error : function() {
				Dialog.alert("提示：操作失败,请重试");
			}
		});
	});
};

//点击弹出新增优惠券按钮。
var addCoupon = function(){
	var diag =new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title="新增优惠券";
	diag.URL=contextPath + "/merchant/coupon/add.htm";
	diag.MessageTitle="无";
	diag.OKEvent=function(){
		diag.innerFrame.contentWindow.submit(callBack);
	};
	diag.show();
};


//绑定用户
var bindCustomer = function(couponId){
	var diag =new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title="绑定用户";
	diag.URL=contextPath + "/merchant/coupon/bindCustomer.htm";
	diag.MessageTitle="无";
	diag.OKEvent=function(){
		diag.innerFrame.contentWindow.submit(callBack,couponId);
	};
	diag.show();
	
};
















