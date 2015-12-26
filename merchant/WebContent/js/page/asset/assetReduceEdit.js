jQuery(document).ready(function() {

	reduceDetailTable.reloadData();
	jQuery("#reduceDate").datepicker();
	jQuery("#reduceStatus").chosen();

	jQuery("#form1").validate({
		rules : {
			'repairDate' : "required",
			'reduceReason' : "required",
			'applyUser' : "required",
		}
	});

});

/**
 * 提交form表单
 */
var submit = function(callBack) {
	if (jQuery("#reduceStatus").val() == '') {
		Dialog.alert("提示：请选择减少方式");
		return false;
	}
	if (jQuery("#form1").valid()) {
		jQuery.ajax({
			cache : true,
			type : "POST",
			url : contextPath + '/asset/reduce/saveEdit.htm?',
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

var reduceDetailTable = new PageDataTables({
	tableId : 'reduceDetailTable',
	bSort : false,
	iDisplayLength : 6, 
	bLengthChange : false,
	ajaxUrl : contextPath + "/asset/reduce/ajaxDetailData.htm?t="
			+ new Date().getTime(),
	exportUrl : contextPath + "/asset/reduce/exportDetailToExcel.htm?",
	aoColumns : [{
		"mDataProp" : "assetsId",
		"sClass" : "center"
	}, {
		"mDataProp" : "assetsName",
		"sClass" : "center"
	}, {
		"mDataProp" : "name",
		"sClass" : "center"
	}, {
		"mDataProp" : "model",
		"sClass" : "center"
	},{
		"mDataProp" : "unit",
		"sClass" : "center"
	}],
	beforeload : function() {
		this.paraData = {
			"reduceId" : jQuery('#id').val(),
		};
	}
});

