jQuery(document).ready(function() {

			repairDetailTable.reloadData();
			manufacturerCombox.requestData();

			jQuery("#repairDate").datepicker();

			jQuery("#form1").validate({
				rules : {
					'repairDate' : "required",
					'repairReason' : "required",
					'applyUser' : "required",
					'fee' : {
						"required" : true,
						"number" : true
					},
				}
			});

});

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#manufacturId").val() == '') {
		Dialog.alert("提示：请选择维修商");
		return false;
	}
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/asset/repair/saveEdit.htm?',
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

var manufacturerCombox = new Combox({
	id : 'manufacturId',
	url : contextPath + '/car/manufacturer/ajaxManufacturer.htm?t='
			+ new Date().getTime(),
	cText : 'manufacturerName',
	cValue : 'id',
	emptyText : '请选择维修商',
	beforeload : function() {
		this.emptyValue = jQuery('#hid_manufacturId').val();
	},
	change : function() {

	}
});


var repairDetailTable = new PageDataTables({
	tableId : 'repairDetailTable',
	iDisplayLength : 6,
	bLengthChange : false,
	ajaxUrl : contextPath + "/asset/repair/ajaxDetailData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/asset/repair/exportDetailToExcel.htm?",
	aoColumns : [ {
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
	},{
		"mDataProp" : "repairReason",
		"sClass" : "center"
	}, {
		"mDataProp" : "repairStatus",
		"sClass" : "center"
	}, {
		"mDataProp" : "fee",
		"sClass" : "center"
	} ,
	 {
		"mDataProp" : "accessories",
		"sClass" : "center"
	},
	{
		"fnRender" : function(obj) {
			var id = obj.aData['id'];
			var assetsName = obj.aData['assetsName'];
			return '<a href="javaScript:openEditWin(' + id + ',\''+assetsName+'\');">修改</a>';
		},
		"sClass" : "center"
	}],
	beforeload : function() {
		this.paraData = {
			"repairId" : jQuery('#id').val(),
		};
	}
});


var openEditWin = function(id,assetsName) {
	var diag = new Dialog();
	diag.Width = 800;
	diag.Height = 600;
	diag.Title = "资产维修信息修改";
	diag.MessageTitle = assetsName;
	diag.URL = contextPath + "/asset/repair/editDetail.htm?id=" + id;
	diag.OKEvent = function() {
		diag.innerFrame.contentWindow.submit(callBack);
		diag.close();
	};
	diag.show();
};

// 保存方法
var callBack = function() {
	location.reload();
};
