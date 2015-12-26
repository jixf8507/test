jQuery(document).ready(function() {


	useCarsTable.reloadData();

	// 点击使用车辆查询按钮
	jQuery('#useQueryBtn').click(function() {
		useCarsTable.reloadData();
	});

});

// 已经使用的车载设备
var useCarsTable = new PageDataTables({
	tableId : 'useCarsTable',
	ajaxUrl : contextPath + "/car/device/ajaxSimData.htm?t=" + new Date().getTime(),
	aoColumns : [
	     		{
	    			"fnRender" : function(obj) {
	    				var id = obj.aData['id'];
	    				var noCarDevice = jQuery('#noCarDevice').val();
	    				var deviceNO = obj.aData['deviceNO'];
	    				if (noCarDevice == 'yes' && deviceNO != '') {
	    					return '';
	    				}
	    				return '<input style="width: 20px;" type="radio" name="id" id="'
	    						+ id
	    						+ '" value="'
	    						+ id
	    						+ '_' + deviceNO + '" >';
	    			},
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "simCard",
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "facilitator",
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "flowOfMonth",
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "status",
	    			"sClass" : "center"
	    		}, {
	    			"mDataProp" : "deviceNO",
	    			"sClass" : "center"
	    		}],
	beforeload : function() {
		this.paraData = {
			"simCard" : jQuery('#simCard').val() == '' ? '' : '%'
					+ jQuery('#simCard').val() + '%',
					"status" : '正常'
		};
	}
});

/**
 * 提交form表单
 */
var submit = function(callBackSave) {
	var selId = '';
	jQuery("input[name=id]").each(function() {
		if (this.checked) {
			selId = jQuery(this).val();
		}
	});
	var arr = selId.split('_');
	var data = {
		id : arr[0]
	};
	jQuery.ajax({
		cache : true,
		type : "POST",
		url : contextPath + '/car/device/updateSim.htm?',
		data : {
			id : data['id'],
			deviceId : jQuery('#deviceId').val()
		},
		async : false,
		dataType : 'json',
		error : function(request) {
			Dialog.alert("提示：操作失败");
		},
		success : function(data) {
			if (data.success) {
				Dialog.alert("提示：操作成功",function(){
					callBackSave();
				});
				
			} else {
				Dialog.alert("提示：操作失败");
			}
		}
	});
};