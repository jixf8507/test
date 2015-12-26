jQuery(document).ready(function() {
	
	useDetailTable.reloadData();
	siteCombox.requestData();

	jQuery("#useDate").datepicker();

	
	jQuery("#form1").validate({
		rules : {
			'useDate' : "required",
			'userName' : "required"
			}
	});
	

	jQuery(".checkall").click(function() {
		var thiz = this;
		jQuery("input[name=cids]").each(function() {
			jQuery(this).attr('checked', thiz.checked);
		});
	});
	jQuery('#operateBtn').click(function() {
		operate();
	});
	
	jQuery('#returnAllBtn').click(function() {
		var useId = jQuery('#id').val();
		returnAllFun(useId);
	});
	
//	getAssetTable.createTable(assetsDetail);
	
});

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#siteId").val() == '') {
		Dialog.alert("提示：请选择领入点");
		return false;
	}
	if (jQuery("#assetIds").val() == '') {
		Dialog.alert("提示：请添加领用资产内容");
		return false;
	}
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/asset/use/saveEdit.htm?',
			data : jQuery('#form1').serialize(), 
			async : false,
			dataType : 'json',
			error : function(request) {
				Dialog.alert("错误：操作失败");
			},
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功",function(){
						callBack();
					});
					
				}else{
					Dialog.alert("提示：操作失败");
				}
			}
		});
	}
};

var useDetailTable = new PageDataTables({
	tableId : 'useDetailTable',
	bSort : false,
	iDisplayLength : 6, 
	bLengthChange : false,
	ajaxUrl : contextPath + "/asset/use/ajaxDetailData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/asset/use/exportDetailToExcel.htm?",
	aoColumns : [  {
		"mDataProp" : "assetsId",
		"sClass" : "center"
	},{
		"mDataProp" : "assetsName",
		"sClass" : "center"
	}, {
		"mDataProp" : "name",
		"sClass" : "center"
	}, {
		"mDataProp" : "model",
		"sClass" : "center"
	}, {
		"mDataProp" : "unit",
		"sClass" : "center"
	} , {
		"mDataProp" : "flag",
		"sClass" : "center"
	} ,
	{
		"fnRender" : function(obj) {
			var assetsId = obj.aData['assetsId']; 
			var assetsName = obj.aData['assetsName'];
			var flag = obj.aData['flag'];
			if(flag=='在用'){
				return '<a href="javaScript:returnFun(' + assetsId + ',\''+flag+'\',\''+assetsName+'\');">归还</a>';
			}else{
				return '<a href="javaScript:void(' + assetsId + ',\''+flag+'\',\''+assetsName+'\');">已归还</a>';
			}
		},
		"sClass" : "center"
	}],
	beforeload : function() {
		this.paraData = {
			"useId" : jQuery('#id').val(),
		};
	}
});
 
//点击归还按钮
var returnFun = function(assetsId,flag,assetsName) {
	Dialog.confirm('警告：确认归还资产 [' + assetsName + '] 吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/asset/manage/batchUpdateAssetFlag.htm?",
			data : "flag=闲置&assetIds="+assetsId,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功",function(){
						location.reload(true);
					});
					
				} else {
					Dialog.alert("提示：操作失败"); 
				}
			},
			error : function() {
				Dialog.alert("错误：操作失败");
			}
		});
	});
};

var operate = function() {
	var sel = false;
	var plds = '';
	jQuery("input[name=cids]").each(function() {
		if (jQuery(this).is(':checked')) {
			sel = true;
			plds += jQuery(this).val() + ",";
		}
	});

	if (!sel) {
		Dialog.alert('没有选择数据');
		return;
	}
	Dialog.confirm('确认归还?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/asset/manage/batchUpdateAssetFlag.htm?",
			data : "flag=闲置&assetIds=" + plds,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					Dialog.alert("提示：操作成功",function(){
						location.reload(true);
					});
					
				} else {
					Dialog.alert("提示：操作失败");
				}
			},
			error : function() {
				Dialog.alert("错误：系统异常");
			}
		});
	});
};

var siteCombox = new Combox({
	id : 'siteId',
	url : contextPath + '/merchant/site/ajaxSites.htm?t='
			+ new Date().getTime(),
	cText : 'siteName',
	cValue : 'id',
	emptyText : '请选择租赁点',
	beforeload : function() {
		this.emptyValue = jQuery("#hid_siteId").val();
	},
	change : function() { 
		
}
});

var openDetailWin = function(id) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "资产领用详细信息";
	diag.MessageTitle =  "单据号： "+id;
	diag.URL = contextPath + "/asset/use/detail.htm?id=" + id;
	diag.show();
};

//获取员工列表
var getMerchantUser = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "领用人选择";
	diag.URL = contextPath + "/page/merchant/userWin.jsp";
	diag.MessageTitle = '选择领用人';
	diag.Message = "";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
		diag.close();
	}; 
	diag.show();
};

//获取资产列表
var getAssets = function() {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 500;
	diag.Title = "资产选择";
	diag.URL = contextPath + "/page/asset/assetWin.jsp?assetIds="+jQuery('#assetIds').val();
	diag.MessageTitle = '选择资产';
	diag.Message = "";
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBackCheckbox);
		diag.close();
	}; 
	diag.show();
};

var callBack = function(obj) {
	var arr = obj.split('_');
	jQuery("#userId").attr("value", arr[0]);
	jQuery("#userName").attr("value", arr[1]);
};

//点击归还按钮
var returnAllFun = function(useId) {
	Dialog.confirm('警告：确认归还该单据号下的所有资产吗?', function() {
		jQuery.ajax({
			type : "POST",
			url : contextPath + "/asset/use/updateAllAssetFlag.htm?",
			data : "flag=闲置&useId=" + useId,
			async : true,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					location.reload(true);

				} else {
					Dialog.alert("提示：操作失败");
				}
			},
			error : function() {
				Dialog.alert("错误：操作失败");
			}
		});
	});
};